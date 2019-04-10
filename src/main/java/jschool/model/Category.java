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
@Table(name="CATEGORY")
public class Category {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="name")
	private String name;


	@Column(name="Parent_Category_id")
	private Integer parentId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="category", cascade=CascadeType.ALL)
	private Set<Product> products;

	public int getParentId() {
		return Objects.isNull(parentId) ? 0 : parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString(){
		return "id="+id+", name="+name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Category)) return false;
		Category category = (Category) o;
		return getId() == category.getId() &&
				Objects.equals(getName(), category.getName()) &&
				Objects.equals(getParentId(), category.getParentId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getParentId());
	}
}
