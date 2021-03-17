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

	private Long id;
	private ApplianceType type;
	private String name;
	private Double price;
	private String company;
	private String assemblyPlace;
	private Integer quantity;
	private String characteristics;
	private String description;

	private Set<Order> orders = new HashSet<Order>();

	public Good() {}
    
  public Good(ApplianceType type, String name,
      Double price, String company, String assemblyPlace,
      Integer quantity, String characteristics, String description) {
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
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Lob
	@Column(name = "good_characteristics")
	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	@Lob
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

	@Override
  public int hashCode() {
		final int prime = 31;

		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((assemblyPlace == null) ? 0 : assemblyPlace.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((characteristics == null) ? 0 : characteristics.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		
    return result;
  }

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		Good other = (Good) obj;

		if ((id == null && other.id != null)
				|| !id.equals(other.id)) {
			return false;
		}
		if ((type == null && other.type != null)
				|| !type.equals(other.type)) {
			return false;
		}
		if ((name == null && other.name != null)
				|| !name.equals(other.name)) {
			return false;
		}
		if ((price == null && other.price != null)
				|| !price.equals(other.price)) {
			return false;
		}
		if ((company == null && other.company != null)
				|| !company.equals(other.company)) {
			return false;
		}
		if ((assemblyPlace == null && other.assemblyPlace != null)
				|| !assemblyPlace.equals(other.assemblyPlace)) {
			return false;
		}
		if ((quantity == null && other.quantity != null)
				|| !quantity.equals(other.quantity)) {
			return false;
		}
		if ((characteristics == null && other.characteristics != null)
				|| !characteristics.equals(other.characteristics)) {
			return false;
		}
		if ((description == null && other.description != null)
				|| !description.equals(other.description)) {
			return false;
		}
		if ((orders == null && other.orders != null)
				|| !orders.equals(other.orders)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String str =  "User{id=" + id
				+ ", type=" + type
				+ ", name=" + name
				+ ", price=" + price
				+ ", company=" + company
				+ ", assemblyPlace=" + assemblyPlace
				+ ", quantity=" + quantity
				+ ", characteristics=" + characteristics
				+ ", description=" + description
				+ ", orders=" + orders.toString()
				+ "}";
		return str;
	}
}
