package dataClasses;

import java.nio.ByteBuffer;

public class File_class {

	public String path;
	public String name;
	public byte[] content;
	public int content_len;
	
	
	public static int encode(File_class file_c, byte[] data) throws Exception
	{
		//ByteBuffer bbuf = ByteBuffer.wrap(data);
		ByteBuffer bbuf = ByteBuffer.allocate(81920).wrap(data);
		//bbuf.limit(40000);
		// 文件路径
		byte[] strbuf = file_c.path.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf.length);
		bbuf.put (strbuf);
		
		// 文件路径
		byte[] strbuf2 = file_c.name.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf2.length);
		bbuf.put (strbuf2);
		
		//内容长度
		bbuf.putInt(file_c.content_len);
		
//		//System.out.println(bbuf.capacity());
//		// 文件内容
//		bbuf.putInt( (int)file_c.content.length);
//		bbuf.put (file_c.content);
		
		return bbuf.position();//长度
	}
	
	public static byte[] encode(File_class file_c) throws Exception
	{
		//ByteBuffer bbuf = ByteBuffer.wrap(data);
		ByteBuffer bbuf = ByteBuffer.allocate(81920);
		//bbuf.limit(40000);
		// 文件路径
		byte[] strbuf = file_c.path.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf.length);
		bbuf.put (strbuf);
		
		// 文件路径
		byte[] strbuf2 = file_c.name.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf2.length);
		bbuf.put (strbuf2);
		
		//内容长度
		bbuf.putInt(file_c.content_len);
		
//		//System.out.println(bbuf.capacity());
//		// 文件内容
//		bbuf.putInt( (int)file_c.content.length);
//		bbuf.put (file_c.content);
		
		return bbuf.array();//长度
	}
	
	public static File_class decode(byte[] data) throws Exception
	{
		ByteBuffer bbuf = ByteBuffer.allocate(81920).wrap(data);
		File_class FC = new File_class();
		
	
		// 文件所在路径
		byte[] strbuf = new byte[1000];
		int strlen = bbuf.getShort();
		bbuf.get(strbuf, 0, strlen); // 读取N个字节
		FC.path = new String(strbuf, 0, strlen, "UTF-8");
				
		//文件名称
		byte[] strbuf1 = new byte[1000];
		int strlen1 = bbuf.getShort();
		bbuf.get(strbuf1, 0, strlen1); // 读取N个字节
		FC.name = new String(strbuf1, 0, strlen1, "UTF-8");
		
		//内容长度
		FC.content_len = bbuf.getInt();
		
//		//文件内容
//		byte[] strbuf2 = new byte[40000];
//		int strlen2 = bbuf.getInt();
//		bbuf.get(strbuf2, 0, strlen2); // 读取N个字节
//		FC.content = strbuf2;
	
		return FC;

	}
	public static byte[] addBytes(byte[] data1, byte[] data2) {
		byte[] data3 = new byte[data1.length + data2.length];

		System.arraycopy(data1, 0, data3, 0, data1.length);

		System.arraycopy(data2, 0, data3, data1.length, data2.length);

		return data3;

		}
}
