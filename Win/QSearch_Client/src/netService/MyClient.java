package netService;

import yuanNet.YuanNetConnection;
import yuanNet.YuanNetPacket;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import dataClasses.QA_class;
import dataClasses.SB_class;
import dataClasses.USER_class;
import handler.FileUploadHandler;


public class MyClient
{
	YuanNetConnection conn;
	public MyClient() {
		conn = new YuanNetConnection();
		// 连接至服务器
		try {
			conn.connect("127.0.0.1", 2021, 3000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接服务器失败");
		}
	}


	public int typeInQuestion(QA_class QA_now) throws Exception {
		byte[] data= new byte[50000];
		int len = QA_class.encode(QA_now, data); //n长度
		// 发送请求
		byte[] data_out = new byte[len];
		QA_class.encode(QA_now, data_out);
		conn.send((short)0,data_out,len); 
		//获得数据库储存主键
		int id =-1;
		YuanNetPacket ynp = conn.recv();
		
		if(ynp.action==(short)2) {
			String res = new String(ynp.content,"UTF-8");
			id = Integer.decode(res);
		}
		return id;
	}
	
	public FileUploadHandler uploadImage(Image image,String path,String name) {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write((RenderedImage)image,"jpg", bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileUploadHandler fuh = new FileUploadHandler(conn, bos.toByteArray(), path, name);
		//fuh.start();
		
		return fuh;
	}
	
	public USER_class login(USER_class user) throws Exception {
		byte[] data = new byte[1000];
		ByteBuffer bbuf = ByteBuffer.allocate(1000).wrap(data);
		bbuf.putInt(user.id);
		bbuf.putInt(user.password.length());
		try {
			bbuf.put(user.password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			System.out.println("密码放入缓冲区错误");
			e1.printStackTrace();
		}
		
		//输入的user信息
//		System.out.println("id:"+user.id);
//		System.out.println("password:"+user.password);
		//向服务器发送登录信息
		try {
			conn.send((short)3, data);
		} catch (Exception e) {
			System.out.print("发送登录信息失败");
			e.printStackTrace();
		}
		//接收服务器返回值
		YuanNetPacket ynp = null;
		try {
			ynp = conn.recv();
		} catch (Exception e) {
			System.out.println("接收登录信息失败，连接服务器失败");
			e.printStackTrace();
		}
		
		//判断是否成功
		USER_class us = null;
		//若返回action==3则成功
		if(ynp.action==(short)3)
			us = USER_class.decode(ynp.content);
		
		//若返回action==4则登录失败
		if(ynp.action==(short)4)
			System.out.println("用户名或密码错误");
		return us;
		
	}
	
	public SB_class get_sb() {
		SB_class SB = new SB_class();
		try {
			conn.send((short)9);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			YuanNetPacket ynp = conn.recv();
			if(ynp.action==(short)9) {
				SB=SB.decode(ynp.content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SB;
		
	}
	
	public void close() {
		// 关闭连接
		conn.close();
	}
	
}
