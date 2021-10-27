<%@ page pageEncoding="UTF-8"
	import="it.cnr.jada.util.jsp.*,
	    it.cnr.jada.action.*,
		it.cnr.jada.bulk.*,
		java.util.*,
		it.cnr.jada.util.action.*,
		it.cnr.jada.util.jsp.*,
		it.cnr.contab.incarichi00.bp.*,
		it.cnr.contab.incarichi00.bulk.*,
        it.cnr.contab.docamm00.bp.*,
        it.cnr.contab.anagraf00.tabrif.bulk.*"
%>

<%

		CRUDAnagraficaDottoratiBP bp = (CRUDAnagraficaDottoratiBP)BusinessProcess.getBusinessProcess(request);
        AnagraficaDottoratiBulk anagraficaDottorati = (AnagraficaDottoratiBulk)bp.getModel();
%>

<html>

<head>
<% JSPUtils.printBaseUrl(pageContext); %>
<script language="javascript" src="scripts/css.js"></script>
<script language="JavaScript" src="scripts/util.js"></script>
<title><%=bp.getBulkInfo().getShortDescription()%></title>
</head>

<body class="Form">
    <div class="card p-2 w-100">
        <table class="Panel w-100">
        <tr><td>
<div class="Group card p-2 mt-2" style="width:100%">
    <fieldset>
        <legend style="color:blue">Identificativo</legend>
    	<table>
    		<tr>
    			<td><% bp.getController().writeFormLabel(out, "id"); %>	</td>
    			<td><% bp.getController().writeFormInput(out,"id");%></td>
    			<td><% bp.getController().writeFormLabel(out, "esercizio"); %>	</td>
                <td colspan="3"><% bp.getController().writeFormInput(out,"esercizio");%></td>
            </tr>
    		<tr>
                <td><% bp.getController().writeFormLabel(out,"cdCds"); %></td>
                <td><% bp.getController().writeFormInput(out,"cdCds"); %></td>
                <td><% bp.getController().writeFormLabel(out,"cdUnitaOrganizzativa"); %></td>
                <td><% bp.getController().writeFormInput(out,"cdUnitaOrganizzativa"); %></td>
            </tr>
            <tr>
                <td><% bp.getController().writeFormLabel(out,"find_PhdtipoDottorati"); %></td>
                <td colspan="3"><% bp.getController().writeFormInput(out,"find_PhdtipoDottorati"); %></td>
            </tr>
    	</table>
    </fieldset>
    <fieldset>
        <legend style="color:blue">Universit&aacute;</legend>
        <table>
            <tr>
    			<td><% bp.getController().writeFormLabel(out, "cdTerzo"); %></td>
    			<td><% bp.getController().writeFormInput(out, "cdTerzo"); %></td>
    		</tr>
            <tr>
            	<td><% bp.getController().writeFormLabel(out, "regione");%></td>
            	<td><% bp.getController().writeFormInput(out, "regione");%></td>
            </tr>
    	    <tr>
    			<td><% bp.getController().writeFormLabel(out, "universitaCapofila");%></td>
    			<td><% bp.getController().writeFormInput(out, "universitaCapofila");%></td>
    		</tr>
    		<tr>
                <td><% bp.getController().writeFormLabel(out, "modalita_pagamento"); %>
                <% bp.getController().writeFormInput(out,null,"modalita_pagamento",false,null,"onChange=\"submitForm('doOnModalitaPagamentoChange')\"");%>
                </td>
            </tr>
<tr>
       		<td>
       			<% 	if (anagraficaDottorati.getBanca() != null) {
       					bp.getController().writeFormInput(out, null, "listaBanche", false, null, "");
       				} %>
          		</td>
             </tr>
<tr>
           		  	<td colspan="2">
           		<%	if (anagraficaDottorati.getBanca() != null) {
           				if (Rif_modalita_pagamentoBulk.BANCARIO.equalsIgnoreCase(anagraficaDottorati.getBanca().getTi_pagamento())) {
           			 	     	bp.getController().writeFormInput(out,"contoB");
           				} else if (Rif_modalita_pagamentoBulk.POSTALE.equalsIgnoreCase(anagraficaDottorati.getBanca().getTi_pagamento())) {
           			 	     	bp.getController().writeFormInput(out,"contoP");
           				} else if (Rif_modalita_pagamentoBulk.QUIETANZA.equalsIgnoreCase(anagraficaDottorati.getBanca().getTi_pagamento())) {
           			 	     	bp.getController().writeFormInput(out,"contoQ");
           				} else if (Rif_modalita_pagamentoBulk.ALTRO.equalsIgnoreCase(anagraficaDottorati.getBanca().getTi_pagamento())) {
           			 	     	bp.getController().writeFormInput(out,"contoA");
           				} else if (Rif_modalita_pagamentoBulk.IBAN.equalsIgnoreCase(anagraficaDottorati.getBanca().getTi_pagamento())) {
           		 	     	bp.getController().writeFormInput(out,"contoN");
           				} else if (Rif_modalita_pagamentoBulk.BANCA_ITALIA.equalsIgnoreCase(anagraficaDottorati.getBanca().getTi_pagamento()) && anagraficaDottorati.getBanca().isTABB()) {
                               bp.getController().writeFormInput(out,"contoB");
                           }
           			}  %>
           			<td>
           		<tr>

    		<tr>
    			<td><% bp.getController().writeFormLabel(out, "pgBanca"); %></td>
    			<td><% bp.getController().writeFormInput(out, "pgBanca"); %></td>
    		</tr>
            <tr>
    			<td><% bp.getController().writeFormLabel(out, "telefonoAteneo"); %></td>
    		    <td><% bp.getController().writeFormInput(out, "telefonoAteneo"); %></td>
    		</tr>
    		<tr>
    		    <td><% bp.getController().writeFormLabel(out, "emailAteneo"); %></td>
                <td><% bp.getController().writeFormInput(out, "emailAteneo"); %></td>
    		</tr>
    		<tr>
    			<td><% bp.getController().writeFormLabel(out, "altreUniversita"); %></td>
                <td><% bp.getController().writeFormInput(out, "altreUniversita"); %></td>
    		</tr>
    		<!--<tr>
                <td><% bp.getController().writeFormLabel(out, "find_TipocorsoDottorati"); %></td>
                <td><% bp.getController().writeFormInput(out, "find_TipocorsoDottorati"); %></td>
            </tr>-->
    	</table>
    	</fieldset>
        <fieldset>
            <legend style="color:blue">Dati Azienda</legend>
            <table>
                <tr>
    			    <td><% bp.getController().writeFormLabel(out, "azienda"); %></td>
                    <td><% bp.getController().writeFormInput(out, "azienda"); %></td>
    			</tr>
                <tr>
    			    <td><% bp.getController().writeFormLabel(out, "aziendaSettore"); %></td>
                    <td><% bp.getController().writeFormInput(out, "aziendaSettore"); %></td>
    			</tr>
                <tr>
    			    <td><% bp.getController().writeFormLabel(out, "aziendaSede"); %></td>
                    <td><% bp.getController().writeFormInput(out, "aziendaSede"); %></td>
    			</tr>
    		</table>
        </fieldset>
        <fieldset>
            <legend style="color:blue">Dati Infrastruttura Europea</legend>
            <table>
                <tr>
    			    <td><% bp.getController().writeFormLabel(out, "nomeInfraEuropea"); %></td>
                    <td><% bp.getController().writeFormInput(out, "nomeInfraEuropea"); %></td>
    			</tr>
                <tr>
    			    <td><% bp.getController().writeFormLabel(out, "descrInfraEuropea"); %></td>
                    <td><% bp.getController().writeFormInput(out, "descrInfraEuropea"); %></td>
    			</tr>
    		</table>
    	</fieldset>
    	<fieldset>
            <legend style="color:blue">Dati Dottorato</legend>
            <table>
                 <tr>
                     <td><% bp.getController().writeFormLabel(out, "find_CicloDottorati"); %></td>
                     <td colspan="3"><% bp.getController().writeFormInput(out, "find_CicloDottorati"); %></td>
                 </tr>
                 <tr>
                     <td><% bp.getController().writeFormLabel(out, "importoTotaleBorsa"); %></td>
                     <td colspan="3"><% bp.getController().writeFormInput(out, "importoTotaleBorsa"); %> &euro;</td>
                 </tr>
                 <tr>
                     <td><% bp.getController().writeFormLabel(out, "dtRegistrazione"); %></td>
                     <td><% bp.getController().writeFormInput(out, "dtRegistrazione"); %></td>
                     <td><% bp.getController().writeFormLabel(out, "nRateDottorato"); %></td>
                     <td><% bp.getController().writeFormInput(out, "nRateDottorato"); %></td>
                 </tr>
                 <tr>
                     <td><% bp.getController().writeFormLabel(out, "dtInizioDottorato"); %></td>
                     <td><% bp.getController().writeFormInput(out, "dtInizioDottorato"); %></td>
                     <td><% bp.getController().writeFormLabel(out, "dtFineDottorato"); %></td>
                     <td><% bp.getController().writeFormInput(out, "dtFineDottorato"); %></td>
                 </tr>
                 <tr>
                 <td colspan="4">
        <fieldset>
            <legend style="color:blue">Sospensione Dottorato</legend>
            <table>
                 <tr>
                     <td><% bp.getController().writeFormLabel(out, "dtSospensione"); %></td>
                     <td><% bp.getController().writeFormInput(out, "dtSospensione"); %></td>
                     <td><% bp.getController().writeFormLabel(out, "dtRiattivazione"); %></td>
                     <td><% bp.getController().writeFormInput(out, "dtRiattivazione"); %></td>
                 </tr>
                 <tr>
                     <td><% bp.getController().writeFormLabel(out, "dtProrogaDottorato"); %></td>
                     <td colspan="3"><% bp.getController().writeFormInput(out, "dtProrogaDottorato"); %></td>
                 </tr>
            <!--<tr>
                     <td><% bp.getController().writeFormLabel(out, "numeroBorseFinanziate"); %></td>
                     <td><% bp.getController().writeFormInput(out, "numeroBorseFinanziate"); %></td>
                 </tr>
    			 <tr>
    			     <td><% bp.getController().writeFormLabel(out, "numeroCicliFinanziati"); %></td>
                     <td><% bp.getController().writeFormInput(out, "numeroCicliFinanziati"); %></td>
    			 </tr>
    			 <tr>
                     <td><% bp.getController().writeFormLabel(out, "totaleBorseFinanziate"); %></td>
                     <td><% bp.getController().writeFormInput(out, "totaleBorseFinanziate"); %></td>
                 </tr>-->
            </table>
        </fieldset>
        </td></tr>
            </table>
        </fieldset>
        <fieldset>
        <legend style="color:blue">Dati Corso</legend>
    		<table>
                <tr>
    		        <td><% bp.getController().writeFormLabel(out, "dipartimentoUniversita"); %></td>
                    <td><% bp.getController().writeFormInput(out, "dipartimentoUniversita"); %></td>
    		    </tr>
    			<tr>
    			    <td><% bp.getController().writeFormLabel(out, "corsoDottorato"); %></td>
                    <td><% bp.getController().writeFormInput(out, "corsoDottorato"); %></td>
    			</tr>
    			<tr>
    			    <td><% bp.getController().writeFormLabel(out, "tematica"); %></td>
                    <td><% bp.getController().writeFormInput(out, "tematica"); %></td>
    			</tr>
    		</table>
    	</fieldset>
        <fieldset>
        <legend style="color:blue">Dati CNR e Data</legend>
            <table>
    			<tr>
    			    <td><% bp.getController().writeFormLabel(out, "ricercatore"); %></td>
                    <td><% bp.getController().writeFormInput(out, "ricercatore"); %></td>
    			</tr>
    			<tr>
    			    <td><% bp.getController().writeFormLabel(out, "dipartimentoCnr"); %></td>
                    <td><% bp.getController().writeFormInput(out, "dipartimentoCnr"); %></td>
    			</tr>
    			<tr>
    			    <td><% bp.getController().writeFormLabel(out, "istitutoCnr"); %></td>
                    <td><% bp.getController().writeFormInput(out, "istitutoCnr"); %></td>
    			</tr>
                <tr>
    			    <td><% bp.getController().writeFormLabel(out, "uoCnr"); %></td>
                    <td><% bp.getController().writeFormInput(out, "uoCnr"); %></td>
    			</tr>
    			<tr>
    			    <td colspan="2">
    			    <fieldset>
                        <legend style="color:blue">Cofinanziamento CNR</legend>
    			        <table>
                        <tr>
    			            <td><% bp.getController().writeFormLabel(out, "cofinanziamentoEffettuato"); %></td>
                            <td><% bp.getController().writeFormInput(out, "cofinanziamentoEffettuato"); %></td>
    			        </tr>
                        <tr>
    			            <td><% bp.getController().writeFormLabel(out, "cofinanziamentoCnr"); %></td>
                            <td><% bp.getController().writeFormInput(out, "cofinanziamentoCnr"); %> &euro;</td>
    			        </tr>
    			        </table>
    			    </fieldset>
    			    </td>
    			</tr>
    			<tr>
    			    <td><% bp.getController().writeFormLabel(out, "dataStipulaConvenzione"); %></td>
                    <td><% bp.getController().writeFormInput(out, "dataStipulaConvenzione"); %></td>
    			</tr>
    			<tr>
                    <td><% bp.getController().writeFormLabel(out, "nomeDottorato"); %></td>
                    <td><% bp.getController().writeFormInput(out, "nomeDottorato"); %></td>
                </tr>
    			<tr>
    			    <td><% bp.getController().writeFormLabel(out, "note"); %></td>
                    <td><% bp.getController().writeFormInput(out, "note"); %></td>
    			</tr>
    		</table>
        </fieldset>
    </td>
    </tr>
    </table>
</div>
    <% bp.closeFormWindow(pageContext); %>
</body>

