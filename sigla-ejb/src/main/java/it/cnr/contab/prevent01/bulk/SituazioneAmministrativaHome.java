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

package it.cnr.contab.prevent01.bulk;

import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.persistency.PersistentCache;

import java.sql.Connection;

/**
 * Insert the type's description here.
 * Creation date: (24/10/2023 12.20.00)
 * @author: Piergiorgio Faraglia
 */
public class SituazioneAmministrativaHome extends BulkHome {

	public SituazioneAmministrativaHome(Class clazz, Connection conn) {
		super(clazz, conn);
	}

	public SituazioneAmministrativaHome(Class clazz, Connection conn, PersistentCache persistentCache) {
		super(clazz, conn, persistentCache);
	}

	public SituazioneAmministrativaHome(Connection conn, PersistentCache persistentCache) {
		super(RiaccertamentoResiduiBulk.class, conn, persistentCache);
	}

}
