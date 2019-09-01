package com.bookservice.err;

/**
 * Created by Yurii on 8/13/2019.
 */
public class DataEmptyException extends Exception {
    public DataEmptyException(String message) {
        super("Data is empty exception!" + message);
    }

}
