package com.blog.service.impl;

import com.blog.dao.DraftThemeDao;
import com.blog.dao.ThemeDao;
import com.blog.domain.Theme;
import com.blog.service.DraftThemeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DraftThemeServiceImpl implements DraftThemeService {
    private DraftThemeDao draftThemeDao;
    private ThemeDao themeDao;

    public DraftThemeServiceImpl(DraftThemeDao draftThemeDao, ThemeDao themeDao) {
        this.draftThemeDao = draftThemeDao;
        this.themeDao = themeDao;
    }

    @Override
    public void setDraftTheme(int draftId, int themeId) {
        draftThemeDao.setDraftTheme(draftId, themeId);
    }

    @Override
    public void deleteDraftTheme(int draftId) {
        draftThemeDao.deleteDraftTheme(draftId);
    }

    @Override
    public Theme getDraftTheme(int draftId) {
        int draftThemeId = draftThemeDao.getDraftThemeId(draftId);
        return themeDao.get(draftThemeId);
    }

    @Override
    public void updateDraftTheme(int draftId, int themeId) {
        deleteDraftTheme(draftId);
        setDraftTheme(draftId, themeId);
    }

}
