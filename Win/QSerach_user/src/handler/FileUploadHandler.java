package handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.json.JSONObject;

import dataClasses.File_class;
import yuanNet.YuanNetConnection;
import yuanNet.YuanNetPacket;

/**
 * 
 * @author DingKunYuan
 *
 */
public class FileUploadHandler extends Thread{


	YuanNetConnection conn;
	File_class FC;
	
	public FileUploadHandler(YuanNetConnection conn, byte[] data,String absolute_path,String file_name)
	{
		this.conn = conn;
		this.FC = new File_class();
		this.FC.content = data;
		this.FC.content_len=FC.content.length;
		this.FC.path = absolute_path;
		this.FC.name = file_name;
		
	}
	
	@Override
	public void run() {
		try {
			byte[] sendb = new byte[40000];
			int len = File_class.encode(FC, sendb);
			
			
			byte[] send = new byte[len];
			File_class.encode(FC, send);
			
			conn.send( (short)1,send );
			int file_pkt_len = (FC.content.length/500)+1;
			
			System.out.println("发送文件"+FC.name+"共"+file_pkt_len+"个包"+"长度："+FC.content_len);
			
			for(int i =0;i<file_pkt_len;i++) {
				int start_point = 500*i>FC.content.length?FC.content.length:500*i;
				int send_len = (start_point+500)>FC.content.length?(FC.content.length-start_point):500;
				byte[] by = new byte[send_len];
				System.arraycopy(FC.content, start_point, by, 0, send_len);
				conn.send((short)9, by);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件发送失败");
		}
	}
	
}
