package com.codereview.ipcounter.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParserUtils {
  public static long toLongValue(String ipString) throws UnknownHostException {
    long result = 0;
    for (byte b : InetAddress.getByName(ipString).getAddress()) {
      result = (result << 8) | (b & 255);
    }
    return result;
  }
}