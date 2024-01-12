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
			AccertamentoBulk accertamentoRifBulk = new AccertamentoBulk(accertamentoPluriennaleBulk.getCdCds(),2023,2023,2086L);
			accertamentoPluriennaleBulk.setAccertamentoRif(accertamentoRifBulk);
			//accertamentoPluriennaleBulk.setAccertamentoRif(2023);
			//accertamentoPluriennaleBulk.setCdCdsRif(accertamentoPluriennaleBulk.getCdCds());
			//accertamentoPluriennaleBulk.setEsercizioOriginaleRif(2023);
			//accertamentoPluriennaleBulk.setPgAccertamentoRif(2086L);
			//aggiornamento riferimento accertamente creato
			//pluriennaleHome.update(accertamentoPluriennaleBulk,uc);




			return null;
		}catch(Exception e )
		{
			throw handleException(e);
		}
	}
}
