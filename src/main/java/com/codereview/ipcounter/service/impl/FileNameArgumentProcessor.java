package com.codereview.ipcounter.service.impl;

import static com.codereview.ipcounter.utils.Constants.FILE_NAME_ARG;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.codereview.ipcounter.service.ArgumentProcessor;
import com.codereview.ipcounter.service.FileDataProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileNameArgumentProcessor implements ArgumentProcessor {

  private final FileDataProcessor fileDataProcessor;

  @Override
  public void process(ApplicationArguments applicationArguments) {
    var optionValues = applicationArguments.getOptionValues(FILE_NAME_ARG);
    if (isEmpty(optionValues)) {
      log.error("Can't find file name argument");
    } else {
      var filePath = optionValues.get(0);
      log.debug("Received file path argument {}", filePath);
      fileDataProcessor.processFile(filePath.trim());
    }
  }
}