package me.itoxic.repository;

import me.itoxic.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, Serializable {

   List<Product> findAll();

   Product findByProductName(String ProductName);

}


