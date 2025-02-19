package com.example.questioncollector;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.questioncollector.service.FileStorageService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@AutoConfigureMockMvc
public class FileUploadControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private FileStorageService fileStorageService;

  @Test
  void testFileUploadInterface() throws Exception {
    MockMultipartFile mockFile = new MockMultipartFile(
        "file",
        "test.jpg",
        "image/jpeg",
        "测试文件内容".getBytes());

    var fileCaptor = ArgumentCaptor.forClass(MultipartFile.class);
    when(fileStorageService.storeFile(any(MultipartFile.class)))
        .thenReturn("/files/uuid-test.jpg");

    mockMvc.perform(multipart("/api/upload")
        .file(mockFile))
        .andExpect(status().isOk())
        .andExpect(content().string("/files/uuid-test.jpg"));

    verify(fileStorageService).storeFile(fileCaptor.capture());
    var capturedFile = fileCaptor.getValue();
    assertEquals("test.jpg", capturedFile.getOriginalFilename());
    assertEquals("image/jpeg", capturedFile.getContentType());
    assertArrayEquals("测试文件内容".getBytes(), capturedFile.getBytes());
  }
}