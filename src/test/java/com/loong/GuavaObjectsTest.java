package com.loong;


import com.google.common.base.Objects;
import org.junit.Test;

public class GuavaObjectsTest {
    /**
     * 判断两个对象是否相等
     */
    @Test
    public void testEqual(){
        Object a = null;
        Object b = new Object();
        boolean aEqualsB = Objects.equal(a, b);

        System.out.println("结果为："+aEqualsB);
    }
}