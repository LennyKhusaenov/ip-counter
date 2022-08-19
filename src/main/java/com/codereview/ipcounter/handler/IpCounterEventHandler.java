package com.codereview.ipcounter.handler;

import com.codereview.ipcounter.service.ArgumentProcessor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IpCounterEventHandler {

  private final ApplicationArguments applicationArguments;
  private final List<ArgumentProcessor> argumentProcessors;

  @EventListener
  public void onApplicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
    argumentProcessors.forEach(
        argumentProcessor -> argumentProcessor.process(applicationArguments));
  }
}