package com.dfhe.cn.model;

import com.dfhe.cn.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/5/26.
 */
public class UserModel implements IUserModel {
    List<UserBean>list=new ArrayList<UserBean>();

    @Override
    public void save(UserBean userBean) {
        list.add(userBean);
    }

    @Override
    public UserBean load(int id) {
        return list.get(list.indexOf(new UserBean(id,"","")));
    }
}
