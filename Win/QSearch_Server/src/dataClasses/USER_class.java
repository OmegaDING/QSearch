package dataClasses;

import java.nio.ByteBuffer;

public class USER_class {

	//全局管理员
	public final static int top_level = 0;
	//维护
	public final static int admin_level =1;
	//录入人员
	public final static int typer_level = 2;
	//普通用户
	public final static int user_level = 3;
	
	public String name;
	public int id;
	public String password;
	public String phone;
	public String mail;
	public String oranization;
	public String major;
	public int coin;//搜题币？
	public int exp;//经验值
	public int Qserach_num;//搜题次数
	
	public int user_type;
	
	
	public static int encode(USER_class USER, byte[] data) throws Exception
	{
		ByteBuffer bbuf = ByteBuffer.allocate(8000).wrap(data);
		
		// id
		bbuf.putInt(USER.id);
		
		// NAME
		byte[] strbuf = USER.name.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf.length);
		bbuf.put (strbuf);
		
		// PASSWORD
		byte[] strbuf0 = USER.password.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf0.length);
		bbuf.put (strbuf0);
		
				// PHONE
		byte[] strbuf1 = USER.phone.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf1.length);
		bbuf.put (strbuf1);
				// MAIL
		byte[] strbuf2 = USER.mail.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf2.length);
		bbuf.put (strbuf2);
		
				// ORANIZATION
		byte[] strbuf3 = USER.oranization.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf3.length);
		bbuf.put (strbuf3);
		
				// MAJOR
		byte[] strbuf4 = USER.major.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf4.length);
		bbuf.put (strbuf4);

		bbuf.putInt(USER.coin);
		bbuf.putInt(USER.exp);
		bbuf.putInt(USER.Qserach_num);
		bbuf.putInt(USER.user_type);
		
		return bbuf.position();//长度
	}
	
	public static USER_class decode(byte[] data) throws Exception
	{
		ByteBuffer bbuf = ByteBuffer.allocate(8000).wrap(data);
		USER_class USER = new USER_class();
		
		// id
		USER.id = bbuf.getInt();
		
		USER.name = get_string(bbuf);
		USER.password = get_string(bbuf);
		USER.phone = get_string(bbuf);
		USER.mail = get_string(bbuf);
		USER.oranization = get_string(bbuf);
		USER.major = get_string(bbuf);
	
		USER.coin = bbuf.getInt();
		USER.exp = bbuf.getInt();
		USER.Qserach_num = bbuf.getInt();
		USER.user_type = bbuf.getInt();
		
		return USER;

	}
	private static String get_string(ByteBuffer bbuf) throws Exception {
		byte[] strbuf = new byte[1000];
		int strlen = bbuf.getShort();
		bbuf.get(strbuf, 0, strlen); // 读取N个字节
		return new String(strbuf, 0, strlen, "UTF-8");
	}
}
