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

import it.cnr.contab.doccont00.core.bulk.AccertamentoBulk;
import it.cnr.contab.doccont00.core.bulk.Accertamento_pluriennaleBulk;
import it.cnr.contab.doccont00.core.bulk.Accertamento_pluriennaleHome;
import it.cnr.jada.UserContext;
import it.cnr.jada.persistency.sql.ApplicationPersistencyException;
import it.cnr.jada.persistency.sql.FindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;

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
			sql.addClause(FindClause.AND, "cd_cds__rif", SQLBuilder.ISNOTNULL, null);
			sql.addClause(FindClause.AND, "esercizio_rif", SQLBuilder.ISNOTNULL, null);
			sql.addClause(FindClause.AND, "esercizio_originale_rif", SQLBuilder.ISNOTNULL, null);
			sql.addClause(FindClause.AND, "pg_accertamento_rif", SQLBuilder.ISNOTNULL, null);
			return accertamento_pluriennaleHome.fetchAll(sql);
		} catch (it.cnr.jada.persistency.PersistencyException e) {
			throw handleException( new ApplicationPersistencyException(e));
		}
	}
	public AccertamentoBulk createAccertamentoNew(UserContext uc, Accertamento_pluriennaleBulk accertamentoPluriennaleBulk) throws it.cnr.jada.comp.ComponentException {
		return null;
	}
}
