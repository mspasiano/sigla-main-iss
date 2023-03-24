CREATE OR REPLACE FORCE VIEW "V_CONTROLLO_DISPACC_ACCERTAMENTO_SCADENZARIO" ("CD_UNITA_ORGANIZZATIVA", "ESERCIZIO_ACCERTAMENTO", "ESERCIZIO_ORI_ACCERTAMENTO", "CD_CDS_ACCERTAMENTO", "PG_ACCERTAMENTO", "PG_ACCERTAMENTO_SCADENZARIO", "TOT_FATTURE", "TOT_DOCUMENTI_GENERICI", "TOT_ACCERTAMENTO_ASSOCIATO_CALCOLATO", "TOT_ACCERTAMENTO_ASSOCIATO", "TOT_ACCERTAMENTO_DA_ASSOCIARE") AS
  SELECT CD_UNITA_ORGANIZZATIVA, ESERCIZIO_ACCERTAMENTO, ESERCIZIO_ORI_ACCERTAMENTO, CD_CDS_ACCERTAMENTO, PG_ACCERTAMENTO, PG_ACCERTAMENTO_SCADENZARIO,
        SUM(TOT_FATTURE) TOT_FATTURE,
        SUM(TOT_DOCUMENTI_GENERICI) TOT_DOCUMENTI_GENERICI,
        SUM(TOT_FATTURE) + SUM(TOT_DOCUMENTI_GENERICI) TOT_ACCERTAMENTO_ASSOCIATO_CALCOLATO,
        SUM(TOT_ACCERTAMENTO_ASSOCIATO) TOT_ACCERTAMENTO_ASSOCIATO,
        SUM(TOT_ACCERTAMENTO_DA_ASSOCIARE) TOT_ACCERTAMENTO_DA_ASSOCIARE
  FROM V_CONTROLLO_DISPACC_DETAIL
  GROUP BY CD_UNITA_ORGANIZZATIVA, ESERCIZIO_ACCERTAMENTO, ESERCIZIO_ORI_ACCERTAMENTO, CD_CDS_ACCERTAMENTO, PG_ACCERTAMENTO, PG_ACCERTAMENTO_SCADENZARIO;