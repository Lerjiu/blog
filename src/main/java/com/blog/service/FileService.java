package com.blog.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
public interface FileService {
    String upload(MultipartFile file, String relativePath);
}
