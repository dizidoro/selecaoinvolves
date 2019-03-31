package com.involves.selecao.domain;

public class Share {

    private final int value;

    public Share(int value) {
        if (value < 0 || value > 100) throw new IllegalArgumentException("share must be between 0 and 100");
        this.value = value;
    }

    public boolean isGreaterThan(Share otherShare) {
        return this.value > otherShare.value;
    }

    public boolean isLowerThan(Share otherShare) {
        return this.value < otherShare.value;
    }

    public Share marginFrom(Share otherShare) {
        return new Share(Math.abs(this.value - otherShare.value));
    }

    public int value() {
        return this.value;
    }
}
