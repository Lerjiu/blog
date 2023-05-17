package com.blog.service;

import com.blog.domain.Label;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DraftLabelService {
    void setDraftLabels(int draftId, List<String> labelNames);
    void deleteDraftLabels(int draftId);
    void updateDraftLabels(int draftId, List<String> labelNames);
    List<Label> getDraftLabels(int draftId);
}
