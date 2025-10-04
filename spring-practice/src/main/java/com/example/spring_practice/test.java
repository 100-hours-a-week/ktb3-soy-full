package com.example.spring_practice;

import com.example.spring_practice.dto.BookDto;
import com.example.spring_practice.repository.BookRepository;

public class test {
    public static void main(String[] args) {
        BookDto bookDto = new BookDto(1, "Clean Code",
                "Robert C. Martin", "소프트웨어 장인 정신을 담은 책입니다.", "9780132350884");
        BookRepository bookRepository = new BookRepository();
        bookRepository.save(bookDto);
        System.out.println(bookRepository.findAll());
        System.out.println(bookDto.getId());
    }


}
