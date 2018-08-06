package com.firstproject.filipe.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.domain.Demand;
import com.firstproject.filipe.domain.ItemDemand;
import com.firstproject.filipe.domain.PaymentSlip;
import com.firstproject.filipe.domain.Product;
import com.firstproject.filipe.domain.enums.PaymentState;
import com.firstproject.filipe.repositories.ClientRepository;
import com.firstproject.filipe.repositories.DemandRepository;
import com.firstproject.filipe.repositories.ItemDemandRepository;
import com.firstproject.filipe.repositories.PaymentRepository;
import com.firstproject.filipe.repositories.ProductRepository;

@Service
public class DemandService {
	
	@Autowired
	private DemandRepository demandRepository;
	@Autowired
	private PaymentSlipService paymentSlipService;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ItemDemandRepository itemDemandRepository;
	 
	@Autowired
	private EmailService emailService;
	
	public Demand find(Integer id) {
		Optional<Demand> demand = demandRepository.findById(id);
		return demand.orElse(null);
	}
	
	@Transactional
	public Demand insert( Demand demand) {
		
		demand.setId(null);
		demand.setOrderTime(new Date());
		demand.setClient(
				(
						(Optional<Client>)clientRepository
						.findById(demand.getClient().getId()
								)
						).orElse(null)
				);
		demand.getPayment().setPaymentState(PaymentState.PENDING);
		demand.getPayment().setDemand(demand);
		if(demand.getPayment() instanceof PaymentSlip) {
			PaymentSlip payment = (PaymentSlip) demand.getPayment();
			paymentSlipService.fillPaymentSlip(payment, demand.getOrderTime());
			
			
		}
		
		demand = demandRepository.save(demand);
		paymentRepository.save(demand.getPayment());
		
		for(ItemDemand item : demand.getItens()) {
			item.setDiscount(0.0);
			item.setProduct((
					(Optional<Product>)productRepository
					.findById(item.getProduct().getId()
							)
					).orElse(null));
			item.setPrice(item.getProduct().getPrice());
			item.setDemand(demand);
		}
		itemDemandRepository.saveAll(demand.getItens());
		emailService.sendOrderConfirmationEmail(demand);
		
		return demand;
	}
	
}
