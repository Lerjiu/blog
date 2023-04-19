package com.blog.service.impl;

import com.blog.exception.Code;
import com.blog.exception.SystemException;
import com.blog.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.location}")
    private String location;
    @Override
    public String upload(MultipartFile file, String relativePath) {
        String fileName = null;
        if (!file.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = location + relativePath + fileName;
            try {
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                throw new SystemException(Code.FILE_SAVE_EXCEPTION, Code.FILE_SAVE_EXCEPTION_MESSAGE, e);
            }
        } else {
            throw new SystemException(Code.FILE_EMPTY_EXCEPTION, Code.FILE_EMPTY_EXCEPTION_MESSAGE);
        }
        return fileName;
    }
}
