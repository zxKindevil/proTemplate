package com.zhangxin.mybatis;

import java.io.Reader;
import java.util.List;

import com.zhangxin.mybatis.dao.UserDao;
import com.zhangxin.mybatis.model.Article;
import com.zhangxin.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Test {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis/config/Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserDao userOperation = session.getMapper(UserDao.class);
            User user = userOperation.selectByID(1);
            System.out.println(user.getUserAddress());
            System.out.println(user.getUserName());

            //test insert
            addUser();

            //test update
            update();

            //test selectLikeName
            getUserList("%");

            // articles
            getUserArticles(1);

        } finally {
            session.close();
        }
    }

    public static void getUserList(String userName){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserDao userOperation=session.getMapper(UserDao.class);
            List<User> users = userOperation.selectLikeName(userName);
            for(User user:users){
                System.out.println(user.getId()+":"+user.getUserName()+":"+user.getUserAddress());
            }

        } finally {
            session.close();
        }
    }

    public static void addUser(){
        User user=new User();
        user.setUserAddress("人民广场");
        user.setUserName("飞鸟");
        user.setUserAge("80");
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserDao userOperation=session.getMapper(UserDao.class);
            userOperation.insert(user);
            session.commit();
            System.out.println("当前增加的用户 id为:"+user.getId());
        } finally {
            session.close();
        }
    }

    public static void update(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserDao userOperation=session.getMapper(UserDao.class);
            User user = userOperation.selectByID(1);
            user.setUserAddress("原来是魔都的浦东创新园区");
            userOperation.update(user);
            session.commit();
        } finally {
            session.close();
        }
    }

    /**
     * 删除数据，删除一定要 commit.
     * @param id
     */
    public static void deleteUser(int id){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserDao userOperation=session.getMapper(UserDao.class);
            userOperation.deleteByID(id);
            session.commit();
        } finally {
            session.close();
        }
    }

    public static void getUserArticles(int userid){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserDao userOperation=session.getMapper(UserDao.class);
            List<Article> articles = userOperation.selectArticlesByUserID(userid);
            for(Article article:articles){
                System.out.println(article.getTitle()+":"+article.getContent()+
                        ":作者是:"+article.getUser().getUserName()+":地址:"+
                        article.getUser().getUserAddress());
            }
        } finally {
            session.close();
        }
    }
}