package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.Product;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.service.ProductService;
import com.example.reddit_rep.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {

    private final ProductService productService;
    private final UserService userService;

    public DashboardController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String rootView() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User user, ModelMap modelMap) {
        List<Product> productList = productService.getProducts();

        modelMap.put("products", productList);

        return "dashboard";
    }

    @GetMapping("/profile/me")
    public String profile(@AuthenticationPrincipal User user, ModelMap modelMap) {
        List<Product> productList = productService.findByUser(user);

        modelMap.put("username", user.getName());
        modelMap.put("products", productList);

        return "profile";
    }

    @GetMapping("/profile/user/{name}")
    public String userProfile(ModelMap modelMap, @PathVariable String name) {
        User user = userService.getUserByName(name);

        List<Product> productList = productService.findByUser(user);
        modelMap.put("username", user.getName());
        modelMap.put("products", productList);

        return "profile";
    }

    @PostMapping("/search/user")
    public String searchProfile(@RequestParam(name = "nameofuser") String name) {
        return "redirect:/profile/user/" + name;
    }

    //TODO: get all products that not belong to user.
    //TODO: get all products that to specific user (search engine)
}
