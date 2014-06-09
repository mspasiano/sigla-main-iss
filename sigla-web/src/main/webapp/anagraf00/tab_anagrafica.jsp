<!-- 
 ?ResourceName ""
 ?ResourceTimestamp ""
 ?ResourceEdition ""
-->

<%@ page 
	import="it.cnr.jada.action.*,
		it.cnr.jada.UserContext,
		it.cnr.contab.anagraf00.bp.*,
		it.cnr.contab.anagraf00.core.bulk.*"
%>
<%
	CRUDAnagraficaBP bp = (CRUDAnagraficaBP)BusinessProcess.getBusinessProcess(request);
	UserContext uc = HttpActionContext.getUserContext(session);
	AnagraficoBulk anagrafico = (AnagraficoBulk)bp.getModel();
%>
<fieldset class="fieldset">
<legend class="GroupLabel">Tipologia</legend>
	<table>
		<tr>
			<td><%bp.getController().writeFormInput(out,"default","ti_entita",false,"FormInput","onclick=\"submitForm('doCambiaTi_entita')\"");%></td>
			 	<td>
					<% if (anagrafico.getDt_fine_rapporto()!=null) { %>
						<span class="FormLabel" style="color:red">
							DISABILITATA
						</span>
					<%	} %> 
					<% if (anagrafico.isDipendente()) { %>
					<span class="FormLabel" style="color:red">
							DIPENDENTE
						</span>
					<%	} %>	
			  	</td>
		</tr>
	<%if (anagrafico.isPersonaFisica() || anagrafico.isPersonaGiuridica() || anagrafico.isDiversi()) {%>
		<tr>
			<td><%bp.getController().writeFormInput(out,null,"ti_italiano_estero_anag",!bp.isInserting() && !bp.isItalianoEsteroModificabile(uc,anagrafico),null,"onclick=\"submitForm('doCambiaTi_italiano_estero')\"");%></td>
		</tr>
	<%} %>
	<%if (anagrafico.isPersonaFisica() || anagrafico.isDiversi()) {%>
		<tr>
			<td><%bp.getController().writeFormInput(out,"default","ti_entita_fisica",false,"FormInput","onclick=\"submitForm('doCambiaTi_entita')\"");%></td>
		</tr>
			<%} else if (anagrafico.isPersonaGiuridica()) {%>
		<tr>
			<td><%bp.getController().writeFormInput(out,"default","ti_entita_giuridica",false,"FormInput","onclick=\"submitForm('doCambiaTi_entita')\"");%></td>
		</tr>
	<%} %>
<%if (!anagrafico.isStrutturaCNR()) { %>
		<tr>
			<td>
				<% bp.getController().writeFormInput(out,"fl_soggetto_iva");%>
				<% bp.getController().writeFormLabel(out,"fl_soggetto_iva");%>

		<%if (anagrafico.isEntePubblico()) { %>
				<% bp.getController().writeFormInput(out,"fl_fatturazione_differita");%>
				<% bp.getController().writeFormLabel(out,"fl_fatturazione_differita");%>
		<%} %>
		<%if (anagrafico.isPersonaGiuridica()) { %>
				<% bp.getController().writeFormInput(out,"fl_studio_associato");%>
				<% bp.getController().writeFormLabel(out,"fl_studio_associato");%>
		<%} %>
			</td>
		</tr>
		<tr>	
			<td colspan="2">
				<%bp.getController().writeFormInput(out,null,"fl_sospensione_irpef",!bp.isAbilitatoSospensioneCori(),null,"");%>		
				<% bp.getController().writeFormLabel(out,"fl_sospensione_irpef");%>
			</td>	
		</tr>
		<tr>	
			<td colspan="2">		
				<% bp.getController().writeFormInput(out,"default","fl_cervellone",false,"FormInput","onclick=\"submitForm('doCambiaFl_cervellone')\"");%>
				<% bp.getController().writeFormLabel(out,"fl_cervellone");%>
			</td>	
		</tr>	
		<%if (anagrafico.isFl_cervellone().booleanValue()) { %>	
		</table>
		<table>		
		<tr>
			<td><% bp.getController().writeFormLabel(out,"dt_inizio_res_italia");%></td>
			<td><% bp.getController().writeFormInput(out,null,"dt_inizio_res_italia",false,null,"");%></td>
			<td><% bp.getController().writeFormLabel(out,"dt_fine_res_italia");%></td>
			<td><% bp.getController().writeFormInput(out,null,"dt_fine_res_italia",false,null,"");%></td>
		</tr>
		<tr>	
			<td><% bp.getController().writeFormLabel(out,"anno_inizio_res_fis");%></td>
			<td><% bp.getController().writeFormInput(out,"anno_inizio_res_fis");%></td>
			<td><% bp.getController().writeFormLabel(out,"anno_fine_agevolazioni");%></td>
			<td><% bp.getController().writeFormInput(out,"anno_fine_agevolazioni");%></td>
		</tr>
		<%} %>	
<%} %>

		<tr>
			<td colspan="2"><% bp.getController().writeFormLabel(out,"pg_tipologia_istat");%>
							<% bp.getController().writeFormInput(out,"pg_tipologia_istat");%>
							<% bp.getController().writeFormInput(out,"ds_tipologia_istat");%></td>
			<td><% bp.getController().writeFormInput(out,null,"find_tipologia_istat",(anagrafico!=null?!bp.isGestoreOk(uc):false),null,null);%></td>
		</tr>
	</table>
	<%if (!anagrafico.isPersonaFisica()) {%>
	<table>
		<%	if(bp.isGestoreIstat(uc, anagrafico)){ %>
			<tr>
				<% bp.writeFormField(out,"codiceAmministrazioneIpa");%>
			</tr>
			<tr>
				<% bp.writeFormField(out,"dataAvvioFattElettr");%>
			</tr>			
		<%} else { %> 
			<tr>
				<td><%bp.writeFormLabel(out,"default","codiceAmministrazioneIpa"); %></td>
				<td><% bp.writeFormInput(out,"default","codiceAmministrazioneIpa",true,null,null);%></td>
			</tr>
			<tr>
				<td><%bp.writeFormLabel(out,"default","dataAvvioFattElettr"); %></td>
				<td><% bp.writeFormInput(out,"default","dataAvvioFattElettr",true,null,null);%></td>
			</tr>				
		<%} %>
	</table>
	<%} %>
</fieldset>

<fieldset class="fieldset">
	<legend class="GroupLabel">Informazioni anagrafiche</legend>
	<table>
		<tr>
	<%if (anagrafico.isPersonaFisica() || anagrafico.isDiversi()) { %>
			<% bp.getController().writeFormField(out,"cognome");%>
			<td colspan="2"><%bp.getController().writeFormLabel(out,"classificazione_anag");%></td>
		</tr>
		<tr>
			<% bp.getController().writeFormField(out,"nome");%>
			<td colspan="2"><%bp.getController().writeFormInput(out,"classificazione_anag");%></td>
		</tr>
	<%} %>

	<%if (anagrafico.isStrutturaCNR() || anagrafico.isPersonaGiuridica() || anagrafico.isDittaIndividuale()) { %>
		<tr>
		<%if (anagrafico.isStrutturaCNR()) { %>
			<td><b>Nome</b></td>
		<%} else { %>
			<td>
			<%bp.getController().writeFormLabel(out,"ragione_sociale");%>
			</td>
		<%} %>
		<td>
		<%bp.getController().writeFormInput(out,"ragione_sociale");%>
		</td>
		<%if (anagrafico.isPersonaGiuridica()) { %>
			<td colspan="2"><%bp.getController().writeFormLabel(out,"classificazione_anag");%></td>
		<%} %>
		</tr>
	<%} %>		
		<tr><% bp.getController().writeFormField(out,"partita_iva");%>
		<%if (anagrafico.isPersonaGiuridica()) { %>
			<td colspan="2"><%bp.getController().writeFormInput(out,"classificazione_anag");%></td>
		<%} %>
		</tr>
	
		<tr><% bp.getController().writeFormField(out,"codice_fiscale");%>
		<%if(anagrafico.isPersonaFisica())
			bp.getController().writeFormField(out,"ti_sesso");%>
		</tr>

		<tr><% bp.getController().writeFormField(out,"titolo_studio");%></tr>
	</table>
</fieldset>

<fieldset class="fieldset">
	<legend class="GroupLabel">Sede legale/Domicilio fiscale</legend>
	<table>
	<tr>
		<% bp.getController().writeFormField(out,"ds_comune_fiscale");%>
		<td><% bp.getController().writeFormInput(out,"find_comune_fiscale");%></td>
		<% bp.getController().writeFormField(out,"cap_comune_fiscale");%>
	</tr>
	<tr>
		<td><% bp.getController().writeFormLabel(out,"frazione_fiscale");%></td>
		<td colspan="2"><% bp.getController().writeFormInput(out,"frazione_fiscale");%></td>
		<td><% bp.getController().writeFormLabel(out,"ds_provincia_fiscale");%></td>
		<td colspan="2"><% bp.getController().writeFormInput(out,"ds_provincia_fiscale");%></td>

	</tr>
	<tr>
		<td><% bp.getController().writeFormLabel(out,"via_fiscale");%></td>
		<td colspan="2"><% bp.getController().writeFormInput(out,"via_fiscale");%></td>
		<td><% bp.getController().writeFormLabel(out,"num_civico_fiscale");%></td>
		<td colspan="2"><% bp.getController().writeFormInput(out,"num_civico_fiscale");%></td>
	</tr>
	<tr>
		<td><% bp.getController().writeFormLabel(out,"ds_nazione_fiscale");%></td>
		<td colspan="2"><% bp.getController().writeFormInput(out,"ds_nazione_fiscale");%></td>
		<% bp.getController().writeFormField(out,"codice_iso");%>
	</tr>
	<tr>
	<%	if(it.cnr.contab.anagraf00.tabter.bulk.NazioneBulk.EXTRA_CEE.equals( anagrafico.getTi_italiano_estero_anag() ) ) {
			bp.getController().writeFormField(out,"id_fiscale_estero");
		} %>
	</tr>
	</table>
</fieldset>

<% if (anagrafico.isPersonaFisica()) { %>
<fieldset class="fieldset">
	<legend class="GroupLabel">Dati anagrafici</legend>
	<table>
	<tr>
		<% bp.getController().writeFormField(out,"dt_nascita");%>
		<% bp.getController().writeFormField(out,"ds_comune_nascita");%>
		<td><% bp.getController().writeFormInput(out,"find_comune_nascita");%></td>
		
	</tr>
	<tr>
		<% bp.getController().writeFormField(out,"ds_provincia_nascita");%>
		<td><% bp.getController().writeFormLabel(out,"ds_nazione_nascita");%></td>
		<td colspan="2"><% bp.getController().writeFormInput(out,"ds_nazione_nascita");%></td>
	</tr>
	<tr>
		<td><% bp.getController().writeFormLabel(out,"ds_nazione_nazionalita");%></td>
		<td><% bp.getController().writeFormInput(out,"cd_nazione_nazionalita");%></td>
		<td><% bp.getController().writeFormInput(out,"ds_nazione_nazionalita");%></td>
		<td><% bp.getController().writeFormInput(out,"find_nazione_nazionalita");%></td>
	</tr>
	</table>
</fieldset>
<% } %>
