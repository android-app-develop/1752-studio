package com.bingo.util;

import java.util.Random;

public class RandUtil {
	
	public static int getRandom(int begin, int end) {
		Random rand=new Random();
		return rand.nextInt(end-begin+1)+begin;
	}
}
