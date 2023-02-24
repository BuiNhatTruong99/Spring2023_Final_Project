package com.datamining.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.datamining.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query(value="SELECT * FROM products WHERE categories_id=?1", nativeQuery = true)
    Page<Product> findByCategoryId(String cid,Pageable pageable);


    Product findByUrlEquals(String url);

    @Query(value="SELECT * FROM Products p WHERE p.name like %?1%", nativeQuery = true)
	Page<Product> findByKeyword(String keyword, Pageable pageable);
    ;

    //find product bettwen two price
    @Query(value="SELECT * FROM Products p WHERE p.price >= ?1 AND p.price <= ?2", nativeQuery = true)
    Page<Product> findByPriceBetween(Double price1, Double price2, Pageable pageable);

    //find product by keyword and filter
    @Query(value= "SELECT * FROM Products p WHERE p.name like %?1% and p.price >= ?2 AND p.price <= ?3",nativeQuery = true)
    Page<Product> findByKeywordAndPrice(String keyword,Double price1, Double price2, Pageable pageable);

    //find product by category and filter
    @Query(value = "SELECT * FROM Products p join categories c on p.categories_id = c.id where c.url=?1 and p.price >= ?2 and p.price <= ?3 ",nativeQuery = true)
    Page<Product> findByPriceAndCater(String cid,Double price1, Double price2, Pageable pageable);


   
}
