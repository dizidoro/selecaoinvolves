package com.involves.selecao.domain;

public class Research {

    private long id;
    private String label;
    private String pointOfSale;
    private Marketable marketable;

    public Research(long id, String label, String pointOfSale, Marketable marketable) {
        this.id = id;
        this.label = label;
        this.pointOfSale = pointOfSale;
        this.marketable = marketable;
    }

    public long id() {
        return id;
    }

    public String label() {
        return label;
    }

    public String pointOfSale() {
        return pointOfSale;
    }

    public Marketable marketable() {
        return this.marketable;
    }


}
