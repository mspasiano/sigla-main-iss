<%@ page pageEncoding="UTF-8"
	import="it.cnr.jada.util.jsp.*,
		it.cnr.jada.action.*,
		it.cnr.jada.bulk.*,
		it.cnr.jada.util.action.*,
		it.cnr.contab.prevent01.bulk.*,
		it.cnr.contab.prevent01.bp.*"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<% JSPUtils.printBaseUrl(pageContext);%>
<%
   StampaMastroRendicontoFinanziarioBP bp = (StampaMastroRendicontoFinanziarioBP)BusinessProcess.getBusinessProcess(request);
   Stampa_pdgp_bilancioBulk model = (Stampa_pdgp_bilancioBulk)bp.getModel();
%>
<script language="JavaScript" src="scripts/util.js"></script>
<script language="javascript" src="scripts/css.js"></script>
<title><%=bp.getFormTitle()%></title>
</head>
<body class="Form">

<%	bp.openFormWindow(pageContext); %>

<table width=100%>
 <tr>
  <td>
	<div class="Group card p-2">
	<table>
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"esercizio"); %></td>
		<td><% bp.getController().writeFormInput(out,"esercizio"); %></td>
	  </tr>	
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_gestione"); %></td>
        <td><% bp.getController().writeFormInput(out,null,"ti_gestione",false,null,"onClick=\"submitForm('doOnTipoChange')\""); %></td>
	  </tr>  
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_stampa"); %></td>
        <td><% bp.getController().writeFormInput(out,null,"ti_stampa",false,null,"onClick=\"submitForm('doOnTipoBilancioChange')\""); %></td>
	  </tr>  
      <tr>
        <td><% bp.getController().writeFormLabel(out,"ti_aggregazione"); %></td>
        <td><% bp.getController().writeFormInput(out,null,"ti_aggregazione",model.isTipoGestioneEntrata(),null,"onClick=\"submitForm('doOnTipoAggregazioneChange')\""); %></td>
      </tr>
          <tr>
            <td><% bp.getController().writeFormLabel(out,"ti_origine"); %></td>
            <td><% bp.getController().writeFormInput(out,"ti_origine"); %></td>
          </tr>
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_livello"); %></td>
		<td><% bp.getController().writeFormInput(out,"ti_livello"); %></td>
	  </tr>
	  <tr>
		<td><% bp.getController().writeFormLabel(out,"ti_riepilogo"); %></td>
		<td><% bp.getController().writeFormInput(out,"ti_riepilogo"); %></td>
	  </tr>
	</table>
   </td>
  </tr>
</table>


<% bp.closeFormWindow(pageContext); %>

</body>
</html>