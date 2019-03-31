package com.involves.selecao.service;

import com.involves.selecao.domain.Alert;
import com.involves.selecao.domain.Marketable;
import com.involves.selecao.domain.MonetaryAmount;
import com.involves.selecao.domain.PriceResearch;
import com.involves.selecao.domain.Research;
import com.involves.selecao.domain.Share;
import com.involves.selecao.domain.ShareResearch;
import com.involves.selecao.form.ResearchForm;
import com.involves.selecao.gateway.AlertGateway;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResearchAlertService {

    private final AlertGateway gateway;

    @Autowired
    public ResearchAlertService(AlertGateway gateway) {
        this.gateway = gateway;
    }

    public void processResearch(ResearchForm researchForm) throws IOException {
        Research research = this.createFrom(researchForm);
        List<Alert> alerts = Alert.allTriggeredBy(research);

        this.gateway.save(alerts);
    }

    public List<Alert> all() {
        return this.gateway.findAll();
    }

    private Research createFrom(ResearchForm researchForm) {
        String marketableName =  researchForm.getProduct() != null ? researchForm.getProduct() : researchForm.getCategory();
        Marketable.Type  marketableType = researchForm.getProduct() != null ? Marketable.Type.PRODUCT : Marketable.Type.CATEGORY;
        Marketable.Builder marketableBuilder = new Marketable.Builder(marketableName, marketableType);

        if (researchForm.getShare() != null && researchForm.getStipulatedShare() != null) {
            Share stipulatedShare = new Share(researchForm.getStipulatedShare());
            Share share = new Share(researchForm.getShare());

            marketableBuilder.shareResearch(new ShareResearch(stipulatedShare, share));
        }

        if (researchForm.getPrice() != null && researchForm.getStipulatedPrice() != null) {
            MonetaryAmount stipulatedPrice = new MonetaryAmount(researchForm.getStipulatedPrice());
            MonetaryAmount price = new MonetaryAmount(researchForm.getPrice());

            marketableBuilder.priceResearch(new PriceResearch(stipulatedPrice, price));
        }

        if (researchForm.getIsAvailable() != null) {
            marketableBuilder.isAvailable(researchForm.getIsAvailable());
        }

        return new Research(researchForm.getId(),
                            researchForm.getLabel(),
                            researchForm.getPointOfSale(),
                            marketableBuilder.build());
    }
}
