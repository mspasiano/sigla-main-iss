--------------------------------------------------------
--  DDL for View V_FONDO_ECONOMALE
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "V_FONDO_ECONOMALE" ("ESERCIZIO", "CDS", "UO", "CD_CODICE_FONDO", "DS_FONDO", "ECONOMO", "FL_APERTO", "IM_AMMONTARE_INIZIALE", "IM_AMMONTARE_FONDO", "IM_RESIDUO_FONDO", "IM_TOTALE_SPESE", "IM_TOTALE_REINTEGRI", "IM_TOTALE_NETTO_SPESE", "CD_MODALITA_PAG", "DS_MODALITA_PAG", "ABI", "CAB", "NUMERO_CONTO", "CIN", "INTESTAZIONE", "CODICE_IBAN", "CODICE_SWIFT") AS 
  SELECT FONDO_ECONOMALE.ESERCIZIO,
	   FONDO_ECONOMALE.CD_CDS,
	   FONDO_ECONOMALE.CD_UNITA_ORGANIZZATIVA,
	   FONDO_ECONOMALE.CD_CODICE_FONDO,
	   FONDO_ECONOMALE.DS_FONDO,
	   TERZO.DENOMINAZIONE_SEDE ECONOMO,
	   FONDO_ECONOMALE.FL_APERTO,
	   FONDO_ECONOMALE.IM_AMMONTARE_INIZIALE,
	   FONDO_ECONOMALE.IM_AMMONTARE_FONDO,
	   FONDO_ECONOMALE.IM_RESIDUO_FONDO,
	   FONDO_ECONOMALE.IM_TOTALE_SPESE,
	   FONDO_ECONOMALE.IM_TOTALE_REINTEGRI,
	   FONDO_ECONOMALE.IM_TOTALE_NETTO_SPESE,
	   MODALITA_PAGAMENTO.CD_MODALITA_PAG,
	   RIF_MODALITA_PAGAMENTO.DS_MODALITA_PAG,
	   BANCA.ABI,
	   BANCA.CAB,
	   BANCA.NUMERO_CONTO,
	   BANCA.CIN,
	   BANCA.INTESTAZIONE,
	   BANCA.CODICE_IBAN,
	   BANCA.CODICE_SWIFT
FROM 	FONDO_ECONOMALE,
	TERZO,
BANCA,
MODALITA_PAGAMENTO,
RIF_MODALITA_PAGAMENTO
WHERE FONDO_ECONOMALE.CD_TERZO	 = TERZO.cd_terzo
AND	FONDO_ECONOMALE.cd_terzo 	= BANCA.cd_terzo
AND	FONDO_ECONOMALE.pg_banca 	=  BANCA.pg_banca
AND	FONDO_ECONOMALE.CD_TERZO	 = MODALITA_PAGAMENTO.CD_TERZO
AND	FONDO_ECONOMALE.cd_modalita_pag = MODALITA_PAGAMENTO.CD_MODALITA_PAG
AND	FONDO_ECONOMALE.CD_MODALITA_PAG =  RIF_MODALITA_PAGAMENTO.CD_MODALITA_PAG;