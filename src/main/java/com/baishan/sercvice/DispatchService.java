package com.baishan.sercvice;

import com.alibaba.fastjson.JSONObject;

/**
 * Dispatch Service.
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/2/4 15:00
 */
public interface DispatchService {

  /**
   * Dispatch method
   *
   * @return Forwarded request result
   */
  JSONObject dispatch();
}
