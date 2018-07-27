package com.loong;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GuavaStringsTest {
    /**
     * 测试代码是否为空或者为null
     */
    @Test
    public void testNull(){
        String test = null;
        String test1 = "";
        System.out.println(Strings.emptyToNull(test));
        System.out.println(Strings.emptyToNull(test1));
        System.out.println(Strings.isNullOrEmpty(test));
        System.out.println(Strings.isNullOrEmpty(test1));
    }

    /**
     * 获得两个字符串相同的前缀或者后缀
     */
    @Test
    public void commonPrefixOrCommonSuffix(){
        //Strings.commonPrefix(a,b) demo
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a,b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c,d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }

    /**
     *  Strings的padStart和padEnd方法来补全字符串
     */
    @Test
    public void padStartAndPadEnd(){
        int minLength = 4;
        String padEndResult = Strings.padEnd("123", minLength, '0');
        System.out.println("padEndResult is " + padEndResult);
        String padStartResult = Strings.padStart("1", 2, '0');
        System.out.println("padStartResult is " + padStartResult);
    }

    /**
     *  使用Splitter类来拆分字符串
     *  Splitter的onPattern方法传入的是一个正则表达式，
     *  其后紧跟的trimResults()方法表示要对结果做trim，omitEmptyStrings()表示忽略空字符串，
     *  split方法会执行拆分操作。
     * split返回的结果为Iterable<String>，我们可以使用for循环语句来逐个打印拆分字符串的结果。
     */
    @Test
    public void splitterTest(){
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }
    }
    /**
     *  使用Splitter类来拆分字符串
     *  Splitter还有更强大的功能，做二次拆分，这里二次拆分的意思是拆分两次，
     *  例如我们可以将a=b;c=d这样的字符串拆分成一个Map<String,String>。
     *  二次拆分首先是使用onPattern做第一次的拆分，然后再通过withKeyValueSeperator('')方法做第二次的拆分
     */
    @Test
    public void splitter2Test(){
         String toSplitString = "a=b;c=d,e=f";
         Map<String,String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
         for (Map.Entry<String,String> entry : kvs.entrySet()) {
                 System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
         }
    }
    @Test
    public void splitter3Test(){
       /*
         on():指定分隔符来分割字符串
         limit():当分割的子字符串达到了limit个时则停止分割
         fixedLength():根据长度来拆分字符串
         trimResults():去掉子串中的空格
         omitEmptyStrings():去掉空的子串
         withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
        */
        System.out.println(Splitter.on(",").limit(3).trimResults().split(" a,  b,  c,  d"));//[ a, b, c,d]
        System.out.println(Splitter.fixedLength(3).split("1 2 3"));//[1 2,  3]
        System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1  2 3"));
        System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));//[1, 2, 3]
        System.out.println(Splitter.on(" ").trimResults().split("1 2 3")); //[1, 2, 3],默认的连接符是,
        System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));//{a=1, b=2, c=3}

    }
    /**
     * guava为我们提供了Joiner类来做字符串的合并
     *
     */
    @Test
    public void JoinerTest(){
        String joinResult = Joiner.on("||").join(new String[]{"hello","world"});
        System.out.println(joinResult);
    }
    @Test
    public void Joiner3Test(){
       /*
         on:制定拼接符号，如：test1-test2-test3 中的 “-“ 符号
         skipNulls()：忽略NULL,返回一个新的Joiner实例
         useForNull(“Hello”)：NULL的地方都用字符串”Hello”来代替
        */
        StringBuilder sb=new StringBuilder();
        Joiner.on(",").skipNulls().appendTo(sb,"Hello","guava");
        System.out.println(sb);
        System.out.println(Joiner.on(",").useForNull("none").join(1,null,3));
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1,2,3,4,null,6)));
        Map<String,String>map=new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));
    }
    /**
     * Joiner类二次合并
     *
     */
    @Test
    public void Joiner2Test(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);
    }
}
