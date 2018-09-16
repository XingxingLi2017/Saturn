package saturn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saturn.dao.ProductDao;
import saturn.model.Product;
import saturn.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	public List<Product> getAllProducts() {
		System.out.println("ProductServiceImpl.getAllProducts");
		return productDao.getAllProducts();
	}

	public Product getProductById(int productId) {
		System.out.println("ProductServiceImpl.getProductById");
		return productDao.getProductById(productId);
	}

	public void deleteProduct(int productId) {
		System.out.println("ProductServiceImpl.deleteProduct");
		productDao.deleteProduct(productId);
	}

	public void addProduct(Product product) {
		System.out.println("ProductServiceImpl.addProduct");
		productDao.addProduct(product);
	}

	public void updateProduct(Product product) {
		System.out.println("ProductServiceImpl.updateProduct");
		productDao.updateProduct(product);
	}

}
