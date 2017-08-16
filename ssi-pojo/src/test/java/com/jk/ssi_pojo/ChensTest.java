package com.jk.ssi_pojo;


/**
 * Created by Admin on 2017/8/15.
 */
public class ChensTest {

    public static void main(String[] args) {
        int a = 0;
        for (int i = 0 ; i <= 10; i++){
            for (int j = 1; j <= i; j++){
                System.out.print(i + "*" + j + "=" + i * j + "\t" );
            }
            System.out.println();
        }
    }
}
