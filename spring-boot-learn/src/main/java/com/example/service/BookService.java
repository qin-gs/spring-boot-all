package com.example.service;

import com.example.bean.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 模拟缓存，让程序启动后运行一些代码
 * <p>
 * 如果一个类中的方法缓存操作是相同的(比如全是get方法)，可以使用 @CacheConfig 注解
 */
@Service
// @CacheConfig(cacheNames = "bookService")
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    /**
     * 这个 Map 不能修改，仅供演示使用
     */
    private final Map<Long, Book> books = Map.of(
            1L, Book.BookBuilder.aBook().id(1L).name("a").price(1).build(),
            2L, Book.BookBuilder.aBook().id(2L).name("b").price(2).build(),
            3L, Book.BookBuilder.aBook().id(3L).name("c").price(3).build()
    );

    /**
     * 缓存通过aop实现，方法只能是 public
     * 一个方法A调同一个类里的另一个有缓存注解的方法B，这样是不走缓存的
     * <p>
     * SimpleKeyGenerator 的生成规则
     * 如果方法没有入参，则使用SimpleKey.EMPTY作为key（key = new SimpleKey()）。
     * 如果只有一个入参，则使用该入参作为key（key = 入参的值）。
     * 如果有多个入参，则返回包含所有入参的一个SimpleKey（key = new SimpleKey(params)）。
     */
    @Cacheable(cacheNames = "books", condition = "#id < 10L")
    public Book getBook(long id) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return books.get(id);
    }

    /**
     * 方法每次都会被调用，然后将返回值放入缓存
     */
    @CachePut(cacheNames = "books", key = "#book.id")
    public Book updateBook(Book book) {
        books.put(book.getId(), book);
        return book;
    }

    /**
     * 从缓存中移除对象
     */
    @CacheEvict(cacheNames = "books", condition = "#id > 5")
    public Book deleteBook(long id) {
        return books.remove(id);
    }

    /**
     * {@code @Caching}是一个组注解，可以为一个方法定义提供基于@Cacheable、@CacheEvict或者@CachePut注解的数组。
     * cacheable -> @Cacheable
     * put -> @CachePut
     * evict -> @CacheEvict
     */
    @Caching(cacheable = {
            @Cacheable(value = "dogs", condition = "#animal instanceof T(com.example.service.Dog)"),
            @Cacheable(value = "cats", condition = "#animal instanceof T(com.example.service.Cat)")
    })
    public Animal getAnimal(Animal animal) {
        // 进行数据库查询，返回对象
        return new Dog();
    }

}

class Animal {

}

class Dog extends Animal {

}

class Cat extends Animal {

}