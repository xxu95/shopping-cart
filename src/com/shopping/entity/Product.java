package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "category")
	private String category;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String image;

	@Column(name = "price")
	private Float price;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
//	private List<CartItem> cartItems;
//
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
//	private List<WishListItem> wishListItems;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, String category, String description, String image, Float price) {
		this.productName = productName;
		this.category = category;
		this.description = description;
		this.image = image;
		this.price = price;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", price=" + price + "]";
	}

}
