package com.blog.service;

import com.blog.domain.Label;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LabelService {
    List<Label> getHotLabels();
}
