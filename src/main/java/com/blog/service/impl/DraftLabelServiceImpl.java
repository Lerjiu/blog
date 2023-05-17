package com.blog.service.impl;

import com.blog.dao.DraftLabelDao;
import com.blog.dao.LabelDao;
import com.blog.domain.Label;
import com.blog.service.DraftLabelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DraftLabelServiceImpl implements DraftLabelService {
    private LabelDao labelDao;
    private DraftLabelDao draftLabelDao;

    public DraftLabelServiceImpl(LabelDao labelDao, DraftLabelDao draftLabelDao) {
        this.labelDao = labelDao;
        this.draftLabelDao = draftLabelDao;
    }

    @Override
    public void setDraftLabels(int draftId, List<String> labelNames) {
        for (String labelName : labelNames) {
            Label label = labelDao.getByName(labelName);
            if (label == null) {
                label = new Label();
                label.setName(labelName);
                labelDao.add(label);
            }
            draftLabelDao.add(draftId, label.getId());
        }
    }

    @Override
    public void deleteDraftLabels(int draftId) {
        List<Integer> draftLabelIds = draftLabelDao.getDraftLabelIds(draftId);
        draftLabelDao.deleteDraftLabel(draftId);
    }

    @Override
    public void updateDraftLabels(int draftId, List<String> labelNames) {
        deleteDraftLabels(draftId);
        setDraftLabels(draftId, labelNames);
    }

    @Override
    public List<Label> getDraftLabels(int draftId) {
        List<Integer> draftLabelIds = draftLabelDao.getDraftLabelIds(draftId);
        List<Label> draftLabels = new ArrayList<>();
        for (Integer id : draftLabelIds) {
            Label label = labelDao.getById(id);
            draftLabels.add(label);
        }
        return draftLabels;
    }
}
