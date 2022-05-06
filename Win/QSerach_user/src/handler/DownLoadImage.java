package handler;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import dataClasses.File_class;
import dataClasses.QA_class;
import yuanNet.YuanNetConnection;
import yuanNet.YuanNetPacket;

public class DownLoadImage {

	public static BufferedImage download(File_class que,YuanNetConnection conn) {
				//获取图片
				try {
					conn.send((short)8, File_class.encode(que));
				} catch (Exception e) {
					System.out.println("通过获取题目图片出错，发送请求信息出错");
					e.printStackTrace();
				}
				int len;
				int rec_pkt_num = 0;
				try {
					YuanNetPacket ynp = conn.recv();
					ByteBuffer bbuf1 = ByteBuffer.allocate(100).wrap(ynp.content);
					len=bbuf1.getInt();
					rec_pkt_num = len/500+1;
					System.out.println("开始接收:"+que.name+",大小："+len+"字节，共："+rec_pkt_num+"个包");
				} catch (Exception e) {
					System.out.println("接收题目图片信息出错");
					e.printStackTrace();
				}
				//开始获取图片内容
				byte [] file_data=new byte[0];
				YuanNetPacket yn = null;
				for(int i = 0;i<rec_pkt_num;i++) {
					
					try {
						yn = conn.recv();
							if(yn==null) {
								System.out.println("未知错误，接收中断，接收不完整！接收到："+file_data.length+"字节");
								break;
							}
						file_data = addBytes(file_data, yn.content);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				BufferedImage image = null;
				System.out.println("接收到"+que.name+"字节长度："+file_data.length);
				ByteArrayInputStream in = new ByteArrayInputStream(file_data);
				try {
					image = ImageIO.read(in);
				} catch (IOException e) {
					System.out.println("接收转换为图片出错");
					e.printStackTrace();
				} 
				return image;
				
				
	}
	//将两个字节数组相加
	public static byte[] addBytes(byte[] data1, byte[] data2) {
		byte[] data3 = new byte[data1.length + data2.length];

		System.arraycopy(data1, 0, data3, 0, data1.length);

		System.arraycopy(data2, 0, data3, data1.length, data2.length);

		return data3;

		}
}
