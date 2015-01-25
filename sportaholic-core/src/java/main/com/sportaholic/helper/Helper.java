package com.sportaholic.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.sportaholic.model.Uri;

public class Helper {

	public static Date buildDate(int year, int month, int day, Integer hour, Integer minute) {
		Calendar cal = Calendar.getInstance();
		if (hour == null || minute == null)
			cal.set(year, month-1, day);
		else 
			cal.set(year, month-1, day, hour, minute);
		return cal.getTime();
	}
	
	public static List<Uri> getBreadcrumbs(Uri uri) {
		List<Uri> tempUris = new ArrayList<Uri>();
		while (uri != null) {
			tempUris.add(uri);
			uri = uri.getParent();
		}
		List<Uri> uris = new ArrayList<Uri>();
		for(int i = tempUris.size() - 1; i >= 0; i--) 
			uris.add(tempUris.get(i));
		return uris;
	}
	
	public static String getStringFromInputStream(InputStream inputStream) throws Exception {
		 
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
 
		String line;
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}

		bufferedReader.close();
		
		return stringBuilder.toString();
	}
	
	public static String getRandomString(int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    stringBuilder.append(c);
		}
		return stringBuilder.toString();
		
	}
	
}
