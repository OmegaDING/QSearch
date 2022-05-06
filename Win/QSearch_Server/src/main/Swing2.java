package main;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 
 * @author DingKunYuan
 *
 */
public class Swing2
{
	private static void createGUI()
	{
		
		JFrame frame = new MainFrame("北邮爱学习-服务端");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// 设置窗口的其他参数，如窗口大小
		frame.setSize(800,600);
		
		// 显示窗口
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) throws Exception
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createGUI();
			}
		});

		MyServer server = new MyServer();
		server.startService();    
		
	}
}
