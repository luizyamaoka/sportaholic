package com.sportaholic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.Client;
import com.sportaholic.model.Product;
import com.sportaholic.model.ProductView;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ClientService;
import com.sportaholic.service.ProductService;
import com.sportaholic.service.ProductViewService;
import com.sportaholic.service.SportService;

@Controller
@RequestMapping(UrlConstants.URL_PRODUCT)
public class ProductController {

	private ProductService productService;
	private ProductViewService productViewService;
	private ClientService clientService;
	private SportService sportService;
	
	@Autowired
	public ProductController(ProductService productService,
			ProductViewService productViewService, ClientService clientService,
			SportService sportService) {
		this.productService = productService;
		this.productViewService = productViewService;
		this.clientService = clientService;
		this.sportService = sportService;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("products/show");
			Product product = this.productService.getEager(id);
			modelAndView.addObject("product", product);
			
			List<Product> recommendedProducts = product.getBrand().getProducts();
			modelAndView.addObject("products", recommendedProducts);
			
			if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
				String email = SecurityContextHolder.getContext().getAuthentication().getName();
				Client client = this.clientService.getByEmail(email);
				ProductView productView = new ProductView();
				productView.setProduct(product);
				productView.setClient(client);
				if (client != null) 
					this.productViewService.create(productView);
			}
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("")
	public ModelAndView index(HttpServletRequest request) {
		try {
			String currentPageString = request.getParameter("page");
			int currentPage = currentPageString == null || currentPageString.equals("") ? 1 : Integer.valueOf(currentPageString);
			
			long pages = this.productService.getActiveBySetPages(null, null, 12);
			
			ModelAndView modelAndView = new ModelAndView("products/index");			
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("firstPage", currentPage - 2 < 1 ? 1 : currentPage - 2);
			modelAndView.addObject("lastPage", currentPage + 2 > pages ? pages : currentPage + 2);
			
			modelAndView.addObject("products", this.productService.getActiveBySetPaginated(null, null, currentPage, 12));
			modelAndView.addObject("sports", this.sportService.getEagerBySet(null));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
}
