package com.example.controller;

import com.example.bean.Book;
import com.example.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService service;
    /**
     * 这是默认的缓存管理器
     */
    private final ConcurrentMapCacheManager manager;

    public BookController(BookService service, ConcurrentMapCacheManager manager) {
        this.service = service;
        this.manager = manager;
    }

    @GetMapping("getBookById/{id}")
    public Book getBookById(@PathVariable("id") long id) {
        return service.getBook(id);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(service.getBook(1L).toString());
        log.info(service.getBook(2L).toString());
        log.info(service.getBook(1L).toString());
        log.info(service.getBook(2L).toString());
        log.info(service.getBook(1L).toString());
        log.info(service.getBook(2L).toString());

        Cache books = manager.getCache("books");

        log.info("从缓存管理器中获取对象");
        log.info(books.get(1L, Book.class).toString());

    }
}
