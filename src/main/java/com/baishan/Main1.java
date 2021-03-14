package com.baishan;

import java.util.HashMap;
import java.util.Scanner;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/3/13 13:34
 */
public class Main1 {
  public static void main(String[] args) {
    HashMap map = new HashMap<String,Integer>();
    String[] AZ = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    for (int i = 1; i < 27 ; i++) {
      map.put(AZ[i-1],i);
    }
    Scanner sc = new Scanner(System.in);
    String input = sc. nextLine();
    Long sum = 0L;
    int index = 0;
    for (int i = input.toCharArray().length - 1 ; i > -1 ; i--) {
      sum += Long.parseLong(map.get(String.valueOf(input.toCharArray()[i])).toString())*(long)Math.pow(26,index);
      index += 1;
    }
    System.out.println(sum);
  }

}
