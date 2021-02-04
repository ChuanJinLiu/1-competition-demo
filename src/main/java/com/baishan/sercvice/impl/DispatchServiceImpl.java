package com.baishan.sercvice.impl;

import com.alibaba.fastjson.JSONObject;
import com.baishan.sercvice.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Dispatch ServiceImpl.
 *
 * @author <a href="https://japoul.cn">Japoul</a>
 * @date 2021/2/4 15:37
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DispatchServiceImpl implements DispatchService {

  /**
   * Header of Authorization
   */
  private final String Authorization = "Basic chuanjin.liu";
  /**
   * bestUrl, update every one minutes
   */
  private final Map<String, String> bestUrl = new HashMap<>(1);

  /**
   * thread pool num
   */
  private final int threadPoolNum = 10;

  /**
   * base url，wait forward api
   */
  private final String[] CONFIG_URL = new String[]{
          "http://114.117.0.25/api/1/now",
          "http://114.117.0.25/api/2/now",
          "http://114.117.0.25/api/3/now",
          "http://114.117.0.25/api/4/now",
          "http://114.117.0.25/api/5/now",
          "http://114.117.0.25/api/6/now",
          "http://114.117.0.25/api/7/now",
          "http://114.117.0.25/api/8/now",
          "http://114.117.0.25/api/9/now",
          "http://114.117.0.25/api/10/now",
          "http://114.117.0.25/api/11/now",
          "http://114.117.0.25/api/12/now",
          "http://114.117.0.25/api/13/now",
          "http://114.117.0.25/api/14/now",
          "http://114.117.0.25/api/15/now",
          "http://114.117.0.25/api/16/now",
          "http://114.117.0.25/api/17/now",
          "http://114.117.0.25/api/18/now",
          "http://114.117.0.25/api/19/now",
          "http://114.117.0.25/api/20/now",
          "http://114.117.0.25/api/21/now",
          "http://114.117.0.25/api/22/now",
          "http://114.117.0.25/api/23/now",
          "http://114.117.0.25/api/24/now",
          "http://114.117.0.25/api/25/now",
          "http://114.117.0.25/api/26/now",
          "http://114.117.0.25/api/27/now",
          "http://114.117.0.25/api/28/now",
          "http://114.117.0.25/api/29/now",
          "http://114.117.0.25/api/30/now"
  };
  
  @Override
  public JSONObject dispatch() {
    // Get the url with the lowest latency
    String url = getBestPath();
    return requestUrl(url);
  }

  /**
   * Request get the best URL.
   * 
   * @param url wait request url
   * @return res
   */
  private JSONObject requestUrl(String url) {
    RestTemplate client = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", Authorization);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    //  Perform HTTP request
    ResponseEntity<String> result = client.exchange(url, HttpMethod.GET, entity, String.class);
    return (JSONObject) JSONObject.parse(result.getBody());
  }

  /**
   * Get the best URL.
   *
   * @return
   */
  private String getBestPath() {
    // Get the time of the previous minute
    String oneTime = getOneMinute();
    // No request is made if it is within one minute of the last update
    if (bestUrl.size() != 0 && compareTime(oneTime, bestUrl.get("updateTime"))) {
      return bestUrl.get("url");
    }
    try {
      // Request data, get the optimal URL after calculating the delay and store it in the cache
      String url = ping();
      bestUrl.clear();
      bestUrl.put("updateTime", getNow());
      bestUrl.put("url", url);
      return url;
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }
    return CONFIG_URL[0];
  }

  /**
   * Get the time one minute ago.
   *
   * @return
   */
  private String getOneMinute() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar beforeTime = Calendar.getInstance();
    // get 1 minutes ago
    beforeTime.add(Calendar.MINUTE, -1);
    Date beforeD = beforeTime.getTime();
    return sdf.format(beforeD);
  }

  /**
   * Get the time of now.
   *
   * @return
   */
  private String getNow() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar beforeTime = Calendar.getInstance();
    beforeTime.add(Calendar.MINUTE, 0);
    Date beforeD = beforeTime.getTime();
    return sdf.format(beforeD);
  }

  /**
   * compare time
   *
   * @param time1
   * @param time2
   * @return
   */
  private boolean compareTime(String time1, String time2) {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date t1 = null;
    Date t2 = null;
    try {
      t1 = fmt.parse(time1);
      t2 = fmt.parse(time2);
      return t1.before(t2);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Open multiple threads to request the interface to obtain delay information.
   *
   * @return
   * @throws ExecutionException
   * @throws InterruptedException
   */
  private String ping() throws ExecutionException, InterruptedException {
    // create thread pool
    ExecutorService pool = Executors.newFixedThreadPool(threadPoolNum);
    List<Future> list = new ArrayList<Future>();
    List<String> urlList = Arrays.asList(CONFIG_URL);
    int mod = urlList.size() / threadPoolNum;
    for (int i = 0; i < threadPoolNum; i++) {
      int start = i * mod;
      int end = (i + 1) * mod;
      if (end > urlList.size()) {
        end = urlList.size();
      }
      List<String> arr = urlList.subList(start, end);
      Callable c = new MyCallable(arr);
      Future f = pool.submit(c);
      list.add(f);
    }
    // Close thread pool
    pool.shutdown();
    // Get the running results of all concurrent tasks
    Integer resValue = null;
    String url = CONFIG_URL[0];
    for (Future f : list) {
      // Get the return value of the task from the Future object
      Map res = (HashMap)f.get();
      // Get the lowest latency interface
      Integer tmpValue = Integer.parseInt(res.get("value").toString());
      if( resValue == null || tmpValue < resValue){
        resValue = tmpValue;
        url = res.get("url").toString();
      }
    }
    return url;
  }

  /**
   * Callable Inner class.
   */
  class MyCallable implements Callable<Object> {
    private List<String> urlList;

    MyCallable(List<String> urlList) {
      this.urlList = urlList;
    }

    @Override
    public Object call() throws Exception {
      Map res = new HashMap<String, String>(1);
      for (String url : urlList) {
        Integer tmpValue = requestUrl(url).getInteger("value");
        if (res.get("value") == null || Integer.parseInt(res.get("value").toString()) > tmpValue) {
          res.put("value", tmpValue.toString());
          res.put("url",url);
        }
      }
      return res;
    }
  }
}