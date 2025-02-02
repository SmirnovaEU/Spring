package com.example.homework7_datajpa.repository;

import com.example.homework7_datajpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String name);

//    @Modifying
//    @Query("update Book b set b.title = :name where b.id = :id")
//    void updateNameById(@Param("id") long id,
//                        @Param("name") String name);

}
