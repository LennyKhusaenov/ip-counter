package com.codereview.ipcounter.service.impl;

import static com.codereview.ipcounter.utils.Constants.NUMBER_OF_IP_ADDRESSES;

import com.codereview.ipcounter.service.FileDataProcessor;
import com.codereview.ipcounter.service.IpCounter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileIpDataProcessor implements FileDataProcessor {
  private final IpCounter<String> ipCounter;

  @Override
  public void processFile(String filePath) {
    try (Stream<String> ipLines = Files.lines(Paths.get(filePath))) {
      var linesProcessed = new AtomicInteger();
      ipLines.takeWhile(line -> ipCounter.getCount() < NUMBER_OF_IP_ADDRESSES)
          .forEach(ipView -> {
            ipCounter.countIp(ipView);
            linesProcessed.incrementAndGet();
          });
      log.info("Total lines processed: {} and unique ip count: {}", linesProcessed,
          ipCounter.getCount());
    } catch (IOException e) {
      log.error("An error occurred during try to read file {}", filePath, e);
      throw new IllegalStateException(e);
    }
  }
}