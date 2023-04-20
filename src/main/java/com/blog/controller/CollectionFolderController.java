package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.domain.CollectionFolder;
import com.blog.exception.Code;
import com.blog.service.CollectionFolderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/collectionFolder")
@ResponseBody
public class CollectionFolderController {
    private CollectionFolderService collectionFolderService;

    public CollectionFolderController(CollectionFolderService collectionFolderService) {
        this.collectionFolderService = collectionFolderService;
    }

    @RequestMapping("/add")
    public Response add(@RequestHeader("id") int id, CollectionFolder collectionFolder) {
        collectionFolder.setUserId(id);
        collectionFolderService.add(collectionFolder);
        return Response.success(Code.COMMENT_ADD, Code.COMMENT_ADD_MESSAGE);
    }

    @RequestMapping("/getCollectionFolders")
    public DataResponse getList(@RequestHeader("id") int id) {
        List<CollectionFolder> collectionFolders = collectionFolderService.getCollectionFolders(id);
        return DataResponse.success(Code.COLLECTION_GET_FOLDER_LIST, Code.COLLECTION_GET_FOLDER_LIST_MESSAGE, collectionFolders);
    }

    @RequestMapping("/delete")
    public Response delete(int id) {
        collectionFolderService.delete(id);
        return Response.success(Code.COLLECTION_DELETE_FOLDER, Code.COLLECTION_DELETE_FOLDER_MESSAGE);
    }
}
