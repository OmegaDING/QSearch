package gui;

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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONArray;
import org.json.JSONObject;

import dataClasses.QA_class;
import dataClasses.USER_class;
import handler.CloseAfter;
import handler.FileUploadHandler;
import handler.RunAfter;
import handler.SerachByID;
import handler.SerachByKeyWord;
import netService.MyClient;
import ocr_bd.YuanOCR;
import swingClasses.XLayout;
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
	//当前用户
	public USER_class user_now;
	// 原始图片
	BufferedImage srcImage;

	// 用来显示图片的控件
	YuanImageView imageView = new YuanImageView();
	// 用来显示图片的控件
	public YuanImageView imageView_ans = new YuanImageView();
	// 用来显示图片的控件
	public YuanImageView imageView_ans_0 = new YuanImageView();
//	YuanImageView imageView_ans_1 = new YuanImageView();
//	YuanImageView imageView_ans_2 = new YuanImageView();
//	YuanImageView imageView_ans_3 = new YuanImageView();
//	YuanImageView imageView_ans_4 = new YuanImageView();
//	YuanImageView imageView_ans_5 = new YuanImageView();
	
	public JLabel current_ans_img_num;
	//显示题目文字
	JTextArea jta    =new JTextArea("根据此内容搜索，也可手动更改准确度更高",15,75);
	//显示答案文字
	JTextArea jta_ans=new JTextArea("搜索到题目内容",15,80);
	//显示答案文字
	JTextArea jta_ans2=new JTextArea("搜索到答案内容",15,30);
	//确定当前截图序号
	int screenshot_num=0;
	//当前问题_答案类
	QA_class QA_now = new QA_class();
	
	MyClient mc;
	
	//知识点
	JTextField knowpo;
	
	//难度
	JSpinner Step ;
	
	//是否为家庭作业
	JCheckBox isHome;
	
	//科目
	JComboBox<String> subject;
	
	//题目ID输入框
	public JTextField que_id;
	
	//关键字搜索控制器
	SerachByKeyWord sbkw = null;
	
	//当前是第几题
	public JLabel total_num ;
	
	SerachByID sbi;
	
	int serachType=1;
	
	public MainFrame(String title,Swing2 sw)
	{
		super(title);
		user_now = sw.user;
		QA_now.typer_id = user_now.id;
		QA_now.typer_name = user_now.name;

		// Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//主界面竖排布局
		YuanPanel list = new YuanPanel();
		list.setLayout(new YLayout());
		list.padding(8);
		root.add(list);
		
		//用户相关信息
		JPanel aboutUser = new JPanel();
		aboutUser.setLayout(new FlowLayout());

		JLabel label_userid = new JLabel("当前用户id："+ user_now.id);
		JLabel label_username = new JLabel("当前用户name："+ user_now.name);
		
		aboutUser.add(label_userid);
		aboutUser.add(label_username);
		list.add(aboutUser);
		
		
		JButton scaleButton = new JButton("题目截图");
		scaleButton.addActionListener((e) -> {
			screenshot_num=0;
			startCapture();
		});

		JButton cropButton = new JButton("保存题目截图图片到本地");
		cropButton.addActionListener((e) -> {
			screenshot_num=0;
			saveCapture();
		});

		// 第2排按钮
		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());
		toolbar.add(scaleButton);
		toolbar.add(cropButton);
		list.add(toolbar);
		
		
		YuanPanel img_char_compare = new YuanPanel();
		img_char_compare.setLayout(new XLayout(8));
		img_char_compare.padding(10);

		img_char_compare.add(imageView, "1w");
		imageView.setBgColor(new Color(0x333333));
		imageView.setScaleType(YuanImageView.FIT_CENTER);
		
		JPanel jp=new JPanel();    //创建一个JPanel对象
        jta.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK);    //设置组件的背景色
        jta.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
        jta.setBackground(Color.YELLOW);    
        JScrollPane jsp=new JScrollPane(jta);    //将文本域放入滚动窗口
        Dimension size=jta.getPreferredSize();    //获得文本域的首选大小
        jsp.setBounds(10,10,size.width,size.height);
        jp.add(jsp);    //将JScrollPane添加到JPanel容器中
        
        img_char_compare.add(jp,"1w");    //将JPanel容器添加到JFrame容器中
        img_char_compare.setPreferredSize(new Dimension(root.getWidth(),300));

        
		list.add(img_char_compare);
		
		//中间一栏按钮
		JLabel que_id_label = new JLabel("题目ID：");
		que_id = new JTextField("1", 10);
		
		
		JButton Button_ans = new JButton("根据题目ID搜索");
		Button_ans.addActionListener((e) -> {
			int id = Integer.parseInt(que_id.getText());
			if(id<1) {
				JOptionPane.showMessageDialog(this,"题号错误" );
				return;
			}
			sbi = new SerachByID(id, this);
			sbi.start();
			serachType=1;
		});

		JButton cropButton_ans = new JButton("根据题目内容搜索");
		cropButton_ans.addActionListener((e) -> {
			String keyword = jta.getText();
			if(keyword.length()<30) {
				JOptionPane.showMessageDialog(this,"搜索题目关键字过短" );
				return;
			}
			sbkw = new SerachByKeyWord(keyword,this);
			sbkw.start();
			serachType=2;
		});
		JButton next_ans_img = new JButton("下一个答案图片");
		JButton upper_ans_img = new JButton("上一个答案图片");
		next_ans_img.addActionListener((e)->{
			if(serachType==1)
				sbi.next();
			if(serachType==2)
				sbkw.next_img();
		});
		
		upper_ans_img.addActionListener((e)->{
			if(serachType==1)
				sbi.upper();
			if(serachType==2)
				sbkw.upper_img();
		});
		
		current_ans_img_num = new JLabel("当前是第1张答案图片");
		JPanel toolbar_ans = new JPanel();
		toolbar_ans.add(que_id_label);
		toolbar_ans.add(que_id);
		toolbar_ans.setLayout(new FlowLayout());
		toolbar_ans.add(Button_ans);
		toolbar_ans.add(cropButton_ans);
		toolbar_ans.add(next_ans_img);
		toolbar_ans.add(upper_ans_img);
		toolbar_ans.add(current_ans_img_num);
		list.add(toolbar_ans);
		
		//搜索结果题目
		YuanPanel img_char_compare2 = new YuanPanel();
		img_char_compare2.setLayout(new XLayout(8));
		img_char_compare2.padding(10);

		img_char_compare2.add(imageView_ans, "1w");
		imageView_ans.setBgColor(new Color(0x333333));
		imageView_ans.setScaleType(YuanImageView.FIT_CENTER);
		
		img_char_compare2.add(imageView_ans_0, "1w");
		imageView_ans_0.setBgColor(new Color(0x333333));
		imageView_ans_0.setScaleType(YuanImageView.FIT_CENTER);
		
//		img_char_compare2.add(imageView_ans_1, "1w");
//		imageView_ans_1.setBgColor(new Color(0x333333));
//		imageView_ans_1.setScaleType(YuanImageView.FIT_CENTER);
//		imageView_ans_1.setVisible(false);
//
//		img_char_compare2.add(imageView_ans_2, "1w");
//		imageView_ans_2.setBgColor(new Color(0x333333));
//		imageView_ans_2.setScaleType(YuanImageView.FIT_CENTER);
//		imageView_ans_2.setVisible(false);
//		
//		img_char_compare2.add(imageView_ans_3, "1w");
//		imageView_ans_3.setBgColor(new Color(0x333333));
//		imageView_ans_3.setScaleType(YuanImageView.FIT_CENTER);
//		imageView_ans_3.setVisible(false);
//		
//		img_char_compare2.add(imageView_ans_4, "1w");
//		imageView_ans_4.setBgColor(new Color(0x333333));
//		imageView_ans_4.setScaleType(YuanImageView.FIT_CENTER);
//		imageView_ans_4.setVisible(false);
//		
//		img_char_compare2.add(imageView_ans_5, "1w");
//		imageView_ans_5.setBgColor(new Color(0x333333));
//		imageView_ans_5.setScaleType(YuanImageView.FIT_CENTER);
//		imageView_ans_5.setVisible(false);

        img_char_compare2.setPreferredSize(new Dimension(root.getWidth(),450));
        
        list.add(img_char_compare2);

		
		


		//最下方一排按钮
		JPanel button_btm = new JPanel();
		button_btm.setLayout(new FlowLayout());
		JButton next_que = new JButton("下一个");
		JButton upper_que = new JButton("上一个");

		JButton refush_ser = new JButton("不是我想要的题目");
		total_num = new JLabel("共有00个符合的题目，当前是第00个");
		
		next_que.addActionListener((e)->{
			sbkw.next();
		});
		
		upper_que.addActionListener((e)->{
			sbkw.upper();
		});
		refush_ser.addActionListener((e)->{
			sbkw.re_serach();
		});
		
		button_btm.add(total_num);
		button_btm.add(upper_que);
		button_btm.add(next_que);
		button_btm.add(refush_ser);
		root.add(button_btm,BorderLayout.PAGE_END);
	}
	
	
	public void startCapture()
	{
		this.setVisible(false);		
		new CaptureThread().start();
	}
	
	public void showImage(BufferedImage screenImg, Rectangle area)
	{
		BufferedImage img = screenImg.getSubimage(area.x, area.y,area.width,area.height);
		if(screenshot_num==0)
			imageView.setImage(img);
		else if(screenshot_num==1)
			imageView_ans.setImage(img);
	}
	
	public void showOCRtransferResult(Image image) {
		YuanOCR yocr = new YuanOCR();
		//获取结果集
		JSONObject ocr_result = yocr.getTransfer(image);
		//获取ocr文字
		JSONArray word_result=ocr_result.getJSONArray("words_result");
		//System.out.println(word_result);
		//获取结果长度
		int lenth = (int)ocr_result.get("words_result_num");
		//System.out.println(lenth);

		//将结果合并到一起
		String ocr_text = "" ;
		for(int i=0;i<lenth;i++) {
			ocr_text += (String)word_result.getJSONObject(i).get("words") +"\n";
		}
		if(screenshot_num==0)
			jta.setText(ocr_text);
		else if(screenshot_num==1)
			jta_ans.setText(ocr_text);
	}

	public void saveCapture()
	{
		BufferedImage img = null;
		if(screenshot_num==0) {
			img = (BufferedImage) imageView.getImage();
		}
		else if(screenshot_num ==1) {
			img = (BufferedImage) imageView_ans.getImage();
		}
		if(img == null) {System.out.println("当前窗口里没有图片，请截图");return;}
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("图片", "jpg");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("."));
		int ret = chooser.showSaveDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			// 结果为：用户要保存的文件的路径
			File file = chooser.getSelectedFile();
			String filePath = file.getAbsolutePath();
			if(!filePath.endsWith(".jpg"))
			{
				filePath += ".jpg";
				file = new File(filePath);
			}
			
			try {
				ImageIO.write(img, "JPEG", file);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private class CaptureThread extends Thread
	{
		@Override
		public void run()
		{
			try {
				sleep(500);
			}catch(Exception e) {}
			
			SwingUtilities.invokeLater(()->{				
				captureScreen();
			});
			
		}
		
		private void captureScreen()
		{
			BufferedImage snapshot = null;
			
			try
			{		
				//获取屏幕的尺寸
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				// 屏幕截图
				Robot robot = new Robot();
				snapshot = robot.createScreenCapture(new Rectangle(screenSize));//截取整个屏幕的图像
				
//			    ImageIO.write(snapshot, "PNG", new File("screen.png"));
			    
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			//setVisible(true);
			new CaptureFrame(MainFrame.this, snapshot).setVisible(true);
		}
	}
}
