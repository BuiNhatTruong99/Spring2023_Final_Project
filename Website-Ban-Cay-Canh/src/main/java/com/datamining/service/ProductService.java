package com.datamining.service;

import com.datamining.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
	Page<Product> findAll(Pageable page);

    Product findById(Integer id);

    Product findByUrlEquals(String url);

//    List<Product> findByCategoryId(String cid);
//    Page<Product> findByCategoryId(String cid,Pageable page);

//    List<Product> findByKeyword(String keyword);


    Page<Product> findByPriceBetween(Double price1, Double price2, Pageable pageable);
	Page<Product> findByCategoryId(String cid, Pageable pageable);
	Page<Product> findByKeyword(String keyword, Pageable pageable);
	Page<Product> findByKeywordAndPrice(String keyword,Double price1, Double price2, Pageable pageable);
	Page<Product> findByPriceAndCater(String cid,Double price1, Double price2, Pageable pageable);
}
