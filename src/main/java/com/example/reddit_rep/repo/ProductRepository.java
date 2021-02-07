package com.example.reddit_rep.repo;

import com.example.reddit_rep.domain.Product;
import com.example.reddit_rep.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //select * from product where user = :user and name = :name
    List<Product> findByUser(User user);

    Optional<Product> findByName(String name);

//    @Query("SELECT p from Product p join fetch p.user where p.id = :id")
//    Optional<Product> findByIdWithUser(Long id);
}
