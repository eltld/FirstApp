/**
 * 
 */
package com.example.msalary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * ��Ŀ���ƣ�Ӫ���ƶ����ܹ���ƽ̨ <br>
 * ����ʱ�䣺2013/11/24 <br>
 * ��������: �ն����� <br>
 * �汾 V 1.0 <br>               
 * �޸����� <br>
 * ����      ԭ��  BUG��    �޸��� �޸İ汾 <br>
 */
public class MainPositionsAdapter extends BaseAdapter{

	  private Context context;  
      
      public MainPositionsAdapter(Context context) {  
          this.context = context;  
      }  
        
      int count = 9;  

      @Override  
      public int getCount() {  
          return count;  
      }  

      @Override  
      public Object getItem(int position) {  
          return position;  
      }  

      @Override  
      public long getItemId(int position) {  
          return position;  
      }  

      @Override  
      public View getView(int position, View convertView, ViewGroup parent) {  
          TextView result = new TextView(context);  
          result.setText("Item "+position);  
          result.setTextColor(Color.BLACK);  
          result.setTextSize(24);  
         // result.setLayoutParams(new AbsListView.LayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT)));  
          result.setGravity(Gravity.CENTER);  
          result.setBackgroundColor(Color.parseColor("#FFFAF0")); //���ñ�����ɫ  
          convertView=result;
          return convertView;  
      }  
	
}
