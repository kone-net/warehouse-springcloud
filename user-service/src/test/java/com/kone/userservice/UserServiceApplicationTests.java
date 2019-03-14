package com.kone.userservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

    @Test
    public void contextLoads() {
//        String strInput = "86:老马2-7蓝天寿200交筹委会";
//        String regEx = "[^0-9]";//匹配指定范围内的数字
//
//        //Pattern是一个正则表达式经编译后的表现模式
//        Pattern p = Pattern.compile(regEx);
//
//        // 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
//        Matcher m = p.matcher(strInput);
//
//        //将输入的字符串中非数字部分用空格取代并存入一个字符串
//        String string = m.replaceAll(" ").trim();
//
//        //以空格为分割符在讲数字存入一个字符串数组中
//        String[] strArr = string.split(" ");
//
//        //遍历数组转换数据类型输出
//        for(String s:strArr){
//            System.out.println(s);
//        }
    }

}
