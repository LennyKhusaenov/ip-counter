package com.codereview.ipcounter.service;

import org.springframework.boot.ApplicationArguments;

@FunctionalInterface
public interface ArgumentProcessor {

  void process(ApplicationArguments applicationArguments);
}