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
import dataClasses.SB_class;
import dataClasses.USER_class;
import handler.CloseAfter;
import handler.FileUploadHandler;
import handler.RunAfter;
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
	USER_class user_now;
	// 原始图片
	BufferedImage srcImage;

	// 用来显示图片的控件
	YuanImageView imageView = new YuanImageView();
	// 用来显示图片的控件
	YuanImageView imageView_ans = new YuanImageView();
	YuanImageView imageView_ans_1 = new YuanImageView();
	YuanImageView imageView_ans_2 = new YuanImageView();
	YuanImageView imageView_ans_3 = new YuanImageView();
	YuanImageView imageView_ans_4 = new YuanImageView();
	YuanImageView imageView_ans_5 = new YuanImageView();
	
	//显示题目文字
	JTextArea jta    =new JTextArea("题目截图内容",14,80);
	//显示答案文字
	JTextArea jta_ans=new JTextArea("答案截图内容",14,80);
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
	
	JLabel ans_num;
	
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
		
		//题目相关信息
		JPanel aboutBar = new JPanel();
		aboutBar.setLayout(new FlowLayout());
		
		JLabel label_know = new JLabel("知识点：");
		knowpo = new JTextField(10);
		knowpo.setText("Test知识点");
		aboutBar.add(label_know);
		aboutBar.add(knowpo);
		
		JLabel label_diff = new JLabel("难度：");
		Step = new JSpinner(new SpinnerNumberModel(1,0,10,1));
		aboutBar.add(label_diff);
		aboutBar.add(Step);
		
		JLabel label_sub = new JLabel("科目：");
		subject = new JComboBox<String>();
		subject.addItem("JAVA");
		QA_now.subject = "JAVA";
		
		mc = new MyClient();
		SB_class SB = mc.get_sb();
		mc.close();
		for(int i=0;i<SB.num;i++) {
			subject.addItem(SB.name.get(i));
		}
		
		
		//将科目设置
		subject.addActionListener((e->{
			String item = (String)subject.getSelectedItem();
//			if(item.equals("红"));
//			if(subject.getSelectedIndex()==1);
			QA_now.subject = item;
		}));
		aboutBar.add(label_sub);
		aboutBar.add(subject);
		
		JLabel label_isHome = new JLabel("是否为作业：");
		isHome = new JCheckBox();
		aboutBar.add(label_isHome);
		aboutBar.add(isHome);
		
		list.add(aboutBar);
		
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
		img_char_compare.padding(20);

		img_char_compare.add(imageView, "40%");
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
		JButton scaleButton_ans = new JButton("答案截图");
		scaleButton_ans.addActionListener((e) -> {
			if(screenshot_num==0||screenshot_num==-1)
				screenshot_num=1;
			startCapture();
		});

		JButton cropButton_ans = new JButton("保存答案截图图片到本地");
		cropButton_ans.addActionListener((e) -> {
			screenshot_num=1;
			saveCapture();
		});
		ans_num = new JLabel("当前是第1个答案图片");
		JPanel toolbar_ans = new JPanel();
		toolbar_ans.setLayout(new FlowLayout());
		toolbar_ans.add(ans_num);
		toolbar_ans.add(scaleButton_ans);
		toolbar_ans.add(cropButton_ans);
		list.add(toolbar_ans);
		
		
		YuanPanel img_char_compare2 = new YuanPanel();
		img_char_compare2.setLayout(new XLayout(8));
		img_char_compare2.padding(20);

		img_char_compare2.add(imageView_ans, "40%");
		imageView_ans.setBgColor(new Color(0x333333));
		imageView_ans.setScaleType(YuanImageView.FIT_CENTER);
		
		img_char_compare2.add(imageView_ans_1, "40%");
		imageView_ans_1.setBgColor(new Color(0x333333));
		imageView_ans_1.setScaleType(YuanImageView.FIT_CENTER);
		imageView_ans_1.setVisible(false);
		
		img_char_compare2.add(imageView_ans_2, "40%");
		imageView_ans_2.setBgColor(new Color(0x333333));
		imageView_ans_2.setScaleType(YuanImageView.FIT_CENTER);
		imageView_ans_2.setVisible(false);
		
		img_char_compare2.add(imageView_ans_3, "40%");
		imageView_ans_3.setBgColor(new Color(0x333333));
		imageView_ans_3.setScaleType(YuanImageView.FIT_CENTER);
		imageView_ans_3.setVisible(false);
		
		img_char_compare2.add(imageView_ans_4, "40%");
		imageView_ans_4.setBgColor(new Color(0x333333));
		imageView_ans_4.setScaleType(YuanImageView.FIT_CENTER);
		imageView_ans_4.setVisible(false);
		
		img_char_compare2.add(imageView_ans_5, "40%");
		imageView_ans_5.setBgColor(new Color(0x333333));
		imageView_ans_5.setScaleType(YuanImageView.FIT_CENTER);
		imageView_ans_5.setVisible(false);
		
		
		JPanel jp_ans=new JPanel();    //创建一个JPanel对象
        jta_ans.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta_ans.setForeground(Color.BLACK);    //设置组件的背景色
        jta_ans.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
        jta_ans.setBackground(Color.CYAN);    
        JScrollPane jsp_ans =new JScrollPane(jta_ans);    //将文本域放入滚动窗口
        Dimension size2=jta_ans.getPreferredSize();    //获得文本域的首选大小
        jsp_ans.setBounds(10,10,size2.width,size2.height);
        jp_ans.add(jsp_ans);    //将JScrollPane添加到JPanel容器中
        
        img_char_compare2.add(jp_ans,"1w");    //将JPanel容器添加到JFrame容器中
        img_char_compare2.setPreferredSize(new Dimension(root.getWidth(),300));
        
        list.add(img_char_compare2);
		
       
		
		

//
		//最下方一排按钮
		JPanel button_btm = new JPanel();
		button_btm.setLayout(new FlowLayout());
		JButton next_ans_img = new JButton("下一个答案图片");
		JButton upper_ans_img = new JButton("上一个答案图片");
		JButton save_to_db = new JButton("确认无误，添加题目到云端");
		button_btm.add(next_ans_img);
		button_btm.add(upper_ans_img);
		button_btm.add(save_to_db);
		next_ans_img.addActionListener((e)->{
			if(screenshot_num<5) {
				screenshot_num++;
				ans_num.setText("当前是第"+screenshot_num+"个答案图片");
				showAnsImage();
			}
		});
		upper_ans_img.addActionListener((e)->{
			if(screenshot_num>1) {
				screenshot_num--;
				ans_num.setText("当前是第"+screenshot_num+"个答案图片");
				showAnsImage();
			}
		});
		save_to_db.addActionListener((e)->{
			try {
				setQAclass();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		list.add(button_btm);
	}
	
	public void setQAclass() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		QA_now.question_id=1;
		QA_now.question_img_path = "c:/DB/IMG/QUE/";
		QA_now.question_img_name="-1_que.jpg";
		QA_now.question_img_path_1 = "c:/DB/IMG/QUE/";
		QA_now.question_img_name_1="-1_que.jpg";
		QA_now.question_string = jta.getText();
		if(QA_now.question_string.length()<2) {
			JOptionPane.showMessageDialog(this, "问题文字不能为空！或过短<2");
			return;
		}
		QA_now.answer_img_path_0 = "c:/DB/IMG/ANS/";
		QA_now.answer_img_path_1 = "c:/DB/IMG/ANS/";
		QA_now.answer_img_path_2 = "c:/DB/IMG/ANS/";
		QA_now.answer_img_path_3 = "c:/DB/IMG/ANS/";
		QA_now.answer_img_path_4 = "c:/DB/IMG/ANS/";
		QA_now.answer_img_path_5 = "c:/DB/IMG/ANS/";
		QA_now.answer_img_name_0 = "-1_ans.jpg";
		QA_now.answer_img_name_1 = "-1_ans.jpg";
		QA_now.answer_img_name_2 = "-1_ans.jpg";
		QA_now.answer_img_name_3 = "-1_ans.jpg";
		QA_now.answer_img_name_4 = "-1_ans.jpg";
		QA_now.answer_img_name_5 = "-1_ans.jpg";
		
		QA_now.answer_string = jta_ans.getText();
		int num=0;
		if(imageView_ans!=null)
			num++;
		if(imageView_ans_1.getImage()!=null)
			num++;
		if(imageView_ans_2.getImage()!=null)
			num++;
		if(imageView_ans_3.getImage()!=null)
			num++;
		if(imageView_ans_4.getImage()!=null)
			num++;
		if(imageView_ans_5.getImage()!=null)
			num++;
		QA_now.question_num =num;
		if(QA_now.answer_string.length()<2) {
			JOptionPane.showMessageDialog(this, "答案文字不能为空！或过短<2");
			return;
		}
		QA_now.knowledge_point = knowpo.getText();
		if(QA_now.knowledge_point.length()<1) {
			JOptionPane.showMessageDialog(this, "知识点不能为空！");
			return;
		}
		QA_now.type_in_time = date;
//		QA_now.typer_name = "dky";
//		QA_now.typer_id = 1;
//		QA_now.subject =;
		QA_now.difficulty = (int)Step.getValue();
		QA_now.is_homework=isHome.isSelected()==true?1:0;
		if(imageView.getImage()==null ) {
			JOptionPane.showMessageDialog(this, "题目图片不能为空，请截图！");
			return;
		}
		if(imageView_ans.getImage()==null ) {
			JOptionPane.showMessageDialog(this, "答案图片不能为空，请截图！");
			return;
		}

		
		//连接服务器
		mc = new MyClient();
		//int time_start;
		//上传题目
		int id =mc.typeInQuestion(QA_now);
		System.out.println("上传后获得数据库主键为"+id);
		
		//设置问题图片名
		QA_now.question_img_name = id+"_que.jpg";
		//设置答案图片名
		QA_now.answer_img_name_0 = id +"_0_ans.jpg";
		QA_now.answer_img_name_1 = id +"_1_ans.jpg";
		QA_now.answer_img_name_2 = id +"_2_ans.jpg";
		QA_now.answer_img_name_3 = id +"_3_ans.jpg";
		QA_now.answer_img_name_4 = id +"_4_ans.jpg";
		QA_now.answer_img_name_5 = id +"_5_ans.jpg";
		
		//上传题目图片
		FileUploadHandler fuh1 = mc.uploadImage(imageView.getImage(), QA_now.question_img_path, QA_now.question_img_name);
		fuh1.start();
		//上传答案图片
		FileUploadHandler fuh2 = mc.uploadImage(imageView_ans.getImage(), QA_now.answer_img_path_0, QA_now.answer_img_name_0);
		fuh2.start();
		if(imageView_ans_1.getImage()!=null) {
			FileUploadHandler fuh3 = mc.uploadImage(imageView_ans_1.getImage(), QA_now.answer_img_path_1, QA_now.answer_img_name_1);
			fuh3.start();
		}
		if(imageView_ans_2.getImage()!=null) {
			FileUploadHandler fuh3 = mc.uploadImage(imageView_ans_2.getImage(), QA_now.answer_img_path_2, QA_now.answer_img_name_2);
			fuh3.start();
		}
		if(imageView_ans_3.getImage()!=null) {
			FileUploadHandler fuh3 = mc.uploadImage(imageView_ans_3.getImage(), QA_now.answer_img_path_3, QA_now.answer_img_name_3);
			fuh3.start();
		}
		if(imageView_ans_4.getImage()!=null) {
			FileUploadHandler fuh3 = mc.uploadImage(imageView_ans_4.getImage(), QA_now.answer_img_path_4, QA_now.answer_img_name_4);
			fuh3.start();
		}
		if(imageView_ans_5.getImage()!=null) {
			FileUploadHandler fuh3 = mc.uploadImage(imageView_ans_5.getImage(), QA_now.answer_img_path_5, QA_now.answer_img_name_5);
			fuh3.start();
		}


		JOptionPane.showMessageDialog(this, "上传成功！\n上传后获得数据库主键为"+id+"\n 答案图片数量："+num);
		imageView.setImage(null);
		imageView_ans.setImage(null);
		imageView_ans_1.setImage(null);
		imageView_ans_2.setImage(null);
		imageView_ans_3.setImage(null);
		imageView_ans_4.setImage(null);
		imageView_ans_5.setImage(null);
		ans_num.setText("当前是第1个答案图片");
		screenshot_num=0;
		jta.setText("题目截图内容");
		jta_ans.setText("答案截图内容");
	}
	
	public void startCapture()
	{
		this.setVisible(false);		
		new CaptureThread().start();
	}
	
	public void showAnsImage()
	{
		if(screenshot_num==1) {
			imageView_ans.setVisible(true);
			imageView_ans_1.setVisible(false);
			imageView_ans_2.setVisible(false);
			imageView_ans_3.setVisible(false);
			imageView_ans_4.setVisible(false);
			imageView_ans_5.setVisible(false);
		}
		else if(screenshot_num==2) {
			imageView_ans.setVisible(false);
			imageView_ans_1.setVisible(true);
			imageView_ans_2.setVisible(false);
			imageView_ans_3.setVisible(false);
			imageView_ans_4.setVisible(false);
			imageView_ans_5.setVisible(false);
		}
		else if(screenshot_num==3) {
			imageView_ans.setVisible(false);
			imageView_ans_1.setVisible(false);
			imageView_ans_2.setVisible(true);
			imageView_ans_3.setVisible(false);
			imageView_ans_4.setVisible(false);
			imageView_ans_5.setVisible(false);
		}
		else if(screenshot_num==4) {
			imageView_ans.setVisible(false);
			imageView_ans_1.setVisible(false);
			imageView_ans_2.setVisible(false);
			imageView_ans_3.setVisible(true);
			imageView_ans_4.setVisible(false);
			imageView_ans_5.setVisible(false);
		}
		else if(screenshot_num==5) {
			imageView_ans.setVisible(false);
			imageView_ans_1.setVisible(false);
			imageView_ans_2.setVisible(false);
			imageView_ans_3.setVisible(false);
			imageView_ans_4.setVisible(true);
			imageView_ans_5.setVisible(false);
		}
		else if(screenshot_num==5) {
			imageView_ans.setVisible(false);
			imageView_ans_1.setVisible(false);
			imageView_ans_2.setVisible(false);
			imageView_ans_3.setVisible(false);
			imageView_ans_4.setVisible(false);
			imageView_ans_5.setVisible(true);
		}
		
	}
	
	public void showImage(BufferedImage screenImg, Rectangle area)
	{
		BufferedImage img = screenImg.getSubimage(area.x, area.y,area.width,area.height);
		if(screenshot_num==0) {
			imageView.setImage(img);
		}
		else if(screenshot_num==1) {
			imageView_ans.setImage(img);
			showAnsImage();
		}
		else if(screenshot_num==2) {
			imageView_ans_1.setImage(img);
			showAnsImage();
		}
		else if(screenshot_num==3) {
			imageView_ans_2.setImage(img);
			showAnsImage();
		}
		else if(screenshot_num==4) {
			imageView_ans_3.setImage(img);
			showAnsImage();
		}
		else if(screenshot_num==5) {
			imageView_ans_4.setImage(img);
			showAnsImage();
		}
		else if(screenshot_num==6) {
			imageView_ans_5.setImage(img);
			showAnsImage();
		}
	}
	
	public void showOCRtransferResult(Image image) {
		if(screenshot_num==0 ||screenshot_num==1) {
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
		if(img == null) {System.out.println("当前窗口里没有图片，请截图");;return;}
		
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
