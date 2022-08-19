package com.codereview.ipcounter.service;

public interface IpCounter<T> {

  void countIp(T ipView);

  long getCount();

  void resetCounter();
}