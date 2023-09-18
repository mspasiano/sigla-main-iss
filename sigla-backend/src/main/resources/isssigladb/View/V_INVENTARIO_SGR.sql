CREATE OR REPLACE FORCE VIEW "V_INVENTARIO_SGR" ("ETICHETTA","EDIFICIO","SCALA","PIANO","STANZA","CATASTO","CD_UBICAZIONE", "DS_BENE","CD_CATEGORIA", "CD_GRUPPO", "CONDIZIONE_BENE", "ID_BENE_ORIGINE", "DT_ACQUISIZIONE", "SIGLA_INT_ENTE", "MATRICOLA","DISMESSO") AS
SELECT inv.ETICHETTA,
       ub.edificio,
       '.' as scala,
       ub.piano,
       ub.stanza,ub.catasto,
       inv.CD_UBICAZIONE,
       inv.DS_BENE,
       cat.CD_CATEGORIA_PADRE,
       cat.CD_PROPRIO,
       cond.DS_CONDIZIONE_BENE,
       inv.ID_BENE_ORIGINE,
       inv.DT_ACQUISIZIONE,
       uo.SIGLA_INT_ENTE,
       SUBSTR( terzo.CD_PRECEDENTE, 10),
       DECODE(inv.FL_DISMESSO, null, 'N',inv.FL_DISMESSO) dismesso
FROM
    INVENTARIO_BENI inv,
    CATEGORIA_GRUPPO_INVENT cat,
    INVENTARIO_UTILIZZATORI_LA ut,
    CDR cdr,
    UNITA_ORGANIZZATIVA uo,
    TERZO terzo,
    CONDIZIONE_BENE cond,
   (select  cd_ubicazione,regexp_substr(substr( ds_ubicazione_bene,1,INSTR( ds_ubicazione_bene,' ')), '[^-]+', 1, 1) as edificio,
          regexp_substr(substr( ds_ubicazione_bene,1,INSTR( ds_ubicazione_bene,' ')), '[^-]+', 1, 2) as piano,
          regexp_substr(substr( ds_ubicazione_bene,1,INSTR( ds_ubicazione_bene,' ')), '[^-]+', 1, 3) as stanza,
           trim(substr( ds_ubicazione_bene,INSTR( ds_ubicazione_bene,' '))) as catasto
          from ubicazione_bene
   ) ub
WHERE inv.CD_CATEGORIA_GRUPPO=cat.CD_CATEGORIA_GRUPPO
    AND inv.NR_INVENTARIO = ut.NR_INVENTARIO
    AND ut.CD_UTILIZZATORE_CDR = cdr.CD_CENTRO_RESPONSABILITA
    AND cdr.CD_UNITA_ORGANIZZATIVA=uo.CD_UNITA_ORGANIZZATIVA
    AND inv.CD_ASSEGNATARIO = terzo.CD_TERZO
    AND ub.cd_ubicazione = inv.CD_UBICAZIONE
    and inv.CD_CONDIZIONE_BENE = cond.CD_CONDIZIONE_BENE




