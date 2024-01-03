/*
 * Created by BulkGenerator 2.0 [07/12/2009]
 * Date 03/01/2024
 */
package it.cnr.contab.doccont00.core.bulk;

public class Accertamento_pluriennale_voceBulk extends Accertamento_pluriennale_voceBase {
	/**
	 * [ACCERTAMENTO_PLURIENNALE ]
	 **/
	private Accertamento_pluriennaleBulk accertamentoPluriennale =  new Accertamento_pluriennaleBulk();
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: ACCERTAMENTO_PLURIENNALE_VOCE
	 **/
	public Accertamento_pluriennale_voceBulk() {
		super();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: ACCERTAMENTO_PLURIENNALE_VOCE
	 **/
	public Accertamento_pluriennale_voceBulk(String cdCds, Integer esercizio, Integer esercizioOriginale, Long pgAccertamento, Integer anno, String cdCentroResponsabilita, String cdLineaAttivita) {
		super(cdCds, esercizio, esercizioOriginale, pgAccertamento, anno, cdCentroResponsabilita, cdLineaAttivita);
		setAccertamentoPluriennale( new Accertamento_pluriennaleBulk(cdCds,esercizio,esercizioOriginale,pgAccertamento,anno) );
	}
	public Accertamento_pluriennaleBulk getAccertamentoPluriennale() {
		return accertamentoPluriennale;
	}
	public void setAccertamentoPluriennale(Accertamento_pluriennaleBulk accertamentoPluriennale)  {
		this.accertamentoPluriennale=accertamentoPluriennale;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Cds dell'accertamento]
	 **/
	public String getCdCds() {
		Accertamento_pluriennaleBulk accertamentoPluriennale = this.getAccertamentoPluriennale();
		if (accertamentoPluriennale == null)
			return null;
		return getAccertamentoPluriennale().getCdCds();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Cds dell'accertamento]
	 **/
	public void setCdCds(String cdCds)  {
		this.getAccertamentoPluriennale().setCdCds(cdCds);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Esercizio dell'accertamento]
	 **/
	public Integer getEsercizio() {
		Accertamento_pluriennaleBulk accertamentoPluriennale = this.getAccertamentoPluriennale();
		if (accertamentoPluriennale == null)
			return null;
		return getAccertamentoPluriennale().getEsercizio();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Esercizio dell'accertamento]
	 **/
	public void setEsercizio(Integer esercizio)  {
		this.getAccertamentoPluriennale().setEsercizio(esercizio);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Esercizio Originale dell'accertamento]
	 **/
	public Integer getEsercizioOriginale() {
		Accertamento_pluriennaleBulk accertamentoPluriennale = this.getAccertamentoPluriennale();
		if (accertamentoPluriennale == null)
			return null;
		return getAccertamentoPluriennale().getEsercizioOriginale();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Esercizio Originale dell'accertamento]
	 **/
	public void setEsercizioOriginale(Integer esercizioOriginale)  {
		this.getAccertamentoPluriennale().setEsercizioOriginale(esercizioOriginale);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Numero dell'accertamento]
	 **/
	public Long getPgAccertamento() {
		Accertamento_pluriennaleBulk accertamentoPluriennale = this.getAccertamentoPluriennale();
		if (accertamentoPluriennale == null)
			return null;
		return getAccertamentoPluriennale().getPgAccertamento();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Numero dell'accertamento]
	 **/
	public void setPgAccertamento(Long pgAccertamento)  {
		this.getAccertamentoPluriennale().setPgAccertamento(pgAccertamento);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [Anno Accertamento Pluriennale]
	 **/
	public Integer getAnno() {
		Accertamento_pluriennaleBulk accertamentoPluriennale = this.getAccertamentoPluriennale();
		if (accertamentoPluriennale == null)
			return null;
		return getAccertamentoPluriennale().getAnno();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [Anno Accertamento Pluriennale]
	 **/
	public void setAnno(Integer anno)  {
		this.getAccertamentoPluriennale().setAnno(anno);
	}
}