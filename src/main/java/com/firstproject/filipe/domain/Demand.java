package com.firstproject.filipe.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.firstproject.filipe.domain.enums.PaymentState;
import com.firstproject.filipe.repositories.PaymentRepository;

@Entity
public class Demand implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date orderTime;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="demand")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="delivery_address_id")
	private Address deliveryAddress;
	
	@OneToMany(mappedBy="id.demand")
	private Set<ItemDemand> itens = new HashSet<>();
	
	public Demand() {
		
	}

	public Demand(Integer id, Date orderTime, Client client, Address deliveryAddress) {
		super();
		this.id = id;
		this.orderTime = orderTime;
		this.client = client;
		this.deliveryAddress = deliveryAddress;
	}

	public double getTotalValor() {
		double sum=0.0;
		for(ItemDemand item: itens) {
			sum  += item.getSubTotal();
		}
		return sum;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<ItemDemand> getItens() {
		return itens;
	}

	public void setItens(Set<ItemDemand> itens) {
		this.itens = itens;
	}


	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Demand other = (Demand) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Demand number:");
		builder.append(getId());
		builder.append(", Instant:");
		builder.append(sdf.format(getOrderTime()));
		builder.append(", Client:");
		builder.append(getClient().getName());
		builder.append(", Payment situation:");
		builder.append(getPayment().getPaymentState().getDescription());
		builder.append("\n Details:\n");
		for(ItemDemand id : getItens()) {

			builder.append(id.toString());
		}
		builder.append("\nValor total:");
		builder.append(nf.format(getTotalValor()));
		return builder.toString();
	}
	
	
	
	
	
}
