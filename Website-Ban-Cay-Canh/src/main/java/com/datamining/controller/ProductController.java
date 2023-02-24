package com.datamining.controller;

import com.datamining.dao.CategoryDAO;
import com.datamining.dao.ProductDao;
import com.datamining.entity.Product;
import com.datamining.service.CategoryService;
import com.datamining.service.ProductService;
import com.datamining.service.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

@Controller
public class ProductController {
	@Autowired
	ProductService pService;

	@Autowired
	CategoryService cService;

	@Autowired
	ProductDao dao;

	@Autowired
	CategoryDAO cdao;

//    @Autowired
//    Session session;

	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cate") Optional<String> cate,
			@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 8); // 8 product/1 page
		if (cate.isPresent()) {

			String id = cService.findIdByUrlEquals(cate.get());
			Page<Product> pages = pService.findByCategoryId(id, pageable);
			model.addAttribute("url", cate.get());
			model.addAttribute("items", pages);
			//filter price and cater
			model.addAttribute("cates", cate.get());
			
		} else {

			Page<Product> pages = pService.findAll(pageable);
			model.addAttribute("items", pages);

		}
		return "user/layout/index";
	}

	@RequestMapping("/product/detail/{url}")
	public String detail(Model model, @PathVariable("url") String url) {
		Product item = pService.findByUrlEquals(url);
		model.addAttribute("item", item);
		return "user/product/product-detail";
	}

	@RequestMapping("/product/search")
	public String search(Model model, @RequestParam("keyword") String keyword,
			@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 8);
		
			Page<Product> list = pService.findByKeyword(keyword, pageable);
			model.addAttribute("search", keyword);
			
			model.addAttribute("keywords", keyword);
			
			
			// fillter name and price
			model.addAttribute("searchs", keyword);
			model.addAttribute("keyword", keyword);
			model.addAttribute("items", list);
			
		return "user/layout/index";
	}
	
	//filter name + price
	@RequestMapping("/product/search/{keyword}")
	public String searchFilter(Model model, @PathVariable("keyword") String keyword,
			@RequestParam("count") String price,
			@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 8);
		
		String[] arr = price.split(" ");
			Double price1 = Double.parseDouble(arr[0]);
			Double price2 = Double.parseDouble(arr[1]);
			
		Page<Product> list = pService.findByKeywordAndPrice(keyword, price1, price2, pageable);
			model.addAttribute("searchs", keyword);
			model.addAttribute("keywords", keyword);
			model.addAttribute("price", price);
			model.addAttribute("items", list);
		
		return "user/layout/index";
	}

	//filter by category and price 
	@RequestMapping("/product/list/{cate}")
	public String filterCaterPrice(Model model,
			@PathVariable("cate") String cate,
			@RequestParam("count") String price,
			@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 8); // 8 product/1 page
		
		String[] arr = price.split(" ");
		Double price1 = Double.parseDouble(arr[0]);
		Double price2 = Double.parseDouble(arr[1]);
		Page<Product> pages = pService.findByPriceAndCater(cate, price1, price2, pageable);
			
			model.addAttribute("cates", cate);
			model.addAttribute("items", pages);
		
		return "user/layout/index";
	}
	
	@RequestMapping("/products_filter")
	public String filter(Model model, @RequestParam("count") String price,
			@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 8);

		String[] arr = price.split(" ");
		Double price1 = Double.parseDouble(arr[0]);
		Double price2 = Double.parseDouble(arr[1]);

		Page<Product> list = pService.findByPriceBetween(price1, price2, pageable);

		model.addAttribute("price", price);
		model.addAttribute("items", list);
		return "user/layout/index";
	}

}
