package backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "app_type")
public class AppType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
	@Column(name = "app_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app_type_name", nullable = false, length = 30)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "app_type_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Good> goods = new HashSet<Good>();

    public AppType() {}

    public AppType(String name) {
        this.name = name;
    }

	public void addGood(Good good) {
		good.setAppType(this);
		goods.add(good);
	}

	public void removeGood(Good good) {
		goods.remove(good);
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

		AppType other = (AppType) obj;

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
		String str =  "AppType{id=" + id
				+ ", name=" + name
				+ "}";
		return str;
	}
}
