package com.zhangxin.mybatis.dao;

import com.zhangxin.mybatis.bean.TempBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**Created by zhangxin.zhang on 2015/4/13.
 */
@Component
public interface TempBeanDao {
    void insert(TempBean tempBean);
    List<TempBean> selectAll();
}
