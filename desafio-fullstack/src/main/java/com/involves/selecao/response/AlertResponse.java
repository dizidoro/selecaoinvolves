package com.involves.selecao.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.involves.selecao.domain.AlertType;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AlertResponse {

    private AlertType type;
    private String marketable;
    private String pointOfSale;
    private Integer shareMargin;
    private BigDecimal priceMargin;

    public AlertType getType() {
        return this.type;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    public void setPriceMargin(BigDecimal priceMargin) {
        this.priceMargin = priceMargin;
    }

    public String getMarketable() {
        return marketable;
    }

    public void setMarketable(String marketable) {
        this.marketable = marketable;
    }

    public String getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(String pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    public Integer getShareMargin() {
        return shareMargin;
    }

    public void setShareMargin(Integer shareMargin) {
        this.shareMargin = shareMargin;
    }

    public BigDecimal getPriceMargin() {
        return priceMargin;
    }
}
