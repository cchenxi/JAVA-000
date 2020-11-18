package io.github.cchenxi.w5.h2.factory;

import io.github.cchenxi.w5.h2.Book;

/**
 * Date: 2020-11-18
 *
 * @author chenxi
 */
public class DefaultBookFactory {
    public Book createBook() {
        return new Book();
    }
}
