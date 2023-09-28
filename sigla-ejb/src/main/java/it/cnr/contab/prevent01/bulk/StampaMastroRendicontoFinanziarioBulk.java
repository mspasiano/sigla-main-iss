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
 * Created on Oct 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.cnr.contab.prevent01.bulk;

import it.cnr.jada.bulk.OggettoBulk;

import java.util.Enumeration;

/**
 * @author 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StampaMastroRendicontoFinanziarioBulk extends Stampa_pdgp_bilancioBulk {

	public final static String TIPO_LIVELLO_VOCE = "5";

	public String getVoce_da() {
		return voce_da;
	}

	public void setVoce_da(String voce_da) {
		this.voce_da = voce_da;
	}

	private String voce_da;

	public String getVoce_a() {
		return voce_a;
	}

	public void setVoce_a(String voce_a) {
		this.voce_a = voce_a;
	}

	private String voce_a;

	public StampaMastroRendicontoFinanziarioBulk() {
		super();
	}
}
