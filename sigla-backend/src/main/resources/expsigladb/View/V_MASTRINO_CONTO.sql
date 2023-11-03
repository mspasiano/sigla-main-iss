--------------------------------------------------------
--  DDL for View V_MASTRINO_CONTO
--------------------------------------------------------
CREATE OR REPLACE FORCE EDITIONABLE VIEW "V_MASTRINO_CONTO" ("PG_SCRITTURA", "DT_CONTABILIZZAZIONE", "DS_SCRITTURA", "ESERCIZIO", "PG_MOVIMENTO", "CD_UNITA_ORGANIZZATIVA", "CD_CDS", "CD_RIGA", "SEZIONE", "IM_MOVIMENTO", "CD_VOCE_EP", "CD_TERZO", "TI_RIGA", "CD_CONTRIBUTO_RITENUTA", "ESERCIZIO_DOCUMENTO", "CD_TIPO_DOCUMENTO", "CD_CDS_DOCUMENTO", "CD_UO_DOCUMENTO", "PG_NUMERO_DOCUMENTO") AS
  SELECT
    --
    -- Date: 09/08/2023
    -- Version: 1.0
    --
    -- Vista di estrazione delle movimentazioni dei Conti Economico Patrimoniale
    --
    -- History:
    --
    -- Date: 09/08/2023
    -- Version: 1.0
    -- Creazione
    --
    -- Body:
    --
    MOVIMENTO_COGE.PG_SCRITTURA,
    SCRITTURA_PARTITA_DOPPIA.DT_CONTABILIZZAZIONE,
    SCRITTURA_PARTITA_DOPPIA.DS_SCRITTURA,
    MOVIMENTO_COGE.ESERCIZIO,
    MOVIMENTO_COGE.PG_MOVIMENTO,
    MOVIMENTO_COGE.CD_UNITA_ORGANIZZATIVA,
    MOVIMENTO_COGE.CD_CDS,
    'D' CD_RIGA,
    MOVIMENTO_COGE.SEZIONE,
    MOVIMENTO_COGE.IM_MOVIMENTO,
    MOVIMENTO_COGE.CD_VOCE_EP,
    MOVIMENTO_COGE.CD_TERZO,
    MOVIMENTO_COGE.TI_RIGA,
    NVL(MOVIMENTO_COGE.CD_CONTRIBUTO_RITENUTA, ' '),
    MOVIMENTO_COGE.ESERCIZIO_DOCUMENTO,
    MOVIMENTO_COGE.CD_TIPO_DOCUMENTO,
    MOVIMENTO_COGE.CD_CDS_DOCUMENTO,
    MOVIMENTO_COGE.CD_UO_DOCUMENTO,
    MOVIMENTO_COGE.PG_NUMERO_DOCUMENTO
    FROM MOVIMENTO_COGE, SCRITTURA_PARTITA_DOPPIA
    WHERE SCRITTURA_PARTITA_DOPPIA.ESERCIZIO=MOVIMENTO_COGE.ESERCIZIO
    AND SCRITTURA_PARTITA_DOPPIA.CD_CDS=MOVIMENTO_COGE.CD_CDS
    AND SCRITTURA_PARTITA_DOPPIA.CD_UNITA_ORGANIZZATIVA=MOVIMENTO_COGE.CD_UNITA_ORGANIZZATIVA
    AND SCRITTURA_PARTITA_DOPPIA.PG_SCRITTURA=MOVIMENTO_COGE.PG_SCRITTURA;