package com.baishan.sercvice;

import com.baishan.model.NowResult;

import java.util.Objects;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/2/4 15:00
 */
public interface DispatchService {

  /**
   * 调度方法
   *
   * @return 转发的请求结果
   */
  NowResult dispatch();
}