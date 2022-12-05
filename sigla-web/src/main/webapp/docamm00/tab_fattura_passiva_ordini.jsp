<%@ page pageEncoding="UTF-8"
	import="it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		java.util.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.docamm00.tabrif.bulk.*,
		it.cnr.contab.docamm00.docs.bulk.*,
		it.cnr.contab.doccont00.core.bulk.*,
		it.cnr.contab.ordmag.ordini.bulk.*,
		it.cnr.contab.docamm00.bp.*"
%>

<%	CRUDFatturaPassivaBP bp = (CRUDFatturaPassivaBP)BusinessProcess.getBusinessProcess(request);
	Fattura_passivaBulk fatturaPassiva = (Fattura_passivaBulk)bp.getModel();
 	FatturaOrdineBulk fatturaOrdine = (FatturaOrdineBulk)bp.getFatturaOrdiniController().getModel();
	String collapseIconClass = bp.getFatturaOrdiniController().isRettificheCollapse() ? "fa-chevron-circle-down" : "fa-chevron-circle-up";
%>

<% bp.getCrudDocEleAcquistoColl().writeHTMLTable(pageContext,"default",false,false,false,"100%","10vh"); %>
<% bp.getCrudDocEleAcquistoColl().closeHTMLTable(pageContext);%>
<div class="card">
    <fieldset class="fieldset mt-1 mb-1">
        <legend class="GroupLabel card-header text-primary p-0 pl-2">Righe di consegna selezionate</legend>
        <table width="100%">
            <tr>
                <td>
                    <% bp.getFatturaOrdiniController().writeHTMLTable(pageContext,"default",false,false,true,"100%","50vh"); %>
                </td>
            </tr>
        </table>
    </fieldset>
</div>
<div class="card border-primary">
    <div class="card-header">
        <h5 class="mb-0">
            <a onclick="submitForm('doToggle(ordiniRettifiche)')" class="text-primary"><i aria-hidden="true" class="fa <%=collapseIconClass%>"></i> Rettifiche</a>
        </h5>
    </div>
    <div class="card-block p-2">
        <div>
        <% if (!bp.getFatturaOrdiniController().isRettificheCollapse()) { %>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <% bp.getFatturaOrdiniController().writeFormLabel(out, "voceIva"); %>
                    <% bp.getFatturaOrdiniController().writeFormInput(out, "voceIva"); %>
                </div>
                <div class="form-group col-md-3">
                    <% bp.getFatturaOrdiniController().writeFormLabel(out, "prezzoUnitarioRett"); %>
                    <% bp.getFatturaOrdiniController().writeFormInput(out, "prezzoUnitarioRett"); %>
                </div>
                <div class="form-group col-md-2">
                    <% bp.getFatturaOrdiniController().writeFormLabel(out, "sconto1Rett"); %>
                    <% bp.getFatturaOrdiniController().writeFormInput(out, "sconto1Rett"); %>
                </div>
                <div class="form-group col-md-2">
                    <% bp.getFatturaOrdiniController().writeFormLabel(out, "sconto2Rett"); %>
                    <% bp.getFatturaOrdiniController().writeFormInput(out, "sconto2Rett"); %>
                </div>
                <div class="form-group col-md-2">
                    <% bp.getFatturaOrdiniController().writeFormLabel(out, "sconto3Rett"); %>
                    <% bp.getFatturaOrdiniController().writeFormInput(out, "sconto3Rett"); %>
                </div>
            </div>
            <div class="form-row pt-2">
                <div class="form-group col-md-6">
                    <% bp.getFatturaOrdiniController().writeFormLabel(out, "imponibileErrato"); %>
                    <% bp.getFatturaOrdiniController().writeFormInput(out, "imponibileErrato"); %>
                </div>
                <div class="form-group col-md-6 h-100">
                    <% if (fatturaOrdine.isRigaAttesaNotaCredito()) { %>
                        <% bp.getFatturaOrdiniController().writeFormLabel(out, "operazioneImpegnoNotaCredito"); %>
                        <% bp.getFatturaOrdiniController().writeFormInput(out, "operazioneImpegnoNotaCredito"); %>
                    <%}%>
                </div>
            </div>
        <% } %>
        </div>
    </div>
</div>