/*
 * Copyright (C) 2019  Consiglio Nazionale delle Ricerche
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

/*
 * Created on Feb 6, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.cnr.contab.doccont00.comp;

import it.cnr.contab.config00.contratto.bulk.ContrattoBulk;
import it.cnr.contab.config00.latt.bulk.WorkpackageBulk;
import it.cnr.contab.doccont00.core.bulk.*;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.BulkList;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.sql.ApplicationPersistencyException;
import it.cnr.jada.persistency.sql.FindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author rpagano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AccertamentoPluriennaleComponent extends AccertamentoComponent {
	public AccertamentoPluriennaleComponent()
	{
		/*Default constructor*/
	}

	public List<Accertamento_pluriennaleBulk> findAccertamentiPluriennali(UserContext uc, int esercizio) throws it.cnr.jada.comp.ComponentException {
		try{
			Accertamento_pluriennaleHome accertamento_pluriennaleHome = ( Accertamento_pluriennaleHome) getHome(uc, Accertamento_pluriennaleBulk.class);
			SQLBuilder sql = accertamento_pluriennaleHome.createSQLBuilder();
			sql.addClause(FindClause.AND, "anno", SQLBuilder.EQUALS, esercizio);
			sql.addClause(FindClause.AND, "cdCdsRif", SQLBuilder.ISNULL, null);
			sql.addClause(FindClause.AND, "esercizioRif", SQLBuilder.ISNULL, null);
			sql.addClause(FindClause.AND, "esercizioOriginaleRif", SQLBuilder.ISNULL, null);
			sql.addClause(FindClause.AND, "pgAccertamentoRif", SQLBuilder.ISNULL, null);
			sql.addClause(FindClause.AND, "pgAccertamento", SQLBuilder.EQUALS, 2086);
			return accertamento_pluriennaleHome.fetchAll(sql);
		} catch (it.cnr.jada.persistency.PersistencyException e) {
			throw handleException( new ApplicationPersistencyException(e));
		}
	}
	public AccertamentoBulk createAccertamentoNew(UserContext uc, Integer esercizio,Accertamento_pluriennaleBulk accertamentoPluriennaleBulk) throws it.cnr.jada.comp.ComponentException {
		try {
			Accertamento_pluriennaleHome pluriennaleHome = (Accertamento_pluriennaleHome) getHome(uc, Accertamento_pluriennaleBulk.class);
			AccertamentoHome accertamentoHome = (AccertamentoHome) getHome(uc, AccertamentoBulk.class);
			accertamentoPluriennaleBulk.setRigheVoceColl(new BulkList(pluriennaleHome.findAccertamentiPluriennaliVoce(uc, accertamentoPluriennaleBulk)));

			AccertamentoBulk accertamentoBulk =accertamentoHome.findAccertamento(new AccertamentoBulk(accertamentoPluriennaleBulk.getCdCds(),
					accertamentoPluriennaleBulk.getEsercizio(),
					accertamentoPluriennaleBulk.getEsercizioOriginale(),
					accertamentoPluriennaleBulk.getPgAccertamento()));
			AccertamentoBulk newAccertamentoBulk =( AccertamentoBulk) accertamentoBulk.clone();
			WorkpackageBulk lineaAttivita = (WorkpackageBulk) getHome(uc, WorkpackageBulk.class).findByPrimaryKey(accertamentoPluriennaleBulk.getRigheVoceColl().get(0).getLinea_attivita());
			newAccertamentoBulk.setCrudStatus(OggettoBulk.TO_BE_CREATED);
			newAccertamentoBulk.setPg_accertamento(null);
			newAccertamentoBulk.setEsercizio(esercizio);
			newAccertamentoBulk.setEsercizio_originale(esercizio);
			newAccertamentoBulk.setCd_tipo_documento_cont(Numerazione_doc_contBulk.TIPO_ACR);
			//Elemento_voceBulk voce = (Elemento_voceBulk)getHome(uc, Elemento_voceBulk.class).findByPrimaryKey(accertamentoBulk.getCd_elemento_voce());
			//newAccertamentoBulk.setCapitolo(new V_voce_f_partita_giroBulk(voce.getCd_voce(), voce.getEsercizio(), voce.getTi_appartenenza(), voce.getTi_gestione()));

			if ( newAccertamentoBulk.getContratto()!= null && newAccertamentoBulk.getContratto().getPg_contratto()!=null && newAccertamentoBulk.getContratto().getPg_contratto()>0)
				newAccertamentoBulk.setContratto(((ContrattoBulk) getHome(uc, ContrattoBulk.class).findByPrimaryKey(newAccertamentoBulk.getContratto())));
			else
				newAccertamentoBulk.setContratto( null);
			//newAccertamentoBulk.setDt_registrazione(it.cnr.jada.util.ejb.EJBCommonServices.getServerDate());
			newAccertamentoBulk.setIm_accertamento(accertamentoPluriennaleBulk.getImporto());
			newAccertamentoBulk.setEsercizio_competenza(esercizio);
			Accertamento_scadenzarioBulk acc_scadenza = new Accertamento_scadenzarioBulk();
			acc_scadenza.setUtcr(newAccertamentoBulk.getUtcr());
			acc_scadenza.setToBeCreated();

			acc_scadenza.setAccertamento(newAccertamentoBulk);
			acc_scadenza.setDt_scadenza_incasso(it.cnr.jada.util.ejb.EJBCommonServices.getServerDate());
			acc_scadenza.setDs_scadenza(newAccertamentoBulk.getDs_accertamento());
			newAccertamentoBulk.addToAccertamento_scadenzarioColl(acc_scadenza);
			acc_scadenza.setIm_scadenza(newAccertamentoBulk.getIm_accertamento());
			acc_scadenza.setIm_associato_doc_amm(newAccertamentoBulk.getIm_accertamento());
			acc_scadenza.setIm_associato_doc_contabile(new BigDecimal(0));

			Accertamento_scad_voceBulk acc_scad_voce = new Accertamento_scad_voceBulk();
			//acc_scad_voce.setUtcr(testata.getUtcr());
			acc_scad_voce.setToBeCreated();
			acc_scad_voce.setAccertamento_scadenzario(acc_scadenza);
			acc_scad_voce.setIm_voce(newAccertamentoBulk.getIm_accertamento());

			acc_scad_voce.setLinea_attivita(lineaAttivita);

			acc_scadenza.getAccertamento_scad_voceColl().add((acc_scad_voce));

			inizializzaBulkPerInserimento(uc,newAccertamentoBulk);
			newAccertamentoBulk = (AccertamentoBulk)creaConBulk(uc, newAccertamentoBulk);
			accertamentoPluriennaleBulk.setAccertamentoRif(newAccertamentoBulk);
			//aggiornamento riferimento accertamente creato
			pluriennaleHome.update(accertamentoPluriennaleBulk,uc);
			return newAccertamentoBulk;
		}catch(Exception e )
		{
			throw handleException(e);
		}
	}
}
