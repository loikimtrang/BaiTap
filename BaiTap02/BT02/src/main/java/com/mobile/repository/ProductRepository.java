package com.mobile.repository;

import com.mobile.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findAllByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT p FROM Product p ORDER BY p.quantity DESC LIMIT :limit")
    List<Product> findTopSellingProducts(@Param("limit") int limit);

    @Query("SELECT p FROM Product p WHERE p.createDate >= :startDate ORDER BY p.createDate DESC")
    List<Product> findRecentProducts(@Param("startDate") Date startDate);
}
