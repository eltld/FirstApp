/**
 * 
 */
package com.example.msalary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msalary.R;
import com.example.msalary.entity.ResponseResult;
import com.example.msalary.internet.IRequestCallBack;

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

	protected TextView tv_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	}

	/**
	 * ��ʼ���ؼ�
	 */
	protected void initView(){
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
		tv_title = (TextView) findViewById(R.id.title_tv);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            share();
        }
        return true;
    }

	
	/**����*/
	private void share(){
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");//intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "�����Ƽ�");
		intent.putExtra(Intent.EXTRA_TEXT, "�ˣ�������ʹ�ò鹤�ʣ���Ҳ�����ɣ�");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(intent, getTitle()));

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
	
	public void Toast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
}
