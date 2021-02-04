package com.baishan.controller;

import com.baishan.sercvice.DispatchService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调度控制器.
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/2/4 15:03
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DispatchController {

  private final @NonNull DispatchService dispatchService;

  @GetMapping("/now")
  public HttpEntity<?> dispatch() {
    return ResponseEntity.ok(dispatchService.dispatch());
  }
}
