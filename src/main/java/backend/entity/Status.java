package backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "\"status\"")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
	@Column(name = "status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", nullable = false, length = 20)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "status_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Order> orders = new HashSet<Order>();

    public Status() {}

    public Status(String name) {
        this.name = name;
    }

	public void addOrder(Order order) {
		order.setStatus(this);
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

		Status other = (Status) obj;

		if ((id == null && other.id != null)
				|| !id.equals(other.id)) {
			return false;
		}
		if ((name == null && other.name != null)
				|| !name.equals(other.name)) {
			return false;
		}
		return true;
	}

    @Override
	public String toString() {
		String str =  "Status{id=" + id
				+ ", name=" + name
				+ "}";
		return str;
	}
}
