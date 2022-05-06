package dataClasses;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SB_class {

	public ArrayList<String> name;
	public int num;
	
	public SB_class() {
		name = new ArrayList<String>();
	}
	
	public SB_class(ArrayList<String> name) {
		this.name=name;
		this.num = name.size();
	}

	public static int encode(SB_class SB, byte[] data) throws Exception
	{
		ByteBuffer bbuf = ByteBuffer.allocate(80000).wrap(data);
		bbuf.putInt(SB.name.size());
		for(int i=0;i<SB.name.size();i++) {
			byte[] strbuf = SB.name.get(i).getBytes("UTF-8");
			bbuf.putShort( (short) strbuf.length);
			bbuf.put (strbuf);
		}
		
		return bbuf.position();//长度
	}
	
	public static SB_class decode(byte[] data) throws Exception
	{
		ByteBuffer bbuf = ByteBuffer.allocate(80000).wrap(data);
		SB_class SB = new SB_class();
		
		SB.num = bbuf.getInt();
		System.out.println("目前共有"+SB.num+"门课");
		for(int i=0;i<SB.num;i++) {
			SB.name.add(get_string(bbuf));
		}
		
		return SB;

	}
	private static String get_string(ByteBuffer bbuf) throws Exception {
		byte[] strbuf = new byte[1000];
		int strlen = bbuf.getShort();
		bbuf.get(strbuf, 0, strlen); // 读取N个字节
		return new String(strbuf, 0, strlen, "UTF-8");
	}
}
