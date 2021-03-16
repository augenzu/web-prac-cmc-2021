package entity;

import type.ApplianceType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "good")
public class Good implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private ApplianceType type;
	private String name;
	private double price;
	private String company;
	private String assemblyPlace;
	private int quantity;
	private String characteristics;
	private String description;

	private Set<Order> orders = new HashSet<Order>();

	public Good() {}
    
  public Good(ApplianceType type, String name,
      double price, String company, String assemblyPlace,
      int quantity, String characteristics, String description) {
		this.type = type;
		this.name = name;
		this.price = price;
		this.company = company;
		this.assemblyPlace = assemblyPlace;
		this.quantity = quantity;
		this.characteristics = characteristics;
		this.description = description;
  }

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	@Id
	@Column(name = "good_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@Column(name = "good_type", nullable = false)
	public ApplianceType getType() {
		return type;
	}

	@Column(name = "good_name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "good_price", nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "good_company", length = 50)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "good_assembly_place", length = 50)
	public String getAssemblyPlace() {
		return assemblyPlace;
	}

	public void setAssemblyPlace(String assemblyPlace) {
		this.assemblyPlace = assemblyPlace;
	}

	@Column(name = "good_quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "good_characteristics")
	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	@Column(name = "good_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_good",
			joinColumns = @JoinColumn(name = "good_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id")
	)
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
			this.orders = orders;
	}
}
