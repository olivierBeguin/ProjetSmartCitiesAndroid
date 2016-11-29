package com.henallux.smartcities.DAO;

import com.henallux.smartcities.model.Comment;
import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.UserApp;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public interface IComment
{
    public void createComment(DoService doService);
    public void getCommentOfUser(UserApp userApp);
}
