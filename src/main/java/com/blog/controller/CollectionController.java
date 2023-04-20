package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.domain.Collection;
import com.blog.exception.Code;
import com.blog.service.CollectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/collection")
@ResponseBody
public class CollectionController {
    private CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int id, Collection collection) {
        collection.setUserId(id);
        collectionService.add(collection);
        return Response.success(Code.COLLECTION_ADD, Code.COMMENT_ADD_MESSAGE);
    }

    @RequestMapping("/getCollections")
    public DataResponse getCollections(int folderId) {
        List<Collection> collections = collectionService.getCollections(folderId);
        return DataResponse.success(Code.COMMENT_GET_LIST, Code.COMMENT_GET_LIST_MESSAGE, collections);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        collectionService.delete(id);
        return Response.success(Code.COLLECTION_DELETE, Code.COLLECTION_DELETE_MESSAGE);
    }
}
