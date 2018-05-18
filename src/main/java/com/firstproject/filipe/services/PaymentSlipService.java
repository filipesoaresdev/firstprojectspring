package com.firstproject.filipe.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.PaymentSlip;

@Service
public class PaymentSlipService {

	public void fillPaymentSlip(PaymentSlip paymentSlip, Date demandTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(demandTime);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		paymentSlip.setDueDate(cal.getTime()); 
	}
	
	
}
