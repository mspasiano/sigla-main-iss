/*
 * Copyright (C) 2020  Consiglio Nazionale delle Ricerche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package it.cnr.contab.ordmag.ordini.comp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.cnr.contab.anagraf00.core.bulk.AnagraficoBulk;
import it.cnr.contab.anagraf00.core.bulk.TerzoBulk;
import it.cnr.contab.docamm00.tabrif.bulk.Bene_servizioBulk;
import it.cnr.contab.docamm00.tabrif.bulk.Bene_servizioHome;
import it.cnr.contab.docamm00.tabrif.bulk.DivisaBulk;
import it.cnr.contab.docamm00.tabrif.bulk.DivisaHome;
import it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioBulk;
import it.cnr.contab.doccont00.core.bulk.Obbligazione_scadenzarioHome;
import it.cnr.contab.ordmag.anag00.NumerazioneMagBulk;
import it.cnr.contab.ordmag.ejb.NumeratoriOrdMagComponentSession;
import it.cnr.contab.ordmag.magazzino.bulk.BollaScaricoMagBulk;
import it.cnr.contab.ordmag.magazzino.bulk.BollaScaricoMagHome;
import it.cnr.contab.ordmag.magazzino.bulk.MovimentiMagBulk;
import it.cnr.contab.ordmag.magazzino.ejb.MovimentiMagComponentSession;
import it.cnr.contab.ordmag.ordini.bulk.*;
import it.cnr.contab.ordmag.ordini.dto.ImportoOrdine;
import it.cnr.contab.ordmag.ordini.dto.ParametriCalcoloImportoOrdine;
import it.cnr.contab.ordmag.ordini.ejb.OrdineAcqComponentSession;
import it.cnr.contab.util.Utility;
import it.cnr.jada.DetailedRuntimeException;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.bulk.BulkList;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.comp.ApplicationException;
import it.cnr.jada.comp.ComponentException;
import it.cnr.jada.comp.ICRUDMgr;
import it.cnr.jada.persistency.FetchAllPolicy;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.sql.FindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;
import it.cnr.jada.util.RemoteIterator;

public class EvasioneOrdineComponent extends it.cnr.jada.comp.CRUDComponent implements ICRUDMgr,Cloneable,Serializable {

	public final static String TIPO_TOTALE_COMPLETO = "C";
	public final static String TIPO_TOTALE_PARZIALE = "P";
	private final static int INSERIMENTO = 1;

	public EvasioneOrdineBulk cercaOrdini(UserContext usercontext, EvasioneOrdineBulk filtro) throws ComponentException {
		OrdineAcqConsegnaHome home = (OrdineAcqConsegnaHome) getHome(usercontext, OrdineAcqConsegnaBulk.class, null, getFetchPolicyName("cercaOrdini"));
		it.cnr.jada.persistency.sql.SQLBuilder sql = ricercaOrdini(usercontext, filtro, home);

		try {
			BulkList<OrdineAcqConsegnaBulk> consegne = new BulkList<OrdineAcqConsegnaBulk>(home.fetchAll(sql));
			getHomeCache(usercontext).fetchAll(usercontext, home);
			filtro.setRigheConsegnaDaEvadereColl(consegne);
			return filtro;
		} catch (PersistencyException e) {
			throw new ComponentException(e);
		}
	}

	private it.cnr.jada.persistency.sql.SQLBuilder ricercaOrdini(UserContext context,
																 EvasioneOrdineBulk filtro, OrdineAcqConsegnaHome home) throws ApplicationException {

		if (filtro.getDataBolla() == null) {
			throw new it.cnr.jada.comp.ApplicationException("E' necessario valorizzare la data della bolla.");
		}
		if (filtro.getNumeroBolla() == null) {
			throw new it.cnr.jada.comp.ApplicationException("E' necessario valorizzare il numero della bolla.");
		}
		if (filtro.getDataConsegna() == null) {
			throw new it.cnr.jada.comp.ApplicationException("E' necessario valorizzare la data di consegna.");
		}
		if (filtro.getCdMagazzino() == null) {
			throw new it.cnr.jada.comp.ApplicationException("E' necessario valorizzare il magazzino.");
		}

		it.cnr.jada.persistency.sql.SQLBuilder sql = home.createSQLBuilder();

		sql.setAutoJoins(true);

		sql.addSQLClause(FindClause.AND, "ORDINE_ACQ_CONSEGNA.STATO_FATT", SQLBuilder.EQUALS, OrdineAcqConsegnaBulk.STATO_FATT_NON_ASSOCIATA);
		sql.addSQLClause(FindClause.AND, "ORDINE_ACQ_CONSEGNA.STATO", SQLBuilder.EQUALS, OrdineAcqConsegnaBulk.STATO_INSERITA);
		sql.addSQLClause(FindClause.AND, "ORDINE_ACQ_CONSEGNA.PG_OBBLIGAZIONE", SQLBuilder.ISNOTNULL, null);

		sql.generateJoin("ordineAcqRiga", "ORDINE_ACQ_RIGA");

		sql.generateJoin(OrdineAcqRigaBulk.class, OrdineAcqBulk.class, "ordineAcq", "ORDINE_ACQ");
		sql.addSQLClause(FindClause.AND, "ORDINE_ACQ.STATO", SQLBuilder.EQUALS, OrdineAcqBulk.STATO_DEFINITIVO);

		Optional.ofNullable(filtro.getNumerazioneMag())
				.map(NumerazioneMagBulk::getCdMagazzino)
				.ifPresent(cdMagazzino -> sql.addSQLClause(FindClause.AND, "ORDINE_ACQ_CONSEGNA.CD_MAGAZZINO", SQLBuilder.EQUALS, cdMagazzino));

		Optional.ofNullable(filtro.getFind_data_ordine())
				.ifPresent(findDataOrdine -> sql.addSQLClause(FindClause.AND, "ORDINE_ACQ.DATA_ORDINE", SQLBuilder.EQUALS, findDataOrdine));

		Optional.ofNullable(filtro.getFind_cd_numeratore_ordine())
				.ifPresent(findCdNumeratoreOrdine -> sql.addSQLClause(FindClause.AND, "ORDINE_ACQ.CD_NUMERATORE", SQLBuilder.EQUALS, findCdNumeratoreOrdine));

		Optional.ofNullable(filtro.getFind_esercizio_ordine())
				.ifPresent(findEsercizioOrdine -> sql.addSQLClause(FindClause.AND, "ORDINE_ACQ.ESERCIZIO", SQLBuilder.EQUALS, findEsercizioOrdine));

		Optional.ofNullable(filtro.getFind_numero_ordine())
				.ifPresent(findNumeroOrdine -> sql.addSQLClause(FindClause.AND, "ORDINE_ACQ.NUMERO", SQLBuilder.EQUALS, findNumeroOrdine));

		Optional.ofNullable(filtro.getFind_riga_ordine())
				.ifPresent(findRigaOrdine -> sql.addSQLClause(FindClause.AND, "ORDINE_ACQ_RIGA.RIGA", SQLBuilder.EQUALS, findRigaOrdine));

		Optional.ofNullable(filtro.getFind_consegna_ordine())
				.ifPresent(findConsegnaOrdine -> sql.addSQLClause(FindClause.AND, "ORDINE_ACQ_CONSEGNA.CONSEGNA", SQLBuilder.EQUALS, findConsegnaOrdine));

		Optional.ofNullable(filtro.getFind_cd_uop_ordine())
				.ifPresent(findCdUopOrdine -> sql.addSQLClause(FindClause.AND, "ORDINE_ACQ.CD_UOP_ORDINE", SQLBuilder.EQUALS, findCdUopOrdine));

		sql.addSQLClause(FindClause.AND, "ORDINE_ACQ.DATA_ORDINE", SQLBuilder.LESS_EQUALS, filtro.getDataConsegna());

		if (filtro.getFind_cd_precedente() != null || filtro.getFind_cd_terzo() != null || filtro.getFind_ragione_sociale() != null) {
			sql.generateJoin(OrdineAcqBulk.class, TerzoBulk.class, "fornitore", "TERZO");

			Optional.ofNullable(filtro.getFind_cd_terzo())
					.ifPresent(findCdTerzo -> sql.addSQLClause(FindClause.AND, "TERZO.CD_TERZO", SQLBuilder.EQUALS, findCdTerzo));

			Optional.ofNullable(filtro.getFind_cd_precedente())
					.ifPresent(findCdPrecedente -> sql.addSQLClause(FindClause.AND, "TERZO.CD_PRECEDENTE", SQLBuilder.EQUALS, findCdPrecedente));

			Optional.ofNullable(filtro.getFind_ragione_sociale())
					.ifPresent(findRagioneSociale -> {
								sql.generateJoin(TerzoBulk.class, AnagraficoBulk.class, "anagrafico", "ANAGRAFICO");
								sql.addSQLClause(FindClause.AND, "ANAGRAFICO.RAGIONE_SOCIALE", SQLBuilder.LIKE, "%" + findRagioneSociale.toUpperCase() + "%");
							}
					);
		}


		return sql;
	}

	public RemoteIterator preparaQueryBolleScaricoDaVisualizzare(UserContext userContext, List<BollaScaricoMagBulk> bolle) throws ComponentException {
		BollaScaricoMagHome homeBolla = (BollaScaricoMagHome) getHome(userContext, BollaScaricoMagBulk.class);
		return iterator(userContext,
				homeBolla.selectBolleGenerate(bolle),
				BollaScaricoMagBulk.class,
				"dafault");
	}

	private DivisaBulk getEuro(UserContext userContext) throws ComponentException {
		try {
			DivisaBulk divisaDefault = ((DivisaHome) getHome(userContext, DivisaBulk.class)).getDivisaDefault(userContext);
			Optional.ofNullable(divisaDefault)
					.map(DivisaBulk::getCd_divisa)
					.orElseThrow(() -> new it.cnr.jada.comp.ApplicationException("Impossibile caricare la valuta di default! Prima di poter inserire un ordine, immettere tale valore."));
			return divisaDefault;
		} catch (javax.ejb.EJBException | PersistencyException e) {
			handleException(e);
		}
		return null;
	}

	public List<BollaScaricoMagBulk> evadiOrdine(UserContext userContext, EvasioneOrdineBulk evasioneOrdine) throws ComponentException, PersistencyException {
		try {
			OrdineAcqComponentSession ordineComponent = Utility.createOrdineAcqComponentSession();
			MovimentiMagComponentSession movimentiMagComponent = Utility.createMovimentiMagComponentSession();

			List<OrdineAcqConsegnaBulk> listaConsegneEvase = new ArrayList<>();

			List<BollaScaricoMagBulk> listaBolleScarico = new ArrayList<>();
			List<MovimentiMagBulk> listaMovimentiScarico = new ArrayList<>();

			final List<OrdineAcqConsegnaBulk> consegneColl = evasioneOrdine.getRigheConsegnaSelezionate();
			OrdineAcqConsegnaHome consegnaHome = (OrdineAcqConsegnaHome) getHome(userContext, OrdineAcqConsegnaBulk.class);

			@SuppressWarnings("unchecked") final Supplier<Stream<OrdineAcqConsegnaBulk>> selectedElements = () ->
					Optional.ofNullable(consegneColl)
							.filter(List.class::isInstance)
							.map(List.class::cast)
							.filter(list -> !list.isEmpty())
							.map(list -> list.stream())
							.orElse(Stream.empty());

			selectedElements.get().forEach(obj -> {
				if (obj.getQuantitaEvasa() == null)
					throw new DetailedRuntimeException("Indicare la quantità da evadere per la consegna " + obj.getConsegnaOrdineString());
				if (obj.isQuantitaEvasaMinoreOrdine() && obj.getOperazioneQuantitaEvasaMinore() == null)
					throw new DetailedRuntimeException("Per la consegna " + obj.getConsegnaOrdineString() + " è necessario indicare se sdoppiare la riga o evaderla forzatamente");
			});

			final Map<OrdineAcqBulk, Map<OrdineAcqRigaBulk, List<OrdineAcqConsegnaBulk>>> mapOrdine =
					selectedElements.get().collect(Collectors.groupingBy(o -> o.getOrdineAcqRiga().getOrdineAcq(),
							Collectors.groupingBy(o -> o.getOrdineAcqRiga())));

			mapOrdine.keySet().stream().forEach(ordine -> {
				try {
					OrdineAcqBulk ordineComp = (OrdineAcqBulk) ordineComponent.inizializzaBulkPerModifica(userContext, ordine);
					ParametriCalcoloImportoOrdine parametriTestata = new ParametriCalcoloImportoOrdine();
					parametriTestata.setCambio(ordine.getCambio());
					parametriTestata.setDivisa(ordine.getDivisa());
					parametriTestata.setDivisaRisultato(getEuro(userContext));
					parametriTestata.setPercProrata(ordine.getPercProrata());
					ordine.setImImponibile(BigDecimal.ZERO);
					ordine.setImIva(BigDecimal.ZERO);
					ordine.setImIvaD(BigDecimal.ZERO);
					ordine.setImTotaleOrdine(BigDecimal.ZERO);

					//ciclo sulle righe di ordine
					mapOrdine.get(ordine).keySet().stream().forEach(ordineRiga -> {
						//recupero la riga di ordine dall'oggetto proveniente dal Component
						OrdineAcqRigaBulk ordineRigaComp =
								Optional.ofNullable(ordineComp.getRigheOrdineColl())
										.filter(list -> !list.isEmpty())
										.map(list -> list.get(list.indexOfByPrimaryKey(ordineRiga)))
										.orElseThrow(() -> new DetailedRuntimeException("Errore nell'individuazione della riga " + ordineRiga.getRigaOrdineString() + "."));

						ParametriCalcoloImportoOrdine parametriRiga = (ParametriCalcoloImportoOrdine) parametriTestata.clone();
						parametriRiga.setCoefacq(ordineRiga.getCoefConv());
						parametriRiga.setPrezzo(ordineRiga.getPrezzoUnitario());
						parametriRiga.setSconto1(ordineRiga.getSconto1());
						parametriRiga.setSconto2(ordineRiga.getSconto2());
						parametriRiga.setSconto3(ordineRiga.getSconto3());
						parametriRiga.setVoceIva(ordineRiga.getVoceIva());
						ordineRiga.setImImponibile(BigDecimal.ZERO);
						ordineRiga.setImImponibileDivisa(BigDecimal.ZERO);
						ordineRiga.setImIva(BigDecimal.ZERO);
						ordineRiga.setImIvaDivisa(BigDecimal.ZERO);
						ordineRiga.setImIvaD(BigDecimal.ZERO);
						ordineRiga.setImIvaNd(BigDecimal.ZERO);
						ordineRiga.setImTotaleRiga(BigDecimal.ZERO);

						//ciclo sulle righe di consegna
						mapOrdine.get(ordine).get(ordineRiga).stream().forEach(ordineConsegna -> {
							//recupero la riga di consegna dall'oggetto proveniente dal Component
							OrdineAcqConsegnaBulk ordineConsegnaComp =
									Optional.ofNullable(ordineRigaComp.getRigheConsegnaColl())
											.filter(list -> !list.isEmpty())
											.map(list -> list.get(list.indexOfByPrimaryKey(ordineConsegna)))
											.orElseThrow(() -> new DetailedRuntimeException("Errore nell'individuazione della consegna " + ordineConsegna.getConsegnaOrdineString() + "."));

							if (ordineConsegnaComp.getStato().equals(OrdineAcqConsegnaBulk.STATO_EVASA))
								throw new DetailedRuntimeException("La consegna " + ordineConsegnaComp.getConsegnaOrdineString() + " è stata già evasa");
							else if (ordineConsegna.isQuantitaEvasaMaggioreOrdine())
								throw new DetailedRuntimeException("La quantità evasa della consegna " + ordineConsegnaComp.getConsegnaOrdineString() + " non può essere maggiore di quella ordinata.");

							//Creo una nuova consegna se richiesto 
							if (ordineConsegna.isQuantitaEvasaMinoreOrdine()) {
								if (ordineConsegna.isOperazioneCreaNuovaConsegna()) {
									OrdineAcqConsegnaBulk newConsegna = (OrdineAcqConsegnaBulk) ordineConsegna.clone();
									try {
										getHomeCache(userContext).fetchAll(userContext, consegnaHome);
									} catch (PersistencyException e) {
										throw new DetailedRuntimeException(e);
									} catch (ComponentException e) {
										throw new DetailedRuntimeException(e);
									}

									newConsegna = (OrdineAcqConsegnaBulk) newConsegna.inizializza(userContext);
									newConsegna.setConsegna(ordineRigaComp.getRigheConsegnaColl().size() + 1);
									newConsegna.setVecchiaConsegna(ordineConsegna.getConsegna());
									newConsegna.setQuantita(ordineConsegna.getQuantita().subtract(ordineConsegna.getQuantitaEvasa()));
									newConsegna.setCrudStatus(OggettoBulk.TO_BE_CREATED);
									ParametriCalcoloImportoOrdine parametriNewCons = (ParametriCalcoloImportoOrdine) parametriRiga.clone();
									parametriNewCons.setQtaOrd(newConsegna.getQuantita());
									try {
										ImportoOrdine importo = ordineComponent.calcoloImportoOrdine(userContext, parametriNewCons);
										newConsegna.setImImponibile(importo.getImponibile());
										newConsegna.setImImponibileDivisa(importo.getImponibile());
										newConsegna.setImIva(importo.getImportoIva());
										newConsegna.setImIvaDivisa(importo.getImportoIva());
										newConsegna.setImIvaD(importo.getImportoIvaDetraibile());
										newConsegna.setImIvaNd(importo.getImportoIvaInd());
										newConsegna.setImTotaleConsegna(importo.getTotale());
									} catch (RemoteException|ComponentException e) {
										throw new DetailedRuntimeException(e);
									}
									ordineRigaComp.addToRigheConsegnaColl(newConsegna);

									if (Optional.ofNullable(newConsegna.getObbligazioneScadenzario()).isPresent()) {
										try {
											Obbligazione_scadenzarioHome obblHome = (Obbligazione_scadenzarioHome) getHome(userContext, Obbligazione_scadenzarioBulk.class);
											Obbligazione_scadenzarioBulk obbl = (Obbligazione_scadenzarioBulk) obblHome.findByPrimaryKey(newConsegna.getObbligazioneScadenzario());
											ordineComp.addToOrdineObbligazioniHash(obbl, newConsegna);
										} catch (ComponentException | PersistencyException e) {
											throw new DetailedRuntimeException(e);
										}
									}
								} else {
									ordineConsegna.setQuantitaOrig(ordineConsegna.getQuantita());
								}

								ParametriCalcoloImportoOrdine parametriOrdineConsegna = (ParametriCalcoloImportoOrdine) parametriRiga.clone();
								parametriOrdineConsegna.setQtaOrd(ordineConsegna.getQuantitaEvasa());
								try {
									ImportoOrdine importo = ordineComponent.calcoloImportoOrdine(userContext, parametriOrdineConsegna);
									ordineConsegna.setImImponibile(importo.getImponibile());
									ordineConsegna.setImImponibileDivisa(importo.getImponibile());
									ordineConsegna.setImIva(importo.getImportoIva());
									ordineConsegna.setImIvaDivisa(importo.getImportoIva());
									ordineConsegna.setImIvaD(importo.getImportoIvaDetraibile());
									ordineConsegna.setImIvaNd(importo.getImportoIvaInd());
									ordineConsegna.setImTotaleConsegna(importo.getTotale());
								} catch (RemoteException|ComponentException e) {
									throw new DetailedRuntimeException(e);
								}
							}

							ordineConsegna.setQuantita(ordineConsegna.getQuantitaEvasa());
							ordineConsegna.setStato(OrdineAcqConsegnaBulk.STATO_EVASA);
							ordineConsegna.setToBeUpdated();

							//rimuovo la vecchia consegna
							ordineRigaComp.getRigheConsegnaColl().removeByPrimaryKey(ordineConsegnaComp);

							//inserisco la nuova consegna
							ordineRigaComp.getRigheConsegnaColl().add(ordineConsegna);
							if (ordineConsegna.getQuantitaOrig() != null) {
								ordineComp.sostituisciConsegnaFromObbligazioniHash(ordineConsegna);
								ordineComp.setAggiornaImpegniInAutomatico(true);
							}
							try {
								Bene_servizioHome bene_servizioHome = (Bene_servizioHome) getHome(userContext, Bene_servizioBulk.class);
								Bene_servizioBulk bene_servizioBulk = (Bene_servizioBulk) bene_servizioHome.findByPrimaryKey(ordineRigaComp.getBeneServizio());
								if (bene_servizioBulk.getFl_gestione_inventario()) {

								}
							} catch (ComponentException | PersistencyException e) {
								throw new DetailedRuntimeException(e);
							}

							listaConsegneEvase.add(ordineConsegna);
						});
						/**
						 * Verifico che la somma dell'imponibile delle righe di consegna sia uguale all'imponibile presente sulla riga
						 * in caso di un disallineamento scarico la differenza sull'ultima riga di consegna.
						 */
						try {
							final FatturaOrdineHome fatturaOrdineHome = (FatturaOrdineHome) getHome(userContext, FatturaOrdineBulk.class);
							final BigDecimal diffImponibileConsegna = ordineRigaComp
									.getRigheConsegnaColl()
									.stream()
									.map(ordineAcqConsegnaBulk -> {
										try {
											return fatturaOrdineHome
													.findByRigaConsegna(ordineAcqConsegnaBulk)
													.stream()
													.findAny()
													.map(FatturaOrdineBulk::getImImponibile)
													.orElse(ordineAcqConsegnaBulk.getImImponibile());
										} catch (PersistencyException e) {
											throw new RuntimeException(e);
										}
									})
									.reduce(BigDecimal.ZERO, BigDecimal::add).subtract(ordineRigaComp.getImImponibile()).negate();
							final BigDecimal diffIvaConsegna = ordineRigaComp
									.getRigheConsegnaColl()
									.stream()
									.map(ordineAcqConsegnaBulk -> {
										try {
											return fatturaOrdineHome
													.findByRigaConsegna(ordineAcqConsegnaBulk)
													.stream()
													.findAny()
													.map(FatturaOrdineBulk::getImIva)
													.orElse(ordineAcqConsegnaBulk.getImIva());
										} catch (PersistencyException e) {
											throw new RuntimeException(e);
										}
									})
									.reduce(BigDecimal.ZERO, BigDecimal::add).subtract(ordineRigaComp.getImIva()).negate();

							if (!diffImponibileConsegna.equals(BigDecimal.ZERO.setScale(2))) {
								ordineRigaComp
										.getRigheConsegnaColl()
										.stream()
										.filter(ordineAcqConsegnaBulk -> ordineAcqConsegnaBulk.getStato().equals(OrdineAcqConsegnaBulk.STATO_INSERITA))
										.findAny()
										.ifPresent(ordineAcqConsegnaBulk -> {
											ordineAcqConsegnaBulk.setImImponibile(
													ordineAcqConsegnaBulk.getImImponibile().add(diffImponibileConsegna)
											);
											ordineAcqConsegnaBulk.setImImponibileDivisa(
													ordineAcqConsegnaBulk.getImImponibileDivisa().add(diffImponibileConsegna)
											);
											ordineAcqConsegnaBulk.setImTotaleConsegna(
													ordineAcqConsegnaBulk.getImTotaleConsegna().add(diffImponibileConsegna)
											);
										});
							}
							if (!diffIvaConsegna.equals(BigDecimal.ZERO.setScale(2))) {
								ordineRigaComp
										.getRigheConsegnaColl()
										.stream()
										.filter(ordineAcqConsegnaBulk -> ordineAcqConsegnaBulk.getStato().equals(OrdineAcqConsegnaBulk.STATO_INSERITA))
										.findAny()
										.ifPresent(ordineAcqConsegnaBulk -> {
											ordineAcqConsegnaBulk.setImIva(
													ordineAcqConsegnaBulk.getImIva().add(diffIvaConsegna)
											);
											ordineAcqConsegnaBulk.setImIvaNd(
													ordineAcqConsegnaBulk.getImIvaNd().add(diffIvaConsegna)
											);
											ordineAcqConsegnaBulk.setImIvaDivisa(
													ordineAcqConsegnaBulk.getImIvaDivisa().add(diffIvaConsegna)
											);
											ordineAcqConsegnaBulk.setImTotaleConsegna(
													ordineAcqConsegnaBulk.getImTotaleConsegna().add(diffIvaConsegna)
											);
										});
							}
							makeBulkListPersistent(userContext, ordineRigaComp.getRigheConsegnaColl());
						} catch (ComponentException|PersistencyException e) {
							throw new RuntimeException(e);
						}
					});
				} catch (ComponentException | RemoteException e) {
					throw new DetailedRuntimeException(e);
				}
			});

			if (!listaConsegneEvase.isEmpty()) {
				evasioneOrdine.setStato(OrdineAcqConsegnaBulk.STATO_INSERITA);
				evasioneOrdine.setToBeCreated();

				for (OrdineAcqConsegnaBulk consegnaEvasa : listaConsegneEvase) {
					EvasioneOrdineRigaBulk evasioneOrdineRiga = new EvasioneOrdineRigaBulk();
					evasioneOrdineRiga.setOrdineAcqConsegna(consegnaEvasa);
					evasioneOrdineRiga.setStato(OrdineAcqConsegnaBulk.STATO_INSERITA);
					evasioneOrdineRiga.setQuantitaEvasa(consegnaEvasa.getQuantita());
					evasioneOrdineRiga.setToBeCreated();

					evasioneOrdine.addToEvasioneOrdineRigheColl(evasioneOrdineRiga);
					//effettuo la movimentazione di magazzino
					try {
						Optional.ofNullable(movimentiMagComponent.caricoDaOrdine(userContext, evasioneOrdineRiga.getOrdineAcqConsegna(), evasioneOrdineRiga))
								.ifPresent(
										movimentoCarico -> {
											if (movimentoCarico.getMovimentoRif() != null) {
												listaMovimentiScarico.add(movimentoCarico);
											}
											evasioneOrdineRiga.setMovimentiMag(movimentoCarico);
										});
					} catch (ComponentException | RemoteException | PersistencyException e) {
						throw new DetailedRuntimeException(e);
					}
				}

				//rendo permanente l'evasione ordine
				assegnaProgressivo(userContext, evasioneOrdine);
				creaConBulk(userContext, evasioneOrdine);
			}

			if (!listaMovimentiScarico.isEmpty()) {
				try {
					listaBolleScarico = movimentiMagComponent.generaBolleScarico(userContext, listaMovimentiScarico);
				} catch (RemoteException e) {
					throw handleException(e);
				}
			}
			return listaBolleScarico;
		} catch (DetailedRuntimeException e) {
			throw new ApplicationException(e.getMessage());
		}
	}

	private void assegnaProgressivo(UserContext userContext, EvasioneOrdineBulk evasioneOrdine) throws ComponentException {
		try {
			NumeratoriOrdMagComponentSession progressiviSession = Utility.createNumeratoriOrdMagComponentSession();
			NumerazioneMagBulk numerazione = new NumerazioneMagBulk(evasioneOrdine.getCdCds(), evasioneOrdine.getCdMagazzino(), evasioneOrdine.getEsercizio(), evasioneOrdine.getCdNumeratoreMag());
			evasioneOrdine.setNumero(progressiviSession.getNextPG(userContext, numerazione));
		} catch (Exception t) {
			throw handleException(evasioneOrdine, t);
		}
	}

	public RemoteIterator ricercaEvasioni(UserContext userContext, OrdineAcqConsegnaBulk ordineAcqConsegnaBulk) throws ComponentException {
		final BulkHome home = getHome(userContext, EvasioneOrdineRigaBulk.class);
		final SQLBuilder sqlBuilder = home.createSQLBuilder();
		sqlBuilder.addClause(FindClause.AND, "ordineAcqConsegna", SQLBuilder.EQUALS, ordineAcqConsegnaBulk);
		return  iterator(userContext, sqlBuilder, EvasioneOrdineRigaBulk.class, null);
	}
}