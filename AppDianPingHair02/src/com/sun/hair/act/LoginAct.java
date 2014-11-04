package com.sun.hair.act;

import java.io.UnsupportedEncodingException;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.sun.hair.BaseAct;
import com.sun.hair.R;
import com.sun.hair.service.CommentsService;
import com.sun.hair.service.IRequestCallBack;
import com.sun.hair.utils.MConstant;

/**
 * ��¼ҳ��
 * @author sunqm
 *
 */
public class LoginAct extends BaseAct implements IRequestCallBack{

	private EditText etName,etPwd;
	
	@Override
	public void initTitle() {
		setContentView(R.layout.act_login);
		setTitle_("��¼");
		findViewById(R.id.act_title).setBackgroundResource(R.drawable.bg_top);
	}

	
	@Override
	public void initView() {
		etName = (EditText) findViewById(R.id.act_login_name);
		etPwd = (EditText) findViewById(R.id.act_login_pwd);
	}
	
	public void onClick(View view) throws UnsupportedEncodingException{
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
	
	private  void request() throws UnsupportedEncodingException{
		AjaxParams params = new AjaxParams();
		
		params.put("name",new String(etName.getText().toString().getBytes("gbk"),"utf-8"));
		params.put("pwd",etPwd.getText().toString());
		new CommentsService().request(this, MConstant.URL_LOGIN, params, this);
	}


	@Override
	public void onSuccess(Object o) {
		
	}


	@Override
	public void onFailed(String msg) {
		
	}

	
}
