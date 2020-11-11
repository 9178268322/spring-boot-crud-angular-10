package com.ip.bootproduct.controller;

import com.ip.bootproduct.exception.ResourceNotFoundException;
import com.ip.bootproduct.model.Product;
import com.ip.bootproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductList() {
        return new ResponseEntity<List<Product>>(productService.getProdutcs(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws ResourceNotFoundException {
        Product product = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateAccount(@RequestBody Product acDetails, @PathVariable Long id) throws ResourceNotFoundException {
        Product ac = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));

        ac.setName(acDetails.getName());
        ac.setPrice(acDetails.getPrice());

        final Product updatedProduct = productService.saveProduct(ac);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable Long id) throws ResourceNotFoundException {
        Product ac = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
        productService.deleteAccount(ac);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
