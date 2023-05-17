package com.blog.service;

import com.blog.domain.Theme;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DraftThemeService {
    void setDraftTheme(int draftId, int themeId);
    void deleteDraftTheme(int draftId);
    Theme getDraftTheme(int draftId);
    void updateDraftTheme(int draftId, int themeId);
}
