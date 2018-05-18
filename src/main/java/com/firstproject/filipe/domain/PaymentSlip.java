package com.firstproject.filipe.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.firstproject.filipe.domain.enums.PaymentState;


@Entity
@JsonTypeName("paymentSlip")
public class PaymentSlip extends Payment {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date payDate;
	
	public PaymentSlip() {
		
	}

	public PaymentSlip(Integer id, PaymentState paymentState, Demand demand, Date dueDate, Date payDate) {
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
