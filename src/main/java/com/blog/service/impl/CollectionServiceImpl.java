package com.blog.service.impl;

import com.blog.dao.CollectionDao;
import com.blog.domain.Collection;
import com.blog.service.CollectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    private CollectionDao collectionDao;

    public CollectionServiceImpl(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    @Override
    public void add(Collection collection) {
        collectionDao.add(collection);
    }

    @Override
    public List<Collection> getCollections(int folderId) {
        return collectionDao.getCollections(folderId);
    }

    @Override
    public void delete(int id) {
        collectionDao.delete(id);
    }
}
