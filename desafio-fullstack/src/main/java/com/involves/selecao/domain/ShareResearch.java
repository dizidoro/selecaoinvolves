package com.involves.selecao.domain;

public class ShareResearch {

    private final Share stipulatedShare;
    private final Share actualShare;

    public ShareResearch(Share stipulatedShare, Share actualShare) {
        this.stipulatedShare = stipulatedShare;
        this.actualShare = actualShare;
    }

    public Share stipulatedShare() {
        return this.stipulatedShare;
    }

    public Share actualShare() {
        return this.actualShare;
    }

    public Share margin() {
        return this.stipulatedShare.marginFrom(this.actualShare);
    }

    public boolean shareIsAboveStipulated() {
        return this.actualShare.isGreaterThan(this.stipulatedShare);
    }

    public boolean shareIsBelowStipulated() {
        return this.actualShare.isLowerThan(this.stipulatedShare);
    }
}
