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

package it.cnr.contab.docamm00.bp;

import it.cnr.contab.docamm00.docs.bulk.Fattura_passivaBulk;

/**
 * Gestisce le catene di elementi correlate con la fattura passiva in uso.
 */
public class CRUDFatturaPassivaAmministraBP extends CRUDFatturaPassivaIBP {
    public CRUDFatturaPassivaAmministraBP() {
        super();
    }

    public CRUDFatturaPassivaAmministraBP(String function) throws it.cnr.jada.action.BusinessProcessException {
        super(function);
    }

    public void create(it.cnr.jada.action.ActionContext context) throws it.cnr.jada.action.BusinessProcessException {
        if (getModel() instanceof Fattura_passivaBulk)
            ((Fattura_passivaBulk)getModel()).setFromAmministra(Boolean.TRUE);
        super.create(context);
    }
}
