package backend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_good")
public class OrderGood implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_good_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne()
	@JoinColumn(name = "order_id")
	private Order order;

    @ManyToOne()
	@JoinColumn(name = "good_id")
	private Good good;

    @Column(name = "order_good_quantity", nullable = false)
	private Integer quantity = 1;

    public OrderGood() {}

    public OrderGood(Order order, Good good, Integer quantity) {
        this.order = order;
        this.good = good;
        this.quantity = quantity;
    }

    public Long getId() {
		return id;
	}

    public Order getOrder() {
        return order;
    }

    public Good getGood() {
        return good;
    }

    public Integer getQuantity() {
		return quantity;
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

		OrderGood other = (OrderGood) obj;

		if ((id == null && other.id != null)
				|| (id != null && !id.equals(other.id))) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String str =  "OrderGood{id=" + id
				+ ", orderId='" + order.getId()
				+ "', goodId=" + good.getId()
				+ ", quantity='" + quantity
				+ "}";
		return str;
	}
}
