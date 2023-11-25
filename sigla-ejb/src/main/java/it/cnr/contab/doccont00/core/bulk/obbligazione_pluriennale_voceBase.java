/*
 * Created by BulkGenerator 2.0 [07/12/2009]
 * Date 24/11/2023
 */
package it.cnr.contab.doccont00.core.bulk;
import it.cnr.jada.persistency.Keyed;
public class obbligazione_pluriennale_voceBase extends obbligazione_pluriennale_voceKey implements Keyed {
//    IMPORTO DECIMAL(15,2) NOT NULL
	private java.math.BigDecimal importo;
 
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: OBBLIGAZIONE_PLURIENNALE_VOCE
	 **/
	public obbligazione_pluriennale_voceBase() {
		super();
	}
	public obbligazione_pluriennale_voceBase(String cdCds, Long esercizio, Long esercizioOriginale, Long pgObbligazione, Long anno, String cdCentroResponsabilita, String cdLineaAttivita, String tiAppartenenza, String tiGestione, String cdVoce) {
		super(cdCds, esercizio, esercizioOriginale, pgObbligazione, anno, cdCentroResponsabilita, cdLineaAttivita, tiAppartenenza, tiGestione, cdVoce);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Importo del dettaglio per la linea attivita]
	 **/
	public java.math.BigDecimal getImporto() {
		return importo;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Importo del dettaglio per la linea attivita]
	 **/
	public void setImporto(java.math.BigDecimal importo)  {
		this.importo=importo;
	}
}