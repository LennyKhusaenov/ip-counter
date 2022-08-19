package com.codereview.ipcounter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import com.codereview.ipcounter.handler.IpCounterEventHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IpCounterApplicationTests {

  @Autowired
  private IpCounterEventHandler ipCounterEventHandler;

  @Test
  void contextLoads() {
    assertThat(ipCounterEventHandler, notNullValue());
  }

}