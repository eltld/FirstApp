package com.example.msalary.activity;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.msalary.R;
import com.example.msalary.entity.RequestEntity;
import com.example.msalary.entity.ResponseResult;
import com.example.msalary.internet.InternetHelper;
import com.example.msalary.util.MConstant;
/**
 * �������
 * @author Administrator
 *
 */
public class MistakeActivity extends BaseActivity {
	private TextView mistake_companyName;
	private TextView mistake_jobName;
	private EditText mistake_salary_et;
	private EditText mistake_critical_et;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.mistake_main);
    }
     protected void initView(){
    	 super.initView();
    	 tv_title.setText(getString(R.string.mistake_title));
    	 mistake_companyName=(TextView) findViewById(R.id.mistake_companyName);
    	 mistake_jobName=(TextView) findViewById(R.id.mistake_critical_et);
    	 mistake_salary_et=(EditText) findViewById(R.id.mistake_salary_et);
    	 mistake_critical_et=(EditText) findViewById(R.id.mistake_critical_et);
    	 
    	 mistake_companyName.setText(getIntent().getStringExtra("companyName"));
    	 mistake_jobName.setText(getIntent().getStringExtra("jobName"));
     }
     
     public void onClick(View view){
 		switch(view.getId()){
 		case R.id.back:
 			finish();
 			break;
 		case R.id.mistake_submit://�ύ
 			String salary = mistake_salary_et.getText().toString();
 			String content = mistake_critical_et.getText().toString();
 			if(checkSalary(salary)&&checkContent(content)){//��������ȷ
 				request(salary,content);
 			}
 			break;
 		}
 	}
     
     /**
      * �������
      * @param str
      * @return
      */
     private boolean checkSalary(String str){
    	 if(str.equals("")){
    		 Toast("н����Ĳ���û��!");
    		 return false;
    	 }else if(str.trim().length()>8){
    		 Toast("����������̫С�ݲ����㰡");
    		 return false;
    	 }
    	 return true;
     }
     
     /**
      * �������
      * @param str
      * @return
      */
     private boolean checkContent(String str){
    	 if(str.equals("")){
    		 Toast("��ôҲ������һ�����۰�");
    		 return false;
    	 }else if(str.trim().length()>100){
    		 Toast("�ף���д��ô�డ");
    		 return false;
    	 }
    	 return true;
     }
     
     
     
     /**
      * �ύ������Ϣ
      * @param salary
      * @param content
      */
     private void request(String salary,String content){
    	 RequestEntity requestEntity = new RequestEntity(this,
 				MConstant.URL_NEW_JOB);
 		HashMap<String, Object> map = new HashMap<String, Object>();
 		map.put("salary", salary);
 		map.put("content", content);
 		requestEntity.params = map;
 		new InternetHelper(this).requestThread(requestEntity, this);
     }
     
	@Override
	public void requestSuccess(ResponseResult responseResult) {
		super.requestSuccess(responseResult);
		finish();
	}
     
     
 	
}
