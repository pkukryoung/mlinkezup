package com.kdj.mlink.ezup3.common;

import com.kdj.mlink.ezup3.data.dao.UserDto;

public class YDMASessonUtil {
	
	static UserDto loginUser;
	static UserDto loginCompno;
	static String  imageFolderName; //
	
	
	public  final  static int YD_LOGIN_TYPE= 0;
	public  final  static int DOMESIN_LOGIN_TYPE= 1;
     static int LoginType = 0;
	
	
     public static int getLoginType() {
    	 return LoginType;
     }
     
     
	public static void setLoginType() {
		
		try {
			LoginType =  YDMAStringUtil.convertToInt( YDMAProperties.getInstance().getAppProperty("User.LoginType"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//LoginType = 0;
		}
		System.out.println(LoginType);	
	}
	
	
	
	
	/*
	 * 프로그램 절대결로를 가져온다. 
	 */
	public static String getAppPath()
	{
		String ret = "";
		try
		{
			ret = java.nio.file.Paths.get("").toAbsolutePath().toString();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	/*
	 * apppath 변경하기.. 
	 */
	public static String getAppPath(String  path)
	{
		 String ret = "";
		 String appPath =  YDMASessonUtil.getAppPath();
		 path =  path.replace("C:", "").replace("c:", "");
		 ret = String.format("%s%s", appPath, path );
		 return ret;
	}
	
	
	public static String getImageFolderName() {
		return imageFolderName;
	}
	
	public static void setImageFolderName(String imageFolder) {
		YDMASessonUtil.imageFolderName = imageFolder;
	}
	
	public static void setUserInfo(UserDto user){
		loginUser = user;
	}
	public static UserDto getUserInfo(){
		return loginUser;
	}
	public static void setCompnoInfo(UserDto compno){
		loginCompno = compno;
	}
	public static UserDto getCompnoInfo(){
		return loginCompno;
	}
	
}
