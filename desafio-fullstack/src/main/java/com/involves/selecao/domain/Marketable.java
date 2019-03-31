package com.involves.selecao.domain;

import java.util.Optional;

public class Marketable {

    private String name;
    private Type type;

    private ShareResearch shareResearch;
    private PriceResearch priceResearch;
    private Boolean isAvailable;

    private Marketable(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.shareResearch = builder.shareResearch;
        this.priceResearch = builder.priceResearch;
        this.isAvailable = builder.isAvailable;
    }

    public String name() {
        return this.name;
    }

    public Type type() {
        return this.type;
    }

    public Optional<ShareResearch> shareResearch() {
        return Optional.ofNullable(this.shareResearch);
    }

    public Optional<PriceResearch> priceResearch() {
        return Optional.ofNullable(this.priceResearch);
    }

    public Optional<Boolean> isAvailable() {
        return Optional.ofNullable(this.isAvailable);
    }

    public static class Builder {
        private String name;
        private Type type;

        private ShareResearch shareResearch;
        private PriceResearch priceResearch;
        private Boolean isAvailable;

        public Builder(String name, Type type) {
            this.name = name;
            this.type = type;
        }

        public Builder shareResearch(ShareResearch shareResearch) {
            this.shareResearch = shareResearch;
            return this;
        }

        public Builder priceResearch(PriceResearch priceResearch) {
            this.priceResearch = priceResearch;
            return this;
        }

        public Builder isAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Marketable build() {
            return new Marketable(this);
        }
    }

    public enum Type {
        PRODUCT,
        CATEGORY
    }

}
