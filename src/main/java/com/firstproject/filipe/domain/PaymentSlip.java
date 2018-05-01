package com.firstproject.filipe.domain;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class PaymentSlip extends Payment {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dueDate;
	private Date payDate;
	
	public PaymentSlip() {
		
	}

	public PaymentSlip(Integer id, Integer paymentState, Demand demand, Date dueDate, Date payDate) {
		super(id, paymentState, demand);
		this.dueDate = dueDate;
		this.payDate = payDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	

}
