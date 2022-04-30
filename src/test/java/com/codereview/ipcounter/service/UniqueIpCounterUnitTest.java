package com.codereview.ipcounter.service;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.codereview.ipcounter.service.impl.UniqueIpCounter;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UniqueIpCounterUnitTest {

  @ParameterizedTest
  @MethodSource("createCountToIpsParams")
  void shouldCountUniqueIps(Long count, List<String> ips) {
    //given
    IpCounter<String> ipCounter = new UniqueIpCounter();

    //when
    ips.forEach(ipCounter::countIp);

    //then
    assertThat(ipCounter.getCount(), is(count));
    ipCounter.resetCounter();
  }

  static Stream<Arguments> createCountToIpsParams() {
    return Stream.of(
        arguments(3L, List.of("127.0.0.1",
            "223.223.223.223",
            "255.255.255.255",
            "255.255.255.255")),
        arguments(1L, List.of("")),
        arguments(0L, emptyList()),
        arguments(2L, List.of("127.0.0.1",
            "223.223.223.223",
            "invalid host name")));
  }
}