package com.example.client;

import java.io.IOException;

/**
 * author ye
 * createDate 2022/3/26  14:44
 */
public class BClient {
    public static void main(String[] args) throws IOException {
        new ChatClient().startClient("mary");
    }
}
