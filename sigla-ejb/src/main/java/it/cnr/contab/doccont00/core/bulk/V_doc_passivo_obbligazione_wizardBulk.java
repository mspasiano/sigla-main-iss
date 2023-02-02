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
 * Created on Apr 6, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.cnr.contab.doccont00.core.bulk;

import it.cnr.contab.anagraf00.core.bulk.BancaBulk;
import it.cnr.contab.anagraf00.core.bulk.Modalita_pagamentoBulk;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class V_doc_passivo_obbligazione_wizardBulk extends V_doc_passivo_obbligazioneBulk {
	protected Mandato_rigaBulk mandatoRiga;

	protected String descrizioneRigaMandatoWizard;

	protected String cdElementoVoce;
	protected BigDecimal imponibileRigaMandatoWizard;
	protected BigDecimal impostaRigaMandatoWizard;
	protected BigDecimal importoRigaMandatoWizard;
	protected BancaBulk bancaRigaMandatoWizard = new BancaBulk();
	protected Modalita_pagamentoBulk modalitaPagamentoRigaMandatoWizard = new Modalita_pagamentoBulk();
	protected List modalitaPagamentoOptions;

	protected List bancaOptions;

	public V_doc_passivo_obbligazione_wizardBulk() {
		super();
	}

	public Mandato_rigaBulk getMandatoRiga() {
		return mandatoRiga;
	}

	public void setMandatoRiga(Mandato_rigaBulk mandatoRiga) {
		this.mandatoRiga = mandatoRiga;
	}

	public String getDescrizioneRigaMandatoWizard() {
		return descrizioneRigaMandatoWizard;
	}

	public void setDescrizioneRigaMandatoWizard(String descrizioneRigaMandatoWizard) {
		this.descrizioneRigaMandatoWizard = descrizioneRigaMandatoWizard;
	}

	public String getCdElementoVoce() {
		return cdElementoVoce;
	}

	public void setCdElementoVoce(String cdElementoVoce) {
		this.cdElementoVoce = cdElementoVoce;
	}

	public BigDecimal getImponibileRigaMandatoWizard() {
		return imponibileRigaMandatoWizard;
	}

	public void setImponibileRigaMandatoWizard(BigDecimal imponibileRigaMandatoWizard) {
		this.imponibileRigaMandatoWizard = imponibileRigaMandatoWizard;
	}

	public BigDecimal getImpostaRigaMandatoWizard() {
		return impostaRigaMandatoWizard;
	}

	public void setImpostaRigaMandatoWizard(BigDecimal impostaRigaMandatoWizard) {
		this.impostaRigaMandatoWizard = impostaRigaMandatoWizard;
	}

	public BancaBulk getBancaRigaMandatoWizard() {
		return bancaRigaMandatoWizard;
	}

	public void setBancaRigaMandatoWizard(BancaBulk bancaRigaMandatoWizard) {
		this.bancaRigaMandatoWizard = bancaRigaMandatoWizard;
	}

	public Long getPgBancaWizard() {
		return Optional.ofNullable(this.getBancaRigaMandatoWizard()).map(BancaBulk::getPg_banca).orElse(null);
	}

	public Modalita_pagamentoBulk getModalitaPagamentoRigaMandatoWizard() {
		return modalitaPagamentoRigaMandatoWizard;
	}

	public void setModalitaPagamentoRigaMandatoWizard(Modalita_pagamentoBulk modalitaPagamentoRigaMandatoWizard) {
		this.modalitaPagamentoRigaMandatoWizard = modalitaPagamentoRigaMandatoWizard;
	}

	public String getCdModalitaPagamentoWizard() {
		return Optional.ofNullable(this.getModalitaPagamentoRigaMandatoWizard()).map(Modalita_pagamentoBulk::getCd_modalita_pag).orElse(null);
	}

	public List<Modalita_pagamentoBulk> getModalitaPagamentoOptions() {
		return modalitaPagamentoOptions;
	}

	public void setModalitaPagamentoOptions(List<Modalita_pagamentoBulk> modalitaPagamentoOptions) {
		this.modalitaPagamentoOptions = modalitaPagamentoOptions;
	}

	public List<BancaBulk> getBancaOptions() {
		return bancaOptions;
	}

	public void setBancaOptions(List<BancaBulk> bancaOptions) {
		this.bancaOptions = bancaOptions;
	}

	public BigDecimal getImportoRigaMandatoWizard() {
		return importoRigaMandatoWizard;
	}

	public void setImportoRigaMandatoWizard(BigDecimal importoRigaMandatoWizard) {
		this.importoRigaMandatoWizard = importoRigaMandatoWizard;
	}
}
