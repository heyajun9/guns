package cn.stylefeng.guns.core.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 * @author 
 */
public class MD5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * MD5加密
	 */

	public static String stringToMd5(String text) throws Exception {
		//加密后的字符串
		String encodeStr= DigestUtils.md5Hex(text);
		System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
		return encodeStr;
	}
	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	// 测试主函数
	public static void main(String args[]) throws Exception {
		String s = new String("1571291145add[{\"shangp_id\":\"D999998\",\"huoz_id\":\"217\",\"jixing\":\"ACCC1\",\"maker\":\"杭州张小泉实业发展有限公司\",\"lot_flg\":\"N\",\"baoz_danw\":\"0101\",\"wlzx_code\":\"020\",\"baoz_num\":\"1\",\"chinese_name\":\"\",\"lius_no\":\"D999998\",\"zengp_flg\":\"N\",\"tongy_name\":\"D999998\",\"zt\":\"Y\",\"shangp_no\":\"D999998\"}]PODWMS9999991571291145");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("md5后："+stringToMd5(s));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));

	}
}

