package com.loong;

public class GuavaTest {

    public static void main(String args[]){
        GuavaTest guavaTester = new GuavaTest();
        Integer a =  null;
        Integer b =  new Integer(10);
        System.out.println(guavaTester.sum(a,b));
    }

    public Integer sum(Integer a, Integer b){
        return a + b;
    }
}
