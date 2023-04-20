package com.blog.service.impl;

import com.blog.dao.CollectionFolderDao;
import com.blog.domain.CollectionFolder;
import com.blog.service.CollectionFolderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionFolderServiceImpl implements CollectionFolderService {
    private CollectionFolderDao collectionFolderDao;

    public CollectionFolderServiceImpl(CollectionFolderDao collectionFolderDao) {
        this.collectionFolderDao = collectionFolderDao;
    }

    @Override
    public void add(CollectionFolder collectionFolder) {
        collectionFolderDao.add(collectionFolder);
    }

    @Override
    public List<CollectionFolder> getCollectionFolders(int userId) {
        return collectionFolderDao.getCollectionFolders(userId);
    }

    @Override
    public void delete(int id) {
        collectionFolderDao.delete(id);
    }
}
