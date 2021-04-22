package backend.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "\"order\"")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_ordered_at", nullable = false)
	private LocalDateTime orderedAt;

	@ManyToOne()
	@JoinColumn(name = "status_id")
	private Status status = new Status("processing");

	@Column(name = "order_delivery_address", nullable = false, length = 50)
	private String deliveryAddress;

	@Column(name = "order_deliver_on")
	private LocalDate deliverOn;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinTable(name = "order_good",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "good_id")
	)
	private List<Good> goods = new ArrayList<>();

	public Order() {}

	public Order(LocalDateTime orderedAt, Status status,
			String deliveryAddress, LocalDate deliverOn,
			User user) {
		this.orderedAt = orderedAt;
		this.status = status;
		this.deliveryAddress = deliveryAddress;
		this.deliverOn = deliverOn;
		this.user = user;
		status.addOrder(this);
		user.addOrder(this);
	}

	public void addGood(Good good) {
		goods.add(good);
		good.addOrder(this);
	}

	public void removeGood(Good good) {
		goods.remove(good);
		good.removeOrder(this);
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status.removeOrder(this);
		this.status = status;
		status.addOrder(this);
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDate getDeliverOn() {
		return deliverOn;
	}

	public void setDeliverOn(LocalDate deliverOn) {
		this.deliverOn = deliverOn;
	}

	public User getUser() {
		return user;
	}

	public List<Good> getGoods() {
		return goods;
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

		Order other = (Order) obj;

		if ((id == null && other.id != null)
				|| (id != null && !id.equals(other.id))) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String str =  "Order{id=" + id
				+ ", orderedAt='" + orderedAt
				+ "', status=" + status
				+ ", deliveryAddress='" + deliveryAddress
				+ "', deliverOn=" + deliverOn
				+ ", user=" + user.toString()
				+ "}";
		return str;
	}
}
