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

package it.cnr.contab.doccont00.core.bulk;

import java.util.*;
import it.cnr.contab.docamm00.docs.bulk.*;
import it.cnr.jada.persistency.*;
import it.cnr.jada.persistency.sql.*;

public class ReversaleIHome extends ReversaleHome {
public ReversaleIHome(Class clazz, java.sql.Connection conn) {
	super(clazz,conn);
}
public ReversaleIHome(Class clazz, java.sql.Connection conn, it.cnr.jada.persistency.PersistentCache persistentCache) {
	super(clazz,conn, persistentCache);
}
public ReversaleIHome(java.sql.Connection conn) {
	super(ReversaleIBulk.class,conn);
}
public ReversaleIHome(java.sql.Connection conn, it.cnr.jada.persistency.PersistentCache persistentCache) {
	super(ReversaleIBulk.class,conn, persistentCache);
}
/**
 * Metodo per cercare i documenti attivi associati alla reversale.
 *
 * @param reversale <code>ReversaleIBulk</code> la reversale
 * @param context <code>CNRUserContext</code>
 *
 * @return <code>Collection</code> i documenti attivi associati alla reversale
 *
 */
public Collection findDocAttivi( ReversaleIBulk reversale, it.cnr.contab.utenze00.bp.CNRUserContext context ) throws IntrospectionException, PersistencyException 
{
	PersistentHome home = getHomeCache().getHome( V_doc_attivo_accertamentoBulk.class );
	SQLBuilder sql = home.createSQLBuilder();
	CompoundFindClause clauses = reversale.getFind_doc_attivi().buildFindClauses(null);
	sql.addClause( clauses );
	// sql.addClause( "AND", "cd_unita_organizzativa", sql.EQUALS, reversale.getCd_unita_organizzativa());
		/* simona 16.06.2003: aggiunta la clausola sull'esercizio_obbligazione x gestire documenti riportati */
	sql.addSQLClause( "AND", "esercizio_accertamento", sql.EQUALS, (((it.cnr.contab.utenze00.bp.CNRUserContext)context).getEsercizio()));			
	sql.addClause( "AND", "cd_cds_accertamento", sql.EQUALS, reversale.getCd_cds());
	sql.addClause( "AND", "cd_cds_origine", sql.EQUALS, context.getCd_cds());
	sql.addClause( "AND", "cd_uo_origine", sql.EQUALS, context.getCd_unita_organizzativa());
	sql.addSQLClause( "AND", "fl_selezione", sql.EQUALS, "Y");	
//	sql.addSQLClause( "AND", "IM_TOTALE_DOC_AMM - IM_ASSOCIATO_DOC_CONTABILE", sql.GREATER, new java.math.BigDecimal(0));
	sql.addClause( "AND", "stato_cofi", sql.EQUALS, Documento_genericoBulk.STATO_CONTABILIZZATO);		
	sql.addSQLClause( "AND", "IM_SCADENZA", sql.GREATER, new java.math.BigDecimal(0));		
	CompoundFindClause clausesNoFattElett = new CompoundFindClause();
	clausesNoFattElett.addClause("OR", "flFatturaElettronica", SQLBuilder.EQUALS, Boolean.FALSE);
	CompoundFindClause clausesOr = new CompoundFindClause();
	CompoundFindClause clausesFattElett = new CompoundFindClause();
	clausesFattElett.addClause("AND", "codiceInvioSdi", SQLBuilder.ISNOTNULL, null);
	CompoundFindClause clausesFattElettPubbliciAnd = new CompoundFindClause();
	CompoundFindClause clausesFattElettPubblici = new CompoundFindClause();
	clausesFattElettPubblici.addClause("OR", "statoInvioSdi", SQLBuilder.EQUALS, Fattura_attivaBulk.FATT_ELETT_ACCETTATA_DESTINATARIO);
	clausesFattElettPubblici.addClause("OR", "statoInvioSdi", SQLBuilder.EQUALS, Fattura_attivaBulk.FATT_ELETT_DECORRENZA_TERMINI);
	clausesFattElettPubblici.addClause("OR", "statoInvioSdi", SQLBuilder.EQUALS, Fattura_attivaBulk.FATT_ELETT_NON_RECAPITABILE);
	CompoundFindClause clausesFattElettPrivati = new CompoundFindClause();
	CompoundFindClause clausesFattElettPrivatiAnd = new CompoundFindClause();
	CompoundFindClause clausesFattElettPrivatiOr = new CompoundFindClause();
	clausesFattElettPrivatiAnd.addClause("AND", "codiceUnivocoUfficioIpa", SQLBuilder.ISNULL, null);
	clausesFattElettPrivatiOr.addClause("or", "statoInvioSdi", SQLBuilder.EQUALS, Fattura_attivaBulk.FATT_ELETT_MANCATA_CONSEGNA);
	clausesFattElettPrivatiOr.addClause("or", "statoInvioSdi", SQLBuilder.EQUALS, Fattura_attivaBulk.FATT_ELETT_AVVISO_NOTIFICA_INVIO_MAIL);
	clausesFattElettPrivatiOr.addClause("OR", "statoInvioSdi", SQLBuilder.EQUALS, Fattura_attivaBulk.FATT_ELETT_CONSEGNATA_DESTINATARIO);
	
	clausesFattElettPrivati.addChild(CompoundFindClause.and(clausesFattElettPrivatiAnd, clausesFattElettPrivatiOr));

	clausesFattElett.addChild(CompoundFindClause.or(clausesFattElettPrivati, clausesFattElettPubblici));
	
	clausesOr.addChild(CompoundFindClause.or(clausesNoFattElett, clausesFattElett));
	sql.addClause(clausesOr);
	sql.addOrderBy( "esercizio_ori_accertamento, pg_accertamento" );
	
	return home.fetchAll( sql);
			
}
/**
 * <!-- @TODO: da completare -->
 * 
 *
 * @param docAttivo	
 * @return 
 * @throws PersistencyException	
 */
public Collection findDocAttiviCollegati(  V_doc_attivo_accertamentoBulk docAttivo ) throws  PersistencyException 
{
	PersistentHome home = getHomeCache().getHome( V_doc_attivo_accertamentoBulk.class );
	SQLBuilder sql = home.createSQLBuilder();
	sql.addClause( "AND", "cd_cds_origine", sql.EQUALS, docAttivo.getCd_cds_origine());
	sql.addClause( "AND", "cd_uo_origine", sql.EQUALS, docAttivo.getCd_uo_origine() );
	sql.addClause( "AND", "cd_cds_accertamento", sql.EQUALS, docAttivo.getCd_cds());	
	sql.addClause( "AND", "esercizio_accertamento", sql.EQUALS, docAttivo.getEsercizio_accertamento());
	sql.addClause( "AND", "esercizio_ori_accertamento", sql.EQUALS, docAttivo.getEsercizio_ori_accertamento());
	sql.addClause( "AND", "pg_accertamento", sql.EQUALS, docAttivo.getPg_accertamento());
	sql.addClause( "AND", "pg_accertamento_scadenzario", sql.EQUALS, docAttivo.getPg_accertamento_scadenzario());	
	sql.addSQLClause( "AND", "fl_selezione", sql.EQUALS, "N");			
	return home.fetchAll( sql);
			
}
/**
 * Metodo per cercare la riga della reversale.
 *
 * @param reversale <code>ReversaleBulk</code> la reversale
 *
 * @return result la riga della reversale
 *
 */
public Collection findReversale_riga(it.cnr.jada.UserContext userContext, ReversaleBulk reversale ) throws PersistencyException, IntrospectionException
{
	PersistentHome home = getHomeCache().getHome( Reversale_rigaIBulk.class );
	SQLBuilder sql = home.createSQLBuilder();
	sql.addClause( "AND", "esercizio", sql.EQUALS, reversale.getEsercizio());	
	sql.addClause( "AND", "cd_cds", sql.EQUALS, reversale.getCd_cds());
	sql.addClause( "AND", "pg_reversale", sql.EQUALS, reversale.getPg_reversale());	
	Collection result = home.fetchAll( sql);
	getHomeCache().fetchAll(userContext);
	return result;
			
}
/**
 * Metodo per cercare l'oggetto Reversale_terzoBulk.
 *
 * @param reversale <code>ReversaleBulk</code> la reversale
 *
 * @return istanza di <code>Reversale_terzoBulk</code>
 *
 */
public Reversale_terzoBulk findReversale_terzo( it.cnr.jada.UserContext userContext,ReversaleBulk reversale ) throws PersistencyException, IntrospectionException
{
	PersistentHome home = getHomeCache().getHome( Reversale_terzoBulk.class );
	SQLBuilder sql = home.createSQLBuilder();
	sql.addClause( "AND", "esercizio", sql.EQUALS, reversale.getEsercizio());	
	sql.addClause( "AND", "cd_cds", sql.EQUALS, reversale.getCd_cds());
	sql.addClause( "AND", "pg_reversale", sql.EQUALS, reversale.getPg_reversale());	
	Collection result = home.fetchAll( sql);
	getHomeCache().fetchAll(userContext);
	return (Reversale_terzoBulk) result.iterator().next();
			
}
/**
 * Carica la reversale <reversale> con tutti gli oggetti complessi
 *
 * @param reversale	
 * @return 
 * @throws PersistencyException	
 */
public ReversaleBulk loadReversale(it.cnr.jada.UserContext userContext,java.lang.String cdCds, java.lang.Integer esercizio, java.lang.Long pgReversale) throws PersistencyException, IntrospectionException {

	SQLBuilder sql = createSQLBuilder();
	sql.addClause("AND", "cd_cds",       sql.EQUALS, cdCds);
	sql.addClause("AND", "esercizio",    sql.EQUALS, esercizio);
	sql.addClause("AND", "pg_reversale", sql.EQUALS, pgReversale); 
	sql.addClause("AND","ti_reversale", sql.EQUALS, ReversaleBulk.TIPO_INCASSO);
	ReversaleIBulk rev = null;
	Broker broker = createBroker(sql);
	if(broker.next()){
		rev = (ReversaleIBulk)fetch(broker);
		rev.setReversale_terzo(findReversale_terzo(userContext,rev));
	}
	broker.close();

	return rev;
}
}
