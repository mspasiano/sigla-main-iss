<%@ page pageEncoding="UTF-8"
	import = "it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		java.util.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.ordmag.ordini.bp.CRUDOrdineAcqBP,
		it.cnr.contab.ordmag.ordini.bulk.OrdineAcqBulk,
		it.cnr.contab.ordmag.anag00.*"
%>

<% CRUDOrdineAcqBP bp = (CRUDOrdineAcqBP)BusinessProcess.getBusinessProcess(request);
OrdineAcqBulk ordine = (OrdineAcqBulk)bp.getModel();
%>
<div class="Group card p-2 m-1">
	<table class="w-100" cellpadding="2">
		<tr>
		    <td><% bp.getController().writeFormLabel(out, "findUnitaOperativaOrd");%>
		    <td colspan="3"><% bp.getController().writeFormInput(out, "findUnitaOperativaOrd");%>
		</tr>
		<tr>
		    <td><% bp.getController().writeFormLabel(out, "findNumerazioneOrd");%>
		    <td colspan="3"><% bp.getController().writeFormInput(out, "findNumerazioneOrd");%>
		</tr>
		<tr>
			<% bp.getController().writeFormField(out, "findDivisa");%>
			<% bp.getController().writeFormField(out, "cambio");%>
		</tr>
		<tr>
		    <td><% bp.getController().writeFormLabel(out, "findTipoOrdine");%>
		    <td colspan="3"><% bp.getController().writeFormInput(out, "findTipoOrdine");%>
		</tr>
	</table>
</div>
<div class="h3 text-primary p-2 m-1">Dati del Contratto</div>
<div class="Group card p-2 m-1">		
	<table class="w-100" cellpadding="2">
		    <tr><% bp.getController().writeFormField(out, "find_contratto");%></tr>
			<tr><% bp.getController().writeFormField(out, "figura_giuridica_interna");%></tr>
			<tr><% bp.getController().writeFormField(out, "findFirmatario");%></tr>
			<tr><% bp.getController().writeFormField(out, "findResponsabile");%></tr>
			<tr><% bp.getController().writeFormField(out, "findDirettore");%></tr>
			<tr><% bp.getController().writeFormField(out, "cig");%></tr>
			<% if (bp.isVisibleMotivoAssenzaCig()) { %>
			    <tr><% bp.getController().writeFormField(out, "motivoAssenzaCig");%></tr>
			<% } %>
			<tr><% bp.getController().writeFormField(out, "procedura_amministrativa");%></tr>
			<tr><% bp.getController().writeFormField(out, "cup");%></tr>
			<tr><% bp.getController().writeFormField(out, "referenteEsterno");%></tr>
			<tr><% bp.getController().writeFormField(out, "fl_mepa");%></tr>
	</table>
</div>

<div class="Group card p-2 m-1">
	<table class="w-100" cellpadding="2">
		<tr><% bp.getController().writeFormField(out, "nota");%></tr>
		<tr><% bp.getController().writeFormField(out, "findNotaPrecodificata");%></tr>
	</table>
</div>
