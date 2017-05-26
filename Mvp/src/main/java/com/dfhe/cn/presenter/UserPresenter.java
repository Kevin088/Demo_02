package com.dfhe.cn.presenter;

import com.dfhe.cn.bean.UserBean;
import com.dfhe.cn.model.IUserModel;
import com.dfhe.cn.model.UserModel;
import com.dfhe.cn.view.IUserView;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/5/26.
 */
public class UserPresenter {
    private IUserView mUserView;
    private IUserModel mUserModel;
    public UserPresenter(IUserView view){
        mUserView=view;
        mUserModel=new UserModel();
    }
    public void saveUser(int id,String firstName,String lastName){
        mUserModel.save(new UserBean(id,firstName,lastName));
    }
    public void loadUser(int id){
        UserBean user=mUserModel.load(id);
        mUserView.setFirstName(user.getFirstName());
        mUserView.setLastName(user.getLastName());
    }
}
