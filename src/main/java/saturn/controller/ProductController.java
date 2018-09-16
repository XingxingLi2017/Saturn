package saturn.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import saturn.model.Product;
import saturn.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/getAllProducts", method = RequestMethod.GET)
	public ModelAndView getAllProducts() {
		System.out.println("ProductController.getAllProducts");
		List<Product> products = productService.getAllProducts();
		return new ModelAndView("productList", "products", products);
	}
	
	@RequestMapping(value = "/getProductById/{productId}", method=RequestMethod.GET)
	public ModelAndView getProductById(@PathVariable(value="productId") int productId) {
		System.out.println("ProductController.getProductById");
		Product product = productService.getProductById(productId);
		return new ModelAndView("productPage", "product", product);
	}
	
	// delete picture in server, need to revise
	@RequestMapping(value = "/admin/delete/{productId}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable(value = "productId")int productId) {
		System.out.println("ProductController.deleteProduct");
		Path path = Paths.get("/Users/lixingxing/products/"+productId+".jpg");
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		productService.deleteProduct(productId);
		return "redirect:/getAllProducts";
	}
	
	@RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.GET)
	public String getProductForm(Model model) {
		System.out.println("ProductController.getProductForm");
		Product product = new Product(); 
		model.addAttribute("productForm", product); 
		return "addProduct";
	}
	// add picture in server
	@RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute(value="productForm")Product product, BindingResult result) {
		System.out.println("ProductController.addProduct");
		if (result.hasErrors()) {
			return "addProduct";
		}
		
		productService.addProduct(product);  // add imformation except image
		// Store picture
		MultipartFile image = product.getProductImage();
		if (image != null && !image.isEmpty()) {
			Path path = Paths.get("/Users/lixingxing/products/"+product.getId()+".jpg");
			try {
				image.transferTo(new File(path.toString()));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return "redirect:/getAllProducts";
		
	}
	@RequestMapping(value = "/admin/product/editProduct/{productId}", method = RequestMethod.GET)
	public ModelAndView getEditForm(@PathVariable(value="productId") int productId) {
		System.out.println("ProductController.getEditForm");
		Product product = productService.getProductById(productId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editProduct");
		modelAndView.addObject("editProductObj", product);
		modelAndView.addObject("productId", productId);
		return modelAndView;
	}
	@RequestMapping(value = "/admin/product/editProduct/{productId}", method = RequestMethod.POST)
	public String editProduct (@ModelAttribute(value="editProductObj")Product product, 
			@PathVariable(value="productId") int productId) {
		System.out.println("ProductController.editProduct");
		product.setId(productId);
		productService.updateProduct(product);
		return "redirect:/getAllProducts";
	}
	
	@RequestMapping("/getProductsList")
	public @ResponseBody List<Product> getProductsListJson() {
		return productService.getAllProducts();
	}
	
}
