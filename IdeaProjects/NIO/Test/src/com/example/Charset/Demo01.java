package com.example.Charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.SortedMap;

/**
 * author ye
 * createDate 2022/3/26  13:42
 */
public class Demo01 {
    public static void main(String[] args) throws CharacterCodingException {
        //1获取charset对象
        Charset charset = Charset.forName("UTF-8");

        //2获得编码器对象
        CharsetEncoder charsetEncoder = charset.newEncoder();

        //3创建缓冲区
        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("尚硅谷");
        buffer.flip();

        //4编码
        ByteBuffer encode = charsetEncoder.encode(buffer);
        System.out.println("编码之后的结果");
        for (int i = 0; i < encode.limit(); i++) {
            System.out.println(encode.get(i));
        }

        //5获取解码器对象
        CharsetDecoder charsetDecoder = charset.newDecoder();

        //6解码
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println("解码后的结果");
        String string = decode.toString();
        System.out.println(string);


        Charset charset1 = Charset.forName("GBK");
        encode.flip();
        CharBuffer decode1 = charset1.decode(encode);
        System.out.println("解码后的结果");
        String string1 = decode1.toString();
        System.out.println(string1);

        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        for (Map.Entry<String, Charset> stringCharsetEntry : stringCharsetSortedMap.entrySet()) {
            System.out.println(stringCharsetEntry.getKey() + " = " + stringCharsetEntry.getValue());
        }

    }
}
