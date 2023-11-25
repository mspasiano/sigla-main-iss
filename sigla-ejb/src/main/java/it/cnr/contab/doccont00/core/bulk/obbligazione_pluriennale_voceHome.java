/*
 * Created by BulkGenerator 2.0 [07/12/2009]
 * Date 25/11/2023
 */
package it.cnr.contab.doccont00.core.bulk;

import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.persistency.PersistentCache;

import java.sql.Connection;
public class obbligazione_pluriennale_voceHome extends BulkHome {
	public obbligazione_pluriennale_voceHome(Connection conn) {
		super(obbligazione_pluriennale_voceBulk.class, conn);
	}
	public obbligazione_pluriennale_voceHome(Connection conn, PersistentCache persistentCache) {
		super(obbligazione_pluriennale_voceBulk.class, conn, persistentCache);
	}
}