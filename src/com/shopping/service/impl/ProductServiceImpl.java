package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.IProductDao;
import com.shopping.dao.impl.ProductDaoImpl;
import com.shopping.entity.Product;
import com.shopping.service.IProductService;

public class ProductServiceImpl implements IProductService {

	IProductDao productDaoImpl = new ProductDaoImpl();

	@Override
	public List<Product> selectAllLaptop() {
		return productDaoImpl.selectProductByCategory("laptop");
	}

	@Override
	public List<Product> selectAllStorage() {
		return productDaoImpl.selectProductByCategory("storage");
	}

	@Override
	public Product selectProductById(Integer productId) {
		return productDaoImpl.selectProductById(productId);
	}
	
	public static void main(String[] args) {
		System.out.println(new ProductServiceImpl().selectAllStorage());
	}
}
