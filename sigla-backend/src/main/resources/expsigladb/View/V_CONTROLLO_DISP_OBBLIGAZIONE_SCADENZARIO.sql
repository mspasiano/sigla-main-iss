CREATE OR REPLACE FORCE VIEW "V_CONTROLLO_DISP_OBBLIGAZIONE_SCADENZARIO" ("CD_UNITA_ORGANIZZATIVA", "ESERCIZIO_OBBLIGAZIONE", "ESERCIZIO_ORI_OBBLIGAZIONE", "CD_CDS_OBBLIGAZIONE", "PG_OBBLIGAZIONE", "PG_OBBLIGAZIONE_SCADENZARIO", "TOT_ORDINE_IMPEGNO_STESSA_UO", "TOT_ORDINE_IMPEGNO_ALTRA_UO", "TOT_FATTURE_ORDINI", "TOT_FATTURE_NO_ORDINI", "TOT_COMPENSI", "TOT_DOCUMENTI_GENERICI", "TOT_MANDATI", "TOT_IMPEGNO_ASSOCIATO_DOCAMM_CALCOLATO", "TOT_IMPEGNO_ASSOCIATO_DOCAMM", "TOT_IMPEGNO_DA_ASSOCIARE_DOCAMM", "TOT_IMPEGNO_ASSOCIATO_DOCCONT_CALCOLATO", "TOT_IMPEGNO_ASSOCIATO_DOCCONT", "TOT_IMPEGNO_DA_ASSOCIARE_DOCCONT") AS
  SELECT CD_UNITA_ORGANIZZATIVA, ESERCIZIO_OBBLIGAZIONE, ESERCIZIO_ORI_OBBLIGAZIONE, CD_CDS_OBBLIGAZIONE, PG_OBBLIGAZIONE, PG_OBBLIGAZIONE_SCADENZARIO,
  		SUM(TOT_ORDINE_IMPEGNO_STESSA_UO) TOT_ORDINE_IMPEGNO_STESSA_UO,
  		SUM(TOT_ORDINE_IMPEGNO_ALTRA_UO) TOT_ORDINE_IMPEGNO_ALTRA_UO,
        SUM(TOT_FATTURE_ORDINI) TOT_FATTURE_ORDINI,
        SUM(TOT_FATTURE_NO_ORDINI) TOT_FATTURE_NO_ORDINI,
        SUM(TOT_COMPENSI) TOT_COMPENSI,
        SUM(TOT_DOCUMENTI_GENERICI) TOT_DOCUMENTI_GENERICI,
        SUM(TOT_MANDATI) TOT_MANDATI,
        SUM(TOT_ORDINE_IMPEGNO_STESSA_UO) + SUM(TOT_ORDINE_IMPEGNO_ALTRA_UO) + SUM(TOT_FATTURE_NO_ORDINI) + SUM(TOT_COMPENSI) + SUM(TOT_DOCUMENTI_GENERICI) TOT_IMPEGNO_ASSOCIATO_DOCAMM_CALCOLATO,
        SUM(TOT_IMPEGNO_ASSOCIATO_DOCAMM) TOT_IMPEGNO_ASSOCIATO_DOCAMM,
        SUM(TOT_IMPEGNO_DA_ASSOCIARE_DOCAMM) TOT_IMPEGNO_DA_ASSOCIARE_DOCAMM,
        SUM(TOT_MANDATI) TOT_IMPEGNO_ASSOCIATO_DOCCONT_CALCOLATO,
        SUM(TOT_IMPEGNO_ASSOCIATO_DOCCONT) TOT_IMPEGNO_ASSOCIATO_DOCCONT,
        SUM(TOT_IMPEGNO_DA_ASSOCIARE_DOCCONT) TOT_IMPEGNO_DA_ASSOCIARE_DOCCONT
  FROM V_CONTROLLO_DISP_DETAIL
  GROUP BY CD_UNITA_ORGANIZZATIVA, ESERCIZIO_OBBLIGAZIONE, ESERCIZIO_ORI_OBBLIGAZIONE, CD_CDS_OBBLIGAZIONE, PG_OBBLIGAZIONE, PG_OBBLIGAZIONE_SCADENZARIO;