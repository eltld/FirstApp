package com.sun.hair.utils;

import android.widget.EditText;

public class CheckInput {
	
	/**
	 * ������Ƿ�Ϊ��
	 * @param et
	 * @return
	 */
	private boolean isNull(EditText et){
		if(et!=null&&et.getText()!=null&&et.getText().toString().trim().equals("")){
			return false;
		}
		return true;
	}
	
	
	
}
