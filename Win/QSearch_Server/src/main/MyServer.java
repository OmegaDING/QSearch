package main;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import org.json.JSONArray;
import org.json.JSONObject;

import dataBase_mySQL.MySQL;
import dataClasses.QA_class;
import handler.FileDownloadHandler;
import handler.RequestHandler;
import yuanNet.YuanNetConnection;
import yuanNet.YuanNetPacket;

public class MyServer
{
	ServerSocket serverSock;
	MySQL mSQL;
	
	public MyServer()
	{		
		mSQL = new MySQL();
	}
	
	public void startService() throws Exception
	{
//		// 为方便观察，把当前进程号给打印出来,
//				RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
//				String name = runtime.getName(); 
//				System.out.println("当前进程: " + name + "\n");
				
		// 建立服务器, 服务于 2021 端口
		serverSock = new ServerSocket(2021);
        System.out.println("服务器启动,等待连接...\n");
        
        while(true)
        {
        	try {
        	// 监听请求,阻塞等待,直接有客户端发起连接 ...
            Socket sock = serverSock.accept();
            System.out.println("有新连接");
            
         // 打印对端的IP和端口
            InetSocketAddress remoteAddr = (InetSocketAddress)sock.getRemoteSocketAddress();
            String remoteIp = remoteAddr.getHostString();
            int remotePort = remoteAddr.getPort();
            System.out.println(">> 连接来自：: " + remoteIp+ ":" + remotePort);
            
            YuanNetConnection conn = new YuanNetConnection(sock);   
            sock.setSoTimeout(3000);

            RequestHandler RH = new RequestHandler(conn,mSQL);
            RH.start();
        	}catch(Exception e) {
        		e.printStackTrace();
        		System.out.println("等待连接出错");
        	}
        }
	}
	

		

}
