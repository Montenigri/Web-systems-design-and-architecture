package com.musso.megliodellavisa.model;

public class Carta {
    private Long id;
    private String numeroCarta;
    private Boolean attiva;
    private Float creditoResiduo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public Boolean getAttiva() {
        return attiva;
    }

    public void setAttiva(Boolean attiva) {
        this.attiva = attiva;
    }

    public Float getCreditoResiduo() {
        return creditoResiduo;
    }

    public void setCreditoResiduo(Float creditoResiduo) {
        this.creditoResiduo = creditoResiduo;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "id=" + id +
                ", numeroCarta='" + numeroCarta + '\'' +
                ", attiva=" + attiva +
                ", creditoResiduo=" + creditoResiduo +
                '}';
    }
}
