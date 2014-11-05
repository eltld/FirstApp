package com.sun.hair.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.hair.entity.ReviewEntity;

public class ShopDetailService extends BaseService{

	@Override
	public Object parse(String result) {
		try {
			JSONObject object = new JSONObject(result);
			String status = object.getString("status");
			if(status.equals("OK")){
			List<ReviewEntity> list = parseReView(object.getJSONArray("reviews"));
				return list;
			}else{
				return "�������쳣";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
//	{"status":"OK","count":3,"reviews":[{"review_id":70198463,"user_nickname":"lei1989",
//	"created_time":"2014-09-28 23:46","text_excerpt":"��.","review_rating":5.0,"rating_img_url":
//		"http://i3.dpfile.com/s/i/app/api/32_5star.png","rating_s_img_url":
//	"http://i3.dpfile.com/s/i/app/api/16_5star.png","product_rating":0,"decoration_rating":4,
//	"service_rating":4,"review_url":"http://dpurl.cn/p/8LFiD8GNnl"},
//	{"review_id":70138915,"user_nickname":"qzuser_53628265609516566","created_time":"2014-09-28 17:54","text_excerpt":"��������ȥ�����������ͦ�õģ��Ե�����Ư��������������������","review_rating":4.0,"rating_img_url":"http://i3.dpfile.com/s/i/app/api/32_4star.png","rating_s_img_url":"http://i1.dpfile.com/s/i/app/api/16_4star.png","product_rating":0,"decoration_rating":3,"service_rating":3,"review_url":"http://dpurl.cn/p/c8l6A-q3es"},{"review_id":69976122,"user_nickname":"�ҽ���С��","created_time":"2014-09-27 19:46","text_excerpt":"����ǿ���Ƽ�������ȥ������һ�¡�����о����ǳ�������ǳ����⡫һ���ž�����������ӭ����ˮ���죬���й�...","review_rating":5.0,"rating_img_url":"http://i1.dpfile.com/s/i/app/api/32_5star.png","rating_s_img_url":"http://i2.dpfile.com/s/i/app/api/16_5star.png","product_rating":0,"decoration_rating":4,"service_rating":4,"review_url":"http://dpurl.cn/p/OdA3KFCu4Y"}],"additional_info":{"more_reviews_url":"http://dpurl.cn/p/iRgiTOcASz"}}
//

	private List<ReviewEntity> parseReView(JSONArray jsonArray) throws JSONException {
		JSONObject item =null;
		List<ReviewEntity> list = new ArrayList<ReviewEntity>();
		ReviewEntity entity = null;
		for(int i=0;i<jsonArray.length();i++){
			entity = new ReviewEntity();
			item = jsonArray.getJSONObject(i);
			entity.review_id = item.getInt("review_id");
			entity.user_nickname = item.getString("user_nickname");
			entity.created_time = item.getString("created_time");
			entity.text_excerpt = item.getString("text_excerpt");
			entity.rating_s_img_url = item.getString("rating_s_img_url");
			entity.review_rating = item.getDouble("review_rating");
			entity.product_rating = item.getDouble("product_rating");
			entity.decoration_rating = item.getDouble("decoration_rating");
			entity.service_rating = item.getDouble("service_rating");
			entity.review_url = item.getString("review_url");
			list.add(entity);
		}
		
		return list;
	}
	
	
}
