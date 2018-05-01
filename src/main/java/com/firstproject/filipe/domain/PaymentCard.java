package com.firstproject.filipe.domain;

import javax.persistence.Entity;


@Entity
public class PaymentCard extends Payment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer installmentsNumber;
	
	public PaymentCard() {
		super();
	}
	
	
	public PaymentCard(Integer id, Integer paymentState, Demand demand, Integer installmentsNumber) {
		super(id, paymentState, demand);
		this.installmentsNumber = installmentsNumber;
	}


	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}
	
	
	
}
