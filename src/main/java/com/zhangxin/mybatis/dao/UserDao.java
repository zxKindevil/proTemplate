package com.zhangxin.mybatis.dao;

import com.zhangxin.mybatis.model.Article;
import com.zhangxin.mybatis.model.User;

import java.util.List;

/**
 * Created by zhangxin.zhang on 2015/4/2.
 */
public interface UserDao {

    public void insert(User user);
    public User selectByID(int id);
    public List<User> selectLikeName(String userName);
    public void update(User user);
    public void deleteByID(int id);
    public List<Article> selectArticlesByUserID(int id);
}
