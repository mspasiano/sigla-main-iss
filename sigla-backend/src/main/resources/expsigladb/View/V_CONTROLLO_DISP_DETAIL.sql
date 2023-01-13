CREATE OR REPLACE FORCE VIEW "V_CONTROLLO_DISP_DETAIL" ("CD_UNITA_ORGANIZZATIVA", "ESERCIZIO_OBBLIGAZIONE", "ESERCIZIO_ORI_OBBLIGAZIONE", "CD_CDS_OBBLIGAZIONE", "PG_OBBLIGAZIONE", "PG_OBBLIGAZIONE_SCADENZARIO", "TOT_ORDINE_IMPEGNO_STESSA_UO", "TOT_ORDINE_IMPEGNO_ALTRA_UO", "TOT_FATTURE_ORDINI", "TOT_FATTURE_NO_ORDINI", "TOT_DOCUMENTI_GENERICI", "TOT_IMPEGNO_ASSOCIATO", "TOT_IMPEGNO_DA_ASSOCIARE") AS
  SELECT o.CD_UNITA_ORGANIZZATIVA, oac.ESERCIZIO_OBBL ESERCIZIO_OBBLIGAZIONE, oac.ESERCIZIO_ORIG_OBBL ESERCIZIO_ORI_OBBLIGAZIONE, oac.CD_CDS_OBBL CD_CDS_OBBLIGAZIONE, oac.PG_OBBLIGAZIONE, oac.PG_OBBLIGAZIONE_SCAD PG_OBBLIGAZIONE_SCADENZARIO, IM_TOTALE_CONSEGNA TOT_ORDINE_IMPEGNO_STESSA_UO, 0 TOT_ORDINE_IMPEGNO_ALTRA_UO,
		   0 TOT_FATTURE_ORDINI, 0 TOT_FATTURE_NO_ORDINI, 0 TOT_DOCUMENTI_GENERICI, 0 TOT_IMPEGNO_ASSOCIATO, 0 TOT_IMPEGNO_DA_ASSOCIARE
	FROM ORDINE_ACQ_CONSEGNA oac
	LEFT JOIN UNITA_OPERATIVA_ORD uoo ON uoo.CD_UNITA_OPERATIVA = oac.CD_UNITA_OPERATIVA
	LEFT JOIN OBBLIGAZIONE o ON o.ESERCIZIO = oac.ESERCIZIO_OBBL AND o.ESERCIZIO_ORIGINALE = oac.ESERCIZIO_ORIG_OBBL AND o.CD_CDS = oac.CD_CDS_OBBL AND o.PG_OBBLIGAZIONE = oac.PG_OBBLIGAZIONE
	WHERE oac.PG_OBBLIGAZIONE IS NOT NULL
	AND o.CD_UNITA_ORGANIZZATIVA = uoo.CD_UNITA_ORGANIZZATIVA
	UNION ALL
	SELECT o.CD_UNITA_ORGANIZZATIVA, oac.ESERCIZIO_OBBL, oac.ESERCIZIO_ORIG_OBBL, oac.CD_CDS_OBBL, oac.PG_OBBLIGAZIONE, oac.PG_OBBLIGAZIONE_SCAD, 0, IM_TOTALE_CONSEGNA, 0, 0, 0, 0, 0
	FROM ORDINE_ACQ_CONSEGNA oac
	LEFT JOIN UNITA_OPERATIVA_ORD uoo ON uoo.CD_UNITA_OPERATIVA = oac.CD_UNITA_OPERATIVA
	LEFT JOIN OBBLIGAZIONE o ON o.ESERCIZIO = oac.ESERCIZIO_OBBL AND o.ESERCIZIO_ORIGINALE = oac.ESERCIZIO_ORIG_OBBL AND o.CD_CDS = oac.CD_CDS_OBBL AND o.PG_OBBLIGAZIONE = oac.PG_OBBLIGAZIONE
	WHERE oac.PG_OBBLIGAZIONE IS NOT NULL
	AND o.CD_UNITA_ORGANIZZATIVA != uoo.CD_UNITA_ORGANIZZATIVA
	UNION ALL
	SELECT o.CD_UNITA_ORGANIZZATIVA uo, fpr.ESERCIZIO_OBBLIGAZIONE, fpr.ESERCIZIO_ORI_OBBLIGAZIONE, fpr.CD_CDS_OBBLIGAZIONE, fpr.PG_OBBLIGAZIONE, fpr.PG_OBBLIGAZIONE_SCADENZARIO, 0, 0,
		CASE WHEN fp.TI_ISTITUZ_COMMERC = 'I' THEN nvl(fpr.IM_IMPONIBILE, 0) + nvl(fpr.IM_IVA, 0) ELSE nvl(fpr.IM_IMPONIBILE, 0) END, 0, 0, 0, 0
	FROM FATTURA_PASSIVA_RIGA fpr
	LEFT JOIN OBBLIGAZIONE o ON o.ESERCIZIO = fpr.ESERCIZIO_OBBLIGAZIONE AND o.ESERCIZIO_ORIGINALE = fpr.ESERCIZIO_ORI_OBBLIGAZIONE AND o.CD_CDS = fpr.CD_CDS_OBBLIGAZIONE AND o.PG_OBBLIGAZIONE = fpr.PG_OBBLIGAZIONE
	LEFT JOIN FATTURA_PASSIVA fp ON fp.ESERCIZIO = fpr.ESERCIZIO AND fp.CD_CDS = fpr.CD_CDS AND fp.CD_UNITA_ORGANIZZATIVA = fpr.CD_UNITA_ORGANIZZATIVA AND fp.PG_FATTURA_PASSIVA = fpr.PG_FATTURA_PASSIVA
	LEFT JOIN FATTURA_ORDINE fo ON fo.ESERCIZIO = fpr.ESERCIZIO AND fo.CD_CDS = fpr.CD_CDS AND fo.CD_UNITA_ORGANIZZATIVA = fpr.CD_UNITA_ORGANIZZATIVA AND fo.PG_FATTURA_PASSIVA = fpr.PG_FATTURA_PASSIVA AND fo.PROGRESSIVO_RIGA = fpr.PROGRESSIVO_RIGA
	WHERE fpr.PG_OBBLIGAZIONE IS NOT NULL
	AND fo.CD_NUMERATORE IS NOT NULL
	UNION ALL
	SELECT o.CD_UNITA_ORGANIZZATIVA uo, fpr.ESERCIZIO_OBBLIGAZIONE, fpr.ESERCIZIO_ORI_OBBLIGAZIONE, fpr.CD_CDS_OBBLIGAZIONE, fpr.PG_OBBLIGAZIONE, fpr.PG_OBBLIGAZIONE_SCADENZARIO, 0, 0,
		0, CASE WHEN fp.TI_ISTITUZ_COMMERC = 'I' THEN nvl(fpr.IM_IMPONIBILE, 0) + nvl(fpr.IM_IVA, 0) ELSE nvl(fpr.IM_IMPONIBILE, 0) END, 0, 0, 0
	FROM FATTURA_PASSIVA_RIGA fpr
	LEFT JOIN OBBLIGAZIONE o ON o.ESERCIZIO = fpr.ESERCIZIO_OBBLIGAZIONE AND o.ESERCIZIO_ORIGINALE = fpr.ESERCIZIO_ORI_OBBLIGAZIONE AND o.CD_CDS = fpr.CD_CDS_OBBLIGAZIONE AND o.PG_OBBLIGAZIONE = fpr.PG_OBBLIGAZIONE
	LEFT JOIN FATTURA_PASSIVA fp ON fp.ESERCIZIO = fpr.ESERCIZIO AND fp.CD_CDS = fpr.CD_CDS AND fp.CD_UNITA_ORGANIZZATIVA = fpr.CD_UNITA_ORGANIZZATIVA AND fp.PG_FATTURA_PASSIVA = fpr.PG_FATTURA_PASSIVA
	LEFT JOIN FATTURA_ORDINE fo ON fo.ESERCIZIO = fpr.ESERCIZIO AND fo.CD_CDS = fpr.CD_CDS AND fo.CD_UNITA_ORGANIZZATIVA = fpr.CD_UNITA_ORGANIZZATIVA AND fo.PG_FATTURA_PASSIVA = fpr.PG_FATTURA_PASSIVA AND fo.PROGRESSIVO_RIGA = fpr.PROGRESSIVO_RIGA
	WHERE fpr.PG_OBBLIGAZIONE IS NOT NULL
	AND fo.CD_NUMERATORE IS NULL
	UNION ALL
	SELECT o.CD_UNITA_ORGANIZZATIVA, dgr.ESERCIZIO_OBBLIGAZIONE, dgr.ESERCIZIO_ORI_OBBLIGAZIONE, dgr.CD_CDS_OBBLIGAZIONE, dgr.PG_OBBLIGAZIONE, dgr.PG_OBBLIGAZIONE_SCADENZARIO, 0, 0, 0, 0, nvl(dgr.IM_RIGA, 0), 0, 0
	FROM DOCUMENTO_GENERICO_RIGA dgr
	LEFT JOIN OBBLIGAZIONE o ON o.ESERCIZIO = dgr.ESERCIZIO_OBBLIGAZIONE AND o.ESERCIZIO_ORIGINALE = dgr.ESERCIZIO_ORI_OBBLIGAZIONE AND o.CD_CDS = dgr.CD_CDS_OBBLIGAZIONE AND o.PG_OBBLIGAZIONE = dgr.PG_OBBLIGAZIONE
	WHERE dgr.PG_OBBLIGAZIONE IS NOT NULL
	UNION ALL
	SELECT o.CD_UNITA_ORGANIZZATIVA, os.ESERCIZIO, os.ESERCIZIO_ORIGINALE, os.CD_CDS, os.PG_OBBLIGAZIONE, os.PG_OBBLIGAZIONE_SCADENZARIO, 0, 0, 0, 0, 0, nvl(os.IM_ASSOCIATO_DOC_AMM, 0), 0
	FROM OBBLIGAZIONE_SCADENZARIO os
	LEFT JOIN OBBLIGAZIONE o ON o.ESERCIZIO = os.ESERCIZIO AND o.ESERCIZIO_ORIGINALE = os.ESERCIZIO_ORIGINALE AND o.CD_CDS = os.CD_CDS AND o.PG_OBBLIGAZIONE = os.PG_OBBLIGAZIONE
	WHERE os.IM_ASSOCIATO_DOC_AMM > 0
	UNION ALL
	SELECT o.CD_UNITA_ORGANIZZATIVA, os.ESERCIZIO, os.ESERCIZIO_ORIGINALE, os.CD_CDS, os.PG_OBBLIGAZIONE, os.PG_OBBLIGAZIONE_SCADENZARIO, 0, 0, 0, 0, 0, 0, nvl(os.IM_SCADENZA, 0)
	FROM OBBLIGAZIONE_SCADENZARIO os
	LEFT JOIN OBBLIGAZIONE o ON o.ESERCIZIO = os.ESERCIZIO AND o.ESERCIZIO_ORIGINALE = os.ESERCIZIO_ORIGINALE AND o.CD_CDS = os.CD_CDS AND o.PG_OBBLIGAZIONE = os.PG_OBBLIGAZIONE
	WHERE os.IM_ASSOCIATO_DOC_AMM = 0;