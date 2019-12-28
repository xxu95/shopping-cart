package com.shopping.service;

import java.util.List;

import com.shopping.entity.Product;

public interface IProductService {

	List<Product> selectAllLaptop();

	List<Product> selectAllStorage();

	Product selectProductById(Integer productId);

}
