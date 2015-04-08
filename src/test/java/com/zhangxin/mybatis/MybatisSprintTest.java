package com.zhangxin.mybatis;

import com.zhangxin.mybatis.dao.UserDao;
import com.zhangxin.mybatis.bean.Article;
import com.zhangxin.mybatis.bean.User;
import com.zhangxin.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:case1-transactional.xml")
public class MybatisSprintTest {

    @Resource(name ="userMapper")
    UserDao mapper;
    @Resource
    UserService userService;

    @Test
    public  void test()
    {
        //test transation
        try {
            userService.test(true);
        }catch (Exception e){
            System.out.println("error");
        }

        System.out.println(mapper.selectLikeName("%"));
        //测试id=1的用户查询，根据数据库中的情况，可以改成你自己的.
        System.out.println("得到用户id=1的用户信息");
        User user = mapper.selectByID(1);
        System.out.println(user.getUserAddress());

        //得到文章列表测试
        System.out.println("得到用户id为1的所有文章列表");
        List<Article> articles = mapper.selectArticlesByUserID(1);

        for(Article article:articles){
            System.out.println(article.getContent()+"--"+article.getTitle());
        }
    }
}