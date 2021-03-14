package com.baishan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/3/13 13:34
 */
public class Main5 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] arr = sc.nextLine().split(",");
    ArrayList nList = new ArrayList();
    ArrayList bsykj = new ArrayList();
    for (String s : arr) {
      if (!"bai".equals(s) && !"shan".equals(s) && !"yun".equals(s) && !"ke".equals(s) && !"ji".equals(s)) {
        nList.add(s);
      } else {
        bsykj.add(s);
      }
    }
    for (int i = 0; i < nList.size(); i++) {
      for (int j = 1; j < nList.size() - i; j++) {
        if (nList.get(j - 1).toString().compareTo(nList.get(j).toString()) > 0) {
          String temp;
          temp = nList.get(j - 1).toString();
          nList.set(j - 1, nList.get(j).toString());
          nList.set(j, temp);
        }
      }
    }
    List<String> bList = new ArrayList();
    List<String> sList = new ArrayList();
    List<String> yList = new ArrayList();
    List<String> kList = new ArrayList();
    List<String> jList = new ArrayList();
    for (int i = 0; i < bsykj.size(); i++) {
      if ("bai".equals(bsykj.get(i))) {
        bList.add(bsykj.get(i).toString());
      } else if ("shan".equals(bsykj.get(i))) {
        sList.add(bsykj.get(i).toString());
      } else if ("yun".equals(bsykj.get(i))) {
        yList.add(bsykj.get(i).toString());
      } else if ("ke".equals(bsykj.get(i))) {
        kList.add(bsykj.get(i).toString());
      } else if ("ji".equals(bsykj.get(i))) {
        jList.add(bsykj.get(i).toString());
      }
    }
    bList.addAll(sList);
    bList.addAll(yList);
    bList.addAll(kList);
    bList.addAll(jList);
    bList.addAll(nList);
    System.out.println(String.join(",", bList));

  }


}
