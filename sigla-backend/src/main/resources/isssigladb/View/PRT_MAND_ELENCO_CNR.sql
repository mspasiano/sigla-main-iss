
  CREATE OR REPLACE FORCE EDITIONABLE VIEW "SIGLACOPIAPROD"."PRT_MAND_ELENCO_CNR" ("ESERCIZIO", "COD_CDS", "NUM_MANDATO", "DATA_EMISSIONE", "UO_EMITTENTE", "TIPOLOGIA", "STATO", "STATO_TRASMISSIONE", "COMPETENZA_RESIDUO", "ANNO_OBBLIGAZIONE", "ANNO_ORI_OBBLIGAZIONE", "NUM_OBBLIGAZIONE", "CDS_ORI_OBBLIGAZIONE", "UO_ORI_OBBLIGAZIONE", "TERZO_COD", "LORDO", "RITENUTE", "COD_VOCE", "TERZO_DES", "PG_SCAD_OBBL", "RITENUTE_TESTATA", "IMP_PAGATO", "TI_PAGAMENTO", "DT_TRASMISSIONE", "DT_RITRASMISSIONE", "DT_ANNULLAMENTO") AS
  select distinct
  --
  -- Date: 19/07/2007
  -- Version: 1.3
  --
  -- Vista di stampa del Giornale dei Mandati
  --
  -- History:
  --
  -- Date: 28/11/2002
  -- Version: 1.0
  -- Creazione
  --
  -- Date: 20/03/2003
  -- Version: 1.1
  -- aggiunta distinct alla select, aggiunto alla vista pg_scad_obbl
  --
  -- Date: 18/07/2006
  -- Version: 1.2
  -- Gestione Impegni/Accertamenti Residui:
  -- gestito il nuovo campo ESERCIZIO_ORIGINALE
  --
  -- Date: 19/07/2007
  -- Version: 1.3
  -- Aggiunto il campo RITENUTE_TESTATA
  -- Date: 14/06/2023
  -- Version: 1.4
  -- Nuova gestione competenza/residuo e gestione BT e BI
  --
  -- Date: 14/08/2023
      -- Version: 1.4
      -- Nuova gestione filtro per Data Trasmissione
  -- Body:
  --
  mandato.ESERCIZIO,
  mandato.CD_CDS,
  mandato.PG_MANDATO,
  mandato.DT_EMISSIONE,
  mandato.CD_UO_ORIGINE,
  mandato.TI_MANDATO,
  mandato.STATO,
  mandato.STATO_TRASMISSIONE,
 -- mandato.TI_COMPETENZA_RESIDUO,
 CASE WHEN ESERCIZIO_OBBLIGAZIONE = ESERCIZIO_ORI_OBBLIGAZIONE THEN 'C' ELSE 'R' END AS competenza_residuo,
  mandato_riga.ESERCIZIO_OBBLIGAZIONE,
  mandato_riga.ESERCIZIO_ORI_OBBLIGAZIONE,
  mandato_riga.PG_OBBLIGAZIONE,
  obbligazione.cd_cds_origine,
  obbligazione.cd_uo_origine,
  mandato_riga.CD_TERZO,
  mandato_riga.IM_MANDATO_RIGA,
  mandato_riga.IM_RITENUTE_RIGA,
  obbligazione_scad_voce.CD_VOCE,
  terzo.DENOMINAZIONE_SEDE,
  MANDATO_RIGA.PG_OBBLIGAZIONE_SCADENZARIO,
  mandato.im_ritenute,
  mandato.im_pagato,
  ti_pagamento,
  TRUNC(mandato.dt_trasmissione) dt_trasmissione,
  TRUNC(mandato.dt_ritrasmissione)dt_ritrasmissione,
  TRUNC(mandato.dt_annullamento) dt_annullamento
  from  mandato, mandato_riga, obbligazione, obbligazione_scad_voce, terzo,banca
  where mandato_riga.CD_CDS = mandato.CD_CDS
  and   mandato_riga.ESERCIZIO = mandato.ESERCIZIO
  and   mandato_riga.PG_MANDATO = mandato.PG_MANDATO
  and   obbligazione.CD_CDS = mandato_riga.CD_CDS
  and   obbligazione.ESERCIZIO = mandato_riga.ESERCIZIO_OBBLIGAZIONE
  and   obbligazione.ESERCIZIO_ORIGINALE = mandato_riga.ESERCIZIO_ORI_OBBLIGAZIONE
  and   obbligazione.PG_OBBLIGAZIONE = mandato_riga.PG_OBBLIGAZIONE
  and   obbligazione_scad_voce.CD_CDS = obbligazione.CD_CDS
  and   obbligazione_scad_voce.ESERCIZIO = obbligazione.ESERCIZIO
  and   obbligazione_scad_voce.ESERCIZIO_ORIGINALE = obbligazione.ESERCIZIO_ORIGINALE
  and   obbligazione_scad_voce.PG_OBBLIGAZIONE = obbligazione.PG_OBBLIGAZIONE
  and   obbligazione_scad_voce.TI_APPARTENENZA = obbligazione.TI_APPARTENENZA
  and   obbligazione_scad_voce.TI_GESTIONE = obbligazione.TI_GESTIONE
  and   obbligazione_scad_voce.PG_OBBLIGAZIONE_SCADENZARIO = mandato_riga.PG_OBBLIGAZIONE_SCADENZARIO
  and   terzo.CD_TERZO = mandato_riga.CD_TERZO
  and   mandato_riga.pg_banca = banca.pg_banca AND mandato_riga.cd_terzo = banca.cd_terzo
  order by mandato.ESERCIZIO, mandato.CD_CDS, mandato.PG_MANDATO;

   COMMENT ON TABLE "PRT_MAND_ELENCO_CNR"  IS 'Vista di stampa del Giornale dei Mandati';
