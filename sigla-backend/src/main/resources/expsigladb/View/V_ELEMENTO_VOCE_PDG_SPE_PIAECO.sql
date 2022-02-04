--------------------------------------------------------
--  DDL for View V_ELEMENTO_VOCE_PDG_SPE_PIAECO
--------------------------------------------------------

CREATE OR REPLACE FORCE VIEW "PCIR009"."V_ELEMENTO_VOCE_PDG_SPE_PIAECO" ("ESERCIZIO", "CD_ELEMENTO_VOCE", "TI_APPARTENENZA", "TI_GESTIONE", "TI_ELEMENTO_VOCE", "CD_PARTE", "CD_PROPRIO_ELEMENTO", "DS_ELEMENTO_VOCE", "DUVA", "CD_ELEMENTO_PADRE", "UTUV", "DACR", "UTCR", "PG_VER_REC", "FL_LIMITE_ASS_OBBLIG", "FL_VOCE_PERSONALE", "FL_PARTITA_GIRO", "CD_CAPOCONTO_FIN", "FL_VOCE_SAC", "FL_VOCE_NON_SOGG_IMP_AUT", "CD_FUNZIONE", "CD_TIPO_UNITA", "ESERCIZIO_CLA_S", "COD_CLA_S", "ESERCIZIO_CLA_E", "COD_CLA_E", "FL_RECON", "FL_INV_BENI_PATR", "FL_VOCE_FONDO", "FL_CHECK_TERZO_SIOPE", "ID_CLASSIFICAZIONE", "CD_CLASSIFICAZIONE", "FL_INV_BENI_COMP", "FL_LIMITE_SPESA", "FL_PRELIEVO", "FL_SOGGETTO_PRELIEVO", "PERC_PRELIEVO_PDGP_ENTRATE", "FL_SOLO_RESIDUO", "FL_SOLO_COMPETENZA", "FL_TROVATO", "FL_MISSIONI", "FL_AZZERA_RESIDUI", "ESERCIZIO_ELEMENTO_PADRE", "TI_APPARTENENZA_ELEMENTO_PADRE", "TI_GESTIONE_ELEMENTO_PADRE", "CD_UNITA_PIANO", "CD_VOCE_PIANO", "GG_DEROGA_OBBL_COMP_PRG_SCAD", "GG_DEROGA_OBBL_RES_PRG_SCAD", "FL_COMUNICA_PAGAMENTI", "FL_LIMITE_COMPETENZA", "BLOCCO_IMPEGNI_NATFIN", "PG_PROGETTO_ASSOCIATO", "ESERCIZIO_PIANO_ASSOCIATO", "CD_UNITA_PIANO_ASSOCIATO", "CD_VOCE_PIANO_ASSOCIATO") AS
  SELECT V_ELEMENTO_VOCE_PDG_SPE.ESERCIZIO, V_ELEMENTO_VOCE_PDG_SPE.CD_ELEMENTO_VOCE, V_ELEMENTO_VOCE_PDG_SPE.TI_APPARTENENZA,
         V_ELEMENTO_VOCE_PDG_SPE.TI_GESTIONE, V_ELEMENTO_VOCE_PDG_SPE.TI_ELEMENTO_VOCE, V_ELEMENTO_VOCE_PDG_SPE.CD_PARTE, V_ELEMENTO_VOCE_PDG_SPE.CD_PROPRIO_ELEMENTO,
         V_ELEMENTO_VOCE_PDG_SPE.DS_ELEMENTO_VOCE, V_ELEMENTO_VOCE_PDG_SPE.DUVA, V_ELEMENTO_VOCE_PDG_SPE.CD_ELEMENTO_PADRE, V_ELEMENTO_VOCE_PDG_SPE.UTUV,
         V_ELEMENTO_VOCE_PDG_SPE.DACR, V_ELEMENTO_VOCE_PDG_SPE.UTCR, V_ELEMENTO_VOCE_PDG_SPE.PG_VER_REC, V_ELEMENTO_VOCE_PDG_SPE.FL_LIMITE_ASS_OBBLIG,
         V_ELEMENTO_VOCE_PDG_SPE.FL_VOCE_PERSONALE, V_ELEMENTO_VOCE_PDG_SPE.FL_PARTITA_GIRO, V_ELEMENTO_VOCE_PDG_SPE.CD_CAPOCONTO_FIN, V_ELEMENTO_VOCE_PDG_SPE.FL_VOCE_SAC,
         V_ELEMENTO_VOCE_PDG_SPE.FL_VOCE_NON_SOGG_IMP_AUT, V_ELEMENTO_VOCE_PDG_SPE.CD_FUNZIONE, V_ELEMENTO_VOCE_PDG_SPE.CD_TIPO_UNITA, V_ELEMENTO_VOCE_PDG_SPE.ESERCIZIO_CLA_S,
         V_ELEMENTO_VOCE_PDG_SPE.COD_CLA_S, V_ELEMENTO_VOCE_PDG_SPE.ESERCIZIO_CLA_E, V_ELEMENTO_VOCE_PDG_SPE.COD_CLA_E, V_ELEMENTO_VOCE_PDG_SPE.FL_RECON,
         V_ELEMENTO_VOCE_PDG_SPE.FL_INV_BENI_PATR, V_ELEMENTO_VOCE_PDG_SPE.FL_VOCE_FONDO, V_ELEMENTO_VOCE_PDG_SPE.FL_CHECK_TERZO_SIOPE,
         V_ELEMENTO_VOCE_PDG_SPE.ID_CLASSIFICAZIONE, V_ELEMENTO_VOCE_PDG_SPE.CD_CLASSIFICAZIONE, V_ELEMENTO_VOCE_PDG_SPE.FL_INV_BENI_COMP,
         V_ELEMENTO_VOCE_PDG_SPE.FL_LIMITE_SPESA, V_ELEMENTO_VOCE_PDG_SPE.FL_PRELIEVO, V_ELEMENTO_VOCE_PDG_SPE.FL_SOGGETTO_PRELIEVO,
         V_ELEMENTO_VOCE_PDG_SPE.PERC_PRELIEVO_PDGP_ENTRATE, V_ELEMENTO_VOCE_PDG_SPE.FL_SOLO_RESIDUO, V_ELEMENTO_VOCE_PDG_SPE.FL_SOLO_COMPETENZA,
         V_ELEMENTO_VOCE_PDG_SPE.FL_TROVATO, V_ELEMENTO_VOCE_PDG_SPE.FL_MISSIONI, V_ELEMENTO_VOCE_PDG_SPE.FL_AZZERA_RESIDUI, V_ELEMENTO_VOCE_PDG_SPE.ESERCIZIO_ELEMENTO_PADRE,
         V_ELEMENTO_VOCE_PDG_SPE.TI_APPARTENENZA_ELEMENTO_PADRE, V_ELEMENTO_VOCE_PDG_SPE.TI_GESTIONE_ELEMENTO_PADRE, V_ELEMENTO_VOCE_PDG_SPE.CD_UNITA_PIANO,
         V_ELEMENTO_VOCE_PDG_SPE.CD_VOCE_PIANO, V_ELEMENTO_VOCE_PDG_SPE.GG_DEROGA_OBBL_COMP_PRG_SCAD, V_ELEMENTO_VOCE_PDG_SPE.GG_DEROGA_OBBL_RES_PRG_SCAD,
         V_ELEMENTO_VOCE_PDG_SPE.FL_COMUNICA_PAGAMENTI, V_ELEMENTO_VOCE_PDG_SPE.FL_LIMITE_COMPETENZA, V_ELEMENTO_VOCE_PDG_SPE.BLOCCO_IMPEGNI_NATFIN,
         PROGETTO_ANNO.PG_PROGETTO PG_PROGETTO_ASSOCIATO, PROGETTO_ANNO.ESERCIZIO_PIANO ESERCIZIO_PIANO_ASSOCIATO,
         ASS_PROGETTO_PIAECO_VOCE.CD_UNITA_ORGANIZZATIVA CD_UNITA_PIANO_ASSOCIATO,
         ASS_PROGETTO_PIAECO_VOCE.CD_VOCE_PIANO CD_VOCE_PIANO_ASSOCIATO
  FROM  (SELECT DISTINCT PG_PROGETTO, ESERCIZIO_PIANO FROM PROGETTO_PIANO_ECONOMICO) PROGETTO_ANNO
  LEFT JOIN V_ELEMENTO_VOCE_PDG_SPE ON 1=1
  LEFT JOIN ASS_PROGETTO_PIAECO_VOCE ON ASS_PROGETTO_PIAECO_VOCE.PG_PROGETTO = PROGETTO_ANNO.PG_PROGETTO AND
                                        ASS_PROGETTO_PIAECO_VOCE.ESERCIZIO_VOCE = PROGETTO_ANNO.ESERCIZIO_PIANO AND
                                        ASS_PROGETTO_PIAECO_VOCE.TI_GESTIONE = V_ELEMENTO_VOCE_PDG_SPE.TI_GESTIONE AND
                                        ASS_PROGETTO_PIAECO_VOCE.TI_APPARTENENZA = V_ELEMENTO_VOCE_PDG_SPE.TI_APPARTENENZA AND
                                        ASS_PROGETTO_PIAECO_VOCE.CD_ELEMENTO_VOCE = V_ELEMENTO_VOCE_PDG_SPE.CD_ELEMENTO_VOCE;