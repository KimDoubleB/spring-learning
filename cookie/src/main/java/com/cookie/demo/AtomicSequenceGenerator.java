package com.cookie.demo;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicSequenceGenerator {

    private AtomicLong value = new AtomicLong(1);

    public long getNext() {
        return value.getAndIncrement();
    }

}
