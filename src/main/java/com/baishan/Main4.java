package com.baishan;

import java.util.Scanner;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/3/13 13:34
 */
public class Main4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int num = Integer.parseInt(sc.nextLine());
    Extension[] extArr = new Extension[num];
    for (int i = 0; i < num; i++) {
      String params = sc.nextLine();
      extArr[i] = new Extension(params.split(",",3));
    }
    for (int i = 0; i < num; i++) {
      for (int j = 1; j < num - i; j++) {
        if (extArr[j - 1].compareTo(extArr[j])) {
          Extension temp;
          temp = extArr[j - 1];
          extArr[j - 1] = extArr[j];
          extArr[j] = temp;
        }
      }
    }
    for (Extension e: extArr) {
      System.out.println(e.toString());
    }
  }

  static class Extension {
    private String firstName;
    private String lastName;
    private String ext;

    Extension(String[] parpms) {
      if (parpms.length >= 1 ) {
        this.firstName = parpms[0];
      }

      if (parpms.length >= 2 ) {
          this.lastName = parpms[1];
      }
      if (parpms.length >= 3 ) {
        this.ext = parpms[2];
      }else{
        this.ext = "";
      }
    }

    @Override
    public String toString() {
      if ("".equals(this.firstName)){
        this.firstName = "null";
      }
      if ("".equals(this.lastName)){
        this.lastName = "null";
      }
      if ("".equals(this.ext)){
        this.ext = "null";
      }
      return this.firstName + "," + this.lastName + "," + this.ext;
    }

    public boolean compareTo(Extension ext) {
      boolean res = true;
      if (this.firstName.equals(ext.firstName)) {
        if (this.lastName.equals(ext.lastName)) {
          if (this.ext.compareTo(ext.ext) < 0) {
            res = false;
          }
        } else if (this.lastName.compareTo(ext.lastName) < 0) {
          res = false;
        }
      } else if (this.firstName.compareTo(ext.firstName) < 0) {
        res = false;
      }
      return res;
    }
  }
}
