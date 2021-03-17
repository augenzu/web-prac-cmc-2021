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

	private Long id;
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
	public Long getId() {
		return id;
	}

	public Timestamp getTime() {
		return time;
	}

	@Column(name = "order_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.DATE)
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

	@Override
  public int hashCode() {
		final int prime = 31;

		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
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
		if ((time == null && other.time != null)
				|| !time.equals(other.time)) {
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
		if ((deliveryDate == null && other.deliveryDate != null)
				|| !deliveryDate.equals(other.deliveryDate)) {
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
		String str =  "User{id=" + id
				+ ", time=" + time
				+ ", status=" + status
				+ ", deliveryAddress=" + deliveryAddress
				+ ", deliveryDate=" + deliveryDate
				+ ", user=" + user.toString()
				+ ", goods=" + goods.toString()
				+ "}";
		return str;
	}
}
