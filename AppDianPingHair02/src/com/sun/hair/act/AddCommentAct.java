package com.sun.hair.act;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.hair.BaseAct;
import com.sun.hair.R;
/**
 * ��������
 * @author sunqm
 *
 */
public class AddCommentAct extends BaseAct{

	private EditText etInput;
	
	private TextView tvSubmit;
	
	@Override
	public void initTitle() {
		setContentView(R.layout.act_add_comment);
		TextView tvLeft = (TextView) findViewById(R.id.act_title_left_tv);
		TextView tvCenter = (TextView) findViewById(R.id.act_title_center);
		tvSubmit = (TextView) findViewById(R.id.act_title_right_tv);
		View ll = (View) findViewById(R.id.act_title);
		tvLeft.setText("ȡ��");
		tvSubmit.setText("�ύ");
		tvCenter.setText("����");
		ll.setBackgroundResource(R.drawable.bg_top);
		
	}
	

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		etInput = (EditText) findViewById(R.id.act_add_comment_et);
		etInput.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			if(arg0.length()>0){
				tvSubmit.setEnabled(true);
			}else{
				tvSubmit.setEnabled(false);
			}
		}
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}
		@Override
		public void afterTextChanged(Editable arg0) {
		}
	});
	}
	

	public void onClick(View view){
		switch(view.getId()){
		case R.id.act_title_right_tv://�ύ
			if(checkInput()){
				request();
			}
			break;
		case R.id.act_title_left_tv://ȡ��
			finish();
			break;
		}
	}
	
	private boolean checkInput(){
		if(etInput.getText()==null||etInput.getText().toString().equals("")){//Ϊ�յ�
			Toast.makeText(this, "д���£���", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	/**
	 * �ύ����
	 */
	public void request(){
		
	}


	
	
	
	
}
