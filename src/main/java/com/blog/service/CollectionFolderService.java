package com.blog.service;

import com.blog.domain.CollectionFolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CollectionFolderService {
    void add(CollectionFolder collectionFolder);
    List<CollectionFolder> getCollectionFolders(int userId);
    void delete(int id);
}
