package com.example.msalary.internet;

import com.example.msalary.entity.ResponseResult;

public interface IRequestCallBack {

	/**
	 * ��������ɹ�
	 * @param responseResult
	 */
	 public void requestSuccess(ResponseResult responseResult);
     
	 /**
	  * ��������ʧ��
	  * @param str
	  */
     public void requestFailedStr(String str);
}
