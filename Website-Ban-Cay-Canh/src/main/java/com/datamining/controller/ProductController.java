package com.datamining.controller;

import com.datamining.entity.Account;
import com.datamining.entity.Product;
import com.datamining.service.AccountService;
import com.datamining.service.CategoryService;
import com.datamining.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
	@Autowired
	AccountService aService;
	
    @Autowired
    ProductService pService;

    @Autowired
    CategoryService cService;

    @RequestMapping("/{cate}")
    public String list(Model model,@PathVariable("cate") Optional<String> cate) {
        if(cate.isPresent()) {
            String id = cService.findIdByUrlEquals(cate.get());
            List<Product> list = pService.findByCategoryId(id);
            model.addAttribute("items", list);
        } else {
            List<Product> list = pService.findAll();
            model.addAttribute("items", list);
            
        }
        List<Product> bestSale = pService.findTop5Seller();

        model.addAttribute("bestSale", bestSale);
        return "user/layout/index";
    }

    @RequestMapping("/product/{url}")
    public String detail(Model model, @PathVariable("url") String url, HttpServletRequest req) {
        Product item = pService.findByUrlEquals(url);
        model.addAttribute("item", item);
        if(req.getRemoteUser() != null) {
			Account us = aService.findByTk(req.getRemoteUser());
			int usId = us.getId();
			model.addAttribute("user_id", usId);
		}
        return "user/product/product-detail";
    }

    @RequestMapping("/product/search")
    public String search(Model model, @RequestParam("keyword") String keyword) {
        List<Product> list = pService.findByKeyword(keyword);
        model.addAttribute("items", list);
        return "user/layout/index";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam(value = "count") String price, HttpServletRequest req) {
        String urlHead = req.getHeader("Referer");
        String url = urlHead.substring(urlHead.lastIndexOf("/") + 1); // lấy url của category
        System.out.println(price);
        String[] arr = price.split(" ");
        Double price1 = Double.parseDouble(arr[0]);
        Double price2 = Double.parseDouble(arr[1]);
        if(url.equals("home")) {
            List<Product> list = pService.findByPriceBetween(price1, price2);
            model.addAttribute("items", list);
            return "user/layout/index";
        }

        List<Product> list = pService.findByPriceBetweenByCate(price1, price2, url);
        model.addAttribute("items", list);
        return "user/layout/index";
    }
}
