package com.example.demo.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
