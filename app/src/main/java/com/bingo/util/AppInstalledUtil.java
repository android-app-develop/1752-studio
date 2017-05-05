package com.bingo.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppInstalledUtil {
	/*
	* check the app is installed
	*/
	public static boolean isAppInstalled(Context context,String packagename)
	{
	PackageInfo packageInfo;        
	try {
	            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
	         }catch (NameNotFoundException e) {
	            packageInfo = null;
	            e.printStackTrace();
	         }
	         if(packageInfo ==null){
	            //System.out.println("没有安装");
	            return false;
	         }else{
	            //System.out.println("已经安装");
	            return true;
	        }
	}
}
