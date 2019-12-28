package com.shopping.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<CartItem> cartItems;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<WishListItem> wishListItems;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public void addCartItems(CartItem theCartItem) { 
		if (cartItems == null) { 
			cartItems = new ArrayList<>();
		}
		cartItems.add(theCartItem);
	}

	public List<WishListItem> getWishListItems() {
		return wishListItems;
	}

	public void setWishListItems(List<WishListItem> wishListItems) {
		this.wishListItems = wishListItems;
	}

	public void addWishListItem(WishListItem theWishListItem) { 
		if (wishListItems == null) { 
			wishListItems = new ArrayList<>();
		}
		wishListItems.add(theWishListItem);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}

}
