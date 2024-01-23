package it.cnr.contab.ordmag.magazzino.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class StampaInventarioDTO extends StampaInventarioDTOKey implements Serializable {

    String desc_magazzino;
    String descArticolo;
    String descCatGrp;
    String cod_categoria;
    String cod_gruppo;
    BigDecimal giacenza;
    String um;
    BigDecimal importoUnitario;

    public String getDesc_magazzino() {
        return desc_magazzino;
    }

    public void setDesc_magazzino(String desc_magazzino) {
        this.desc_magazzino = desc_magazzino;
    }

    public String getDescArticolo() {
        return descArticolo;
    }

    public void setDescArticolo(String descArticolo) {
        this.descArticolo = descArticolo;
    }

    public String getDescCatGrp() {
        return descCatGrp;
    }

    public void setDescCatGrp(String descCatGrp) {
        this.descCatGrp = descCatGrp;
    }

    public String getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(String cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getCod_gruppo() {
        return cod_gruppo;
    }

    public void setCod_gruppo(String cod_gruppo) {
        this.cod_gruppo = cod_gruppo;
    }

    public BigDecimal getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(BigDecimal giacenza) {
        this.giacenza = giacenza;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public BigDecimal getImportoUnitario() {
        return importoUnitario;
    }

    public void setImportoUnitario(BigDecimal importoUnitario) {
        this.importoUnitario = importoUnitario;
    }
}
