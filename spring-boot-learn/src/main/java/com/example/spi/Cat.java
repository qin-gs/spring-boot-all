package com.example.spi;

public class Cat implements Shout {
    @Override
    public void shout() {
        System.out.println("cat shout");
    }
}
