package com.firstproject.filipe.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemDemand implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	
	public Demand getDemand() {
		return id.getDemand();
	}
	
	public Product getProduct(){
		return id.getProduct();
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
	
	
	

}
