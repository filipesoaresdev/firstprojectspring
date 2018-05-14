package com.firstproject.filipe.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static List<Integer> decodeIntList(String s){
		String[] array = s.split(",");
		List<Integer> listInteger = new ArrayList<>();
		for (String string : array) {
			listInteger.add(Integer.parseInt(string));
		}
		return listInteger;
		//Lamb
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());

	}
	
	public static String decodeParam(String s) {
		
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}

}
