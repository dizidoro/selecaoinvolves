package com.involves.selecao.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Alert {

    private final AlertType type;
    private final String marketableName;
    private final String pointOfSale;

    private Share shareMargin;
    private MonetaryAmount priceMargin;

    public Alert(AlertType type, String marketableName, String pointOfSale) {
        this.type = type;
        this.marketableName = marketableName;
        this.pointOfSale = pointOfSale;
    }

    public Alert(AlertType type, String marketableName, String pointOfSale, Share margin) {
        this.type = type;
        this.marketableName = marketableName;
        this.pointOfSale = pointOfSale;
        this.shareMargin = margin;
    }

    public Alert(AlertType type, String marketableName, String pointOfSale, MonetaryAmount margin) {
        this.type = type;
        this.marketableName = marketableName;
        this.pointOfSale = pointOfSale;
        this.priceMargin = margin;
    }

    public String marketableName() {
        return marketableName;
    }

    public String pointOfSale() {
        return pointOfSale;
    }

    public AlertType type() {
        return this.type;
    }

    public Optional<Share> shareMargin() {
        return Optional.ofNullable(this.shareMargin);
    }

    public Optional<MonetaryAmount> priceMargin() {
        return Optional.ofNullable(this.priceMargin);
    }

    public static List<Alert> allTriggeredBy(Research research) {
        return Stream.of(AlertType.values())
            .filter(alertType -> alertType.triggeredBy(research))
            .map(alertType -> create(alertType, research))
            .collect(Collectors.toList());
    }

    private static Alert create(AlertType type, Research research) {
        switch (type) {
            case PRICE_ABOVE_STIPULATED:
            case PRICE_BELOW_STIPULATED:
                return new Alert(type, research.marketable().name(), research.pointOfSale(), research.marketable().priceResearch().get().margin());
            case SHARE_ABOVE_STIPULATED:
            case SHARE_BELOW_STIPULATED:
                return new Alert(type, research.marketable().name(), research.pointOfSale(), research.marketable().shareResearch().get().margin());
            default:
                return new Alert(type, research.marketable().name(), research.pointOfSale());
        }
    }
}
