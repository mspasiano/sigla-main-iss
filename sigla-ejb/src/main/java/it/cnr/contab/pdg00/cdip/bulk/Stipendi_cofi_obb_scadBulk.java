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
 * Created by Aurelio's BulkGenerator 1.0
 * Date 28/09/2006
 */
package it.cnr.contab.pdg00.cdip.bulk;

import it.cnr.jada.bulk.OggettoBulk;

import java.util.Optional;

public class Stipendi_cofi_obb_scadBulk extends Stipendi_cofi_obb_scadBase {
	public Stipendi_cofi_obb_scadBulk() {
		super();
	}
	public Stipendi_cofi_obb_scadBulk(java.lang.Integer esercizio, java.lang.Integer mese, java.lang.String cd_cds_obbligazione, java.lang.Integer esercizio_obbligazione, java.lang.Integer esercizio_ori_obbligazione, java.lang.Long pg_obbligazione) {
		super(esercizio, mese, cd_cds_obbligazione, esercizio_obbligazione, esercizio_ori_obbligazione, pg_obbligazione);
	}

	public static final java.util.Map meseKeys = Stipendi_cofiBulk.meseKeys;

	private Stipendi_cofiBulk stipendi_cofi = new Stipendi_cofiBulk();

	public java.lang.Integer getEsercizio() {
		return Optional.ofNullable(this.getStipendi_cofi()).map(Stipendi_cofiBulk::getEsercizio).orElse(null);
	}

	public void setEsercizio(java.lang.Integer esercizio) {
		Optional.ofNullable(this.getStipendi_cofi()).ifPresent(el->el.setEsercizio(esercizio));
	}
	
	public java.lang.Integer getMese() {
		return Optional.ofNullable(this.getStipendi_cofi()).map(Stipendi_cofiBulk::getMese).orElse(null);
	}

	public void setMese(java.lang.Integer mese)	{
		Optional.ofNullable(this.getStipendi_cofi()).ifPresent(el->el.setMese(mese));
	}

	public java.lang.Integer getMese_reale() {
		return Optional.ofNullable(this.getStipendi_cofi()).map(Stipendi_cofiBulk::getMese_reale).orElse(null);
	}

	public java.lang.Integer getProg_flusso() {
		return Optional.ofNullable(this.getStipendi_cofi()).map(Stipendi_cofiBulk::getProg_flusso).orElse(null);
	}

    public Stipendi_cofiBulk getStipendi_cofi() {
    	return stipendi_cofi;
    }
    public void setStipendi_cofi(Stipendi_cofiBulk bulk) {
    	stipendi_cofi = bulk;
    }

	public OggettoBulk initialize(it.cnr.jada.util.action.CRUDBP bp, it.cnr.jada.action.ActionContext context){
		super.initialize(bp, context);
		this.setEsercizio( ((it.cnr.contab.utenze00.bp.CNRUserContext)context.getUserContext()).getEsercizio() );
		return this;
	}

	public static java.util.Map getMeseKeys() {
		return meseKeys;
	}
}