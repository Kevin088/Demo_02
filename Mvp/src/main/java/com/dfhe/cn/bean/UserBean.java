package com.dfhe.cn.bean;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/5/26.
 */
public class UserBean {
    private int id;
    private String mFirstName;
    private String mLastName;

    public UserBean(int id, String mFirstName, String mLastName) {
        this.id = id;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
    }

    public UserBean(String firstName, String lastName) {
        this. mFirstName = firstName;
        this. mLastName = lastName;
    }
    public UserBean() {
    }
    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setmFirstName(String mFirstName) {

        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof UserBean&&o!=null&&((UserBean) o).getId()==getId())
           return true;
        else
            return false;


    }

}
