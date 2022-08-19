package com.codereview.ipcounter.service.impl;

import com.codereview.ipcounter.service.IpCounter;
import com.codereview.ipcounter.utils.ParserUtils;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UniqueIpCounter implements IpCounter<String> {

  private final int[] seen = new int[1 << 27];

  private long count;

  @Override
  public void countIp(String ipView) {
    try {
      var longIpView = ParserUtils.toLongValue(ipView);
      registerIfNewUnique(longIpView);
    } catch (UnknownHostException e) {
      log.error("An error occurred during try to parse ip {}", ipView, e);
    }
  }

  @Override
  public long getCount() {
    return count;
  }

  @Override
  public void resetCounter() {
    count = 0;
  }

  private void registerIfNewUnique(long longValue) {
    int index = (int) (longValue >> 5);
    int bit = 1 << (longValue & 31);
    if ((seen[index] & bit) == 0) {
      count++;
      seen[index] |= bit;
    }
  }
}