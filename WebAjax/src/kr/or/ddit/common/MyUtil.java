package kr.or.ddit.common;

public class MyUtil {
	/**
	 * 문자열이 빈값인지 체크하는 메서드
	 * @param str
	 * @return 빈값이면 true를 반환 아니면 false 반환
	 */
	public static boolean isEmpty(String str) {
		// 빈값이면 true리턴
		if(str == null) {
			return true;
		}		
		str = str.trim();
		if(str.length() == 0) {
			return true;
		}		
		return false;
	}
	/**
	 * Object가 null인지 체크하는 메서드
	 * @param obj
	 * @return 빈값이면 true를 반환 아니면 false 반환
	 */
	public static boolean isEmpty(Object obj) {
		// 빈값이면 true리턴
		if(obj == null) {
			return true;
		}		
		return false;
	}
	
}
