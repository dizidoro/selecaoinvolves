package com.involves.selecao;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.domain.Alert;
import com.involves.selecao.form.ResearchForm;
import com.involves.selecao.response.AlertResponse;
import com.involves.selecao.service.ResearchAlertService;

@RestController
@RequestMapping("/api/alerts")
public class ResearchAlertController {

	@Autowired
	private ResearchAlertService researchAlertService;

	@GetMapping
    public List<AlertResponse> alerts() {
		return researchAlertService.all().stream().map(this::from).collect(Collectors.toList());
    }

    private AlertResponse from(Alert alert) {
		AlertResponse response =  new AlertResponse();
		response.setPointOfSale(alert.pointOfSale());
		response.setMarketable(alert.marketableName());
		response.setType(alert.type());
		alert.priceMargin().ifPresent(priceMargin -> response.setPriceMargin(priceMargin.value()));
		alert.shareMargin().ifPresent(shareMargin -> response.setShareMargin(shareMargin.value()));
		return response;
	}
	
	@PostMapping("/process-research")
    public void handleNewResearch(@RequestBody ResearchForm researchForm) {
		try {
			this.researchAlertService.processResearch(researchForm);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
