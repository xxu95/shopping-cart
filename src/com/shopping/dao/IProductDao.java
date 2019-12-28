package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Product;

public interface IProductDao {

	List<Product> selectProductByCategory(String category);
	
	Product selectProductById(Integer productId);
}
