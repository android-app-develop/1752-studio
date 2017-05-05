package com.bingo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preference {
	public static void saveFloatData(Context context,String key,float myScore){
	    SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	    Editor editor = sp.edit();
	    editor.putFloat(key, myScore);
	    editor.commit();
	  }
	
	public static Float loadFloatData(Context context,String key) {
	    SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getFloat(key, (float) 0.0);
	  }
	
	public static void saveBooleanData(Context context,String key,Boolean value){
	    SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	    Editor editor = sp.edit();
	    editor.putBoolean(key, value);
	    editor.commit();
	  }
	
	public static Boolean loadBooleanData(Context context,String key) {
	    SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//	    Toast.makeText(getActivity(), sp.getString(key, "").toString(), 0).show();
		return sp.getBoolean(key, false);
	  }
}
