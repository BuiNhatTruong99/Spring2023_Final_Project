package com.datamining.servide.impl;

import com.datamining.dao.ProductDao;
import com.datamining.entity.Product;
import com.datamining.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao pdao;

    @Override
    public Product save(Product product) {
        return pdao.save(product);
    }


    @Override
    public Product findById(Integer id) {
        // TODO Auto-generated method stub
        return pdao.findById(id).get();
    }

    @Override
    public Product findByUrlEquals(String url) {
        return pdao.findByUrlEquals(url);
    }

//    @Override
//    public List<Product> findByCategoryId(String cid) {
//        // TODO Auto-generated method stub
//        return null;
//    }

    @Override
    public Page<Product> findByKeyword(String keyword,Pageable pageable) {
        // TODO Auto-generated method stub
        return pdao.findByKeyword(keyword,pageable);
    }


    @Override
    public Page<Product> findByPriceBetween(Double price1, Double price2,Pageable pageable) {
        return pdao.findByPriceBetween(price1, price2,pageable);
    }


	@Override
	public Page<Product> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return pdao.findAll(page);
	}


	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return pdao.findAll();
	}


	@Override
	public Page<Product> findByCategoryId(String cid, Pageable pageable) {
		// TODO Auto-generated method stub
		return pdao.findByCategoryId(cid, pageable);
	}


	@Override
	public  Page<Product> findByKeywordAndPrice(String keyword,Double price1, Double price2, Pageable pageable) {
		// TODO Auto-generated method stub
		return pdao.findByKeywordAndPrice(keyword, price1,price2, pageable);
	}


	@Override
	public Page<Product> findByPriceAndCater(String cid, Double price1, Double price2, Pageable pageable) {
		// TODO Auto-generated method stub
		return pdao.findByPriceAndCater(cid, price1,  price2,  pageable);
	}


	

}
