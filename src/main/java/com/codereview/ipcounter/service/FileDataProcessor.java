package com.codereview.ipcounter.service;

@FunctionalInterface
public interface FileDataProcessor {
  void processFile(String fileName);
}