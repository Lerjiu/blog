package com.blog.service;

import com.blog.domain.Collection;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CollectionService {
    void add(Collection collection);
    List<Collection> getCollections(int folderId);
    void delete(int id);
}
