package com.dfhe.cn.model;

import com.dfhe.cn.bean.UserBean;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/5/26.
 */
public interface IUserModel {
    void save (UserBean userBean);

    UserBean load(int id);// 通过id读取user信息,返回一个UserBean
}
