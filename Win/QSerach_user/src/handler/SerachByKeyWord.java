package handler;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dataClasses.File_class;
import dataClasses.QA_class;
import gui.MainFrame;
import netService.MyClient;
import yuanNet.YuanNetConnection;
import yuanNet.YuanNetPacket;

public class SerachByKeyWord extends Thread {

	MainFrame MF;
	String key;
	String origin_key;
	QA_class[] result_QA;
	int now_QA_num;
	int count=0;
	int num=0;
	int searcher;
	public SerachByKeyWord(String keyword, MainFrame mainFrame) {

		result_QA = new QA_class[10];
		this.origin_key=keyword;
		this.MF=mainFrame;
		this.key = "%"+keyword.substring(2, 3)+"%"+keyword.substring(6, 7)+"%"+keyword.substring(9, 10)+"%"
				+keyword.substring(12, 13)+"%"+keyword.substring(15, 16)+"%"+keyword.substring(19, 20)+"%"+keyword.substring(26, 27)+"%";
		System.out.println(this.key);
		now_QA_num=0;
		searcher= MF.user_now.id;
	}

	@Override
	public void run() {
		//获取关键词byte【】
		ByteBuffer bbuf = ByteBuffer.allocate(100);
		byte[] strbuf;
		try {
			strbuf = key.getBytes("UTF-8");
			bbuf.putInt( strbuf.length);
			bbuf.put (strbuf);
			bbuf.putInt(searcher);
		} catch (UnsupportedEncodingException e1) {
			System.out.println("通过关键字搜索 keyword getbytes 出错");
			e1.printStackTrace();
		}	
		
		YuanNetConnection conn = new YuanNetConnection();
		// 连接至服务器
		try {
			conn.connect(MyClient.ip, 2021, 3000);
			System.out.println("已经连接到服务器，开始根据keyword模糊搜索");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接服务器失败!!");
		}
		
		//向服务器发送关键词请求
		try {
			conn.send((short)6, bbuf.array());
		} catch (Exception e) {
			System.out.println("向服务器发送keyword查询请求失败!!");
			e.printStackTrace();
		}
//		//接收共有多少符合的题目
//		int num = 0;
//		YuanNetPacket que_num;
//		try {
//			que_num = conn.recv();
//			ByteBuffer que_num_bbuf = ByteBuffer.allocate(100).wrap(que_num.content);
//			num = que_num_bbuf.getInt();
//			System.out.println("共有"+num+"道符合的题目");
//			if(num>=10)
//				System.out.println("（超过十个最大返回10个）");
//		} catch (Exception e) {
//			System.out.println("接收keyword查询请求失败!!");
//			e.printStackTrace();
//		}
//		
		//接收符合的题目QA
		count=0;
		for(int i = 0;i<10 && count<10;i++,count++) {
			YuanNetPacket ynp;
			
			try {
				ynp = conn.recv();
				//接收完毕
				if(ynp.action==(short)7){
					break;
				}
				result_QA[i] = QA_class.decode(ynp.content, 0);
			} catch (Exception e) {
				System.out.println("通过keyword查询接收符合题目出错");
				e.printStackTrace();
			}
		}
		System.out.println("接收到"+count+"个符合的题目");
		
		if(count==0) {
			JOptionPane.showMessageDialog(MF,"无匹配题目，请重试\n我们会尽快录入" );
			conn.close();
		}
		
		
		

		if(count>0) {

			MF.total_num.setText("共有"+count+"个符合的题目，当前是第"+(now_QA_num+1)+"个");
			//题目答案和题目图片文件
			File_class que=new File_class();
			que.path = result_QA[0].question_img_path;
			que.name = result_QA[0].question_img_name;
			File_class ans = new File_class();
			ans.path = result_QA[0].answer_img_path_0;
			ans.name = result_QA[0].answer_img_name_0;
		
			//获取题目图片
			BufferedImage image_que = DownLoadImage.download(que, conn);
			//获取答案图片
			BufferedImage image_ans = DownLoadImage.download(ans, conn);
			MF.imageView_ans.setImage(image_que);
			MF.imageView_ans_0.setImage(image_ans);
		}
		MF.current_ans_img_num.setText("当前是第1/"+result_QA[0].question_num+"张答案图片");
		num=0;
		conn.close();
	}
	
	public void next() {
		if(now_QA_num>=count-1) {
			JOptionPane.showMessageDialog(MF,"已经是最后一道题" );
			return;
		}else {
			
			now_QA_num++;
			MF.total_num.setText("共有"+count+"个符合的题目，当前是第"+(now_QA_num+1)+"个");
			MF.que_id.setText(""+result_QA[now_QA_num].question_id);
		}
		YuanNetConnection conn = new YuanNetConnection();
		// 连接至服务器
		try {
			conn.connect(MyClient.ip, 2021, 3000);
			System.out.println("已经连接到服务器，开始下载下一题图片");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接服务器失败!!");
		}
		//题目答案和题目图片文件
		File_class que=new File_class();
		que.path = result_QA[now_QA_num].question_img_path;
		que.name = result_QA[now_QA_num].question_img_name;
		File_class ans = new File_class();
		ans.path = result_QA[now_QA_num].answer_img_path_0;
		ans.name = result_QA[now_QA_num].answer_img_name_0;
				
		//获取题目图片
		BufferedImage image_que = DownLoadImage.download(que, conn);
		//获取答案图片
		BufferedImage image_ans = DownLoadImage.download(ans, conn);
		MF.imageView_ans.setImage(image_que);
		MF.imageView_ans_0.setImage(image_ans);
		MF.current_ans_img_num.setText("当前是第1/"+result_QA[0].question_num+"张答案图片");
		conn.close();
	}

	public void upper() {
		if(now_QA_num<1) {
			JOptionPane.showMessageDialog(MF,"已经是第一道题" );
			return;
		}else {

			now_QA_num--;

			MF.total_num.setText("共有"+count+"个符合的题目，当前是第"+(now_QA_num+1)+"个");
			MF.que_id.setText(""+result_QA[now_QA_num].question_id);
		}
		
		YuanNetConnection conn = new YuanNetConnection();
		// 连接至服务器
		try {
			conn.connect(MyClient.ip, 2021, 3000);
			System.out.println("已经连接到服务器，开始下载下一题图片");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接服务器失败!!");
		}
		//题目答案和题目图片文件
		File_class que=new File_class();
		que.path = result_QA[now_QA_num].question_img_path;
		que.name = result_QA[now_QA_num].question_img_name;
		File_class ans = new File_class();
		ans.path = result_QA[now_QA_num].answer_img_path_0;
		ans.name = result_QA[now_QA_num].answer_img_name_0;
				
		//获取题目图片
		BufferedImage image_que = DownLoadImage.download(que, conn);
		//获取答案图片
		BufferedImage image_ans = DownLoadImage.download(ans, conn);
		MF.imageView_ans.setImage(image_que);
		MF.imageView_ans_0.setImage(image_ans);
		MF.current_ans_img_num.setText("当前是第1/"+result_QA[0].question_num+"张答案图片");
		conn.close();
	}
	
	public void next_img() {
		if(num<result_QA[now_QA_num].question_num-1) {
			num++;
		YuanNetConnection conn = new YuanNetConnection();
		// 连接至服务器
		try {
			conn.connect(MyClient.ip, 2021, 3000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接服务器失败!!");
		}
		File_class ans = new File_class();
		
		if(num==0) {
			ans.path = result_QA[now_QA_num].answer_img_path_0;
			ans.name = result_QA[now_QA_num].answer_img_name_0;
		}
		else if(num==1) {
			ans.path = result_QA[now_QA_num].answer_img_path_1;
			ans.name = result_QA[now_QA_num].answer_img_name_1;
		}
		else if(num==2) {
			ans.path = result_QA[now_QA_num].answer_img_path_2;
			ans.name = result_QA[now_QA_num].answer_img_name_2;
		}
		else if(num==3) {
			ans.path = result_QA[now_QA_num].answer_img_path_3;
			ans.name = result_QA[now_QA_num].answer_img_name_3;
		}
		else if(num==3) {
			ans.path = result_QA[now_QA_num].answer_img_path_3;
			ans.name = result_QA[now_QA_num].answer_img_name_3;
		}
		else if(num==4) {
			ans.path = result_QA[now_QA_num].answer_img_path_4;
			ans.name = result_QA[now_QA_num].answer_img_name_4;
		}
		else if(num==5) {
			ans.path = result_QA[now_QA_num].answer_img_path_5;
			ans.name = result_QA[now_QA_num].answer_img_name_5;
		}
		//获取答案图片
		BufferedImage image_ans = DownLoadImage.download(ans, conn);
		MF.current_ans_img_num.setText("当前是第"+(num+1)+"/"+result_QA[now_QA_num].question_num+"张答案图片");
		MF.imageView_ans_0.setImage(image_ans);
		
		conn.close();
		}
	}
	
	public void upper_img() {
		if(num>0) {
			num--;
		YuanNetConnection conn = new YuanNetConnection();
		// 连接至服务器
		try {
			conn.connect(MyClient.ip, 2021, 3000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接服务器失败!!");
		}
		File_class ans = new File_class();
		
		if(num==0) {
			ans.path = result_QA[now_QA_num].answer_img_path_0;
			ans.name = result_QA[now_QA_num].answer_img_name_0;
		}
		else if(num==1) {
			ans.path = result_QA[now_QA_num].answer_img_path_1;
			ans.name = result_QA[now_QA_num].answer_img_name_1;
		}
		else if(num==2) {
			ans.path = result_QA[now_QA_num].answer_img_path_2;
			ans.name = result_QA[now_QA_num].answer_img_name_2;
		}
		else if(num==3) {
			ans.path = result_QA[now_QA_num].answer_img_path_3;
			ans.name = result_QA[now_QA_num].answer_img_name_3;
		}
		else if(num==3) {
			ans.path = result_QA[now_QA_num].answer_img_path_3;
			ans.name = result_QA[now_QA_num].answer_img_name_3;
		}
		else if(num==4) {
			ans.path = result_QA[now_QA_num].answer_img_path_4;
			ans.name = result_QA[now_QA_num].answer_img_name_4;
		}
		else if(num==5) {
			ans.path = result_QA[now_QA_num].answer_img_path_5;
			ans.name = result_QA[now_QA_num].answer_img_name_5;
		}
		//获取答案图片
		BufferedImage image_ans = DownLoadImage.download(ans, conn);
		MF.current_ans_img_num.setText("当前是第"+(num+1)+"/"+result_QA[now_QA_num].question_num+"张答案图片");
		MF.imageView_ans_0.setImage(image_ans);
		
		conn.close();
		}
	}
	
	public void re_serach() {
		
		//生成大小依次排列的，不大于问题长度的数组
		int block=origin_key.length()/6;
		int where[] = new int[6];
	    for(int i=0;i<6;i++) {
	    	where[i] = rand_num(i*block,(i+1)*block);
	    }
	    
	    this.key="";
		//生成查询串
	    for(int i = 0;i<6;i++) {
	    	this.key =this.key+"%"+origin_key.substring(where[i], where[i]+1);
	    }
	    this.key=this.key+"%";
//		this.key = "%"+origin_key.substring(2, 3)+"%"+origin_key.substring(6, 7)+"%"+origin_key.substring(9, 10)+"%"
//				+origin_key.substring(12, 13)+"%"+origin_key.substring(15, 16)+"%"+origin_key.substring(19, 20)+"%"+origin_key.substring(26, 27)+"%";
		System.out.println(this.key);
		now_QA_num=0;
		run();
	}
	public int rand_num(int max,int min) {
		return  (int) (Math.random()*(max-min)+min); 
	}
}
