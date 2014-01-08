/**
 * 
 */
package com.example.msalary.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.msalary.R;
import com.example.msalary.adapter.PositionAllCompanyAdapter.ViewHolder;
import com.example.msalary.entity.CompanyEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
public class MainCompanysAdapter extends BaseAdapter{
	
	private Context context = null;
	private ArrayList<CompanyEntity> list = new ArrayList<CompanyEntity>();
	public MainCompanysAdapter(Context context,ArrayList<CompanyEntity> list){
		this.context=context;
		this.list=list;
	}
	
	public void setData(ArrayList<CompanyEntity> list){
		this.list=list;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.hot_company_listitem, null);
			holder.hotCompany_tv=(TextView) convertView.findViewById(R.id.hot_company_textview);
			holder.companyMessage_tv=(TextView) convertView.findViewById(R.id.company_message_textview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.hotCompany_tv.setText(list.get(position).getName());
		holder.companyMessage_tv.setText(list.get(position).getJobCount()+"ְλ");
		return convertView;
	}
	static class ViewHolder {
		TextView hotCompany_tv;
		TextView companyMessage_tv;
	}
	 
}
