package com.my.mealsys;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MealsysApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void stringSplitTest(){
        String image="1,2,3,";
        String[] images=image.split(",");
        System.out.println("len: "+images.length);
        for (String str:images){
            System.out.println("--- "+str);
        }
    }

}
