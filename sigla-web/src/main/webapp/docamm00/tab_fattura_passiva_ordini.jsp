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
                    <% bp.getFatturaOrdiniController().writeFormField(out, "voceIva", Boolean.FALSE); %>
                </div>
                <div class="form-group col-md-3">
                    <% bp.getFatturaOrdiniController().writeFormField(out, "prezzoUnitarioRett", Boolean.FALSE); %>
                </div>
                <div class="form-group col-md-2">
                    <% bp.getFatturaOrdiniController().writeFormField(out, "sconto1Rett", Boolean.FALSE); %>
                </div>
                <div class="form-group col-md-2">
                    <% bp.getFatturaOrdiniController().writeFormField(out, "sconto2Rett", Boolean.FALSE); %>
                </div>
                <div class="form-group col-md-2">
                    <% bp.getFatturaOrdiniController().writeFormField(out, "sconto3Rett", Boolean.FALSE); %>
                </div>
            </div>
            <div class="form-row pt-2">
                <div class="form-group col-md-6">
                    <% bp.getFatturaOrdiniController().writeFormField(out, "imponibileErrato", Boolean.FALSE); %>
                </div>
                <div class="form-group col-md-6 h-100">
                    <% if (fatturaOrdine != null && fatturaOrdine.isRigaAttesaNotaCredito()) { %>
                        <% bp.getFatturaOrdiniController().writeFormField(out, "operazioneImpegnoNotaCredito", Boolean.FALSE); %>
                    <%}%>
                </div>
            </div>
        <% } %>
        </div>
    </div>
</div>