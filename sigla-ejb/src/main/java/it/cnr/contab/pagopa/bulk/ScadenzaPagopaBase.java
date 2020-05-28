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

package it.cnr.contab.pagopa.bulk;

import it.cnr.jada.persistency.Keyed;

import java.sql.Timestamp;

public class ScadenzaPagopaBase extends ScadenzaPagopaKey implements Keyed {
	private static final long serialVersionUID = 1L;

	// ESERCIZIO DECIMAL(4,0) NOT NULL 
	private Integer esercizio;

	// CD_UNITA_ORGANIZZATIVA VARCHAR(30) NOT NULL
	private String cdUnitaOrganizzativa;
	
	// DESCRIZIONE_ATTO VARCHAR2(300 BYTE) NOT NULL
	private String tipoPosizione;

	// ID_TIPO_ATTO_BOLLO NUMBER NOT NULL
	private Integer idTipoScadenzaPagopa;

	public String getCdIuv() {
		return cdIuv;
	}

	public void setCdIuv(String cdIuv) {
		this.cdIuv = cdIuv;
	}

	// CD_PROVV VARCHAR(20)
	private String cdAvviso;
	private String cdIuv;

	// NUMERO_PROVV DECIMAL(10,0)
	private String stato;

	private java.sql.Timestamp dtScadenza;

	private String note;
	public ScadenzaPagopaBase() {
		super();
	}

	public ScadenzaPagopaBase(Long id) {
		super(id);
	}

	public Integer getEsercizio() {
		return esercizio;
	}

	public void setEsercizio(Integer esercizio) {
		this.esercizio = esercizio;
	}

	public String getCdUnitaOrganizzativa() {
		return cdUnitaOrganizzativa;
	}

	public void setCdUnitaOrganizzativa(String cdUnitaOrganizzativa) {
		this.cdUnitaOrganizzativa = cdUnitaOrganizzativa;
	}

	public String getTipoPosizione() {
		return tipoPosizione;
	}

	public void setTipoPosizione(String tipoPosizione) {
		this.tipoPosizione = tipoPosizione;
	}

	public Integer getIdTipoScadenzaPagopa() {
		return idTipoScadenzaPagopa;
	}

	public void setIdTipoScadenzaPagopa(Integer idTipoScadenzaPagopa) {
		this.idTipoScadenzaPagopa = idTipoScadenzaPagopa;
	}

	public String getCdAvviso() {
		return cdAvviso;
	}

	public void setCdAvviso(String cdAvviso) {
		this.cdAvviso = cdAvviso;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Timestamp getDtScadenza() {
		return dtScadenza;
	}

	public void setDtScadenza(Timestamp dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
