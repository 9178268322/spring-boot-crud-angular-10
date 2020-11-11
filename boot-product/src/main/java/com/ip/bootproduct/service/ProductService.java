package com.ip.bootproduct.service;

import com.ip.bootproduct.model.Product;
import com.ip.bootproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProdutcs() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(Long  id) {
        return productRepository.findById(id);
    }

    public void deleteAccount(Product product) {
        productRepository.delete(product);
    }
}
