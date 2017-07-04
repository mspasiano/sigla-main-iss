<%@ page 
	import = "it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		java.util.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.ordmag.ordini.bp.CRUDOrdineAcqBP,
		it.cnr.contab.ordmag.ordini.bulk.OrdineAcqBulk,
		it.cnr.contab.ordmag.anag00.*"
%>

<% CRUDBP bp = (CRUDOrdineAcqBP)BusinessProcess.getBusinessProcess(request);
OrdineAcqBulk ordine = (OrdineAcqBulk)bp.getModel();
%>

<div class="Group">
	<table>
		<tr>
			<%
				bp.getController().writeFormField(out, "findUnitaOperativaOrd");
			%>
		</tr>
	</table>
	<table>
		<tr>
			<%
				bp.getController().writeFormField(out, "findNumerazioneOrd");
			%>
		</tr>
	</table>
	<table>
		<tr>
			<%
				bp.getController().writeFormField(out, "esercizio");
			%>
			<%
				bp.getController().writeFormField(out, "cdNumeratore");
			%>
			<%
				bp.getController().writeFormField(out, "numero");
			%>
			<%
				bp.getController().writeFormField(out, "dataOrdine");
			%>
			<% if (!bp.isSearching()) {
				 bp.getController().writeFormField(out, "stato");
			   } else {
			     bp.getController().writeFormField(out, "statoKeysForSearch");
			   } %>
		</tr>
	</table>
    <table>
      <tr>      	
			<td>
				<% bp.getController().writeFormLabel(out,"dsRichiesta");%>
			</td>      	
			<td colspan="4">
				<% bp.getController().writeFormInput(out,"dsRichiesta");%>
			</td>
      </tr>
      <tr>      	
			<td>
				<% bp.getController().writeFormLabel(out,"nota");%>
			</td>      	
			<td colspan="4">
				<% bp.getController().writeFormInput(out,"nota");%>
			</td>
      </tr>
	</table>
    <table>
		<tr>
			<%
			bp.getController().writeFormField(out, "findUnitaOperativaOrdDest");
			%>
		</tr>
	</table>
</div>
