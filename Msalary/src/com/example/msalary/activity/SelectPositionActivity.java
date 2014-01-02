package com.example.msalary.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import com.example.msalary.R;
import com.example.msalary.entity.CompanyEntity;
import com.example.msalary.entity.JobEntity;
import com.example.msalary.entity.RequestEntity;
import com.example.msalary.entity.ResponseResult;
import com.example.msalary.entity.ShowResult;
import com.example.msalary.internet.InternetHelper;
import com.example.msalary.json.JsonSelectConpany;
import com.example.msalary.json.JsonSelectPosition;
import com.example.msalary.util.MConstant;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * ѡ��Ҫ���ְλ���档����һ�����Ĳ�ְͬλ������ҳ��Ĳ���������
 * @author Administrator
 *
 */
public class SelectPositionActivity extends BaseActivity implements OnItemClickListener{

	private ImageButton back_btn;
	private ArrayList<HashMap<String, String>> listPosition;
	private ListView position_list;
	private ShowResult showResult = null;
	selectPosition adapter;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	//�Զ�����⡣��
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    	setContentView(R.layout.position_search_main);
    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.position_search_title);
    	back_btn=(ImageButton) findViewById(R.id.position_search_back);
    	back_btn.setBackgroundResource(R.drawable.cloud_back_click);
    	initView();
    	request(getIntent().getStringExtra("key"));
 
    }
     @Override
    protected void initView() {
    	// TODO Auto-generated method stub
    	 position_list=(ListView) findViewById(R.id.position_list_lv);
    	 showResult = new  ShowResult();
    	 adapter=new selectPosition(this, showResult);
    	 position_list.setAdapter(adapter);
    }
     /**
 	 * ������������
 	 * @param requestStr
 	 */
 	private void request(String requestStr){
 		getString(R.string.url_home);
 		RequestEntity requestEntity =new RequestEntity(this,MConstant.URL_SELECT_POSITION);
 		HashMap<String,Object> map = new HashMap<String,Object>();
 		map.put("key", requestStr);
 		requestEntity.params = map;
 		new InternetHelper().requestThread(requestEntity, this);
 	}

 	@Override
 	public void requestSuccess(ResponseResult responseResult) {
 		Log.d("tag","showResult"+responseResult);
 		showResult = JsonSelectPosition.parse(responseResult,this);
 		
 		 adapter.setData(showResult);
 	}
 	
     /**
      * ͬ�಻ְͬλ���б���Ӧ��adapter��
      * @author Administrator
      *
      */
     private class selectPosition extends BaseAdapter{
    	 private Context context;
    	 private ArrayList<JobEntity> list;
         private selectPosition(Context context,ShowResult result){
        	 this.context=context;
        	 this.list=(ArrayList<JobEntity>) result.list;
         }
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		public void setData(ShowResult result){
			this.list = (ArrayList<JobEntity>) result.list;
			this.notifyDataSetChanged();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder vh=new ViewHolder();
			if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.selectpositionlist, null);
			vh.selectposition_tv=(TextView)convertView.findViewById(R.id.selectposition_tv);
			vh.position_message_tv=(TextView) convertView.findViewById(R.id.position_message_tv);
			vh.selectposition_tv.setText(list.get(position).getName());
			vh.position_message_tv.setText(list.get(position).getCompanyCount()+"");
			convertView.setTag(vh);
			}else{
				vh= (ViewHolder) convertView.getTag(); 
			}
			
			return convertView;
		}
		
    	 class ViewHolder{
    		 public TextView selectposition_tv;
    		 public TextView position_message_tv;
    	 }
     }
     /**
      * ����¼���ת������һ��Activity�������ض�ְλ��id
      */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(SelectPositionActivity.this,PositionAllCompanyActivity.class);
		intent.putExtra("positionId", ((JobEntity)showResult.list.get((int)arg3)).getId());
		intent.putExtra("positionName", ((JobEntity)showResult.list.get((int)arg3)).getName());
		startActivity(intent); 
	}
}
         