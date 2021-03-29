package backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderedAt;

	@ManyToOne()
	@JoinColumn(name = "status_id")
	private Status status;

	@Column(name = "order_delivery_address", nullable = false, length = 50)
	private String deliveryAddress;

	@Column(name = "order_deliver_on")
	@Temporal(TemporalType.DATE)
	private Date deliverOn;

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

	public Order(Date orderedAt, Status status, String deliveryAddress, Date deliverOn) {
		this.orderedAt = orderedAt;
		this.status = status;
		this.deliveryAddress = deliveryAddress;
		this.deliverOn = deliverOn;
	}

	public void addGood(Good good) {
		goods.add(good);
	}

	public void removeGood(Good good) {
		goods.remove(good);
	}

	public Long getId() {
		return id;
	}

	public Date getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(Date orderedAt) {
		this.orderedAt = orderedAt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getDeliverOn() {
		return deliverOn;
	}

	public void setDeliverOn(Date deliverOn) {
		this.deliverOn = deliverOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
			this.goods = goods;
	}

	@Override
  	public int hashCode() {
		final int prime = 31;

		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderedAt == null) ? 0 : orderedAt.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + ((deliverOn == null) ? 0 : deliverOn.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((goods == null) ? 0 : goods.hashCode());
		
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
				|| !id.equals(other.id)) {
			return false;
		}
		if ((orderedAt == null && other.orderedAt != null)
				|| !orderedAt.equals(other.orderedAt)) {
			return false;
		}
		if ((status == null && other.status != null)
				|| !status.equals(other.status)) {
			return false;
		}
		if ((deliveryAddress == null && other.deliveryAddress != null)
				|| !deliveryAddress.equals(other.deliveryAddress)) {
			return false;
		}
		if ((deliverOn == null && other.deliverOn != null)
				|| !deliverOn.equals(other.deliverOn)) {
			return false;
		}
		if ((user == null && other.user != null)
				|| !user.equals(other.user)) {
			return false;
		}
		if ((goods == null && other.goods != null)
				|| !goods.equals(other.goods)) {
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
				// + ", goods=" + goods.toString()
				+ "}";
		return str;
	}
}
