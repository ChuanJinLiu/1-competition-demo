package com.baishan;

import java.util.*;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/3/13 13:34
 */
public class Main3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] input1 = sc. nextLine().split(",");
    String[] input2 = sc. nextLine().split(",");
    Set<String> set1 = new TreeSet<>(Arrays.asList(input1));
    Set<String> set2 = new TreeSet<>(Arrays.asList(input2));
    set1.retainAll(set2);
    System.out.println(set1.toString().substring(1,set1.toString().length()-1).replace(", ", ","));
  }

}
