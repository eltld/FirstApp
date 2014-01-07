package com.example.msalary.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.msalary.entity.JobEntity;
import com.example.msalary.entity.ResponseResult;
import com.example.msalary.entity.ShowResult;
import com.example.msalary.internet.IRequestCallBack;
import com.example.msalary.util.ErrorCodeUtils;

/**
 * �½�����
 * @author sunqm
 * Create at:   2013-12-29 ����2:48:39 
 * TODO
 */
public class JsonNewComment {
	
	public static ShowResult parse(ResponseResult responseResult,IRequestCallBack requestCallBack){
		ShowResult showResult = new ShowResult();
		try {
			JSONObject object = new JSONObject(responseResult.resultStr);
			int code = object.getInt("code");
			
			showResult.resultCode =code;
		} catch (JSONException e) {
			requestCallBack.requestFailedStr(ErrorCodeUtils.changeCodeToStr(1));
			e.printStackTrace();
			return null;
		}
		return showResult;
	}
}
