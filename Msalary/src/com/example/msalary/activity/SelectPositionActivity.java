package com.example.msalary.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import com.example.msalary.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
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
public class SelectPositionActivity extends Activity {
	private TextView selectposition_tv;
	private TextView position_message_tv;
	private ImageButton back_btn;
	private ArrayList<HashMap<String, String>> listPosition;
	private ListView position_list;
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
    	//ģ�����ݡ���
    	for(int i=0;i<10;i++){
       HashMap<String, String>  map1=new HashMap<String, String>();
       map1.put("position", "�绰���۴���");
       map1.put("message","321����Ϣ");
       listPosition.add(map1);
    	}
    	BaseAdapter adapter=new selectPosition(this, listPosition);
    	position_list=(ListView) findViewById(R.id.position_list_lv);
    	position_list.setAdapter( adapter);
    }
     /**
      * ͬ�಻ְͬλ���б���Ӧ��adapter��
      * @author Administrator
      *
      */
     private class selectPosition extends BaseAdapter{
    	 private Context context;
    	 private ArrayList<HashMap<String, String>> list;
         private selectPosition(Context context,ArrayList<HashMap<String, String>> list){
        	 this.context=context;
        	 this.list=list;
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView=LayoutInflater.from(context).inflate(R.layout.selectpositionlist, null);
			selectposition_tv=(TextView)convertView.findViewById(R.id.selectposition_tv);
			position_message_tv=(TextView) convertView.findViewById(R.id.position_message_tv);
			selectposition_tv.setText(list.get(position).get("position"));
			position_message_tv.setText(list.get(position).get("message"));
			return convertView;
		}
    	 
     }
}
         