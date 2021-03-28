package backend.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "\"user\"")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", nullable = false, length = 70)
	private String name;

	@Column(name = "user_address", nullable = false, length = 50)
	private String address;

	@Column(name = "user_email", nullable = false, length = 30)
	private String email;

	@Column(name = "user_number", length = 20)
	private String number;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Order> orders = new HashSet<Order>();

	public User() {}

	public User(String name, String address, String email, String number) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.number = number;
	}

	public void addOrder(Order order) {
		order.setUser(this);
		orders.add(order);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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

		User other = (User) obj;

		if ((id == null && other.id != null)
				|| !id.equals(other.id)) {
			return false;
		}
		if ((name == null && other.name != null)
				|| !name.equals(other.name)) {
			return false;
		}
		if ((address == null && other.address != null)
				|| !address.equals(other.address)) {
			return false;
		}
		if ((email == null && other.email != null)
				|| !email.equals(other.email)) {
			return false;
		}
		if ((number == null && other.number != null)
				|| !number.equals(other.number)) {
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
				+ ", name='" + name
				+ "', address='" + address
				+ "', email=" + email
				+ ", number='" + number
				// + ", orders=" + orders.toString()
				+ "'}";
		return str;
	}
}
