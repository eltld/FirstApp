package com.sun.hair.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.sun.hair.entity.DistrictsEntity;

/**
 * h��ȡ����
 * @author sunqm
 *
 */
public class RegionsService extends BaseService{

	/**
	 *{"status":"OK","cities":[{"city_name":"����","districts":[{"district_name":"������","neighborhoods":["��������","����","����/�˼�԰","������","������","������԰/�Ž��","���ׯ","������/��Ԫ��","���˴�","����","����·","���⾭ó","������","������","˫��","��ó","�׶�����","��ׯ","ʮ�����","ʮ�ﱤ","��Է��԰","����","��ȪӪ","���","����ׯ","��ɳ̲","����","Сׯ/����","��Ӫ","798/��ɽ��","������վ","����·","��ɫ����","��ɯ/ũҵչ����","Ҧ��԰","ʮ���","��ˮ��","СӪ","������","����������","������/����ׯ","��ˮ԰","�߱���","�������ֹ�","˫��","��ý��ѧ/����","ʯ��Ӫ","����·","̫����","�Ļ�"]},{"district_name":"������","neighborhoods":["������","������","����","������/����վ","������/����","��̳","������","��ֱ��","��ƽ��","������","ɳ�ӿ�","����","����ʮ��","Ӻ�͹�/��̳","�������/��¥�����","����¥/��̶��","������/����","ɳ̲/�����ݵ��п�"]},{"district_name":"������","neighborhoods":["������","������","���¹�","������/����·","��̫ƽׯ","������","�йش�","�����","��������","�ϵ�","�ú�԰","κ����","���","�����","˫����","Զ��·","��ɽ","ũҵ��ѧ����","����","�����ѧ","�ļ���","ѧԺ��","����","������","������","֪��·"]},{"district_name":"������","neighborhoods":["ǰ��","������","������","���ڴ��","�ذ���","�½ֿ�","��ֱ��/����԰","������","������","����","ţ��","������","��̳","ʲɲ��","����","���п�","������","��Ȼͤ","�ϲ�԰/��ֽ��"]},{"district_name":"����","neighborhoods":["����","��������","��ͷ��","ƽ��","������","������"]},{"district_name":"��̨��","neighborhoods":["��̨����","������/������","��ׯ","����/ľ��԰","�Ұ���","�����","����Ҥ","������","����","����","������","����","�����","¬����","��������","�Ƹ�","������/������","������վ/������","��ұ�/����","�ļҺ�ͬ/�ͼ���","������/���·","�ܲ�����"]},{"district_name":"ʯ��ɽ��","neighborhoods":["ʯ��ɽ����","³��","�ų�/�˽�","ƻ��԰","ģʽ��"]},{"district_name":"������","neighborhoods":["�ƴ�","�ɹ�","��ׯ","������"]},{"district_name":"��ƽ��","neighborhoods":["��ƽ��","��ͨԷ","������","С��ɽ","���߼�","�Ͽ���","ɳ��"]},{"district_name":"ͨ����","neighborhoods":["�»����","��԰","��԰","�ſ���","���Ļ�԰","ͨ�ݱ�Է"]},{"district_name":"��ɽ��","neighborhoods":["����"]},{"district_name":"˳����","neighborhoods":["��չ","˳��"]}]}]}

	 */
	@Override
	public Object parse(String result) {
		
		List<DistrictsEntity> list = new ArrayList<DistrictsEntity>();
		String status = null;
		try {
			JSONObject object = new JSONObject(result);
			status = object.getString("status");
			if(status.equals("OK")){
				JSONObject city = object.getJSONArray("cities").getJSONObject(0);
				list = parseDistricts(city.getJSONArray("districts"));
			}else{
				
			}
			Log.d("tag","parse-->"+status);
		} catch (JSONException e) {
			status = "���ݴ���";
			e.printStackTrace();
		}
		return list;
	}

	private List<DistrictsEntity> parseDistricts(JSONArray jsonArray) throws JSONException {
		List<DistrictsEntity> list = new ArrayList<DistrictsEntity>();
		DistrictsEntity entity = null;
		JSONObject item = null;
		for(int i=0;i<jsonArray.length();i++){
			entity = new DistrictsEntity();
			item = jsonArray.getJSONObject(i);
			entity.name = item.getString("district_name");
			entity.list = parseNeigh(item.getJSONArray("neighborhoods"));
			list.add(entity);
		}
		return list;
	}
	
	private List<String> parseNeigh(JSONArray array) throws JSONException{
		List<String> list = new ArrayList<String>();
		for(int i=0;i<array.length();i++){
			list.add(array.getString(i));
		}
		return list;
	}
	
	
}
