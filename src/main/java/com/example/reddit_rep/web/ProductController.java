package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.Product;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{productId}") //TODO: /products/id/{productId}
    public String getProduct(@PathVariable Long productId, ModelMap modelMap, HttpServletResponse response) throws IOException {
        Optional<Product> productOptional = productService.findProductById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            modelMap.put("product", product);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + productId + " was not found");
            return "product";
        }
        return "product";
    }

    @PostMapping("products/{productId}") //TODO: /products/id/{productId}
    public String saveProduct(@PathVariable Long productId, Product product) {

        product = productService.saveProduct(product);

        return "redirect:/products/" + product.getId();
    }

    @GetMapping("/p/{productName}") //TODO: /products/name/{productName}
    public String productUserView(@PathVariable String productName, ModelMap modelMap) {

        if (productName != null) {
            try {
                String decodeProductName = URLDecoder.decode(productName, StandardCharsets.UTF_8.name());
                Optional<Product> productOptional = productService.findProductByName(decodeProductName);

                productOptional.ifPresent(product -> modelMap.put("product", product));
            } catch (UnsupportedEncodingException e) {
                log.error("Error in decoding a product URL", e);
            }
        }
        return "productUserView";
    }

    @PostMapping("/products")
    public String createProduct(@AuthenticationPrincipal User user) {

        Product product = new Product();

        product.setPublished(false);
        product.setUser(user);

        product = productService.saveProduct(product);

        return "redirect:/products/" + product.getId();
    }
}
