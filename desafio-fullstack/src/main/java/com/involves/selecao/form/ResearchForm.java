package com.involves.selecao.form;
import java.math.BigDecimal;

public class ResearchForm {

	private long id;
	private String label;
	private String pointOfSale;
	private String product;
	private String category;
	private Integer stipulatedShare;
	private Integer share;
	private BigDecimal stipulatedPrice;
	private BigDecimal price;
	private Boolean isAvailable;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPointOfSale() {
		return pointOfSale;
	}

	public void setPointOfSale(String pointOfSale) {
		this.pointOfSale = pointOfSale;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getStipulatedShare() {
		return stipulatedShare;
	}

	public void setStipulatedShare(Integer stipulatedShare) {
		this.stipulatedShare = stipulatedShare;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public BigDecimal getStipulatedPrice() {
		return stipulatedPrice;
	}

	public void setStipulatedPrice(BigDecimal stipulatedPrice) {
		this.stipulatedPrice = stipulatedPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
