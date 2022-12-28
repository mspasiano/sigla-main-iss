<%@page import="it.cnr.contab.ordmag.ordini.bp.CRUDOrdineAcqBP"%>
<%@ page pageEncoding="UTF-8"
	import="it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		java.util.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.ordmag.anag00.*,
		it.cnr.contab.ordmag.ordini.bulk.OrdineAcqBulk,
		it.cnr.contab.ordmag.ordini.*"
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<% JSPUtils.printBaseUrl(pageContext); %>
<script language="JavaScript" src="scripts/util.js"></script>
<script language="javascript" src="scripts/css.js"></script>
<%
    CRUDBP bp = (CRUDOrdineAcqBP)BusinessProcess.getBusinessProcess(request);
    OrdineAcqBulk ordine = (OrdineAcqBulk)bp.getModel();
%>
<script language="JavaScript">
function doStampaOrdine() {
	doPrint('<%=JSPUtils.getAppRoot(request)%>genericdownload/stampaOrdineAcq.html?methodName=stampaOrdine&it.cnr.jada.action.BusinessProcess=<%=bp.getPath()%>', 
			'Ordine di Acquisto', 'toolbar=no, location=no, directories=no, status=no, menubar=no,resizable,scrollbars,width=800,height=600').focus() ;
}
</script>
<title>Ordine d'Acquisto</title>
</head>
<body class="Form">
<%
    boolean isNumeroPresent = Optional.ofNullable(ordine).map(o -> Optional.ofNullable(o.getDataOrdineDef()).isPresent()).orElse(Boolean.FALSE);
    String statocss = isNumeroPresent ? "col-md-4" : "col-md-6";
    bp.openFormWindow(pageContext);
%>
	<div class="Group card p-2 mb-2">
        <div class="form-row">
            <div class="col-md-1"><% bp.getController().writeFormField(out, "esercizio", Boolean.FALSE);%></div>
            <div class="col-md-1"><% bp.getController().writeFormField(out, "numero", Boolean.FALSE);%></div>
            <div class="col-md-2"><% bp.getController().writeFormField(out, "dataOrdine", Boolean.FALSE);%></div>
            <div class="col-md-2"><% bp.getController().writeFormField(out, "percProrata", Boolean.FALSE);%></div>
            <div class="<%=statocss%> h-100">
                <%
				   if (bp.isInserting()) {
					 bp.getController().writeFormField(out, "stato", Boolean.FALSE);
				   } else if (bp.isSearching()) {
				     bp.getController().writeFormField(out, "statoForSearch", Boolean.FALSE);
				   } else {
				     bp.getController().writeFormField(out, "statoForUpdate", Boolean.FALSE);
				   }
				%>
            </div>
            <div class="col-md-2">
                <%
                    if (isNumeroPresent) {
                        bp.getController().writeFormField(out, "dataOrdineDef", Boolean.FALSE);
                    }
                %>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-3"><% bp.getController().writeFormField(out, "imImponibile", Boolean.FALSE);%></div>
            <div class="col-md-3"><% bp.getController().writeFormField(out, "imIva", Boolean.FALSE);%></div>
            <div class="col-md-3"><% bp.getController().writeFormField(out, "imIvaD", Boolean.FALSE);%></div>
            <div class="col-md-3"><% bp.getController().writeFormField(out, "imTotaleOrdine", Boolean.FALSE);%></div>
        </div>
	</div>
	<%
	JSPUtils.tabbed(
				pageContext,
				"tab",
				new String[][] {
				{ "tabOrdineAcq","Ordine d'Acquisto","/ordmag/ordini/tab_ordine_acq.jsp" },
				{ "tabOrdineFornitore","Fornitore","/ordmag/ordini/tab_ordine_fornitore.jsp" },
				{ "tabOrdineAcqDettaglio","Dettaglio","/ordmag/ordini/tab_ordine_acq_dettagli.jsp" },
				{ "tabOrdineAcqObbligazioni","Obbligazioni Collegate","/ordmag/ordini/tab_ordine_acq_obbligazioni.jsp" },
				{ "tabAllegati","Allegati","/ordmag/ordini/tab_ordine_acq_allegati.jsp" }},
				bp.getTab("tab"),
				"center",
				"100%",
				null );
	%>	
<%	bp.closeFormWindow(pageContext); %>
</body>
</html>