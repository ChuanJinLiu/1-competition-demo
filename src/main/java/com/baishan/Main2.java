package com.baishan;

import java.util.Scanner;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/3/13 13:34
 */
public class Main2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = new StringBuffer(sc. nextLine()).reverse().toString();
    Long result = Long.parseLong(input);
    System.out.println(result);
  }

}
