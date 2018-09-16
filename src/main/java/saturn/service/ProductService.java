package saturn.service;

import java.util.List;

import saturn.model.Product;
/**
 * Product CRUD
 * */
public interface ProductService {
	List<Product> getAllProducts();
	Product getProductById(int productId);
	void deleteProduct(int productId);
	void addProduct(Product product);
	void updateProduct(Product product);
}
