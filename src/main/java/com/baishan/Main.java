package com.baishan;

import java.util.*;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/3/13 13:34
 */
public class Main {
  public static void main(String[] args) {
    Map map = new HashMap();
    map.put("One", 1);
    map.put("Two", 2);
    map.put("Three", 3);
    map.put("Four", 4);
    map.put("Five", 5);
    map.put("Six", 6);
    map.put("Seven", 7);
    map.put("Eight", 8);
    map.put("Nine", 9);
    map.put("Ten", 10);
    map.put("ELeven", 11);
    map.put("Twelve", 12);
    map.put("Thirteen", 13);
    map.put("Fourteen", 14);
    map.put("Fifteen", 15);
    map.put("Sixteen", 16);
    map.put("Seventeen", 17);
    map.put("Eighteen", 18);
    map.put("Nineteen", 19);
    map.put("Twenty", 20);
    map.put("Thirty", 30);
    map.put("Forty", 40);
    map.put("Fifty", 50);
    map.put("Sixty", 60);
    map.put("Seventy", 70);
    map.put("Eighty", 80);
    map.put("Ninety", 90);
    map.put("Thousand", 1000);
    map.put("Hundred", 100);

    Scanner sc = new Scanner(System.in);
    String[] str = sc.nextLine().split("-");
    Long res = 0L;
    for (int i = 0; i < str.length; i++) {
      Long num = Long.parseLong(map.get(str[i]).toString());
      if (i + 1 < str.length && ("Hundred".equals(str[i + 1]) || "Thousand".equals(str[i + 1]))) {
        num = num * Long.parseLong(map.get(str[i + 1]).toString());
        i++;
      }
      res += num;
    }
    System.out.println(res);
  }


}
