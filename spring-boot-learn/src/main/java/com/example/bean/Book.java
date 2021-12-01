package com.example.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "返回的相应数据")
public class Book {
    @ApiModelProperty("图书id")
    private Long id;
    @ApiModelProperty("图书名称")
    private String name;
    @ApiModelProperty("图书价格")
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static final class BookBuilder {
        private Long id;
        private String name;
        private int price;

        private BookBuilder() {
        }

        public static BookBuilder aBook() {
            return new BookBuilder();
        }

        public BookBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder price(int price) {
            this.price = price;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.setId(id);
            book.setName(name);
            book.setPrice(price);
            return book;
        }
    }
}
