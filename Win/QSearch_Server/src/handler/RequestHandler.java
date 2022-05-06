package handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.util.ArrayList;

import dataBase_mySQL.MySQL;
import dataClasses.File_class;
import dataClasses.QA_class;
import dataClasses.SB_class;
import dataClasses.USER_class;
import yuanNet.YuanNetConnection;
import yuanNet.YuanNetPacket;

public class RequestHandler extends Thread{
	YuanNetPacket requestPkt;
	YuanNetConnection conn;
	MySQL mSQL;
	public RequestHandler(YuanNetConnection conn,MySQL mSQL) {
		this.conn=conn;
		this.mSQL=mSQL;
	}
	
	@Override
	public void run() {
		//接受请求
        while(true) {
        if(conn.sock == null) {
        	System.out.println("sock断开");
        	break;
        }
        try {
        	if(conn.sock != null) {
        	requestPkt = conn.recv();
        	if(requestPkt==null) {
        		Thread.sleep(300);
        		requestPkt = conn.recv();
        		if(requestPkt==null)
        			break;
        		System.out.println("接收到两次空包,线程关闭\n");
        		conn.close();
        		break;
        	}
			handleRequest(requestPkt,conn);
			}

		} catch (Exception e) {
			//e.printStackTrace();
			//System.out.println(e.toString());
			if(e.toString().equals("java.lang.NullPointerException"))
				System.out.println("连接已断开,线程关闭\n");
			else if(e.toString().equals("java.net.SocketTimeoutException: Read timed out")){
				System.out.println("连接超时，客户端无响应！客户端可能已经关闭");
				System.out.println("线程关闭\n");
			}
			else
			{
				System.out.println("连接出错,线程关闭\n");
				//System.out.println(e.toString());
				e.printStackTrace();
			}
			break;
		}
        }
	}




	private int handleRequest(YuanNetPacket requestPkt,YuanNetConnection conn) throws Exception
	{
		if(requestPkt==null) {
			//System.out.println("接受到空包");
			return -1;
		}
		
//		//若为关闭服务请求
//		if(requestPkt.action==(short)-100 && conn.sock != null) {
//			conn.close();
//		}
		
		//若为录入操作
		if(requestPkt.action==(short)0) {
		QA_class QA_typeIn = QA_class.decode(requestPkt.content, 0);
		
//		System.out.println(QA_typeIn.question_id);
//		System.out.println(QA_typeIn.question_img_path);
//
//		System.out.println(QA_typeIn.question_img_name);
//
//		System.out.println(QA_typeIn.question_string);
//
//		System.out.println(QA_typeIn.answer_img_path);
//
//		System.out.println(QA_typeIn.answer_img_name);
//
//		System.out.println(QA_typeIn.answer_string);
//
//		System.out.println(QA_typeIn.knowledge_point);
//
//		System.out.println(QA_typeIn.type_in_time);
//
//		System.out.println(QA_typeIn.typer_name);
//
//		System.out.println(QA_typeIn.typer_id);
            
		System.out.println("接收到QA类");
		
		Integer id = mSQL.insertQue(QA_typeIn);
		System.out.println("已保存到数据库，question_id:"+id);
		
		//返回主键id code 2
		conn.send((short)2, id.toString());
		return 0;  
		}
		
		//若为保存文件操作
		if(requestPkt.action==(short)1) {
			System.out.println("接收到文件");
			FileDownloadHandler fdh = new FileDownloadHandler(requestPkt,conn); 
			return 1;  
		}
		
		//验证登录
		if(requestPkt.action==(short)3) {

			byte[] strbuf = new byte[1000];
			ByteBuffer bbuf1 = ByteBuffer.allocate(1000).wrap(requestPkt.content);
			int id = bbuf1.getInt();
			int len = bbuf1.getInt();
			
			bbuf1.get(strbuf, 0, len); // 读取N个字节
			//获取到的密码
			String pass = new String(strbuf, 0, len, "UTF-8");
			pass = mSQL.escape(pass);
			
			String sql ="SELECT * FROM `que_ans`.user WHERE id = "+id;
			
			ResultSet rs = mSQL.exeQuery(sql);
			USER_class user_try=new USER_class();
			while(rs.next())
			{
				user_try.id = id;
				user_try.password = rs.getString("password");
				user_try.name=rs.getString("name");
				user_try.phone=rs.getString("phone");
				user_try.mail=rs.getString("mail");
				user_try.oranization=rs.getString("oranization");
				user_try.major=rs.getString("major");
				user_try.coin = rs.getInt("coin");
				user_try.user_type = rs.getInt("user_type");
			}    
//			System.out.println("获取到密码："+pass);
//			System.out.println("正确密码："+pass_right);
			
			if(pass.equals(user_try.password)) {

				byte[] data = new byte[1000];
				USER_class.encode(user_try, data);
				conn.send((short)3,data );
				System.out.println("用户"+user_try.name+"id:"+user_try.id+"登陆成功！");
			}else {
				ByteBuffer bbuf2 = ByteBuffer.allocate(100);
				bbuf2.putInt(-1);
				//action code 4 :登录失败
				conn.send((short)4, bbuf2.array());
				System.out.println("用户"+user_try.name+"id:"+user_try.id+"用户名或密码错误登陆失败！");
			}
			return 3;  
		}
		
		//id搜题
		if(requestPkt.action==(short)5) {
			//接受一个question_id返回QA_class
			ByteBuffer bbuf = ByteBuffer.allocate(100).wrap(requestPkt.content);
			int q_id = bbuf.getInt();
			System.out.println("客户端通过id查询题目id:"+q_id);
			QA_class QA =null;
			QA= mSQL.QSerach_id(q_id);
			//System.out.println("从数据库种获得题目的id:"+QA.question_id);
			if(QA==null||QA.question_id==0)
				System.out.println("不存在用户查询的题目，id:"+q_id);
			
			if(QA!=null&&QA.question_id!=0) {
			byte[] data = new byte[10000];
			int len =QA_class.encode(QA, data);
			byte[] data1 = new byte[len];
			QA_class.encode(QA, data1);
			//发送题目信息
			//若不存在题目则题目id=0
			conn.send((short)5, data1);
			}
			else
			{
				QA_class q = new QA_class();
				q.question_id=0;
				byte[] data = new byte[10000];
				int len =q.encode(q, data);
				byte[] data1 = new byte[len];
				q.encode(q, data1);
				
				//不存在发送的题目
				conn.send((short)5,data1);
			}
			
			
			return 5;  
		}
		
		//关键字搜题
		if(requestPkt.action==(short)6) {
			ByteBuffer bbuf = ByteBuffer.allocate(10000).wrap(requestPkt.content);
			int len = bbuf.getInt();
			
			//解析关键字内容
			byte[] keyword_b = new byte[len];	
			bbuf.get(keyword_b, 0, len); // 读取N个字节
			String keyword = new String(keyword_b, 0, len, "UTF-8");
			int user = bbuf.getInt();
			//获取结果集
			ResultSet rs = mSQL.QSerach_keyword(keyword,user) ;
			
			//通过最后发送消息码确定
//			//发送共有多少题目
//			int que_num = rs.getRow();
//			ByteBuffer bbuf1 = ByteBuffer.allocate(100);
//			bbuf1.putInt(5);
//			
//			conn.send((short)6, bbuf1.array());
			
			QA_class QA = null;
			int count =0;//发送了多少道题目
			while(rs.next())
			{
				QA=new QA_class(rs.getInt("question_id"), rs.getString("question_img_path"), rs.getString("question_img_name"), rs.getString("question_img_path_1"), rs.getString("question_img_name_1"),
								rs.getString("question_string"), rs.getString("answer_img_path_0"), rs.getString("answer_img_name_0"), rs.getString("answer_img_path_1"), rs.getString("answer_img_name_1"),
								rs.getString("answer_img_path_2"), rs.getString("answer_img_name_2"), rs.getString("answer_img_path_3"), rs.getString("answer_img_name_3"), rs.getString("answer_img_path_4"),
								rs.getString("answer_img_name_4"), rs.getString("answer_img_path_5"), rs.getString("answer_img_name_5"), rs.getInt("question_num"), rs.getString("answer_string"), 
								rs.getString("knowledge_point"), rs.getString("type_in_time"), rs.getString("typer_name"), rs.getInt("typer_id"), rs.getString("subject"), rs.getInt("difficulty"), 
								rs.getInt("is_homework"));
				int len1 = QA.encode(QA, new byte[9000]);
			    byte[] send = new byte[len1];
				QA.encode(QA, send);
				
				System.out.println("发送题目id:"+QA.question_id);
				//action code 6
				conn.send((short)6, send);
				count++;
				//最多发送十道题
				if(count>=10) {
					break;
				}
			}
			System.out.println("发送题目数量："+count);
			//QA id=0也是无相关题目
			//告诉客户端结束发送
			conn.send((short)7);
			
			return 6;  
		}
		
		
		//若为请求文件操作
		if(requestPkt.action==(short)8) {
			File_class FC = File_class.decode(requestPkt.content);
			
			File Dir = new File(FC.path);
			File file = new File(Dir,FC.name);
			if(file.isFile()) {
				
			
			// 打开文件
			InputStream inputStream = new FileInputStream(file);
			
			byte[] s = new byte[400000];
			int len = inputStream.read(s);
			int pkt_num = len/500+1;
			System.out.println("要发送的文件为："+FC.name+"，大小为："+len+"字节，共"+pkt_num+"个包");
			
			//返回客户端数据包大小
			ByteBuffer bbuf1 = ByteBuffer.allocate(100);
			bbuf1.putInt(len);
			conn.send((short)8, bbuf1.array());
			
			FC.content=s;
			FC.content_len=len;
			
			//发送文件
			for(int i =0;i<pkt_num;i++) {
				int start_point = 500*i>FC.content.length?FC.content.length:500*i;
				int send_len = (start_point+500)>FC.content.length?(FC.content.length-start_point):500;
				byte[] by = new byte[send_len];
				System.arraycopy(FC.content, start_point, by, 0, send_len);
				conn.send((short)8, by);
			}
			}
			else {
				System.out.println("请求的文件不存在");
				conn.send((short)-1);
			}
			return 8;  
		}
		
		//若为请求subject
		if(requestPkt.action==(short)9) {

			ArrayList<String> name = mSQL.get_subject();
			SB_class SB = new SB_class(name);
			int len = SB.encode(SB,new byte[99999]);
		    byte[] send = new byte[len];
			SB.encode(SB, send);
			
			conn.send((short)9, send);
			System.out.println("已发送subject");
			return 9;  
		}
		//都不是
		System.out.println("接收到无意义消息");
		return -2;  
	}
}
