package com.Library_Management_System.Repository;

import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Enums.Categories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepo extends JpaRepository<Books,Long> {
    Optional<Books> findByIsbn(String isbn);

    List<Books> findAllByCategory(Categories category);

	List<Books> findAllByBookAuthor(String author);
}
