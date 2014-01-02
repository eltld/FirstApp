package com.example.msalary.activity;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.msalary.R;
import com.example.msalary.entity.RequestEntity;
import com.example.msalary.entity.ResponseResult;
import com.example.msalary.entity.ShowResult;
import com.example.msalary.internet.InternetHelper;
import com.example.msalary.util.MConstant;

/**
 * ��Ҫ�ع��ʵĽ���
 * 
 * @author Administrator
 * 
 */
public class ExposureSalary extends BaseActivity {
	private ImageButton back_btn;

	private EditText et_CompanyName, et_JobName, et_Salary;

	private ShowResult showResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// �Զ������
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.exposure_salary_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebutton);
		initView();
	}

	@Override
	protected void initView() {
		// ���ؼ�
		back_btn = (ImageButton) findViewById(R.id.exposure_salary_back);
		back_btn.setBackgroundResource(R.drawable.cloud_back_click);
		et_CompanyName = (EditText) findViewById(R.id.exposure_et_companyName);
		et_JobName = (EditText) findViewById(R.id.exposure_et_jobName);
		et_Salary = (EditText) findViewById(R.id.exposure_et_salary);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.exposure_btn_submit:// �����xml�����õ�
			String company = et_CompanyName.getText().toString().trim();
			String job = et_JobName.getText().toString().trim();
			String salary = et_Salary.getText().toString().trim();

			if (checkInput(company, job, salary)) {
				request(company, job, salary);
			}
			break;
		}
	}

	private boolean checkInput(String company, String job, String salary) {
		if (company.equals("")) {
			Toast("�ף�д����˾����");
			return false;
		} else if (job.equals("")) {
			Toast("�ף�ְλҲҪ�и����ְ�");
			return false;
		} else if (salary.equals("")) {
			Toast("�ף�����־Ը����?");
			return false;
		}
		Toast("success>" + company);
		return true;
	}

	private void request(String company, String job, String salary) {
		RequestEntity requestEntity = new RequestEntity(this,
				MConstant.URL_NEW_JOB);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("companyName", company);
		map.put("jobName", job);
		map.put("salary", salary);
		requestEntity.params = map;
		new InternetHelper().requestThread(requestEntity, this);
	}

	@Override
	public void requestSuccess(ResponseResult responseResult) {
		Log.d("tag", "showResult" + responseResult);
		// showResult = JsonSelectConpany.parse(responseResult,this);
		Intent i = new Intent(ExposureSalary.this, MainActivity.class);
		startActivity(i);

	}

}
