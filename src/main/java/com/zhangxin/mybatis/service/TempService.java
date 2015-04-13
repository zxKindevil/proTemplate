package com.zhangxin.mybatis.service;

import com.zhangxin.mybatis.bean.TempBean;

/** Created by zhangxin.zhang on 2015/4/13.
 */
public interface TempService {
    void insert(TempBean tempBean);
    void testTransation(boolean flag);
}
