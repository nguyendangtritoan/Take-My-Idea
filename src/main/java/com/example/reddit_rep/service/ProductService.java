package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.Product;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId).orElse(null);
        assert existingProduct != null;
        existingProduct.setPublished(product.getPublished());
        existingProduct.setName(product.getName());
        return productRepository.save(existingProduct);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> findByUser(User user) {
        return productRepository.findByUser(user);
    }

}
