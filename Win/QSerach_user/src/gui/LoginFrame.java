package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataClasses.USER_class;
import netService.MyClient;
import swingClasses.YLayout;

/**
 * 
 * @author DingKunYuan
 *
 */
public class LoginFrame extends JFrame{

	
	
	public LoginFrame(String title,Swing2 sw) {		
		super(title);
		
		// Content Pane
		JPanel root = new Mypanel();
		this.setContentPane(root);
		root.setLayout(new YLayout(8));
				
		JLabel label_id = new JLabel("id:");
		JLabel label_password = new JLabel("password:");
		JTextField id = new JTextField("1");
		JTextField password = new JTextField("password");
		root.add(label_id);
		root.add(id);
		root.add(label_password);
		root.add(password);
		
		USER_class user = new USER_class();
		JButton login = new JButton("登录");
		login.addActionListener((e)->{
			user.id = Integer.parseInt(id.getText());
			user.password = password.getText();
			MyClient mc = new MyClient();
			try {
				sw.user=mc.login(user);
				if(sw.user!=null) {
					if(sw.user.user_type<=2) {
					JOptionPane.showMessageDialog(this, "欢迎使用！");
					this.setVisible(false);
					sw.createGUI(sw);
					}
					else {
						JOptionPane.showMessageDialog(this, "您无权访问此模块！");
					}
				}else {
					JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				}
			} catch (Exception e1) {
				//e1.printStackTrace();
				System.out.println("连接服务器失败");
				JOptionPane.showMessageDialog(this, "连接服务器失败");
			}
			mc.close();
		});
		root.add(login);
	}

}
