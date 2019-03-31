package com.involves.selecao.domain;

public enum AlertType {

    PRICE_ABOVE_STIPULATED {
        @Override
        public boolean triggeredBy(Research research) {
            return research.marketable().priceResearch()
                .map(PriceResearch::priceIsAboveStipulated)
                .orElse(false);
        }
    },
    PRICE_BELOW_STIPULATED {
        @Override
        public boolean triggeredBy(Research research) {
            return research.marketable().priceResearch()
                .map(PriceResearch::priceIsBelowStipulated)
                .orElse(false);
        }
    },
    SHARE_ABOVE_STIPULATED {
        @Override
        public boolean triggeredBy(Research research) {
            return research.marketable().shareResearch()
                .map(ShareResearch::shareIsAboveStipulated)
                .orElse(false);
        }
    },
    SHARE_BELOW_STIPULATED {
        @Override
        public boolean triggeredBy(Research research) {
            return research.marketable().shareResearch()
                .map(ShareResearch::shareIsBelowStipulated)
                .orElse(false);
        }
    },
    RUPTURE {
        @Override
        public boolean triggeredBy(Research research) {
            return research.marketable().isAvailable()
                .map(isAvailable -> !isAvailable).orElse(false);
        }
    };

    public abstract boolean triggeredBy(Research research);

}
