package com.sun.hair.act;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.hair.R;
import com.sun.hair.adapter.CommentsAdapter;
import com.sun.hair.entity.CommentEntity;
import com.sun.hair.entity.FamousEntity;
import com.sun.hair.service.CommentsService;
import com.sun.hair.service.IRequestCallBack;
import com.sun.hair.utils.MConstant;

public class PhotoAct extends Activity implements IRequestCallBack{
	/**¥��ͷ��*/
	private ImageView imgIcon;
	
	private TextView tvNickName;
	
	private TextView tvTime;
	
	private TextView tvConent;
	
	private TextView tvNoComment;
	
	private ImageView imgContent;
	
	private ImageView img1,img2,img3,img4;
	
	private TextView tvNumber;
	
	private ListView listView ;
	
	private CommentsAdapter adapter;
	
	private FamousEntity entity;
	
	private List<CommentEntity> comments = new ArrayList<CommentEntity>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_photo);
		initView();
		initData();
	}
	
	private void initView() {
		imgIcon = (ImageView) findViewById(R.id.act_photo_icon);
		tvNickName = (TextView) findViewById(R.id.act_photo_nickname);
		tvTime = (TextView) findViewById(R.id.act_photo_time);
		tvConent = (TextView) findViewById(R.id.act_photo_description);
		tvNoComment = (TextView) findViewById(R.id.act_photo_no_comment_tv);
		imgContent = (ImageView) findViewById(R.id.act_photo_img);
		img1 = (ImageView) findViewById(R.id.act_photo_img1);
		img2 = (ImageView) findViewById(R.id.act_photo_img2);
		img3 = (ImageView) findViewById(R.id.act_photo_img3);
		img4 = (ImageView) findViewById(R.id.act_photo_img4);
		tvNumber = (TextView) findViewById(R.id.act_photo_ok_number);
		listView = (ListView) findViewById(R.id.act_photo_listview);
	}
	
	private void initData() {
		try{
			entity = (FamousEntity) getIntent().getSerializableExtra("photo");
		}catch(Exception e){
			e.printStackTrace();
			Log.d("tag","log--init"+entity+e);
		}
		
		
		if(entity==null){
			Toast.makeText(this, "���ݴ���", Toast.LENGTH_SHORT).show();
			return;
		}
			
		tvNickName.setText(entity.name);
		tvTime.setText(entity.time);
		tvConent.setText(entity.introduce);
		tvNumber.setText("��"+entity.ok_num+"���޹�");
		adapter  = new CommentsAdapter(this);
		listView.setAdapter(adapter);
		if(entity!=null){
			request();
		}
	}
	
	public void onClick(View view){
		switch(view.getId()){
		case R.id.act_photo_ok://��
			break;
		case R.id.act_photo_add://��������
			break;
		case R.id.act_photo_share://����
			break;
		}
	}
	
	private void request(){
		AjaxParams params = new AjaxParams();
		params.put("id",String.valueOf(entity.id));
		new CommentsService().request(this, MConstant.URL_COMMENTS, params, this);
	}
	
	private void requestZan(){
		AjaxParams params = new AjaxParams();
		params.put("id",String.valueOf(entity.id));
		new CommentsService().request(this, MConstant.URL_COMMENTS, params, this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onSuccess(Object o) {
		if(o instanceof List<?>){
			comments = (List<CommentEntity>) o;
			adapter.setData(comments);
			if(comments.size()==0){
				tvNoComment.setText("������ɳ����!");
			}
		}
		
	}

	@Override
	public void onFailed(String msg) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}