package com.baishan;

import java.util.Scanner;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/3/13 13:34
 */
public class Main6 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char[] input = sc.nextLine().toCharArray();

    StringBuilder res = new StringBuilder();
    for (int i = 0; i < input.length; i++) {
      if (input[i] == "(".charAt(0) && i != 0) {
        int replaceNum = 0;
        int d = 0;
        for (int j = i - 1; j >= 0; j--) {
          char num = input[j];
          if (Character.isDigit(num)) {
            replaceNum += Integer.parseInt(String.valueOf(num)) * Math.pow(10, d);
            d++;
          } else {
            break;
          }
        }
        StringBuilder replaceStr = new StringBuilder();
        while (input[i+1] != ")".charAt(0) && i+1 < input.length) {
          replaceStr.append(input[i+1]);
          i++;
        }
        if(i+1 > input.length){
          res.append(replaceStr);
        }else{
          for (int n = 0; n < replaceNum; n++) {
            res.append(replaceStr);
          }
          i++;
        }
      } else if (!Character.isDigit(input[i])) {
        res.append(input[i]);
      }
    }
    System.out.println(res.toString());
  }


}
