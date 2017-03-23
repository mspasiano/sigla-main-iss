/*
* Created by Generator 1.0
* Date 29/08/2005
*/
package it.cnr.contab.config00.pdcep.cla.bulk;

import it.cnr.contab.config00.pdcep.bulk.Voce_epHome;
import it.cnr.jada.UserContext;
import it.cnr.jada.persistency.PersistencyException;
import it.cnr.jada.persistency.PersistentCache;
import it.cnr.jada.persistency.sql.CompoundFindClause;
import it.cnr.jada.persistency.sql.SQLBuilder;

public class Classificazione_voci_ep_patHome extends Classificazione_voci_epHome {
	protected Classificazione_voci_ep_patHome(Class clazz,java.sql.Connection connection) {
		super(clazz,connection);
	}
	protected Classificazione_voci_ep_patHome(Class clazz,java.sql.Connection connection,PersistentCache persistentCache) {
		super(clazz,connection,persistentCache);
	}

	public Classificazione_voci_ep_patHome(java.sql.Connection conn) {
		super(Classificazione_voci_ep_patBulk.class, conn);
	}
	public Classificazione_voci_ep_patHome(java.sql.Connection conn, PersistentCache persistentCache) {
		super(Classificazione_voci_ep_patBulk.class, conn, persistentCache);
	}
	public SQLBuilder selectByClause(UserContext usercontext, CompoundFindClause compoundfindclause)
		throws PersistencyException
	{
		return selectPat(usercontext, compoundfindclause);
	}	
	public SQLBuilder selectPat(UserContext usercontext, CompoundFindClause compoundfindclause)
		throws PersistencyException
	{
		SQLBuilder sql = super.selectByClause(usercontext, compoundfindclause);
		sql.addSQLClause("AND","tipo",SQLBuilder.EQUALS,Voce_epHome.PATRIMONIALE);
		return sql;
	}	
}