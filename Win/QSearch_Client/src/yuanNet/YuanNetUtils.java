package yuanNet;

/**
 * 
 * @author DingKunYuan
 *
 */
public class YuanNetUtils {

	// 此工具方法用于按十六进制打印一个字节数组 ( 十六进制，参考《二进制篇》 )
		public static void print(byte[] array, int off, int length)
		{
			for(int i=0; i<length; i++)
			{
				int index = off + i;
				if(index >= array.length) break;
				
				if( i%8 == 0)
				{
					if (i % 16 != 0) System.out.print("  ");
				}
				if( i>0 && i%16 == 0)
				{
					System.out.printf("\n");
				}
				
				System.out.printf("%02X ", array[index]);			
			}
			System.out.printf("\n");
		}
}
