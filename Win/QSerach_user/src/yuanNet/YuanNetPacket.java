package yuanNet;

/**
 * 
 * @author DingKunYuan
 *
 */
public class YuanNetPacket {

	// 类型常量
		public static final byte BINARY = 0;
		public static final byte TEXT = 1;
		
		// 头部
		public byte type; // 内容类型  0: 二进制, 1:字符串
		public byte flag; // reserved 
		public short action; // 消息码 或 命令码
		public int length; // 内容长度
		
		// 内容
		public byte[] content ;	
		
		// 设置 content 字段  (文本方式)
		public void setContent(String msg, String charset)
		{
			if(msg == null)
			{
				this.length = 0;
				return;
			}
			
			try {
				this.content = msg.getBytes(charset);
				this.length = this.content.length;
				
			}catch(Exception e)
			{	
				// 一般不会出错，除非在指定charset的时候写错了
				String message = e.getMessage() + ":" + e.getClass().getName();
				this.content = message.getBytes();
				this.length = this.content.length;
			}
		}
		
		// 获取 content 字段  (文本方式)
		public String toString(String charset)
		{
			if(this.length <=0) 
				return "";
			if(this.type == BINARY)
				return "此数据包为BINARY类型!";
			
			try {
				String str = new String(content, 0, length, charset);
				return str;
			}catch(Exception e)
			{
				return e.getMessage() + " : " + e.getClass().getName() ;
			}
		}
		
		@Override
		public String toString()
		{
			return toString("UTF-8");
		}
		
}
