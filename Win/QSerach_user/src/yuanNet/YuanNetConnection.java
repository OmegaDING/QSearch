package yuanNet;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
/**
 * 
 * @author DingKunYuan
 *
 */
/* 客户端的连接: 
 * 	AfNetConnection conn = new AfNetConnection();
 *  conn.connect(...)
 * 服务端的连接:
 * 	AfNetConnection conn = new AfNetConnection(sock);
 * 
 */
public class YuanNetConnection {

	public int maxPacketSize = 1024 * 1024; // 收到到包的最大为1M
	public String charset = "UTF-8";  // 字符集: UTF-8 或 GBK	
	public Socket sock ;	
	
    private InputStream inputStream ;
    private OutputStream outputStream ; 
	
	public YuanNetConnection()
	{		
	}
	
	public YuanNetConnection(Socket sock) throws Exception
	{
		this.sock = sock;
        inputStream= sock.getInputStream();
        outputStream = sock.getOutputStream(); 
	}
	
	// 连接至服务器
	public void connect(String ip, int port, int timeout) throws Exception
	{
		sock = new Socket();

		sock.connect( new InetSocketAddress(ip,port), timeout);
        inputStream= sock.getInputStream();
        outputStream = sock.getOutputStream(); 
	}
	
	// 关闭连接
	public void close()
	{
		try {
			sock.close();
			sock = null;
		}catch(Exception e)
		{			
		}
	}
	
	// 完全接受N个字节：此方法会一直等待，直接收满N个字节才返回
	public int readFully(byte[] data, int off, int N) throws Exception
	{
		int count = 0; // 已经读取字节数
		while(count < N)
		{
			int remain = N  - count; // remain: 还要接收多少字节
			int numBytes = inputStream.read(data, off + count, remain);
			if(numBytes < 0)
				return -1; // 对方socket已经关闭, 此read()并不会异常,而是返回-1
			
			count += numBytes;
		}
		return N;
	}
	
	///////////////  数据包的发送 ////////////
	// 只有消息类型，不带消息体
	public void send(short action) throws Exception
	{
		send (action , (String)null);
	}
	
	// 发送文本数据
	public void send(short action, String msg) throws Exception
	{
		send(action, msg, this.charset);
	}
	
	public void send(short action, String msg, String charset) throws Exception
	{
		YuanNetPacket packet = new YuanNetPacket();
		packet.type = YuanNetPacket.TEXT;
		packet.flag = 0;
		packet.action = action;		
		if(msg == null)
			packet.length = 0;
		else
			packet.setContent(msg, this.charset);
		
		send (packet);
	}
	
	// 发送二进制数据
	public void send(short action, byte[] msg) throws Exception
	{
		if(msg == null)
			send(action, msg, 0);
		else			
			send (action, msg, msg.length);
	}
	
	public void send(short action, byte[] msg, int size) throws Exception
	{
		YuanNetPacket packet = new YuanNetPacket();
		packet.type = YuanNetPacket.BINARY;
		packet.flag = 0;
		packet.action = action;		
		packet.content = msg;
		packet.length = size;
		
		send (packet);
	}
	
	// 编码并发送一个消息包
	public void send(YuanNetPacket pkt) throws Exception
	{
		// 先发头部
		ByteBuffer headBuf = ByteBuffer.allocate(8);
		headBuf.put(pkt.type);
		headBuf.put(pkt.flag);
		headBuf.putShort( pkt.action );
		headBuf.putInt(pkt.length);
		outputStream.write(headBuf.array(), 0, headBuf.position());		
		
		// 再发内容
		if(pkt.length > 0)
		{
			outputStream.write(pkt.content, 0, pkt.length);
		}
	}
	
	////////////// 数据包的接收 ////////////	
	public YuanNetPacket recv() throws Exception
	{
		ByteBuffer headBuf = ByteBuffer.allocate(8);
		int rc = readFully(headBuf.array(), 0, 8);
		if(rc != 8) 
			return null; // 当对方socket已经关闭时，我方无法感知,readFully()仍返回-1
		
		YuanNetPacket pkt = new YuanNetPacket();
		pkt.type = headBuf.get();
		pkt.flag = headBuf.get();
		pkt.action = headBuf.getShort();
		pkt.length = headBuf.getInt();
		
//		if(pkt.length ==0)
//		{
//			pkt.type = 1;
//		}
		if(pkt.length > maxPacketSize)
		{
			// 此包不正常，length字段不可能这么大
			throw new Exception("收到AfNetPacket.length太大，可能是编码错误!"
							+ pkt.length + ">" + maxPacketSize);
		}
		
		if(pkt.length > 0)
		{
			pkt.content = new byte[ pkt.length];
			rc = readFully(pkt.content, 0, pkt.length);
			if(rc != pkt.length)
				return null; // 当对方socket已经关闭时，我方无法感知,readFully()仍返回-1
			
			//System.out.println("内容: " + pkt.length);
		}		
		return pkt;
	}
}
