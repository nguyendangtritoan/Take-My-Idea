package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.Product;
import com.example.reddit_rep.repo.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @Test
    public void testSaveMethod() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
        Product product = new Product();
        product.setName("Facebook");
        product.setPublished(true);

        Mockito.when(productRepository.save(product)).thenReturn(product);
        assertEquals(productService.saveProduct(product).getPublished(), true);
    }

    @Test
    public void testFindMethod() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
        Product product = new Product();
        product.setName("Facebook");
        product.setPublished(true);

        Mockito.when(productRepository.findByName("Facebook")).thenReturn(Optional.of(product));
        assertEquals(productService.findProductByName("Facebook").get().getPublished(), true);
    }

}
