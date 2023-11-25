/*
 * Created by BulkGenerator 2.0 [07/12/2009]
 * Date 24/11/2023
 */
package it.cnr.contab.doccont00.core.bulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.util.action.CRUDBP;
public class obbligazione_pluriennale_voceBulk extends obbligazione_pluriennale_voceBase {
	/**
	 * [OBBLIGAZIONE_PLURIENNALE ]
	 **/
	private Obbligazione_pluriennaleBulk obbligazionePluriennale =  new Obbligazione_pluriennaleBulk();
	/**
	 * [ELEMENTO_VOCE ]
	 **/
	private Elemento_voceBulk elementoVoce =  new Elemento_voceBulk();
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: OBBLIGAZIONE_PLURIENNALE_VOCE
	 **/
	public obbligazione_pluriennale_voceBulk() {
		super();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: OBBLIGAZIONE_PLURIENNALE_VOCE
	 **/
	public obbligazione_pluriennale_voceBulk(String cdCds, Long esercizio, Long esercizioOriginale, Long pgObbligazione, Long anno, String cdCentroResponsabilita, String cdLineaAttivita, String tiAppartenenza, String tiGestione, String cdVoce) {
		super(cdCds, esercizio, esercizioOriginale, pgObbligazione, anno, cdCentroResponsabilita, cdLineaAttivita, tiAppartenenza, tiGestione, cdVoce);
		setObbligazionePluriennale( new Obbligazione_pluriennaleBulk(cdCds,esercizio,esercizioOriginale,pgObbligazione,anno) );
		setElementoVoce( new Elemento_voceBulk(esercizio,tiAppartenenza,tiGestione,cdVoce) );
	}
	public Obbligazione_pluriennaleBulk getObbligazionePluriennale() {
		return obbligazionePluriennale;
	}
	public void setObbligazionePluriennale(Obbligazione_pluriennaleBulk obbligazionePluriennale)  {
		this.obbligazionePluriennale=obbligazionePluriennale;
	}
	public Elemento_voceBulk getElementoVoce() {
		return elementoVoce;
	}
	public void setElementoVoce(Elemento_voceBulk elementoVoce)  {
		this.elementoVoce=elementoVoce;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Cds dell'obbligazione]
	 **/
	public String getCdCds() {
		Obbligazione_pluriennaleBulk obbligazionePluriennale = this.getObbligazionePluriennale();
		if (obbligazionePluriennale == null)
			return null;
		return getObbligazionePluriennale().getCd_cds();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Cds dell'obbligazione]
	 **/
	public void setCdCds(String cdCds)  {
		this.getObbligazionePluriennale().setCd_cds(cdCds);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Esercizio dell'obbligazione]
	 **/
	public Long getEsercizio() {
		Obbligazione_pluriennaleBulk obbligazionePluriennale = this.getObbligazionePluriennale();
		if (obbligazionePluriennale == null)
			return null;
		return getObbligazionePluriennale().getEsercizio();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Esercizio dell'obbligazione]
	 **/
	public void setEsercizio(Long esercizio)  {
		this.getObbligazionePluriennale().setEsercizio(esercizio);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Esercizio Originale dell'obbligazione]
	 **/
	public Long getEsercizioOriginale() {
		Obbligazione_pluriennaleBulk obbligazionePluriennale = this.getObbligazionePluriennale();
		if (obbligazionePluriennale == null)
			return null;
		return getObbligazionePluriennale().getEsercizio_originale();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Esercizio Originale dell'obbligazione]
	 **/
	public void setEsercizioOriginale(Long esercizioOriginale)  {
		this.getObbligazionePluriennale().setEsercizio_originale(esercizioOriginale);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Numero dell'obbligazione]
	 **/
	public Long getPgObbligazione() {
		Obbligazione_pluriennaleBulk obbligazionePluriennale = this.getObbligazionePluriennale();
		if (obbligazionePluriennale == null)
			return null;
		return getObbligazionePluriennale().getPg_obbligazione();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Numero dell'obbligazione]
	 **/
	public void setPgObbligazione(Long pgObbligazione)  {
		this.getObbligazionePluriennale().setPg_obbligazione(pgObbligazione);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Anno Obbligazione Pluriennale]
	 **/
	public Long getAnno() {
		Obbligazione_pluriennaleBulk obbligazionePluriennale = this.getObbligazionePluriennale();
		if (obbligazionePluriennale == null)
			return null;
		return getObbligazionePluriennale().getAnno();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Anno Obbligazione Pluriennale]
	 **/
	public void setAnno(Long anno)  {
		this.getObbligazionePluriennale().setAnno(anno);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Esercizio dell'obbligazione]
	 **/
	public Long getEsercizio() {
		Elemento_voceBulk elementoVoce = this.getElementoVoce();
		if (elementoVoce == null)
			return null;
		return getElementoVoce().getEsercizio();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Esercizio dell'obbligazione]
	 **/
	public void setEsercizio(Long esercizio)  {
		this.getElementoVoce().setEsercizio(esercizio);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Definisce l'appartenenza (CNR o CdS) delle voci del piano finanziario di riferimento dell'obbligazione]
	 **/
	public String getTiAppartenenza() {
		Elemento_voceBulk elementoVoce = this.getElementoVoce();
		if (elementoVoce == null)
			return null;
		return getElementoVoce().getTi_appartenenza();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Definisce l'appartenenza (CNR o CdS) delle voci del piano finanziario di riferimento dell'obbligazione]
	 **/
	public void setTiAppartenenza(String tiAppartenenza)  {
		this.getElementoVoce().setTi_appartenenza(tiAppartenenza);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Definisce la sezione (entrata o spesa) delle voci del piano finanziario di riferimento dell'obbligazione]
	 **/
	public String getTiGestione() {
		Elemento_voceBulk elementoVoce = this.getElementoVoce();
		if (elementoVoce == null)
			return null;
		return getElementoVoce().getTi_gestione();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Definisce la sezione (entrata o spesa) delle voci del piano finanziario di riferimento dell'obbligazione]
	 **/
	public void setTiGestione(String tiGestione)  {
		this.getElementoVoce().setTi_gestione(tiGestione);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Codice del capitolo finanziario (mastrino VOCE_F) di riferimento dell'obbligazione]
	 **/
	public String getCdVoce() {
		Elemento_voceBulk elementoVoce = this.getElementoVoce();
		if (elementoVoce == null)
			return null;
		return getElementoVoce().getCd_elemento_voce();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Codice del capitolo finanziario (mastrino VOCE_F) di riferimento dell'obbligazione]
	 **/
	public void setCdVoce(String cdVoce)  {
		this.getElementoVoce().setCd_elemento_voce(cdVoce);
	}
}