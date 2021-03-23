package backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "good")
public class Good implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "good_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "app_type_id")
	private AppType app_type;

	@Column(name = "good_name", nullable = false, length = 100)
	private String name;

	@Column(name = "good_price", nullable = false)
	private Double price;

	@Column(name = "good_company", length = 50)
	private String company;

	@Column(name = "good_assembly_place", length = 50)
	private String assemblyPlace;

	@Column(name = "good_quantity", nullable = false)
	private Integer quantity;

	@Lob
	@Column(name = "good_characteristics")
	private String characteristics;

	@Lob
	@Column(name = "good_description")
	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_good",
			joinColumns = @JoinColumn(name = "good_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id")
	)
	private Set<Order> orders = new HashSet<Order>();

	public Good() {}
    
  	public Good(AppType app_type, String name,
      Double price, String company, String assemblyPlace,
      Integer quantity, String characteristics, String description) {
		this.app_type = app_type;
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

	public long getId() {
		return id;
	}

	public AppType getAppType() {
		return app_type;
	}

	public void setAppType(AppType app_type) {
		this.app_type = app_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAssemblyPlace() {
		return assemblyPlace;
	}

	public void setAssemblyPlace(String assemblyPlace) {
		this.assemblyPlace = assemblyPlace;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
		result = prime * result + ((app_type == null) ? 0 : app_type.hashCode());
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
		if ((app_type == null && other.app_type != null)
				|| !app_type.equals(other.app_type)) {
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
				+ ", app_type=" + app_type
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
