CREATE OR REPLACE FORCE VIEW "V_CONTROLLO_DISPACC_DETAIL" ("CD_UNITA_ORGANIZZATIVA", "ESERCIZIO_ACCERTAMENTO", "ESERCIZIO_ORI_ACCERTAMENTO", "CD_CDS_ACCERTAMENTO", "PG_ACCERTAMENTO", "PG_ACCERTAMENTO_SCADENZARIO", "TOT_FATTURE", "TOT_DOCUMENTI_GENERICI", "TOT_ACCERTAMENTO_ASSOCIATO", "TOT_ACCERTAMENTO_DA_ASSOCIARE") AS
  SELECT o.CD_UNITA_ORGANIZZATIVA uo, far.ESERCIZIO_ACCERTAMENTO, far.ESERCIZIO_ORI_ACCERTAMENTO, far.CD_CDS_ACCERTAMENTO, far.PG_ACCERTAMENTO, far.PG_ACCERTAMENTO_SCADENZARIO,
		   CASE WHEN fa.FL_LIQUIDAZIONE_DIFFERITA = 'N'
		        THEN (nvl(far.IM_IMPONIBILE, 0) + nvl(far.IM_IVA, 0))*DECODE(fa.TI_FATTURA,'C',-1,1)
		        ELSE nvl(far.IM_IMPONIBILE, 0)*DECODE(fa.TI_FATTURA,'C',-1,1)
		   END TOT_FATTURE,
		   0 TOT_DOCUMENTI_GENERICI,
		   0 TOT_ACCERTAMENTO_ASSOCIATO,
		   0 TOT_ACCERTAMENTO_DA_ASSOCIARE
	FROM FATTURA_ATTIVA_RIGA far
	LEFT JOIN ACCERTAMENTO o ON o.ESERCIZIO = far.ESERCIZIO_ACCERTAMENTO AND o.ESERCIZIO_ORIGINALE = far.ESERCIZIO_ORI_ACCERTAMENTO AND o.CD_CDS = far.CD_CDS_ACCERTAMENTO AND o.PG_ACCERTAMENTO = far.PG_ACCERTAMENTO
	LEFT JOIN FATTURA_ATTIVA fa ON fa.ESERCIZIO = far.ESERCIZIO AND fa.CD_CDS = far.CD_CDS AND fa.CD_UNITA_ORGANIZZATIVA = far.CD_UNITA_ORGANIZZATIVA AND fa.PG_FATTURA_ATTIVA = far.PG_FATTURA_ATTIVA
	WHERE far.PG_ACCERTAMENTO IS NOT NULL
	UNION ALL
	SELECT o.CD_UNITA_ORGANIZZATIVA, dgr.ESERCIZIO_ACCERTAMENTO, dgr.ESERCIZIO_ORI_ACCERTAMENTO, dgr.CD_CDS_ACCERTAMENTO, dgr.PG_ACCERTAMENTO, dgr.PG_ACCERTAMENTO_SCADENZARIO,
	     0 TOT_FATTURE,
		   NVL(dgr.IM_RIGA, 0) TOT_DOCUMENTI_GENERICI,
		   0 TOT_IMPEGNO_ASSOCIATO,
		   0 TOT_IMPEGNO_DA_ASSOCIARE
	FROM DOCUMENTO_GENERICO_RIGA dgr
	LEFT JOIN DOCUMENTO_GENERICO dg ON dg.CD_CDS = dgr.CD_CDS AND dg.CD_UNITA_ORGANIZZATIVA = dgr.CD_UNITA_ORGANIZZATIVA AND dg.ESERCIZIO = dgr.ESERCIZIO AND dg.CD_TIPO_DOCUMENTO_AMM = dgr.CD_TIPO_DOCUMENTO_AMM AND dg.PG_DOCUMENTO_GENERICO = dgr.PG_DOCUMENTO_GENERICO
	LEFT JOIN ACCERTAMENTO o ON o.ESERCIZIO = dgr.ESERCIZIO_ACCERTAMENTO AND o.ESERCIZIO_ORIGINALE = dgr.ESERCIZIO_ORI_ACCERTAMENTO AND o.CD_CDS = dgr.CD_CDS_ACCERTAMENTO AND o.PG_ACCERTAMENTO = dgr.PG_ACCERTAMENTO
	WHERE dgr.PG_ACCERTAMENTO IS NOT NULL
	AND   dg.STATO_COFI != 'A'
	UNION ALL
	SELECT o.CD_UNITA_ORGANIZZATIVA, os.ESERCIZIO, os.ESERCIZIO_ORIGINALE, os.CD_CDS, os.PG_ACCERTAMENTO, os.PG_ACCERTAMENTO_SCADENZARIO,
		   0 TOT_FATTURE,
		   0 TOT_DOCUMENTI_GENERICI,
		   CASE WHEN os.IM_ASSOCIATO_DOC_AMM > 0
		        THEN nvl(os.IM_ASSOCIATO_DOC_AMM, 0)
		        ELSE 0
		   END TOT_ACCERTAMENTO_ASSOCIATO,
		   CASE WHEN os.IM_ASSOCIATO_DOC_AMM = 0
		        THEN nvl(os.IM_SCADENZA, 0)
		        ELSE 0
		   END TOT_ACCERTAMENTO_DA_ASSOCIARE
	FROM ACCERTAMENTO_SCADENZARIO os
	LEFT JOIN ACCERTAMENTO o ON o.ESERCIZIO = os.ESERCIZIO AND o.ESERCIZIO_ORIGINALE = os.ESERCIZIO_ORIGINALE AND o.CD_CDS = os.CD_CDS AND o.PG_ACCERTAMENTO = os.PG_ACCERTAMENTO;