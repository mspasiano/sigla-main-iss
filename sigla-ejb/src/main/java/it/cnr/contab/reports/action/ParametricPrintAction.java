/*
 * Copyright (C) 2019  Consiglio Nazionale delle Ricerche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package it.cnr.contab.reports.action;

import it.cnr.contab.docamm00.tabrif.bulk.Bene_servizioBulk;
import it.cnr.contab.docamm00.tabrif.bulk.Categoria_gruppo_inventBulk;
import it.cnr.contab.ordmag.anag00.UnitaOperativaOrdBulk;
import it.cnr.contab.ordmag.magazzino.bulk.Stampa_consumiBulk;
import it.cnr.contab.ordmag.magazzino.bulk.Stampa_consumiHome;
import it.cnr.contab.ordmag.ordini.bulk.OrdineAcqRigaBulk;
import it.cnr.contab.reports.bp.*;
import it.cnr.contab.reports.bulk.Print_spooler_paramBulk;
import it.cnr.jada.action.*;
import it.cnr.jada.bulk.*;
import it.cnr.jada.util.*;
import it.cnr.jada.util.action.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Action che gestisce le operazioni di CRUD (Create,Read,Update e Delete)
 * per un CRUDBusinessProcess.
 * <p>Implementa i seguenti comandi:
 * <ul>
 * <il> doCerca
 * <il> doElimina
 * <il> doNuovo
 * <il> doSalva
 * <il> doRiportaSelezione
 * </ul>
 * <code>CRUDAction</code> funziona solo se il business process corrente 
 * è un <code>CRUDBusinessProcess</code>.
 */
public class ParametricPrintAction extends BulkAction  implements java.io.Serializable {
public ParametricPrintAction() {
	super();
}
public Forward doPrint(ActionContext context) {
	ParametricPrintBP bp = (ParametricPrintBP)context.getBusinessProcess();
	try {
		fillModel(context);
		bp.completeSearchTools(context,bp.getController());
		bp.validate(context);
		bp.print(context,bp.getModel());
		OfflineReportPrintBP printbp = (OfflineReportPrintBP)context.createBusinessProcess("OfflineReportPrintBP");
		printbp.setReportName(bp.getReportName());
		printbp.setRepotWhithDsOffLine( bp.getRepotWhitDsOffLine());
		for (Enumeration e = bp.getBulkInfo().getPrintFieldProperties(bp.getReportName());e.hasMoreElements();) {
			PrintFieldProperty printFieldProperty = (PrintFieldProperty)e.nextElement();
			Object value = printFieldProperty.getValueFrom(bp.getModel());
			Print_spooler_paramBulk param = new Print_spooler_paramBulk();
			if (bp.getReportName().endsWith("jasper")){
				param.setNomeParam(printFieldProperty.getParamNameJR());
				param.setParamType(printFieldProperty.getParamTypeJR());
			}else{
				param.setNomeParam("prompt"+printFieldProperty.getParameterPosition());
			}
			switch(printFieldProperty.getParamType()) {
				case PrintFieldProperty.TYPE_DATE:
					if(value != null) {
						param.setValoreParam(ReportPrintBP.DATE_FORMAT.format((java.sql.Timestamp) value));
					}else{
						param.setValoreParam(null);
					}
					break;
				case PrintFieldProperty.TYPE_TIMESTAMP:
					if(value != null) {
						param.setValoreParam(ReportPrintBP.TIMESTAMP_FORMAT.format((java.sql.Timestamp) value));
					}else{
						param.setValoreParam(null);
					}
					break;
				case PrintFieldProperty.TYPE_STRING:
				default:
					if (value == null)
						param.setValoreParam("");
					else
						param.setValoreParam(value.toString());
					break;
			}								
			printbp.addToPrintSpoolerParam(param);
		}
		bp.setDirty(false);
		return context.addBusinessProcess(printbp);
	} catch(ValidationException e) {
		bp.setErrorMessage(e.getMessage());
		return context.findDefaultForward();
	} catch(Throwable e) {
		return handleException(context,e);
	}
}
	public Forward doOnDataInizioMovimentoChange(ActionContext actioncontext) throws FillException {


		ParametricPrintBP bp= (ParametricPrintBP) actioncontext.getBusinessProcess();
		Stampa_consumiBulk model=(Stampa_consumiBulk)bp.getModel();
		fillModel(actioncontext);

		try {
			if(model.getDaDataMovimento() == null ){
				throw new it.cnr.jada.bulk.ValidationException("Selezionare Data Movimento Da");
			}
			if(model.getaDataMovimento() == null) {
				model.setaDataMovimento(model.getDaDataMovimento());
			}
			return actioncontext.findDefaultForward();
		} catch (Throwable e) {
			return handleException(actioncontext, e);
		}
	}
	public Forward doOnDataFineMovimentoChange(ActionContext actioncontext) throws FillException {


		ParametricPrintBP bp= (ParametricPrintBP) actioncontext.getBusinessProcess();
		Stampa_consumiBulk model=(Stampa_consumiBulk)bp.getModel();
		fillModel(actioncontext);

		try {
			if(model.getDaDataMovimento() == null ){
				throw new it.cnr.jada.bulk.ValidationException("Selezionare Data Movimento Da");
			}
			if(model.getaDataMovimento() == null ){
				throw new it.cnr.jada.bulk.ValidationException("Selezionare Data Movimento A");
			}
			if(model.getDaDataMovimento().compareTo(model.getaDataMovimento()) > 0){
				throw new it.cnr.jada.bulk.ValidationException("Intervallo di Data Movimento non corretto, la data Da non può essere maggiore della data A");
			}
			return actioncontext.findDefaultForward();
		} catch (Throwable e) {
			return handleException(actioncontext, e);
		}
	}


	public Forward doBringBackSearchFindDaUnitaOperativa(ActionContext context, Stampa_consumiBulk stampaConsumi, UnitaOperativaOrdBulk uop) {
		stampaConsumi.setDaUnitaOperativa(uop);
		if(stampaConsumi.getaUnitaOperativa() == null || stampaConsumi.getaUnitaOperativa().getCdUnitaOperativa()==null){
			stampaConsumi.setaUnitaOperativa(uop);
		}
		return context.findDefaultForward();
	}
	public Forward doBringBackSearchFindDaCatGrp(ActionContext context, Stampa_consumiBulk stampaConsumi, Categoria_gruppo_inventBulk catGrp) {
		stampaConsumi.setDaCatgrp(catGrp);
		if(stampaConsumi.getaCatgrp() == null || stampaConsumi.getaCatgrp().getCd_categoria_gruppo() == null) {
			stampaConsumi.setaCatgrp(catGrp);
		}
		return context.findDefaultForward();
	}
	public Forward doBringBackSearchFindDaBeneServizio(ActionContext context, Stampa_consumiBulk stampaConsumi, Bene_servizioBulk bene) {
		stampaConsumi.setDaBeneServizio(bene);
		if(stampaConsumi.getaBeneServizio() == null || stampaConsumi.getaBeneServizio().getCd_bene_servizio() == null) {
			stampaConsumi.setaBeneServizio(bene);
		}
		return context.findDefaultForward();
	}
}