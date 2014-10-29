package com.sun.hair.act;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.sun.hair.BaseAct;
import com.sun.hair.R;

/**
 * ��¼ҳ��
 * @author sunqm
 *
 */
public class LoginAct extends BaseAct{

	private EditText etName,etPwd;
	
	@Override
	public void initTitle() {
		setContentView(R.layout.act_login);
		setTitle_("��¼");
	}

	
	@Override
	public void initView() {
		etName = (EditText) findViewById(R.id.act_login_name);
		etPwd = (EditText) findViewById(R.id.act_login_pwd);
	}
	
	public void onClick(View view){
		switch(view.getId()){
		case R.id.act_login_login_btn://��¼
			if(checkInput()){
				request();
			}
			break;
		case R.id.act_login_regist_btn://��תע��ҳ��
			startActivity(new Intent(LoginAct.this,RegistAct.class));
			break;
		}
	}
	
	private boolean checkInput(){
		if(etName.getText().toString().equals("")){
			Toast("�������û���");
		}else if(etPwd.getText().toString().equals("")){
			Toast("����������");
		}else{
			return true;
		}
		return false;
	}
	
	private  void request(){
		
	}

	
}
