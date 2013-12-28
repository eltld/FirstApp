package com.example.msalary.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.msalary.entity.RequestEntity;
import com.example.msalary.entity.ResponseResult;
import com.example.msalary.entity.ShowResult;
import com.example.msalary.util.ErrorCodeUtils;

public class InternetHelper {
	  /**
     * ����Ƿ�����������
     * @param context
     * @return
     */
    public static boolean isInternetAvaliable(Context context) {
            // ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ���
            try {
                    ConnectivityManager connectivity = (ConnectivityManager) context
                                    .getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivity != null) {
                            // ��ȡ�������ӹ���Ķ���
                            NetworkInfo info = connectivity.getActiveNetworkInfo();
                            if (info != null && info.isConnected()) {
                                    // �жϵ�ǰ�����Ƿ��Ѿ�����
                                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                                            return true;
                                    }
                            }
                    }
            } catch (Exception e) {
                    // TODO: handle exception
                    Log.v("error", e.toString());
            }
            return false;
    }
    
    /**
     * ������������
     * @param requestEntity
     * @param requestCallBack
     */
    public static void requestThread(final RequestEntity requestEntity,final IRequestCallBack requestCallBack){
            new Thread(){

                    @Override
                    public void run() {
                            ResponseResult responseResult = InternetHelper.request(requestEntity);
                            if(responseResult.resultCode==HttpStatus.SC_OK){//�ɹ�
                                    requestCallBack.requestSuccess(responseResult);
                            }else{
                                    //ʧ��
                                    requestCallBack.requestFailedStr(ErrorCodeUtils.changeCodeToStr(responseResult.resultCode));
                            }
                            
                            
                    }
                    
            }.start();
    }
    
    /**
     * �������󷽷���
     * @param requestEntity �������
     * @return         ������
     */
    private static ResponseResult request(RequestEntity requestEntity){
//            if(requestEntity.isPost){
//                    
//            }
//                    return doPost(requestEntity);
//            else
                    return doGet(requestEntity);
    }

    
    
    /**
     * Get �����������
     * @param requestEntity
     */
    private static ResponseResult doGet(RequestEntity requestEntity) {
            //��������ȫ��ַ
            
            String url = getUrl(requestEntity.getUrl(),requestEntity.params);
            //�������ĵ�ַ
            Log.d("tag","request--url"+url+"urlend");
            ResponseResult responseResult = get(url);
            Log.d("tag","request--result||>"+responseResult.toString());
            return responseResult;
    }

    /**
     * ��ϵ�ַ
     * @param url
     * @param map
     * @return
     */
    private static String getUrl(String url,Map<String, Object> map){
		if (!map.isEmpty()) {// ������ڲ���
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				try {
					if (value != null) {
						value = URLEncoder.encode(String.valueOf(value),
								"UTF-8");
						// value = new String(value.getBytes(),"utf-8");
						Log.d("tag", "value-->" + value);
					}

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				url += key + "=" + value + "&";
			}
			url = url.substring(0, url.length() - 1);
		}
		url = url.replaceAll(" ", "%20");
		return url;
	}
    
    /**
     * ����url��������
     * @param url
     * @return         
     */
    private static ResponseResult get(String url){
            ResponseResult  responseResult = new ResponseResult();
            BufferedReader reader = null;
            StringBuffer sb = null;
            String result = "";
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            try {
                    /**����ʱ  20��*/
                    request.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20*1000);
//                    request.getParams().setParameter(Charset.defaultCharset(), arg1)
                    // �������󣬵õ���Ӧ
                    HttpResponse response = client.execute(request);
                    
                    // ����ɹ�
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                            reader = new BufferedReader(new InputStreamReader(response
                                            .getEntity().getContent()));
                            sb = new StringBuffer();
                            String line = "";
                            while ((line = reader.readLine()) != null) {
                                    sb.append(line); 
                            }
                    } else {
                            Log.e("tag", "HttpHelper-get-result->"
                                            + response.getStatusLine().getStatusCode());
                    }
                    responseResult.resultCode=(response.getStatusLine().getStatusCode());
            } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    responseResult.resultCode=(-1);
            } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("tag", "HttpHelper-get-result-exception->"
                                    + e);
                    responseResult.resultCode=(-2);
            } finally {
                    try {
                            if (null != reader) {
                                    reader.close();
                                    reader = null;
                            }
                    } catch (IOException e) {
                            e.printStackTrace();
                            responseResult.resultCode=(-2);
                    }
                    client.getConnectionManager().shutdown();
            }
            if (null != sb) {
                    result = sb.toString();
            }
            responseResult.resultStr=(result);
            return responseResult ;
    }
    
    /**
     * Post�����������
     * @param requestEntity
     */
//    @SuppressWarnings("finally")
//    private static ResponseResult doPost(RequestEntity requestEntity) {
//            ResponseResult  responseResult = new ResponseResult();
//
//            Log.d("tag", "Internet-post-url>" + requestEntity.url);
//            HttpPost httpPost = null;
//            try {
//                    httpPost = new HttpPost(requestEntity.url);
//                    // ��������ʱ,20��
//                    httpPost.getParams().setParameter(
//                                    CoreConnectionPNames.CONNECTION_TIMEOUT,
//                                    20*1000);
//                            
//                    HttpResponse httpResponse = null;
//                    /**�����������*/
//                    httpPost.setEntity(new UrlEncodedFormEntity(requestEntity.get_post_params(),
//                                    HTTP.UTF_8));
//                    httpResponse = (HttpResponse) new DefaultHttpClient()
//                                    .execute(httpPost);
//                    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// ���󵽽��
//                            // ��������ʹ��getEntity������÷��ؽ��
//                            
//                            responseResult.setResultStr(EntityUtils.toString(
//                                            httpResponse.getEntity(), "utf-8"));
//                    } else {
//                            Log.e("tag", "Internet-post-response->"
//                                            + httpResponse.getStatusLine().getStatusCode());
//                    }
//                    responseResult.setResultCode(httpResponse.getStatusLine().getStatusCode());
//            }  catch (org.apache.http.conn.ConnectTimeoutException e) {
//                    responseResult.setResultCode(0);
//                    e.printStackTrace();
//                    Log.i("tag", "Internet-����code-4>" + e);
//            }  catch (Exception e) {
//                    responseResult.setResultCode(1);
//                    e.printStackTrace();
//                    Log.i("tag", "Internet-����code-6>" + e);
//            }
//
//            finally {
////                    client.getConnectionManager().shutdown();
//                    Log.i("tag", "Internet-post-  end..." + responseResult.toString());
//                    return responseResult;
//            }
//    }
    

}