package com.codereview.ipcounter.service;

import static com.codereview.ipcounter.utils.Constants.FILE_NAME_ARG;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.codereview.ipcounter.service.impl.FileNameArgumentProcessor;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

@ExtendWith(MockitoExtension.class)
class FileNameArgumentProcessorUnitTest {

  @Mock
  private ApplicationArguments applicationArguments;
  @Mock
  private FileDataProcessor fileDataProcessor;

  @InjectMocks
  private FileNameArgumentProcessor fileNameArgumentProcessor;

  @Test
  void shouldProcessIfFileNameArgumentExists() {
    //given
    var fileName = "test.txt";
    doReturn(List.of(fileName))
        .when(applicationArguments)
        .getOptionValues(FILE_NAME_ARG);

    //when
    fileNameArgumentProcessor.process(applicationArguments);

    //then
    verify(fileDataProcessor, times(1)).processFile(fileName);
  }

  @Test
  void shouldNotProcessIfFileNameArgumentDoesntExist() {
    //given
    doReturn(null)
        .when(applicationArguments)
        .getOptionValues(FILE_NAME_ARG);

    //when
    fileNameArgumentProcessor.process(applicationArguments);

    //then
    verify(fileDataProcessor, times(0)).processFile(any());
  }

  @Test
  void shouldProcessFileNameTrimmed() {
    //given
    var fileName = "    test.txt     ";
    doReturn(List.of(fileName))
        .when(applicationArguments)
        .getOptionValues(FILE_NAME_ARG);

    //when
    fileNameArgumentProcessor.process(applicationArguments);

    //then
    verify(fileDataProcessor, times(1)).processFile(fileName.trim());
  }

  @Test
  void shouldNotIfArgsAreEmpty() {
    //given
    doReturn(List.of())
        .when(applicationArguments)
        .getOptionValues(FILE_NAME_ARG);

    //when
    fileNameArgumentProcessor.process(applicationArguments);

    //then
    verify(fileDataProcessor, times(0)).processFile(any());
  }

}