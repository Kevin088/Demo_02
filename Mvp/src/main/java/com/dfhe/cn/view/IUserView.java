package com.dfhe.cn.view;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/5/26.
 */
public interface IUserView {
    int getID();

    String getFristName();

    String getLastName();

    void setFirstName(String firstName);

    void setLastName(String lastName);
}
