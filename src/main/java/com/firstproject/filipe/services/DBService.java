package com.firstproject.filipe.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Address;
import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.domain.City;
import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.domain.Demand;
import com.firstproject.filipe.domain.ItemDemand;
import com.firstproject.filipe.domain.Payment;
import com.firstproject.filipe.domain.PaymentCard;
import com.firstproject.filipe.domain.PaymentSlip;
import com.firstproject.filipe.domain.Product;
import com.firstproject.filipe.domain.State;
import com.firstproject.filipe.domain.enums.ClientType;
import com.firstproject.filipe.domain.enums.PaymentState;
import com.firstproject.filipe.repositories.AddressRepository;
import com.firstproject.filipe.repositories.CategoryRepository;
import com.firstproject.filipe.repositories.CityRepository;
import com.firstproject.filipe.repositories.ClientRepository;
import com.firstproject.filipe.repositories.DemandRepository;
import com.firstproject.filipe.repositories.ItemDemandRepository;
import com.firstproject.filipe.repositories.PaymentRepository;
import com.firstproject.filipe.repositories.ProductRepository;
import com.firstproject.filipe.repositories.StateRepository;

@Service
public class DBService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private DemandRepository demandRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemDemandRepository itemDemandRepository;
	
	public void instantiateTestDataBase() throws ParseException {

		Category cat1 =  new Category(null, "Computer");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Kitchen");
		Category cat4 = new Category(null, "Home");
		Category cat5 = new Category(null, "Geek");
		Category cat6 = new Category(null, "Games");
		Category cat7 = new Category(null, "Toys");
		Category cat8 = new Category(null, "Cleaning");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Office Table", 300.00);
		Product p4 = new Product(null, "Towel", 50.00);
		Product p5 = new Product(null, "True Color TV", 1200.00);
		Product p6 = new Product(null, "Quilt", 200.00);
		Product p7 = new Product(null, "Roçadeira", 800.00);
		Product p8 = new Product(null, "Abajour", 100.00);
		Product p9 = new Product(null, "Pendente", 180.00);
		Product p10 = new Product(null, "Shampoo", 90.00);
		Product p11 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2,p4));
		cat3.getProducts().addAll(Arrays.asList(p5,p6));
		cat4.getProducts().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9,p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		
		p1.getCategories().addAll(Arrays.asList(cat1,cat4));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategories().addAll(Arrays.asList(cat1,cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));

		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		State state1 = new State(null,"Piauí");
		State state2 = new State(null,"Ceará");
		
		City c1 = new City(null, "Teresina", state1);
		City c2 = new City(null, "Fortaleza", state2);
		City c3 = new City(null, "Parnaíba", state1);
		
		state1.getCities().addAll(Arrays.asList(c1,c3));
		state2.getCities().addAll(Arrays.asList(c2));
		
		
		stateRepository.saveAll(Arrays.asList(state1,state2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Client client1 = new Client(null, "Maria Silva", "filipe.shaulin@gmail.com", "12345678901", ClientType.LEGALPERSON);
		client1.getPhones().addAll(Arrays.asList("8699999999","8692929229"));
		
		Address add1 = new Address(null, "Street Flowers", "300", "", "Jockey", "64000-000", client1, c1);
		Address add2 = new Address(null, "Avenue Matos", "200", "", "Jockey", "64001-000", client1, c2);
		
		client1.getAddresses().addAll(Arrays.asList(add1,add2));
		
		clientRepository.save(client1);
		addressRepository.saveAll(Arrays.asList(add1,add2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Demand demand1 = new Demand(null,sdf.parse("30/09/2017 10:30"),client1,add1);
		Demand demand2 = new Demand(null,sdf.parse("10/10/2017 22:10"),client1,add2);
		
		Payment pay1 = new PaymentCard(null,PaymentState.SETTLED,demand1,6);
		demand1.setPayment(pay1);
		Payment pay2 = new PaymentSlip(null, PaymentState.PENDING, demand2, sdf.parse("02/02/2018 15:13"), null);
		demand2.setPayment(pay2);
		

		client1.getDemands().addAll(Arrays.asList(demand1,demand2));

		demandRepository.saveAll(Arrays.asList(demand1,demand2));
		paymentRepository.saveAll(Arrays.asList(pay1,pay2));
		
		ItemDemand itemDemand1 = new ItemDemand(p1, demand1, 0.00, 1, 2000.00);
		ItemDemand itemDemand2 = new ItemDemand(p3, demand1, 0.00, 2, 80.00);
		ItemDemand itemDemand3 = new ItemDemand(p2, demand2, 100.00, 1, 800.00);
		
		demand1.getItens().addAll(Arrays.asList(itemDemand1,itemDemand2));
		demand2.getItens().addAll(Arrays.asList(itemDemand3));

		p1.getItens().addAll(Arrays.asList(itemDemand1));
		p2.getItens().addAll(Arrays.asList(itemDemand3));
		p3.getItens().addAll(Arrays.asList(itemDemand2));
		
		itemDemandRepository.saveAll(Arrays.asList(itemDemand1,itemDemand2,itemDemand3));
		
	}

}
