package com.codereview.ipcounter.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ParserUtilsUnitTest {

  @SneakyThrows
  @ParameterizedTest
  @CsvFileSource(resources = "/ip-to-long-view.csv", numLinesToSkip = 1)
  void shouldTransformIpToLong(String testIp, String expected) {
    //when
    var result = ParserUtils.toLongValue(testIp);

    //then
    assertThat(result, is(Long.parseLong(expected)));
  }

}