/*
 * Created by BulkGenerator 2.0 [07/12/2009]
 * Date 25/11/2023
 */
package it.cnr.contab.doccont00.core.bulk;

import it.cnr.jada.bulk.BulkHome;
import it.cnr.jada.persistency.PersistentCache;

import java.sql.Connection;
public class Obbligazione_pluriennale_voceHome extends BulkHome {
	public Obbligazione_pluriennale_voceHome(Connection conn) {
		super(Obbligazione_pluriennale_voceBulk.class, conn);
	}
	public Obbligazione_pluriennale_voceHome(Connection conn, PersistentCache persistentCache) {
		super(Obbligazione_pluriennale_voceBulk.class, conn, persistentCache);
	}
}