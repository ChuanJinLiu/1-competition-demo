package com.baishan.model;

import java.util.Objects;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/2/4 15:56
 */
public class NowResult {

  private Integer host;
  private Integer value;
  private Integer check;

  public NowResult() {
  }

  public NowResult(Integer host, Integer value, Integer check) {
    this.host = host;
    this.value = value;
    this.check = check;
  }

  @Override
  public String toString() {
    return "NowResult{" +
            "host=" + host +
            ", value=" + value +
            ", check=" + check +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NowResult nowResult = (NowResult) o;
    return Objects.equals(host, nowResult.host) &&
            Objects.equals(value, nowResult.value) &&
            Objects.equals(check, nowResult.check);
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, value, check);
  }
}
