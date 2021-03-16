package entity;

import type.StatusType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private Timestamp time;
	private StatusType status;
	private String deliveryAddress;
	private Date deliveryDate;

	private User user;

	private Set<Good> goods = new HashSet<Good>();

	public Order() {}

	public Order(Timestamp time, StatusType status, String deliveryAddress, Date deliveryDate) {
		this.time = time;
		this.status = status;
		this.deliveryAddress = deliveryAddress;
		this.deliveryDate = deliveryDate;
	}

	public void addGood(Good good) {
		goods.add(good);
	}

	public void removeGood(Good good) {
		goods.remove(good);
	}

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public Timestamp getTime() {
		return time;
	}

	@Column(name = "order_time", nullable = false)
	// @Temporal
	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "order_status", nullable = false)
	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	@Column(name = "order_delivery_address", nullable = false, length = 50)
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Column(name = "order_delivery_date")
	// @Temporal
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@ManyToOne()
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany(cascade = CascadeType.ALL)  // cannot find orphanRemoval
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinTable(name = "order_good",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "good_id")
	)
	public Set<Good> getGoods() {
		return goods;
	}

	public void setGoods(Set<Good> goods) {
			this.goods = goods;
	}
}
