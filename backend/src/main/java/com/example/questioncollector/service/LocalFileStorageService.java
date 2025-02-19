package com.example.questioncollector.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalFileStorageService implements FileStorageService {
  private final Path rootLocation;

  public LocalFileStorageService(
      @Value("${file.upload-dir}") String uploadDir) {
    this.rootLocation = Paths.get(uploadDir);
    init();
  }

  private void init() {
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new RuntimeException("无法创建存储目录", e);
    }
  }

  @Override
  public String storeFile(MultipartFile file) {
    try {
      var originalFilename = file.getOriginalFilename();
      var extension = originalFilename
          .substring(originalFilename.lastIndexOf("."));
      var newFilename = UUID.randomUUID() + extension;

      var targetLocation = rootLocation.resolve(newFilename);
      Files.copy(file.getInputStream(), targetLocation,
          StandardCopyOption.REPLACE_EXISTING);

      return "/files/" + newFilename;
    } catch (IOException e) {
      throw new RuntimeException("文件存储失败", e);
    }
  }

}
