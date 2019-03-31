package com.involves.selecao.domain;

public class PriceResearch {

    private MonetaryAmount stipulatedPrice;
    private MonetaryAmount price;

    public PriceResearch(MonetaryAmount stipulatedPrice, MonetaryAmount price) {
        this.stipulatedPrice = stipulatedPrice;
        this.price = price;
    }

    public MonetaryAmount stipulatedPrice() {
        return this.stipulatedPrice;
    }

    public MonetaryAmount price() {
        return this.price;
    }

    public MonetaryAmount margin() {
        return this.stipulatedPrice.marginFrom(this.price);
    }

    public boolean priceIsAboveStipulated() {
        return this.price.isGreaterThan(this.stipulatedPrice);
    }

    public boolean priceIsBelowStipulated() {
        return this.price.isLowerThan(this.stipulatedPrice);
    }

}
