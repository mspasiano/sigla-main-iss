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

package it.cnr.contab.utenze00.bulk;

import it.cnr.jada.UserContext;
import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.PersistentCache;
import it.cnr.jada.persistency.sql.FindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;

import java.util.List;

public class Ruolo_accessoHome extends BulkHome {
    /**
     * <!-- @TODO: da completare -->
     * Costruisce un Ruolo_accessoHome
     *
     * @param conn La java.sql.Connection su cui vengono effettuate le operazione di persistenza
     */
    public Ruolo_accessoHome(java.sql.Connection conn) {
        super(Ruolo_accessoBulk.class, conn);
    }

    /**
     * <!-- @TODO: da completare -->
     * Costruisce un Ruolo_accessoHome
     *
     * @param conn            La java.sql.Connection su cui vengono effettuate le operazione di persistenza
     * @param persistentCache La PersistentCache in cui vengono cachati gli oggetti persistenti caricati da questo Home
     */
    public Ruolo_accessoHome(java.sql.Connection conn, PersistentCache persistentCache) {
        super(Ruolo_accessoBulk.class, conn, persistentCache);
    }

	public List<Ruolo_accessoBulk> findAccessiByRuoli(UserContext userContext, Integer esercizio, List<String> ruoli) throws PersistencyException {
        final SQLBuilder sqlBuilder = createSQLBuilder();
        sqlBuilder.addTableToHeader("ASS_BP_ACCESSO");
        sqlBuilder.addSQLJoin("RUOLO_ACCESSO.CD_ACCESSO", SQLBuilder.EQUALS, "ASS_BP_ACCESSO.CD_ACCESSO");
        sqlBuilder.openParenthesis(FindClause.AND);
        ruoli.stream().forEach(ruolo -> {
            sqlBuilder.addClause(FindClause.OR, "cd_ruolo", SQLBuilder.EQUALS, ruolo);
        });
        sqlBuilder.closeParenthesis();

        sqlBuilder.openParenthesis(FindClause.AND);
        sqlBuilder.addSQLClause(FindClause.AND, "ASS_BP_ACCESSO.ESERCIZIO_INIZIO_VALIDITA", SQLBuilder.LESS_EQUALS, esercizio);
        sqlBuilder.addSQLClause(FindClause.OR, "ASS_BP_ACCESSO.ESERCIZIO_INIZIO_VALIDITA", SQLBuilder.ISNULL, null);
        sqlBuilder.closeParenthesis();

        sqlBuilder.openParenthesis(FindClause.AND);
        sqlBuilder.addSQLClause(FindClause.AND, "ASS_BP_ACCESSO.ESERCIZIO_FINE_VALIDITA", SQLBuilder.GREATER_EQUALS, esercizio);
        sqlBuilder.addSQLClause(FindClause.OR, "ASS_BP_ACCESSO.ESERCIZIO_FINE_VALIDITA", SQLBuilder.ISNULL, null);
        sqlBuilder.closeParenthesis();

        return fetchAll(sqlBuilder);
    }
}
