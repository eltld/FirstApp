package com.tellout.act;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.tellout.constant.DbConstant;
import com.tellout.constant.MConstant;
import com.tellout.entity.BaseEntity;
import com.tellout.entity.RequestEntity;
import com.tellout.util.InternetHelper;
import com.tellout.util.SpHelper;

public class Login_Regist_Act extends BaseActivity implements OnClickListener,
		OnCheckedChangeListener {

	private EditText edit1, edit2, edit3, edit4;

	private Button leftBtn, rightBtn;

	private CheckBox checkBox;

	private boolean isLogin = true;
	/**是否自动登录*/
	private boolean autoLogin= false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_regist_act);
		initView();
	}

	private void initView() {
		edit1 = (EditText) findViewById(R.id.login_regist_ededitText1);
		edit2 = (EditText) findViewById(R.id.login_regist_ededitText2);
		edit3 = (EditText) findViewById(R.id.login_regist_ededitText3);
		edit4 = (EditText) findViewById(R.id.login_regist_ededitText4);
		leftBtn = (Button) findViewById(R.id.login_regist_left_btn);
		rightBtn = (Button) findViewById(R.id.login_regist_right_btn);
		checkBox = (CheckBox) findViewById(R.id.login_regist_checkBox1);
		findViewById(R.id.back).setOnClickListener(this);
		checkBox.setOnCheckedChangeListener(this);
		leftBtn.setOnClickListener(this);
		rightBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.login_regist_left_btn:// 左侧
			if (isLogin) {
				edit3.setVisibility(View.VISIBLE);
				edit4.setVisibility(View.VISIBLE);
				edit2.setVisibility(View.GONE);
				edit1.setHint(getString(R.string.nick_name));
				edit2.setHint(getString(R.string.email));
				edit2.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); 
				leftBtn.setText(getString(R.string.login));
				rightBtn.setText(getString(R.string.regist));
			} else {
				edit2.setVisibility(View.VISIBLE);
//				edit1.setText("test1");
//				edit2.setText("123456");
				edit1.setHint(getString(R.string.email));
				edit2.setHint(getString(R.string.pwd));
				edit2.setTransformationMethod(PasswordTransformationMethod.getInstance()); 
				edit3.setVisibility(View.GONE);
				edit4.setVisibility(View.GONE);
				leftBtn.setText(getString(R.string.regist));
				rightBtn.setText(getString(R.string.login));
			}
			isLogin = !isLogin;

			break;
		case R.id.login_regist_right_btn:// 右侧
//			showResult(0, null);
			if (!InternetHelper.isInternetAvaliable(this)) {
				Toast("当前无网络");
				return;
			}
			if (isLogin) {// 登录
				if(!checkLogin())//检查登录输入是否正确
					return ;
				LoginRequest();
			} else {// 注册
				
				if(!checkRegist()){//检查注册输入是否正确
					return;
				}
				RegistRequest();
			}

			break;
		case R.id.back://返回
			finish();
			break;
		}
	}
	
	
	
	@Override
	public void showResult(int type, BaseEntity baseEntity) {
//		super.showResult(type, baseEntity);
//		if(type ==MConstant.REQUEST_CODE_LOGIN_){//
//			
//		}else if(type == MConstant.REQUEST_CODE_REGIST){
//			
//		}
		
		String name = null;
		String pwd = null;
		if(isLogin){
			name = edit1.getText().toString();
			pwd = edit2.getText().toString();
		}else{
			name = edit2.getText().toString();
			pwd = edit3.getText().toString();
		}
		SpHelper spHelper = new SpHelper(this);
		spHelper.putBool("autoLogin", autoLogin);
		spHelper.putStr("name", name);
		spHelper.putStr("pwd", pwd);
		MConstant.USER_ID_VALUE = baseEntity.getMap().get(DbConstant.DB_USER_ID);
		spHelper.putStr("userId", MConstant.USER_ID_VALUE);
//		startActivity(new Intent(Login_Regist_Act.this, TellOutAct.class));
		Log.d("tag","userid--》"+MConstant.USER_ID_VALUE);
		finish();
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		autoLogin = arg1;

	}

	private boolean checkLogin() {
		String name = edit1.getText().toString();
		String pwd = edit2.getText().toString();
		if (name.equals("") || pwd.equals("")) {
			Toast("用户名或者密码不能为空");
			return false;
		}
//		if(!EmailFormat(name)){//验证邮箱格式
//			Toast("邮箱格式错误");
//			return false;
//		}
		if(pwd.length()<6 || pwd.length()>15){
			Toast("密码长度应为6~15位");
			return false;
		}
		return true;
	}

	private boolean EmailFormat(String eMAIL1) {// 邮箱判断正则表达式
		Pattern pattern = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher mc = pattern.matcher(eMAIL1);
		return mc.matches();
	}

	private void LoginRequest(){
		String name = edit1.getText().toString();
		String pwd = edit2.getText().toString();
		RequestEntity requestEntity = new RequestEntity();
		requestEntity.setRequestType(MConstant.REQUEST_CODE_LOGIN_);
		requestEntity.setPost(true);
		Map<String,String> map = new HashMap<String,String>();
		
		map.put(DbConstant.DB_USER_NICK_NAME, name);
		map.put(DbConstant.DB_USER_PWD, pwd);
		requestEntity.setParams(map);
		request(requestEntity);
	}
	
	private void RegistRequest(){
		String nickName = edit1.getText().toString();
//		String email = edit2.getText().toString();
		String pwd = edit3.getText().toString();
		
		RequestEntity requestEntity = new RequestEntity();
		requestEntity.setRequestType(MConstant.REQUEST_CODE_REGIST);
		requestEntity.setPost(true);
		Map<String,String> map = new HashMap<String,String>();
		map.put(DbConstant.DB_USER_NICK_NAME, nickName);
//		map.put(DbConstant.DB_USER_EMAIL, email);
		map.put(DbConstant.DB_USER_PWD, pwd);
		
		requestEntity.setParams(map);
		request(requestEntity);
	}
	
	private boolean checkRegist() {
		String nickName = edit1.getText().toString();
//		String email = edit2.getText().toString();
		String pwd = edit3.getText().toString();
		String pwd2 = edit4.getText().toString();
		if(nickName.equals("")){
			Toast("昵称不能为空");
			return false;
		}
//		if(email == ""){
//			Toast("邮箱不能为空");
//			return false;
//		}
		if(pwd .equals("")|| pwd2.equals("")){
			Toast("密码不能为空");
			return false;
		}
		if(nickName.length()>20){
			Toast("昵称长度不能超过20");
			return false;
		}
//		if(!EmailFormat(email)){
//			Toast("邮箱格式不正确");
//			return false;
//		}
		if(!pwd.equals(pwd2)){
			Toast("两次密码不一致");
			return false;
		}
		if(pwd.length()<6 || pwd.length()>15){
			Toast("密码长度应为6~15位");
			return false;
		}
		return true;
	}

	
	
//	public void showResult(int type, Object object) {
//		JSONObject jsonObject = (JSONObject) object;
//		Log.d("tag", "Login--->showResult" + jsonObject);
//		try {
//			
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		
//
//	}	

}
