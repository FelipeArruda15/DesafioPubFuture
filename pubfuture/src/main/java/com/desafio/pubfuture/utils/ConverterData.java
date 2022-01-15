package com.desafio.pubfuture.utils;

public class ConverterData {
	
	public static String ConverterParaSQL(String data) {
		return data.substring(6,10) + "-" + data.substring(3, 5) + "-" + data.substring(0,2);
	}

}
