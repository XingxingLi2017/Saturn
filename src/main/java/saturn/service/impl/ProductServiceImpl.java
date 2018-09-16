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
		return null;
	}

	public Product getProductById(int productId) {
		return null;
	}

	public void deleteProduct(int productId) {
	}

	public void addProduct(Product product) {
	}

	public void updateProduct(Product product) {
	}

}
