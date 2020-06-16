package com.kdj.mlink.common;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.regex.Pattern;
import com.mysql.cj.util.StringUtils;

public class YDMAStringUtil {
	
	public static String isNullString(String val) {
		return (val == null) ? "" : val;
	}
	
	
	//글짜 깨짐없이 자르기
	public static String subStringBytes(String str, int byteLength, int sizePerLetter) {
		  int retLength = 0;
		  int tempSize = 0;
		  int asc;
		  if (str == null || "".equals(str) || "null".equals(str)) {
		    str = "";
		  }
		 
		  int length = str.length();
		 
		  for (int i = 1; i <= length; i++) {
		    asc = (int) str.charAt(i - 1);
		    if (asc > 127) {
		      if (byteLength >= tempSize + sizePerLetter) {
		        tempSize += sizePerLetter;
		        retLength++;
		      }
		    } else {
		      if (byteLength > tempSize) {
		        tempSize++;
		        retLength++;
		      }
		    }
		  }
		 
		  return str.substring(0, retLength);
		}

	/*  글자수 자르기. */
	public static String setTextStrtok(String text, int length) {
		byte[] strByte = text.getBytes();
		String ret = text;
		if (strByte.length > length) 
			ret= new String(strByte, 0, length);
		return ret;
	}
	
	
	public static String isNullString(String orgin, String defaultString) {
		if(orgin.isEmpty()) return defaultString;
		return orgin;
	}
	
	
	public static String replaceNullvalue(String orgin) {

		if (StringUtils.isNullOrEmpty(orgin)) {
			return orgin = "";
		}
		return orgin;
	}

	public static String replaceEmptyStringToNull(String orgin) {

		if (StringUtils.isNullOrEmpty(orgin)) {
			return null;
		}
		return orgin;
	}

	public static int convertToInt(String value) {
		try {
			value = value + "";
			if(value.isEmpty()) return 0;
			value = value.replaceAll("[^0-9--]", "");	//자와 마이너스(-) 기회를 제외한 모든 문자를 공백으로 변환 함 
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	public static long convertToLong(String value) {
		try {
			value = value + "";
			if(value.isEmpty()) return 0;
			value = value.replaceAll("[^0-9--]", "");
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	


	public static String leftPad(String input, int length, String fill) {
		String pad = String.format("%" + length + "s", "").replace(" ", fill) + input.trim();
		return pad.substring(pad.length() - length, pad.length());
	}

//	public static void sortArrayString(String[] array, boolean asc) {
//
//		
//		Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
//		
//
////		String temp;
////		for (int i = 0; i < array.length / 2; i++) {
////			temp = array[i];
////			array[i] = array[(array.length - 1) - i];
////			array[(array.length - 1) - i] = temp;
////		}
//	}

//	// 내림차순
//	public static class DescendingStr implements Comparator<String> {
//	 
//	    @Override
//	    public int compare(String o1, String o2) {
//	        return o2.compareTo(o1);
//	    }
//	 
//	}
//	 
//	// 오름차순
//	public static class AscendingStr implements Comparator<String> {
//	 
//	    @Override
//	    public int compare(String o1, String o2) {
//	        return o1.compareTo(o2);
//	    }
//	 
//	}
	
    // 문자열 인코딩을 고려해서 문자열 자르기
    public static String Char_substring(String parameterName, int maxLength) {
        int DB_FIELD_LENGTH = maxLength;
 
        Charset utf8Charset = Charset.forName("UTF-8");
        CharsetDecoder cd = utf8Charset.newDecoder();
 
        try {
            byte[] sba = parameterName.getBytes("UTF-8");
            // Ensure truncating by having byte buffer = DB_FIELD_LENGTH
            ByteBuffer bb = ByteBuffer.wrap(sba, 0, DB_FIELD_LENGTH); // len in [B]
            CharBuffer cb = CharBuffer.allocate(DB_FIELD_LENGTH); // len in [char] <= # [B]
            // Ignore an incomplete character
            cd.onMalformedInput(CodingErrorAction.IGNORE);
            cd.decode(bb, cb, true);
            cd.flush(cb);
            parameterName = new String(cb.array(), 0, cb.position());
        } catch (UnsupportedEncodingException e) {
            System.err.println("### 지원하지 않는 인코딩입니다." + e);
        }
 
        return parameterName;
    }
	
    // 문자열 인코딩에 따라서 글자수 체크
    public static int length(CharSequence sequence) {
        int count = 0;
        for (int i = 0, len = sequence.length(); i < len; i++) {
            char ch = sequence.charAt(i);
 
            if (ch <= 0x7F) {
                count++;
            } else if (ch <= 0x7FF) {
                count += 2;
            } else if (Character.isHighSurrogate(ch)) {
                count += 4;
                ++i;
            } else {
                count += 3;
            }
        }
        return count;
    }
    //정규식찾기
    public static boolean checkRegex(String express , String value){
        Pattern pattern = Pattern.compile(express );  //"[ ,]"
        return pattern.matcher(value).find();
    }
    

}
