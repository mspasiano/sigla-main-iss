--------------------------------------------------------
--  DDL for View PRT_ANALITICA_LDA_PDG_ENT
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "PRT_ANALITICA_LDA_PDG_ENT" ("ESERCIZIO", "CD_CENTRO_RESPONSABILITA", "TI_APPARTENENZA", "TI_GESTIONE", "CD_ELEMENTO_VOCE", "CD_LINEA_ATTIVITA", "CD_FUNZIONE", "CD_NATURA", "DS_LINEA_ATTIVITA", "CD_INSIEME_LA", "DS_VOCE", "IM_RA_RCE", "IM_RC_ESRC_ESR", "IM_RE_A2_ENTRATE", "IM_RG_A3_ENTRATE", "RICAVO", "IM_RB_RSE", "IM_RD_A2_RICAVI", "IM_RF_A3_RICAVI") AS 
  SELECT
--
-- Date: 27/10/2003
-- Version: 1.0
--
-- Vista di estrazione dati dai piani di gestione per le situazioni ANALITICA per lda  Ricavi/Entrate
--
-- History
--
-- Date :27/10/2003
-- Version: 1.0
-- Creazione
-- (effettuate alcune modifiche per ottimizzazione-Cineca)
--
-- Body
--
-- Estrae i dettagli del PDG relativi a ricavi ed entrate anno1 anno2 anno3, aggregati
-- per cdr, lda, voce di bilancio
-- seleziona i dettagli con stato=Y confermato
-- mette in join la tabella lda per estrarre natura, funzione, descrizione e insieme
PDG_PREVENTIVO_ETR_DET.ESERCIZIO,
PDG_PREVENTIVO_ETR_DET.CD_CENTRO_RESPONSABILITA,
PDG_PREVENTIVO_ETR_DET.TI_APPARTENENZA,
PDG_PREVENTIVO_ETR_DET.TI_GESTIONE,
PDG_PREVENTIVO_ETR_DET.CD_ELEMENTO_VOCE,
PDG_PREVENTIVO_ETR_DET.CD_LINEA_ATTIVITA,
LINEA_ATTIVITA.CD_FUNZIONE,
LINEA_ATTIVITA.CD_NATURA,
LINEA_ATTIVITA.DS_LINEA_ATTIVITA,
LINEA_ATTIVITA.CD_INSIEME_LA,
ELEMENTO_VOCE.DS_ELEMENTO_VOCE,
-- ENTRATE anno 1
SUM(PDG_PREVENTIVO_ETR_DET.IM_RA_RCE),
SUM(PDG_PREVENTIVO_ETR_DET.IM_RC_ESR),
-- ENTRATE  anno 2
SUM(PDG_PREVENTIVO_ETR_DET.IM_RE_A2_ENTRATE),
-- ENTRATE  anno 3
SUM(PDG_PREVENTIVO_ETR_DET.IM_RG_A3_ENTRATE),
-- RICAVI  anno 1
 SUM(PDG_PREVENTIVO_ETR_DET.IM_RA_RCE) RICAVO,
 SUM(PDG_PREVENTIVO_ETR_DET.IM_RB_RSE),
 -- RICAVI  anno 2
 SUM(PDG_PREVENTIVO_ETR_DET.IM_RD_A2_RICAVI),
 -- RICAVI  anno 3
 SUM(PDG_PREVENTIVO_ETR_DET.IM_RF_A3_RICAVI)
 FROM
PDG_PREVENTIVO_ETR_DET ,
LINEA_ATTIVITA,ELEMENTO_VOCE
WHERE
PDG_PREVENTIVO_ETR_DET.STATO = 'Y' AND
LINEA_ATTIVITA.CD_CENTRO_RESPONSABILITA = PDG_PREVENTIVO_ETR_DET.CD_CENTRO_RESPONSABILITA AND
LINEA_ATTIVITA.CD_LINEA_ATTIVITA = PDG_PREVENTIVO_ETR_DET.CD_LINEA_ATTIVITA AND
PDG_PREVENTIVO_ETR_DET.TI_GESTIONE = 'E' AND
PDG_PREVENTIVO_ETR_DET.TI_APPARTENENZA='C' AND
ELEMENTO_VOCE.ESERCIZIO=PDG_PREVENTIVO_ETR_DET.ESERCIZIO AND
ELEMENTO_VOCE.TI_APPARTENENZA='C' AND
ELEMENTO_VOCE.TI_GESTIONE='E' AND
ELEMENTO_VOCE.CD_ELEMENTO_VOCE=PDG_PREVENTIVO_ETR_DET.CD_ELEMENTO_VOCE
GROUP BY PDG_PREVENTIVO_ETR_DET.ESERCIZIO,
PDG_PREVENTIVO_ETR_DET.CD_CENTRO_RESPONSABILITA,
PDG_PREVENTIVO_ETR_DET.CD_LINEA_ATTIVITA,
PDG_PREVENTIVO_ETR_DET.TI_APPARTENENZA,
PDG_PREVENTIVO_ETR_DET.TI_GESTIONE,
PDG_PREVENTIVO_ETR_DET.CD_ELEMENTO_VOCE,
PDG_PREVENTIVO_ETR_DET. cd_linea_attivita,
LINEA_ATTIVITA.CD_FUNZIONE,
LINEA_ATTIVITA.CD_NATURA,
LINEA_ATTIVITA.DS_LINEA_ATTIVITA,
LINEA_ATTIVITA.CD_INSIEME_LA,
ELEMENTO_VOCE.DS_ELEMENTO_VOCE
;

   COMMENT ON TABLE "PRT_ANALITICA_LDA_PDG_ENT"  IS 'Vista di estrazione dati dai piani di gestione per le situazioni ANALITICA per lda  Ricavi/Entrate';