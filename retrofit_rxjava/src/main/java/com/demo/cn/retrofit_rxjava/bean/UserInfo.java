package com.demo.cn.retrofit_rxjava.bean;

/**
 * 用户信息
 * 
 */
public class UserInfo {

	// "UserId": "6",
	// "Name": "王万航",
	// "UserName": "wangwh",
	// "NickName": "",
	// "Gender": "0",
	// "Tel": "",
	// "Mobile": "13301352521",
	// "PostCode": "",
	// "Address": "",
	// "Email": "",
	// "QQ": "",
	// "Dept": "机构1",
	// "Position": "人力",
	// "Avatar": "http://192.168.16.250/"
	// "newNotice": "2"

	/**
	 * UserId
	 */
	public String UserId;
	/**
	 * 真实姓名
	 */
	public String Name;
	/**
	 * 用户名-登录时用
	 */
	public String UserName;
	/**
	 * 昵称
	 */
	public String NickName;
	/**
	 * 性别 0男 1女
	 */
	public String Gender;
	/**
	 * 电话
	 */
	public String Tel;
	/**
	 * 手机
	 */
	public String Mobile;
	/**
	 * 邮编
	 */
	public String PostCode;
	/**
	 * 邮寄地址
	 */
	public String Address;
	/**
	 * 邮箱
	 */
	public String Email;
	/**
	 * QQ
	 */
	public String QQ;
	/**
	 *部门
	 */
	public String Dept;
	/**
	 * 职位
	 */
	public String Position;
	/**
	 * 头像
	 */
	public String Avatar;
	/**
	 * 新消息
	 */
	public String newNotice;
	/**
	 * 单一设备登录标识码
	 */
	public String Stamp;
	/**
	 * 发送积分的时间基数
	 */
	public String hours;
	/**
	 * 个人签名
	 */
	public String Des;

	public String IsShowMobile;
	public String Token;
	public UserInfo() {
		super();
	}


}
