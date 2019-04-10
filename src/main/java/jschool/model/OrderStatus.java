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
@Table(name="ORDER_STATUS")
public class OrderStatus {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="status")
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="orderStatus", cascade=CascadeType.ALL)
	private Set<Order> orders;
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderStatus)) return false;
		OrderStatus that = (OrderStatus) o;
		return getId() == that.getId() &&
				Objects.equals(getStatus(), that.getStatus());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getStatus());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String name) {
		this.status = name;
	}



	@Override
	public String toString(){
		return "id="+id+", name="+status;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
