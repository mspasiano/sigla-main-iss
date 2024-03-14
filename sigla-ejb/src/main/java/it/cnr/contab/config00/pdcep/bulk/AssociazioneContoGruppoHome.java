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

package it.cnr.contab.config00.pdcep.bulk;
import java.sql.Connection;
import java.util.Optional;

import it.cnr.contab.utenze00.bp.CNRUserContext;
import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.PersistentCache;
import it.cnr.jada.persistency.sql.CompoundFindClause;
import it.cnr.jada.persistency.sql.FindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;

public class AssociazioneContoGruppoHome extends BulkHome {

	public static final String CD_VOCE_EP = "CD_VOCE_EP";

	public AssociazioneContoGruppoHome(Connection conn) {
		super(AssociazioneContoGruppoBulk.class, conn);
	}
	public AssociazioneContoGruppoHome(Connection conn, PersistentCache persistentCache) {
		super(AssociazioneContoGruppoBulk.class, conn, persistentCache);
	}

	@Override
	public SQLBuilder selectByClause(UserContext usercontext, CompoundFindClause compoundfindclause) throws PersistencyException {
		final CompoundFindClause compoundFindClause = Optional.ofNullable(compoundfindclause)
				.orElseGet(() -> new CompoundFindClause());
		compoundFindClause.addClause(FindClause.AND, "esercizio", SQLBuilder.EQUALS, CNRUserContext.getEsercizio(usercontext));
		return super.selectByClause(usercontext, compoundFindClause);
	}

	public SQLBuilder selectAssociazioniMultiple(UserContext usercontext, AssociazioneContoGruppoBulk associazioneContoGruppoBulk, CompoundFindClause compoundfindclause) throws PersistencyException {
		final SQLBuilder sqlBuilder = selectByClause(usercontext, compoundfindclause);
		final SQLBuilder sqlBuilderIN = createSQLBuilder();
		sqlBuilderIN.addClause(FindClause.AND, "esercizio", SQLBuilder.EQUALS, CNRUserContext.getEsercizio(usercontext));
		sqlBuilderIN.resetColumns();
		sqlBuilderIN.addColumn(CD_VOCE_EP);
		sqlBuilderIN.addSQLGroupBy(CD_VOCE_EP);
		sqlBuilderIN.addSQLHaving("COUNT(1) > 1");
		sqlBuilder.addSQLINClause(FindClause.AND, CD_VOCE_EP, sqlBuilderIN);
		sqlBuilder.addOrderBy(CD_VOCE_EP);
		return sqlBuilder;
	}
}