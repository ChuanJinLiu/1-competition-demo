package com.baishan.sercvice.impl;

import com.alibaba.fastjson.JSONObject;
import com.baishan.model.NowResult;
import com.baishan.sercvice.DispatchService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/2/4 15:37
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DispatchServiceImpl implements DispatchService {

  @Override
  public NowResult dispatch() {
    return new NowResult();
  }
}
