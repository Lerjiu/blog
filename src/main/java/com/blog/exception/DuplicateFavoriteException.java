package com.blog.exception;

public class DuplicateFavoriteException extends BusinessException {
    public DuplicateFavoriteException() {
        super(Code.FAVORITE_EXCEPTION_DUPLICATE_IN_FOLDER, Code.FAVORITE_EXCEPTION_DUPLICATE_IN_FOLDER_MESSAGE);
    }
}
