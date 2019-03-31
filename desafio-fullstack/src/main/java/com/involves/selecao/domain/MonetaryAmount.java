package com.involves.selecao.domain;

import java.math.BigDecimal;

public class MonetaryAmount {

    private BigDecimal value;

    public MonetaryAmount(BigDecimal value) {
        this.value = value;
    }

    public boolean isGreaterThan(MonetaryAmount otherAmount) {
        return this.value.compareTo(otherAmount.value) > 0;
    }

    public boolean isLowerThan(MonetaryAmount otherAmount) {
        return this.value.compareTo(otherAmount.value) < 0;
    }

    public MonetaryAmount marginFrom(MonetaryAmount otherAmount) {
        return new MonetaryAmount(this.value.subtract(otherAmount.value).abs());
    }

    public BigDecimal value() {
        return this.value;
    }
}
