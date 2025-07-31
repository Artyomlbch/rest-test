package by.artyom.resttest.repositories;

import by.artyom.resttest.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p join fetch p.category where p.category.id = :category_id")
    List<Product> findByCategoryId(@Param("category_id") Long categoryId);

    @EntityGraph(attributePaths = "category")
    @Query("select p from Product p join fetch p.category")
    List<Product> findAllWithCategory();
}
