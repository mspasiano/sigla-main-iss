--------------------------------------------------------
--  DDL for View VPG_MANDATO
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "VPG_MANDATO" ("ID", "CHIAVE", "DESCRIZIONE", "SEQUENZA", "CD_CDS", "ESERCIZIO", "PG_MANDATO", "TI_RECORD_L1", "TI_RECORD_L2", "ID_RIGA", "DT_EMISSIONE", "TI_MANDATO", "TI_COMPETENZA_RESIDUO", "DS_CDS", "CD_UO_ORIGINE", "DS_UO_CDS", "CAB", "ABI", "NUMERO_CONTO", "CIN", "INTESTAZIONE", "DS_ABICAB", "VIA", "CAP", "DS_COMUNE", "CD_PROVINCIA", "IM_NETTO", "IM_NETTO_LETTERE", "DS_MANDATO", "CD_TERZO", "CD_UO_TERZO", "DENOMINAZIONE_SEDE", "VIA_SEDE", "CAP_COMUNE_SEDE", "DS_COMUNE_SEDE", "CD_PROVINCIA_BENEFICIARIO", "CODICE_FISCALE", "PARTITA_IVA", "DT_NASCITA", "CD_TIPO_DOCUMENTO_AMM", "PG_DOC_AMM", "IM_NETTO_RIGA", "IM_NETTO_RIGA_LETTERE", "CD_MODALITA_PAG", "DS_MODALITA_PAG", "TI_PAGAMENTO", "CD_TERZO_CEDENTE", "DENOM_SEDE_CEDENTE", "NUMERO_CONTO_TERZO", "CIN_TERZO", "INTESTAZIONE_TERZO", "CAB_TERZO", "ABI_TERZO", "DS_ABICAB_TERZO", "VIA_BANCA_TERZO", "CAP_BANCA_TERZO", "DS_COMUNE_BANCA_TERZO", "CD_PV_BANCA_TERZO", "DS_MANDATO_RIGA", "ESERCIZIO_ORI_OBBLIGAZIONE", "PG_OBBLIGAZIONE", "DS_OBBLIGAZIONE", "CD_VOCE", "DS_VOCE", "CD_CDS_REV", "ESERCIZIO_REV", "PG_REVERSALE", "DS_REVERSALE_RIGA", "IM_REVERSALE_RIGA", "IM_MANDATO", "IM_RITENUTE", "CD_TIPO_BOLLO", "DS_TIPO_BOLLO", "CD_SOSPESO", "IM_ASSOCIATO", "DT_REGISTRAZ_SOSPESO", "UTCR", "DACR", "CD_TIPO_DOCUMENTO_CONT", "CD_SIOPE", "DS_SIOPE", "IM_SIOPE", "IBAN", "IBAN_TERZO", "SWIFT", "SWIFT_TERZO", "CD_CUP", "DS_CUP", "IM_CUP", "STATO") AS 
  SELECT
--
-- Date: 09/05/2007
-- Version: 1.9
-- Aggiunti i campi relativi ai codici siope
--
-- Vista per la stampa massiva dei mandati
-- Protocollo VPG
--
-- History:
--
-- Date: 18/02/2003
-- Version: 1.0
-- Creazione
--
-- Date: 04/03/2003
-- Version: 1.1
-- Gestione varchar2 con size > 200
--
-- Date: 05/03/2003
-- Version: 1.2
-- Correzione gestione varchar2
--
-- Date: 07/03/2003
-- Version: 1.3
-- Estrazione ds_mandato per causale di pagamento
--
-- Date: 24/03/2003
-- Version: 1.4
-- Estrazione informazioni cedente
--
-- Date: 20/01/2004
-- Version: 1.5
-- Estrazione CIN dalla BANCA (richiesta n. 697)
--
-- Date: 21/01/2004
-- Version: 1.6
-- Estrazione dt_nascita del terzo (richiesta n. 699)
--
-- Date: 30/01/2006
-- Version: 1.7
-- Aggiunto campo CD_TIPO_DOCUMENTO_CONT per la stampa dei mandati vpg_man_rev_ass.rpt
--
-- Date: 18/07/2006
-- Version: 1.8
-- Gestione Impegni/Accertamenti Residui:
-- gestito il nuovo campo ESERCIZIO_ORIGINALE
--
-- Date: 09/05/2007
-- Version: 1.9
-- Aggiunti i campi relativi ai codici siope
--
-- Body:
--
ID
,CHIAVE
,TIPO
,SEQUENZA
,ATTRIBUTO_1     -- CD_CDS,
,IMPORTO_1	     -- ESERCIZIO,
,IMPORTO_2		 -- PG_MANDATO,
,TIPO			 -- TI_RECORD_L1,
,ATTRIBUTO_2	 -- TI_RECORD_L2,
,ATTRIBUTO_3	 -- ID_RIGA,
,DATA_1			 -- DT_EMISSIONE,
,ATTRIBUTO_4	 -- TI_MANDATO,
,ATTRIBUTO_5	 -- TI_COMPETENZA_RESIDUO,
,ATTRIBUTO_LONG_1	 -- DS_CDS,
,ATTRIBUTO_7	 -- CD_UO_ORIGINE,
,ATTRIBUTO_LONG_2	 -- DS_UO_CDS,
,ATTRIBUTO_9	 -- CAB,
,ATTRIBUTO_10	 -- ABI,
,ATTRIBUTO_11	 -- NUMERO_CONTO,
,ATTRIBUTO_51	 -- CIN
,ATTRIBUTO_12	 -- INTESTAZIONE,
,ATTRIBUTO_13	 -- DS_ABICAB,
,ATTRIBUTO_14	 -- VIA,
,ATTRIBUTO_15	 -- CAP,
,ATTRIBUTO_48	 -- DS_COMUNE
,ATTRIBUTO_16	 -- CD_PROVINCIA,
,IMPORTO_3		 -- IM_NETTO,
,ATTRIBUTO_LONG_7	 -- IM_NETTO_LETTERE,
,ATTRIBUTO_LONG_9	 -- DS_MANDATO
,IMPORTO_4		 -- CD_TERZO,
,ATTRIBUTO_18	 -- CD_UO_TERZO,
,ATTRIBUTO_19	 -- DENOMINAZIONE_SEDE,
,ATTRIBUTO_20	 -- VIA_SEDE,
,ATTRIBUTO_21	 -- CAP_COMUNE_SEDE,
,ATTRIBUTO_50	 -- DS_COMUNE_SEDE
,ATTRIBUTO_22	 -- CD_PROVINCIA_BENEFICIARIO,
,ATTRIBUTO_23	 -- CODICE_FISCALE,
,ATTRIBUTO_24	 -- PARTITA_IVA,
,DATA_4			 -- DT_NASCITA,
,ATTRIBUTO_25	 -- CD_TIPO_DOCUMENTO_AMM,
,IMPORTO_5		 -- PG_DOC_AMM,
,IMPORTO_6		 -- IM_NETTO_RIGA,
,ATTRIBUTO_LONG_3	 -- IM_NETTO_RIGA_LETTERE,
,ATTRIBUTO_27	 -- CD_MODALITA_PAG,
,ATTRIBUTO_28	 -- DS_MODALITA_PAG,
,ATTRIBUTO_29	 -- TI_PAGAMENTO,
,IMPORTO_14		 -- CD_TERZO_CEDENTE,
,ATTRIBUTO_LONG_10	-- DENOM_SEDE_CEDENTE,
,ATTRIBUTO_30	 -- NUMERO_CONTO_TERZO,
,ATTRIBUTO_52	 -- CIN_TERZO,
,ATTRIBUTO_31	 -- INTESTAZIONE_TERZO,
,ATTRIBUTO_32	 -- CAB_TERZO,
,ATTRIBUTO_33	 -- ABI_TERZO,
,ATTRIBUTO_34	 -- DS_ABICAB_TERZO,
,ATTRIBUTO_35	 -- VIA_BANCA_TERZO,
,ATTRIBUTO_36	 -- CAP_BANCA_TERZO,
,ATTRIBUTO_49	 -- DS_COMUNE_BANCA_TERZO
,ATTRIBUTO_37	 -- CD_PV_BANCA_TERZO,
,ATTRIBUTO_LONG_8	 -- DS_MANDATO_RIGA,
,IMPORTO_15		 -- ESERCIZIO_ORI_OBBLIGAZIONE,
,IMPORTO_7		 -- PG_OBBLIGAZIONE,
,ATTRIBUTO_LONG_4	 -- DS_OBBLIGAZIONE,
,ATTRIBUTO_40	 -- CD_VOCE,
,ATTRIBUTO_LONG_5	 -- DS_VOCE,
,ATTRIBUTO_42	 -- CD_CDS_REV,
,IMPORTO_8		 -- ESERCIZIO_REV,
,IMPORTO_9		 -- PG_REVERSALE,
,ATTRIBUTO_LONG_6	 -- DS_REVERSALE_RIGA,
,IMPORTO_10		 -- IM_REVERSALE_RIGA,
,IMPORTO_11		 -- IM_MANDATO,
,IMPORTO_12		 -- IM_RITENUTE,
,ATTRIBUTO_44	 -- CD_TIPO_BOLLO,
,ATTRIBUTO_45	 -- DS_TIPO_BOLLO,
,ATTRIBUTO_46	 -- CD_SOSPESO,
,IMPORTO_13		 -- IM_ASSOCIATO,
,DATA_2			 -- DT_REGISTRAZ_SOSPESO,
,ATTRIBUTO_47	 -- UTCR,
,DATA_3			 -- DACR
,ATTRIBUTO_48 	--CD_TIPO_DOCUMENTO_CONT
,ATTRIBUTO_53   --CD_SIOPE
,ATTRIBUTO_54   --DS_SIOPE
,IMPORTO_16     --IM_SIOPE
,ATTRIBUTO_55   -- IBAN
,ATTRIBUTO_56   -- IBAN_TERZO
,ATTRIBUTO_57   -- SWIFT
,ATTRIBUTO_58   -- SWIFT_TERZO
,ATTRIBUTO_59   -- cd_Cup
,ATTRIBUTO_LONG_11   -- Ds_Cup
,IMPORTO_17     -- Im_cup
,ATTRIBUTO_61   -- STATO
from tmp_report_generico;
