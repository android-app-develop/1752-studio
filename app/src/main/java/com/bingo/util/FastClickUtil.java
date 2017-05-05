package com.bingo.util;
public class FastClickUtil {
    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();  
        long timeD = time - lastClickTime;  
        if ( timeD >0 && timeD < 500) {   
            return true;   
        }   
        lastClickTime = time;   
        return false;   
    }
}