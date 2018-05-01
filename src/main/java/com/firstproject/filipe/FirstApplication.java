package com.firstproject.filipe;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.firstproject.filipe.domain.Address;
import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.domain.City;
import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.domain.Demand;
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
import com.firstproject.filipe.repositories.PaymentRepository;
import com.firstproject.filipe.repositories.ProductRepository;
import com.firstproject.filipe.repositories.StateRepository;

@SpringBootApplication
public class FirstApplication implements CommandLineRunner {

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
	
	public static void main(String[] args) {
		SpringApplication.run(FirstApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 =  new Category(null, "Computer");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		State state1 = new State(null,"Piauí");
		State state2 = new State(null,"Ceará");
		
		City c1 = new City(null, "Teresina", state1);
		City c2 = new City(null, "Fortaleza", state2);
		City c3 = new City(null, "Parnaíba", state1);
		
		state1.getCities().addAll(Arrays.asList(c1,c3));
		state2.getCities().addAll(Arrays.asList(c2));
		
		
		stateRepository.saveAll(Arrays.asList(state1,state2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "12345678901", ClientType.LEGALPERSON);
		client1.getPhones().addAll(Arrays.asList("8699999999","8692929229"));
		
		Address add1 = new Address(null, "Street Flowers", "300", "", "Jockey", "64000-000", client1, c1);
		Address add2 = new Address(null, "Avenue Matos", "200", "", "Jockey", "64001-000", client1, c2);
		
		client1.getAddresses().addAll(Arrays.asList(add1,add2));
		
		clientRepository.save(client1);
		addressRepository.saveAll(Arrays.asList(add1,add2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Demand demand1 = new Demand(null,sdf.parse("30/09/2017 10:30"),client1,add1);
		Demand demand2 = new Demand(null,sdf.parse("10/10/2017 22:10"),client1,add2);
		
		Payment pay1 = new PaymentCard(null,PaymentState.SETTLED.getCode(),demand1,6);
		demand1.setPayment(pay1);
		Payment pay2 = new PaymentSlip(null, PaymentState.PENDING.getCode(), demand2, sdf.parse("02/02/2018 15:13"), null);
		demand2.setPayment(pay2);
		

		client1.getOrders().addAll(Arrays.asList(demand1,demand2));

		demandRepository.saveAll(Arrays.asList(demand1,demand2));
		
		paymentRepository.saveAll(Arrays.asList(pay1,pay2));
		
	}
}
