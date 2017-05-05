package com.bingo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

public class CopyImgToSDCard {
	private static final String SD_PATH = "/storage/emulated/0/";

	public static void CopyImg(Context context, String fileName) {
		File file = new File(SD_PATH);
		// ä¸???¨å???»ºï¼???¨å°±è¿??
		if (!file.exists())
			file.mkdirs();
		File copyFile = new File(SD_PATH + fileName);
		InputStream in = null;
		OutputStream out = null;
		try {
			// ?·å??¾ç?ï¼???¾ç?copy??dcard
			in = context.getAssets().open(fileName);
			out = new FileOutputStream(copyFile);
			byte[] buff = new byte[1024];
			int len;
			while ((len = in.read(buff)) > 0) {
				out.write(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
