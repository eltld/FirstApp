package com.example.msalary.util;

public class ErrorCodeUtils {
	
	/**
	 * �������� ת������Ҫ��ʾ�Ĵ��������ַ���
	 * @param resultCode
	 * @return
	 */
	public static String changeCodeToStr(int resultCode) {
		if(resultCode<error.length-1){
			return error[resultCode];
		}else{
			return error[2];
		}
		
	}
	
	private static String[] error = {
		"���������ӣ���������",	
		"���ݽ�������",
		"�������쳣"
	};
	
}
