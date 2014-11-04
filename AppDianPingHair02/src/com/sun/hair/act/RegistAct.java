package com.sun.hair.act;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sun.hair.BaseAct;
import com.sun.hair.R;

/**
 * ע��
 * @author sunqm
 *
 */
public class RegistAct extends BaseAct{

	private EditText etName,etPwd,etPwd2;
	
	@Override
	public void initTitle() {
		setContentView(R.layout.act_regist);
		setTitle_("ע��");
//		TextView tv = (TextView) findViewById(R.id.act_title_left_tv);
//		tv.setText("ȡ��");
		findViewById(R.id.act_title).setBackgroundResource(R.drawable.bg_top);
	}

	
	@Override
	public void initView() {
		etName = (EditText) findViewById(R.id.act_regist_name);
		etPwd = (EditText) findViewById(R.id.act_regist_pwd);
		etPwd2 = (EditText) findViewById(R.id.act_regist_pwd2);
	}
	
	public void onClick(View view){
		switch(view.getId()){
		case R.id.act_regist_btn://��¼
			if(checkInput()){
				request();
			}
			break;
		}
		
	}
	
	private boolean checkInput(){
		if(etName.getText().toString().equals("")){
			Toast("�������û���");
		}else if(etPwd.getText().toString().equals("")){
			Toast("����������");
		}else if(etPwd2.getText().toString().equals("")){
			Toast("���ٴ���������");
		}else if(!etPwd.getText().toString().equals(etPwd2.getText().toString())){
			Toast("�������벻һ��");
		}else{
			return true;
		}
		return false;
	}
	
	private  void request(){
		
	}

	
	
}
