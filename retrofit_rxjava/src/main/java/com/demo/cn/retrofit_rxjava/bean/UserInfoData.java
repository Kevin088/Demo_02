package com.demo.cn.retrofit_rxjava.bean;

import java.util.List;

public class UserInfoData {
//	/**
//	 * 用户信息集合
//	 */
//	public List<UserInfo> Data;
//	/**
//	 * 错误信息
//	 */
//	public String ErrorMsg;
//	/**
//	 * 返回结果
//	 */
//	public String Result;
//
//	@Override
//	public String toString() {
//		return "UserInfoData{" +
//				"Data=" + Data +
//				", ErrorMsg='" + ErrorMsg + '\'' +
//				", Result='" + Result + '\'' +
//				'}';
//	}
	String success;
	String result;
//	String error;
//	String unAuthorizedRequest;


	@Override
	public String toString() {
		return "UserInfoData{" +
				"success='" + success + '\'' +
				", result='" + result + '\'' +
				'}';
	}
}
