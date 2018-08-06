package com.firstproject.filipe.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemDemand implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemDemandPK id = new ItemDemandPK();
	
	private Double discount;
	private Integer quantity;
	private Double price;
	
	public ItemDemand() {
		
	}

	public ItemDemand(Product product, Demand demand, Double discount, Integer quantity, Double price) {
		super();
		id.setDemand(demand);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	} 
	
	public double getSubTotal() {
		return(price-discount)*quantity;
	}
	
	
	@JsonIgnore
	public Demand getDemand(){
		return id.getDemand();
	}
	
	public void setDemand(Demand demand) {
		id.setDemand(demand);
		
	}
	
	public Product getProduct(){
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public ItemDemandPK getId() {
		return id;
	}

	public void setId(ItemDemandPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(", Quantity:");
		builder.append(getQuantity());
		builder.append(", Unit Price:");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal:");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
	

}
