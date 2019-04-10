package jschool.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author pankaj
 *
 */
@Entity
@Table(name="SHIPPING_TYPE")
public class ShippingType {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="type")
	private String type;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="shippingType", cascade=CascadeType.ALL)
	private Set<Order> orders;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ShippingType)) return false;
		ShippingType that = (ShippingType) o;
		return getId() == that.getId() &&
				Objects.equals(getType(), that.getType());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getType());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String name) {
		this.type = name;
	}



	@Override
	public String toString(){
		return "id="+id+", name="+type;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
