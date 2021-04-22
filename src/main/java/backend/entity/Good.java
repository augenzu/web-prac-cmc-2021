package backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "good")
public class Good implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "good_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "app_type_id")
	private AppType appType;

	@Column(name = "good_name", nullable = false, length = 100)
	private String name;

	@Column(name = "good_price", nullable = false)
	private Double price;

	@Column(name = "good_company", length = 50)
	private String company;

	@Column(name = "good_assembly_place", length = 50)
	private String assemblyPlace;

	@Column(name = "good_quantity", nullable = false)
	private Integer quantity = 0;

	@Column(name = "good_description")
	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_good",
			joinColumns = @JoinColumn(name = "good_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id")
	)
	private List<Order> orders = new ArrayList<>();

	public Good() {}
    
  	public Good(AppType appType, String name,
      Double price, String company, String assemblyPlace,
      Integer quantity, String description) {
		this.appType = appType;
		this.name = name;
		this.price = price;
		this.company = company;
		this.assemblyPlace = assemblyPlace;
		this.quantity = quantity;
		this.description = description;
		appType.addGood(this);
  }

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	public Long getId() {
		return id;
	}

	public AppType getAppType() {
		return appType;
	}

	public void setAppType(AppType appType) {
		this.appType.removeGood(this);
		this.appType = appType;
		appType.addGood(this);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
  	public int hashCode() {
		final int prime = 31;

		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
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
				|| (id != null && !id.equals(other.id))) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String str =  "Good{id=" + id
				+ ", appType=" + appType
				+ ", name='" + name
				+ "', price=" + price
				+ ", company='" + company
				+ "', assemblyPlace='" + assemblyPlace
				+ "', quantity=" + quantity
				+ ", description='" + description
				+ "'}";
		return str;
	}
}
