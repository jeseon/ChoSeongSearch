package search.choseong;

import java.lang.Character.UnicodeBlock;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SearchEngine {
	
	private Map<Integer,Integer> map = null;
	
	public SearchEngine() {
		map = new HashMap<Integer,Integer>();
		
		map.put(0x1100, 0x3131); // ㄱ - http://www.unicode.org/charts/PDF/U3130.pdf
		map.put(0x1101, 0x3132); // ㄲ
		map.put(0x1102, 0x3134); // ㄴ
		map.put(0x1103, 0x3137); // ㄷ
		map.put(0x1104, 0x3138); // ㄸ
		map.put(0x1105, 0x3139); // ㄹ
		map.put(0x1106, 0x3141); // ㅁ
		map.put(0x1107, 0x3142); // ㅂ
		map.put(0x1108, 0x3143); // ㅃ
		map.put(0x1109, 0x3145); // ㅅ
		map.put(0x110A, 0x3146); // ㅆ
		map.put(0x110B, 0x3147); // ㅇ
		map.put(0x110C, 0x3148); // ㅈ
		map.put(0x110D, 0x3149); // ㅉ
		map.put(0x110E, 0x314A); // ㅊ
		map.put(0x110F, 0x314B); // ㅋ
		map.put(0x1110, 0x314C); // ㅌ
		map.put(0x1111, 0x314D); // ㅍ
		map.put(0x1112, 0x314E); // ㅎ
	}
	
	/**
	 * 정규표현식으로 대/소문자 구별없이 검색
	 * 
	 * @param choStr 초성 검색어 (예: ㅂㅅ, ㄱㄹㄷ) 
	 * @param searchIndex 검색대상 색인
	 * @return 검색어가 있을 경우 true
	 */
	public Boolean searchChoSeong(String choStr, String searchIndex) {
		return Pattern.compile(Pattern.quote(choStr), Pattern.CASE_INSENSITIVE).matcher(searchIndex).find();
	}
	
	/**
	 * 한글 유니코드 초성 색인 생성
	 * 
	 * @param hanStr 초성으로 변환할 문장 및 단어
	 * @return 초성으로 변환된 문자열
	 */
	public String getSearchIndex(String hanStr) {
		String tmpStr = hanStr.replaceAll("\\p{Punct}|\\p{Space}", ""); // 특수문자/공백 제거
		StringBuffer choChars = new StringBuffer();
		char chr = ' ';
		
		for (int idx = 0; idx < tmpStr.length(); idx++) {
			chr = tmpStr.charAt(idx);
			
			Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(chr);
			if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) ||			// 한글 완성형 유니코
	            UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock) ||	// 한글 호환 자모 유니코드 
	            UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock))					// 한글 자모 유니코드
			{
				choChars.append(getChoUnicodeFromHanChr(tmpStr.charAt(idx)));
			} else {
				choChars.append(chr);
			}
		}
		
		return choChars.toString();
	}
	
	/**
	 * 음절단위로 입력받아 한글 초성을 반환
	 * 
	 * @param hanChar 한글 음절(예: 가, 나, 다)
	 * @return 한글호환자모 초성
	 */
	private char getChoUnicodeFromHanChr(char hanChar) {
		/**
		 * 한글 유니코드 영역이 '가나다'순으로 배열되어 있어 각 초성별 개수를 계산하여 초성 반환
		 */
		int choUnicode = 0x1100; // ㄱ - http://www.unicode.org/charts/PDF/U1100.pdf
		int hanUnicode = hanChar - 0xAC00; // 가 - http://www.unicode.org/charts/PDF/UAC00.pdf
		
		choUnicode += hanUnicode / 28 / 21; // 종성개수*중성개수=588개
		
		return (char)convertHanComJamoFromHanJamo(choUnicode);
	}
	
	
	/**
	 * 한글 자모 초성을 입력받아 한글호환자모 유니코드를 반환
	 * 
	 * @param choUnicode 한글자모 초성 유니코드
	 * @return 한글호환자모 초성 유니코드
	 */
	private int convertHanComJamoFromHanJamo(int choUnicode) {
		return map.get(choUnicode);
	}
}
