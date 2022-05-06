package main;

import java.awt.AWTException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONArray;
import org.json.JSONObject;

import dataClasses.QA_class;
import swingClasses.YLayout;
import swingClasses.YuanImageView;
import swingClasses.YuanPanel;
/**
 * 
 * @author DingKunYuan
 *
 */

public class MainFrame extends JFrame
{
	public MainFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
//		//主界面竖排布局
//		YuanPanel list = new YuanPanel();
//		list.setLayout(new YLayout());
//		list.padding(8);
//		root.add(list);
		
		YuanImageView imageView = new YuanImageView();
		try {
			imageView.setImage(imageFromResource("/image/知卷.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		root.add(imageView);
		
		
		
		
		
		
	}
	
	// 从资源加载图片
		private Image imageFromResource(String imagePath) throws Exception
		{
			java.net.URL imageUrl = this.getClass().getResource(imagePath);
			BufferedImage image = ImageIO.read(imageUrl);
			return image;
		}
		
		// 从文件加载图片
		private Image imageFromFile(File imageFile) throws Exception
		{
			BufferedImage image = ImageIO.read(imageFile);
			return image;
		}

}
