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

import it.cnr.contab.config00.latt.bulk.WorkpackageBulk;
import it.cnr.contab.doccont00.core.bulk.*;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.BulkList;
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
public class ObbligazionePluriennaleComponent extends ObbligazioneComponent {
	public ObbligazionePluriennaleComponent()
	{
		/*Default constructor*/
	}

	public List<Obbligazione_pluriennaleBulk> findObbligazioniPluriennali(UserContext uc, int esercizio) throws it.cnr.jada.comp.ComponentException {
		try{
			Obbligazione_pluriennaleHome obbligazionePluriennaleHome = ( Obbligazione_pluriennaleHome) getHome(uc, Obbligazione_pluriennaleBulk.class);
			SQLBuilder sql = obbligazionePluriennaleHome.createSQLBuilder();
			sql.addClause(FindClause.AND, "anno", SQLBuilder.EQUALS, esercizio);
			sql.addClause(FindClause.AND, "cdCdsRif", SQLBuilder.ISNULL, null);
			sql.addClause(FindClause.AND, "esercizioRif", SQLBuilder.ISNULL, null);
			sql.addClause(FindClause.AND, "esercizioOriginaleRif", SQLBuilder.ISNULL, null);
			sql.addClause(FindClause.AND, "pgObbligazioneRif", SQLBuilder.ISNULL, null);
			sql.addClause(FindClause.AND, "pgObbligazione", SQLBuilder.EQUALS, 4320);

			return obbligazionePluriennaleHome.fetchAll(sql);
		} catch (it.cnr.jada.persistency.PersistencyException e) {
			throw handleException( new ApplicationPersistencyException(e));
		}
	}
	public ObbligazioneBulk createObbligazioneNew(UserContext uc, Obbligazione_pluriennaleBulk pluriennaleBulk, WorkpackageBulk gaeIniziale) throws it.cnr.jada.comp.ComponentException {
		try {
			Obbligazione_pluriennaleHome pluriennaleHome = (Obbligazione_pluriennaleHome) getHome(uc, Obbligazione_pluriennaleBulk.class);
			pluriennaleBulk.setRigheVoceColl(new BulkList(pluriennaleHome.findObbligazioniPluriennaliVoce(uc, pluriennaleBulk)));
			ObbligazioneHome obbligazioneHome = (ObbligazioneHome) getHome(uc, ObbligazioneBulk.class);
			 ObbligazioneBulk obbligazioneBulk =obbligazioneHome.findObbligazione(new ObbligazioneOrdBulk(pluriennaleBulk.getCdCds(),
																		pluriennaleBulk.getEsercizio(),
																	pluriennaleBulk.getEsercizioOriginale(),
																	pluriennaleBulk.getPgObbligazione()));
			ObbligazioneBulk obbligazioneBulkNew =( ObbligazioneBulk) obbligazioneBulk.clone();
			//aggiornamento riferimento obbligazione creata
			//pluriennaleHome.update(pluriennaleBulk,uc);

			return null;
		}catch(Exception e )
			{
				throw handleException(e);
		}
	}
}