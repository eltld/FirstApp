/**
 * 
 */
package com.example.msalary.activity;

import com.example.msalary.entity.ResponseResult;
import com.example.msalary.internet.IRequestCallBack;

import android.app.Activity;
import android.widget.Toast;

/**
 * ��Ŀ���ƣ�Ӫ���ƶ����ܹ���ƽ̨ <br>
 * �ļ�����TerminalDetailsFragment.java <br>
 * ���ߣ�@����    <br>
 * ����ʱ�䣺2013/11/24 <br>
 * ��������: �ն����� <br>
 * �汾 V 1.0 <br>               
 * �޸����� <br>
 * ����      ԭ��  BUG��    �޸��� �޸İ汾 <br>
 */
public class BaseActivity extends Activity implements IRequestCallBack{

	
	/**
	 * ��ʼ���ؼ�
	 */
	protected void initView(){
		
	}
	
	/* (non-Javadoc)
	 * @see com.example.msalary.internet.IRequestCallBack#requestSuccess(com.example.msalary.entity.ResponseResult)
	 */
	@Override
	public void requestSuccess(ResponseResult responseResult) {
		
	}

	/* (non-Javadoc)
	 * @see com.example.msalary.internet.IRequestCallBack#requestFailedStr(java.lang.String)
	 */
	@Override
	public void requestFailedStr(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
}
