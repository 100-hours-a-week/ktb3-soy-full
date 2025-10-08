package com.example.spring_practice.repository;

import com.example.spring_practice.dto.BookDto;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    public LinkedHashMap<Long, BookDto> bookDtoMap;
    private long sequence = 0l;

    public BookRepository() {
        bookDtoMap = new LinkedHashMap<>();
        bookDtoMap.put(++sequence, new BookDto(sequence, "Clean Code",
                "Robert C. Martin", "소프트웨어 장인 정신을 담은 책입니다.", "9780132350884"));
        bookDtoMap.put(++sequence, new BookDto(sequence, "객체지향의 사실과 오해",
                "조영호", "객체지향의 본질을 쉽게 설명합니다.", "9791186710770"));
        bookDtoMap.put(++sequence, new BookDto(sequence, "Effective Java",
                "Joshua Bloch", "자바 개발자를 위한 베스트 프랙티스 모음집입니다.", "9780134685991"));
    }

    public void save(BookDto book){
        if (book.getId() == null){
            book.setId(++sequence);
        }
        bookDtoMap.put(book.getId(), book);
    }

    public Optional<BookDto> findById(Long id){
        return Optional.ofNullable(bookDtoMap.get(id));
    }

    public List<BookDto> findAll(){
        return new ArrayList<>(bookDtoMap.values());
    }
}
