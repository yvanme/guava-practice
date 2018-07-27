package com.loong;


import com.google.common.base.Objects;
import com.google.common.base.Optional;
import org.junit.Test;

public class GuavaOptionalTest {
    /**
     * 使用google guava的Optional接口来避免空指针错误
     */
    @Test
    public void testOptional(){
        Optional<Student> possibleNull = Optional.of(null);
        possibleNull.get();
    }
    public static class Student { }
}