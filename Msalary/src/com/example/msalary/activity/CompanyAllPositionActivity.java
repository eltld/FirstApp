package com.example.msalary.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.msalary.R;

import com.example.msalary.entity.CompanyEntity;
import com.example.msalary.entity.JobEntity;
import com.example.msalary.entity.RequestEntity;
import com.example.msalary.entity.ResponseResult;
import com.example.msalary.entity.ShowResult;
import com.example.msalary.internet.InternetHelper;
import com.example.msalary.json.JsonPositionsOfCompany;
import com.example.msalary.json.JsonSelectCompany;
import com.example.msalary.util.MConstant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**
 * ĳ��˾������ְλ�б��Լ�����������������ۻᵽ���۽��沢����ʾ�������۵����ݡ�
 * 
 * @author Administrator
 * 
 */
public class CompanyAllPositionActivity extends BaseActivity implements
		OnItemClickListener {
	private ListView allPosition_list;// һ����˾����ְλ�б�
	private TextView comment_someCompany;// ����
	private TextView tv_company_name;
	
	private allpositionAdapter adapter;
	private ShowResult showResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ���ñ��⣬�Զ������
		setContentView(R.layout.company_allpositions_salary);
		initView();
	}

	
	
	@Override
	protected void initView() {
		super.initView();
		tv_title.setText(getString(R.string.company_all_position_title));
		tv_company_name = (TextView) findViewById(R.id.company_allpositions_company_name_tv);
		
		allPosition_list = (ListView) findViewById(R.id.allposition_list);
		// �����¼�����
		comment_someCompany = (TextView) findViewById(R.id.comment_somecompany_tv);
		comment_someCompany.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CompanyAllPositionActivity.this,
						CommentActivity.class);
				intent.putExtra("companyId",
						getIntent().getIntExtra("companyId", 0));
				intent.putExtra("companyName",
						getIntent().getStringExtra("companyName"));
				startActivity(intent);
			}
		});
		tv_company_name.setText(getIntent().getStringExtra("companyName"));
		showResult = new ShowResult();
		adapter = new allpositionAdapter(this, showResult);
		allPosition_list.setAdapter(adapter);
		request(getIntent().getIntExtra("companyId", 0));
	}

	public void onClick(View view){
		switch(view.getId()){
		case R.id.back:
			finish();
			break;
		}
	}

	/**
	 * ������������
	 * 
	 * @param i
	 */
	private void request(int i) {
		RequestEntity requestEntity = new RequestEntity(this,
				MConstant.URL_COMPANY_DETAIL);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", i);
		requestEntity.params = map;
		new InternetHelper(this).requestThread(requestEntity, this);
	}

	@Override
	public void requestSuccess(ResponseResult responseResult) {
		Log.d("tag", "showResult" + responseResult);
		showResult = JsonPositionsOfCompany.parse(responseResult, this);
		if(showResult==null){
			return;
		}
		comment_someCompany.setText("����" + showResult.list.size()//map.get("commentCount")
				+ "��");
		adapter.setData(showResult);
	}

	/**
	 * 
	 * @author ĳһ��˾������ְλ������Ӧ���ʡ�adapter
	 * 
	 */
	private class allpositionAdapter extends BaseAdapter {

		private Context context;
		private ArrayList<JobEntity> list;

		public allpositionAdapter(Context context, ShowResult result) {
			this.context = context;
			this.list = (ArrayList<JobEntity>) result.list;
		}

		public void setData(ShowResult result) {
			this.list = (ArrayList<JobEntity>) result.list;
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.company_allposition_list, null);
				holder.allPosition_tv = (TextView) convertView
						.findViewById(R.id.allposition_tv);
				holder.positionSalary_tv = (TextView) convertView
						.findViewById(R.id.position_salary_tv);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.allPosition_tv.setText(list.get(position).getName());
			holder.positionSalary_tv.setText(list.get(position).getSalary()
					+ "��");
			return convertView;
		}

		class ViewHolder {
			public TextView allPosition_tv;
			public TextView positionSalary_tv;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}
}
