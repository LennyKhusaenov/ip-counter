package com.codereview.ipcounter.service;

import static java.nio.file.StandardOpenOption.APPEND;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.codereview.ipcounter.service.impl.FileIpDataProcessor;
import com.codereview.ipcounter.utils.Constants;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.stream.IntStream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileIpDataProcessorUnitTest {

  private static final String TEST_TXT = "test.txt";
  @Mock
  private IpCounter<String> ipCounter;
  @InjectMocks
  private FileIpDataProcessor fileIpDataProcessor;

  @TempDir
  private Path tempDir;

  @SneakyThrows
  @Test
  void shouldProcessFile() {
    //given
    var tempFilePath = Files.createFile(tempDir.resolve(TEST_TXT));
    int linesCount = 3;
    writeIpsToFileByCount(tempFilePath, linesCount);
    doReturn(1L)
        .when(ipCounter)
        .getCount();

    //when
    fileIpDataProcessor.processFile(tempFilePath.toString());

    //then
    verify(ipCounter, times(3)).countIp(anyString());
  }

  @SneakyThrows
  @Test
  void shouldStopProcessIfAllIpsWereRegistered() {
    //given
    var tempFilePath = Files.createFile(tempDir.resolve(TEST_TXT));
    int linesCount = 1;
    writeIpsToFileByCount(tempFilePath, linesCount);
    doReturn(Constants.NUMBER_OF_IP_ADDRESSES)
        .when(ipCounter)
        .getCount();

    //when
    fileIpDataProcessor.processFile(tempFilePath.toString());

    //then
    verify(ipCounter, times(0)).countIp(anyString());
  }

  @SneakyThrows
  @Test
  void shouldStopProcessIfEofReceived() {
    //given
    var tempFilePath = Files.createFile(tempDir.resolve(TEST_TXT));

    //when
    fileIpDataProcessor.processFile(tempFilePath.toString());

    //then
    verify(ipCounter, times(0)).countIp(anyString());
  }

  @Test
  @SneakyThrows
  void shouldCatchExceptionDuringProcessFile() {
    //given
    var wrongFile = "wrongFile";

    //when
    var thrown = assertThrows(IllegalStateException.class,
        () -> fileIpDataProcessor.processFile(wrongFile));

    // then
    assertThat(thrown.getMessage(), containsString(
        String.format("%s: %s", NoSuchFileException.class.getCanonicalName(), wrongFile)));
  }

  private static void writeIpsToFileByCount(Path tempFilePath, int count) {
    IntStream.range(0, count).forEach(num -> {
      try {
        Files.writeString(tempFilePath, String.format("0.0.0.%d\n", num), APPEND);
      } catch (IOException e) {
        throw new IllegalStateException();
      }
    });
  }
}