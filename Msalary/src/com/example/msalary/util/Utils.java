/**
 * 
 */
package com.example.msalary.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * ��Ŀ���ƣ�Ӫ���ƶ����ܹ���ƽ̨ <br>
 * �ļ�����TerminalDetailsFragment.java <br>
 * ���ߣ�@����    <br>
 * ����ʱ�䣺2013/11/24 <br>
 * ��������: �ն����� <br>
 * �汾 V 1.0 <br>               
 * �޸����� <br>
 * ����      ԭ��  BUG��    �޸��� �޸İ汾 <br>
 */
public class Utils {
	
	/**
	 * ����ֻ�imei��
	 * @param context
	 * @return
	 */
	public static String getImeiCode(Context context){
		String Imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
				.getDeviceId();
		return Imei;
	}
	
}
