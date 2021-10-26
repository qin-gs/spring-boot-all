package com.example.spi;

public class Dog implements Shout {
    @Override
    public void shout() {
        System.out.println("dog shout");
    }
}
