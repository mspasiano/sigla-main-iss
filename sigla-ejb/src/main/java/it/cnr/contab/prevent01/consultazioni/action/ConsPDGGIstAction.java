/*
 * Created on Nov 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.cnr.contab.prevent01.consultazioni.action;

import it.cnr.contab.prevent01.consultazioni.bp.ConsPDGGAreaSpeBP;
import it.cnr.contab.prevent01.consultazioni.bp.ConsPDGGIstBP;
import it.cnr.contab.prevent01.consultazioni.bp.ConsPDGGIstEtrBP;
import it.cnr.contab.prevent01.consultazioni.bp.ConsPDGGIstSpeBP;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.action.Forward;
import it.cnr.jada.util.action.ConsultazioniAction;


public class ConsPDGGIstAction extends ConsultazioniAction {
	private static final long serialVersionUID = 1L;

	public Forward doConsulta(ActionContext context, String livelloDestinazione) {
		try {
			ConsPDGGIstBP bp = (ConsPDGGIstBP)context.getBusinessProcess();
			bp.setSelection(context);
			long selectElements = bp.getSelection().size();

			if (selectElements == 0)
				selectElements = Integer.valueOf(bp.getSelection().getFocus()).compareTo(-1);
			
			if (selectElements == 0) {
				bp.setMessage("Non � stata selezionata nessuna riga.");
				return context.findDefaultForward();
			}

			ConsPDGGIstBP consultazioneBP = null;
			context.closeBusinessProcess(bp);			
			if (bp instanceof ConsPDGGIstSpeBP) 
				consultazioneBP = (ConsPDGGIstSpeBP)context.createBusinessProcess("ConsPDGGIstSpeBP");
			else
				consultazioneBP = (ConsPDGGIstEtrBP)context.createBusinessProcess("ConsPDGGIstEtrBP");
			
			consultazioneBP.initVariabili(context, bp.getPathConsultazione(), livelloDestinazione);
			if ((bp.getElementsCount()!=selectElements)||!(bp.getBaseclause().toString().equals(consultazioneBP.getBaseclause().toString()))||bp.getFindclause()!=null)
				consultazioneBP.addToBaseclause(bp.getSelezione(context));
			if (consultazioneBP instanceof ConsPDGGIstSpeBP)
				consultazioneBP.setIterator(context,consultazioneBP.createPdggAreaComponentSession().findConsultazioneSpe(context.getUserContext(),bp.getPathDestinazione(livelloDestinazione),livelloDestinazione,consultazioneBP.getBaseclause(),null));			
			else
				consultazioneBP.setIterator(context,consultazioneBP.createPdggAreaComponentSession().findConsultazioneEtr(context.getUserContext(),bp.getPathDestinazione(livelloDestinazione),livelloDestinazione,consultazioneBP.getBaseclause(),null));			
			
			return context.addBusinessProcess(consultazioneBP);
		} catch(Throwable e) {
			return handleException(context,e);
		}
	}

	public Forward doConsultaCdr(ActionContext context) {
		return doConsulta(context, ConsPDGGIstBP.LIVELLO_CDR);
	}
	public Forward doConsultaModulo(ActionContext context) {
		return doConsulta(context, ConsPDGGIstBP.LIVELLO_MOD);
	}
	public Forward doConsultaLivello1(ActionContext context) {
		return doConsulta(context, ConsPDGGIstBP.LIVELLO_LIV1);
	}
	public Forward doConsultaLivello2(ActionContext context) {
		return doConsulta(context, ConsPDGGIstBP.LIVELLO_LIV2);
	}
	public Forward doConsultaLivello3(ActionContext context) {
			return doConsulta(context, ConsPDGGIstBP.LIVELLO_LIV3);
		}
	public Forward doConsultaLinea(ActionContext context) {
		return doConsulta(context, ConsPDGGAreaSpeBP.LIVELLO_LIN);
	}
	public Forward doConsultaVoce(ActionContext context) {
		return doConsulta(context, ConsPDGGAreaSpeBP.LIVELLO_VOC);
	}
	public Forward doConsultaDettagli(ActionContext context) {
		return doConsulta(context, ConsPDGGIstBP.LIVELLO_DET);
	}
	public Forward doCloseForm(ActionContext context) throws BusinessProcessException {
		Forward appoForward = super.doCloseForm(context);
		if (context.getBusinessProcess() instanceof ConsPDGGIstBP) {
			ConsPDGGIstBP bp = (ConsPDGGIstBP)context.getBusinessProcess();
			bp.setTitle();
		}
		return appoForward;
	}
}