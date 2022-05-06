package handler;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dataClasses.File_class;
import dataClasses.QA_class;
import gui.MainFrame;
import netService.MyClient;
import yuanNet.YuanNetConnection;
import yuanNet.YuanNetPacket;

public class SerachByID extends Thread{

	int id;
	MainFrame MF;
	int num=0;
	QA_class QA;
	public SerachByID(int id,MainFrame MF) {
		this.id=id;
		this.MF=MF;
	}
	
	@Override
	public void run() {
		ByteBuffer bbuf = ByteBuffer.allocate(100);
		bbuf.putInt(id);
		YuanNetConnection conn = new YuanNetConnection();
		// 连接至服务器
		try {
			conn.connect(MyClient.ip, 2021, 3000);
			System.out.println("已经连接到服务器，开始根据id搜索");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接服务器失败!!");
		}

		//发送请求题目id
		try {
			conn.send((short)5, bbuf.array());

			System.out.println("请求题目id："+id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//接收QA_class
		QA = new QA_class();
		try {
			YuanNetPacket ynp = conn.recv();
			QA=QA_class.decode(ynp.content, 0);
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if(QA.question_id==0) {
			System.out.println("题库中不存在此题目");
			conn.close();
			JOptionPane.showMessageDialog(MF,"题库中不存在此题目" );
			return;
		}
		System.out.println("获取到题目，id："+QA.question_id+"开始获取图片");
		
		
		//题目答案和题目图片文件
		File_class que=new File_class();
		que.path = QA.question_img_path;
		que.name = QA.question_img_name;
		File_class ans = new File_class();
		ans.path = QA.answer_img_path_0;
		ans.name = QA.answer_img_name_0;
		
		//获取题目图片
		BufferedImage image_que = DownLoadImage.download(que, conn);
		//获取答案图片
		BufferedImage image_ans = DownLoadImage.download(ans, conn);
		MF.imageView_ans.setImage(image_que);
		MF.imageView_ans_0.setImage(image_ans);
		MF.current_ans_img_num.setText("当前是第1/"+QA.question_num+"张答案图片");
		num=0;
			conn.close();
		}

	public void next() {
		if(num<QA.question_num-1) {
			num++;
			YuanNetConnection conn = new YuanNetConnection();
			// 连接至服务器
			try {
				conn.connect(MyClient.ip, 2021, 3000);
				System.out.println("已经连接到服务器，开始根据id搜索");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("连接服务器失败!!");
			}
			
			File_class ans = new File_class();
			if(num==0) {
				ans.path = QA.answer_img_path_0;
				ans.name = QA.answer_img_name_0;
			}
			else if(num==1) {
				ans.path = QA.answer_img_path_1;
				ans.name = QA.answer_img_name_1;
			}
			else if(num==2) {
				ans.path = QA.answer_img_path_2;
				ans.name = QA.answer_img_name_2;
			}
			else if(num==3) {
				ans.path = QA.answer_img_path_3;
				ans.name = QA.answer_img_name_3;
			}
			else if(num==3) {
				ans.path = QA.answer_img_path_3;
				ans.name = QA.answer_img_name_3;
			}
			else if(num==4) {
				ans.path = QA.answer_img_path_4;
				ans.name = QA.answer_img_name_4;
			}
			else if(num==5) {
				ans.path = QA.answer_img_path_5;
				ans.name = QA.answer_img_name_5;
			}
			//获取答案图片
			BufferedImage image_ans = DownLoadImage.download(ans, conn);
			MF.imageView_ans_0.setImage(image_ans);
			MF.current_ans_img_num.setText("当前是第"+(num+1)+"/"+QA.question_num+"张答案图片");
			conn.close();
		}
	}
	
	public void upper() {
		if(num>0) {
			num--;
			YuanNetConnection conn = new YuanNetConnection();
			// 连接至服务器
			try {
				conn.connect(MyClient.ip, 2021, 3000);
				System.out.println("已经连接到服务器，开始根据id搜索");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("连接服务器失败!!");
			}
			
			File_class ans = new File_class();
			if(num==0) {
				ans.path = QA.answer_img_path_0;
				ans.name = QA.answer_img_name_0;
			}
			else if(num==1) {
				ans.path = QA.answer_img_path_1;
				ans.name = QA.answer_img_name_1;
			}
			else if(num==2) {
				ans.path = QA.answer_img_path_2;
				ans.name = QA.answer_img_name_2;
			}
			else if(num==3) {
				ans.path = QA.answer_img_path_3;
				ans.name = QA.answer_img_name_3;
			}
			else if(num==3) {
				ans.path = QA.answer_img_path_3;
				ans.name = QA.answer_img_name_3;
			}
			else if(num==4) {
				ans.path = QA.answer_img_path_4;
				ans.name = QA.answer_img_name_4;
			}
			else if(num==5) {
				ans.path = QA.answer_img_path_5;
				ans.name = QA.answer_img_name_5;
			}
			//获取答案图片
			BufferedImage image_ans = DownLoadImage.download(ans, conn);
			MF.imageView_ans_0.setImage(image_ans);
			MF.current_ans_img_num.setText("当前是第"+(num+1)+"/"+QA.question_num+"张答案图片");
			conn.close();
		}
	}
}
