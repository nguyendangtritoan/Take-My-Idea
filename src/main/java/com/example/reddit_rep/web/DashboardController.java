package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.Product;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String rootView() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User user, ModelMap modelMap) {
        List<Product> productList = productRepository.findByUser(user);

        modelMap.put("products", productList);

        return "dashboard";
    }
}
