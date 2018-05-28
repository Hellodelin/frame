package delin.util;

import java.nio.charset.Charset;
import java.util.BitSet;

public class ByteUtil {
	public static String transToString(byte[] bytes,Charset charset) {
		return new String(bytes,charset);
	}
	
	public static byte[] setBits(byte[] source,int index) {
		int index_=(index)/8;
		int bitIndex=7-index%8;	
		byte[] subByte= {source[index_]};
		BitSet set=BitSet.valueOf(subByte);
		set.set(bitIndex, true);
		
		byte[] res=set.toByteArray();
		source[index_]=res[0];
		return source;
	}
}
