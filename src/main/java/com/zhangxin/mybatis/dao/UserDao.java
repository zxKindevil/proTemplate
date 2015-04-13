package com.zhangxin.mybatis.dao;

import com.zhangxin.mybatis.bean.User;
import com.zhangxin.mybatis.bean.Article;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {

    public void insert(User user);
    public User selectByID(int id);
    public List<User> selectLikeName(String userName);
    public void update(User user);
    public void deleteByID(int id);
    public List<Article> selectArticlesByUserID(int id);
}
