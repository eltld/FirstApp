package com.example.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.entity.BaseEntity;
import com.example.entity.TypeEntity;
import com.example.entity.UserEntity;
import com.sun.constant.DbConstant;

public class JsonParse {
	
	public static BaseEntity JsonParse(int requestType, String responseStr) {
		BaseEntity baseEntity = null;
		try {
			baseEntity = ParseBase(responseStr);
			switch (requestType) {
			case MConstant.REQUEST_CODE_LOGIN_:
				baseEntity = ParseLogin_Regist(baseEntity);
				break;
			case MConstant.REQUEST_CODE_REGIST:
				baseEntity = ParseLogin_Regist(baseEntity);
				break;
			case MConstant.REQUEST_CODE_GET_SELF_INFOR://获得我的个人信息
				baseEntity = ParseGetSelfInfor(baseEntity);
				break;
			case MConstant.REQUEST_CODE_EDIT_SELF_INFOR://编辑的信息,返回是否成功
//				ParseEditSelfInfor(baseEntity.getResultObject());
				break;
			case MConstant.REQUEST_CODE_GET_MY_RANK://我的个人排名信息
				ParseGetSelfRankInfor(baseEntity);
				break;
			case MConstant.REQUEST_CODE_WORLD_RANK://世界排名，可以限制某一地区，或者某一 行业
				ParseWorldRank(baseEntity);
				break;
			case MConstant.REQUEST_CODE_REGION_RANK://地区排名
				
				break;
			case MConstant.REQUEST_CODE_INDUSTRY_RANK://行业排名
				break;
			case MConstant.REQUEST_CODE_COMPANY_RANK://公司排名
				break;
				
			case MConstant.REQUEST_CODE_REGIONS:
				baseEntity = PaseTypes(baseEntity);
				break;
			case MConstant.REQUEST_CODE_INDUSTRYS:
				break;
			case MConstant.REQUEST_CODE_COMPANYS:
				break;
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return baseEntity;
	}

	private static BaseEntity ParseBase(String str) throws JSONException{
		BaseEntity entity = new BaseEntity();
		JSONObject object = new JSONObject(str);
		// 得到code值
		entity.setCode(object.getInt("code"));
		JSONObject result = object.getJSONObject("result");
		entity.setResultObject(result);
		return entity;
	}
	
	/**
	 * 解析登录返回信息
	 * 
	 * @param responseStr
	 * @return
	 * @throws JSONException
	 */
	private static BaseEntity ParseLogin_Regist(BaseEntity baseEntity)
			throws JSONException {
		Map<String,String> map = new HashMap<String,String>();
		map.put(DbConstant.DB_USER_ID, baseEntity.getResultObject().getString(DbConstant.DB_USER_ID));
		baseEntity.setMap(map);
		return baseEntity;

	}
	
	/**
	 * 解析我的个人信息
	 * @param object
	 * @return  所有的个人信息
	 */
	private static BaseEntity ParseGetSelfInfor(BaseEntity baseEntity){
		UserEntity userEntity = new UserEntity();
		userEntity.setCode(baseEntity.getCode());
		JSONObject object = baseEntity.getResultObject();
//		userEntity.setName(object.getString(DbConstant.DB_USER_NICK_NAME));
//		userEntity.setCompany_name(object.getString(DbConstant.))
		return baseEntity;
		
	}
	
	/**
	 * 获得个人排名信息
	 * @param object
	 * @return
	 * @throws JSONException 
	 */
	private static UserEntity ParseGetSelfRankInfor(BaseEntity baseEntity) throws JSONException{
		UserEntity entity = new UserEntity();
		JSONObject object = baseEntity.getResultObject();
		entity.setWorldRank(object.getInt("worldRank"));
		entity.setRegionRank(object.getInt("regionRank"));
		entity.setIndustryRank(object.getInt("industryRank"));
		entity.setName(object.getString(DbConstant.DB_USER_NICK_NAME));
		entity.setScore(object.getInt(DbConstant.DB_USER_SCORE));
		return entity;
	}
	
	
	/**
	 * 编辑我的个人信息
	 * @param object
	 * @return  是否成功编辑
	 */
	private static UserEntity ParseEditSelfInfor(BaseEntity baseEntity){
		UserEntity entity = new UserEntity();
		
		return entity;
	}
	
	/**
	 * 世界排行
	 * @param baseEntity
	 * @return
	 * @throws JSONException
	 */
	private static BaseEntity ParseWorldRank(BaseEntity baseEntity) throws JSONException{
		List<UserEntity> list = new ArrayList<UserEntity>();
		JSONArray array = baseEntity.getResultObject().getJSONArray("list");
		JSONObject object = null;
		UserEntity userEntity = null;
		for(int i = 0;i<array.length();i++){
			object = array.getJSONObject(i);
			userEntity = new UserEntity();
			userEntity.setName(object.getString(DbConstant.DB_USER_NICK_NAME));
			userEntity.setScore(object.getInt(DbConstant.DB_USER_SCORE));
			userEntity.setSalary(object.getInt(DbConstant.DB_USER_SALARY));
			userEntity.setIndustry_name(object.getString(DbConstant.DB_INDUSTRY_NAME));
			list.add(userEntity);
		}
		baseEntity.setList(list);
		return baseEntity;
	}
	
	/**
	 * 地区，行业，公司列表
	 */
	private static BaseEntity PaseTypes(BaseEntity baseEntity) throws JSONException{
		List<TypeEntity> list  = new ArrayList<TypeEntity>();
		TypeEntity entity =null;
		JSONArray array =baseEntity.getResultObject().getJSONArray("list");
		JSONObject object = null;
		for(int i = 0;i<array.length();i++){
			entity = new TypeEntity();
			object =array.getJSONObject(i);
			entity.setName(object.getString("name"));
			list.add(entity);
		}
		baseEntity.setList(list);
		return baseEntity;
	}

}
