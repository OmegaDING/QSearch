package handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dataClasses.File_class;
import yuanNet.YuanNetConnection;
import yuanNet.YuanNetPacket;

public class FileDownloadHandler {


	YuanNetPacket pkt_rec;
	
	File Dir; // 存储的目录	
	File file;
	String fileName;
	String path;
	OutputStream outputStream;
	File_class FC;
	int file_len;
	
	
	public FileDownloadHandler(YuanNetPacket pkt_rec,YuanNetConnection conn) throws Exception
	{
		this.pkt_rec = pkt_rec;
		this.FC = File_class.decode(pkt_rec.content);
		this.fileName = FC.name;
		this.path = FC.path;
		this.file_len = FC.content_len;
		System.out.println("content_len:"+FC.content_len);

		System.out.println("已经接收到文件"+fileName);
		//创建相应文件夹
		Dir = new File(FC.path);
		if(!Dir.isDirectory())
			Dir.mkdirs();
		//创建文件
		file = new File(Dir,fileName);
		
		try {
			outputStream = new FileOutputStream(file);

			
			byte file_data[] = new byte[0];
			int rec_pkt_num = (FC.content_len/500)+1;
			System.out.println("开始接受文件内容，共"+rec_pkt_num+"个包");
			for(int i = 0;i<rec_pkt_num;i++) {
				YuanNetPacket yn=conn.recv();
				if(yn==null) {
					System.out.println("未知错误，接收中断，接收不完整！接收到："+file_data.length+"字节");
					break;
				}
				file_data = addBytes(file_data, yn.content);
				
			}
			
			
			
			System.out.println("接收到"+fileName+"字节长度："+file_data.length);
			outputStream.write(file_data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("已保存文件到"+path+fileName);
	}
	
//	@Override
//	public void run() {
//		try {
//			outputStream = new FileOutputStream(path+fileName);
//
//			outputStream.write(pkt_rec.content);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				outputStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	//将两个字节数组相加
	public static byte[] addBytes(byte[] data1, byte[] data2) {
		byte[] data3 = new byte[data1.length + data2.length];

		System.arraycopy(data1, 0, data3, 0, data1.length);

		System.arraycopy(data2, 0, data3, data1.length, data2.length);

		return data3;

		}
}
