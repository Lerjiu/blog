package com.blog.service.impl;

import com.blog.dao.LabelDao;
import com.blog.domain.Label;
import com.blog.service.LabelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {
    private LabelDao labelDao;

    public LabelServiceImpl(LabelDao labelDao) {
        this.labelDao = labelDao;
    }

    @Override
    public List<Label> getHotLabels() {
        return labelDao.getHotLabels();
    }
}
