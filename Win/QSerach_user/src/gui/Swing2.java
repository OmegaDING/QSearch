package gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dataClasses.USER_class;
import netService.MyClient;
/**
 * 
 * @author DingKunYuan
 *
 */
public class Swing2
{
	USER_class user;
	public void createGUI(Swing2 sw)
	{
		
		JFrame frame = new MainFrame("北邮爱学习 - 用户端",sw);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// 设置窗口的其他参数，如窗口大小
		frame.setSize(1280,850);
		
		// 显示窗口
		frame.setVisible(true);
		
		
	}
	
	private static void createLOGIN(Swing2 sw)
	{
		
		JFrame frame = new LoginFrame("北邮爱学习-用户端-login",sw);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// 设置窗口的其他参数，如窗口大小
		frame.setSize(500,200);
		
		// 显示窗口
		frame.setVisible(true);
		
		
	}
	public static void main(String[] args)
	{
		Swing2 sw = new Swing2();
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createLOGIN(sw);
			//sw.createGUI(sw);
				
			}
		});

		
	}
}
