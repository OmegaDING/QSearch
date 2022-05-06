package dataClasses;

import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Date;

/**
 * 
 * @author DingKunYuan
 *
 */
public class QA_class {

	public int question_id=0;
	public String question_img_path = "empty";
	public String question_img_name = "empty";
	
	public String question_img_path_1 = "empty";
	public String question_img_name_1 = "empty";
	
	
	public String question_string = "empty";
	
	public String answer_img_path_0 = "empty";
	public String answer_img_name_0 = "empty";
	
	public String answer_img_path_1 = "empty";
	public String answer_img_name_1 = "empty";
	
	public String answer_img_path_2 = "empty";
	public String answer_img_name_2 = "empty";
	
	public String answer_img_path_3 = "empty";
	public String answer_img_name_3 = "empty";
	
	public String answer_img_path_4 = "empty";
	public String answer_img_name_4 = "empty";
	
	public String answer_img_path_5 = "empty";
	public String answer_img_name_5 = "empty";
	
	public int question_num =0;
	
	public String answer_string = "empty";
	
	public String knowledge_point = "empty";
	
	public String type_in_time = "empty";
	public String typer_name = "empty";
	public int typer_id=0;
	public String subject = "empty";
	public int difficulty=0;
	public int is_homework=0;
	
	public QA_class(){
		
	}
	
	
	
	public QA_class(int question_id, String question_img_path, String question_img_name, String question_img_path_1,
			String question_img_name_1, String question_string, String answer_img_path_0, String answer_img_name_0,
			String answer_img_path_1, String answer_img_name_1, String answer_img_path_2, String answer_img_name_2,
			String answer_img_path_3, String answer_img_name_3, String answer_img_path_4, String answer_img_name_4,
			String answer_img_path_5, String answer_img_name_5, int question_num, String answer_string,
			String knowledge_point, String type_in_time, String typer_name, int typer_id, String subject,
			int difficulty, int is_homework) {
		this.question_id = question_id;
		this.question_img_path = question_img_path;
		this.question_img_name = question_img_name;
		this.question_img_path_1 = question_img_path_1;
		this.question_img_name_1 = question_img_name_1;
		this.question_string = question_string;
		this.answer_img_path_0 = answer_img_path_0;
		this.answer_img_name_0 = answer_img_name_0;
		this.answer_img_path_1 = answer_img_path_1;
		this.answer_img_name_1 = answer_img_name_1;
		this.answer_img_path_2 = answer_img_path_2;
		this.answer_img_name_2 = answer_img_name_2;
		this.answer_img_path_3 = answer_img_path_3;
		this.answer_img_name_3 = answer_img_name_3;
		this.answer_img_path_4 = answer_img_path_4;
		this.answer_img_name_4 = answer_img_name_4;
		this.answer_img_path_5 = answer_img_path_5;
		this.answer_img_name_5 = answer_img_name_5;
		this.question_num = question_num;
		this.answer_string = answer_string;
		this.knowledge_point = knowledge_point;
		this.type_in_time = type_in_time;
		this.typer_name = typer_name;
		this.typer_id = typer_id;
		this.subject = subject;
		this.difficulty = difficulty;
		this.is_homework = is_homework;
	}



	



	public static int encode(QA_class question_ans, byte[] data) throws Exception
	{
		ByteBuffer bbuf = ByteBuffer.allocate(80000).wrap(data);
		
		// id
		bbuf.putInt(question_ans.question_id);
		
		// que img path 题目图片文件所在路径
		byte[] strbuf = question_ans.question_img_path.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf.length);
		bbuf.put (strbuf);
		
		// que img name 题目图片文件名
		byte[] que_name_buf = question_ans.question_img_name.getBytes("UTF-8");	
		bbuf.putShort( (short) que_name_buf.length);
		bbuf.put (que_name_buf);
		
		// que img path 题目图片文件所在路径
		byte[] strbuf_1 = question_ans.question_img_path_1.getBytes("UTF-8");	
		bbuf.putShort( (short) strbuf_1.length);
		bbuf.put (strbuf_1);
		
		// que img name 题目图片文件名
		byte[] que_name_buf_1 = question_ans.question_img_name_1.getBytes("UTF-8");	
		bbuf.putShort( (short) que_name_buf_1.length);
		bbuf.put (que_name_buf_1);
		
		// que string 题目文字内容
		byte[] que_string = question_ans.question_string.getBytes("UTF-8");	
		bbuf.putShort( (short) que_string.length);
		bbuf.put (que_string);
		
		// ans img path 答案图片文件所在路径
		byte[] ans_img_path_0 = question_ans.answer_img_path_0.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_path_0.length);
		bbuf.put (ans_img_path_0);
		
		// ans img name 答案图片文件名
		byte[] ans_img_name_0 = question_ans.answer_img_name_0.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_name_0.length);
		bbuf.put (ans_img_name_0);
		
		// ans img path 答案图片文件所在路径
		byte[] ans_img_path_1 = question_ans.answer_img_path_1.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_path_1.length);
		bbuf.put (ans_img_path_1);
				
		// ans img name 答案图片文件名
		byte[] ans_img_name_1 = question_ans.answer_img_name_1.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_name_1.length);
		bbuf.put (ans_img_name_1);
				
		// ans img path 答案图片文件所在路径
		byte[] ans_img_path_2 = question_ans.answer_img_path_2.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_path_2.length);
		bbuf.put (ans_img_path_2);
				
		// ans img name 答案图片文件名
		byte[] ans_img_name_2 = question_ans.answer_img_name_2.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_name_2.length);
		bbuf.put (ans_img_name_2);
				
		// ans img path 答案图片文件所在路径
		byte[] ans_img_path_3 = question_ans.answer_img_path_3.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_path_3.length);
		bbuf.put (ans_img_path_3);
				
		// ans img name 答案图片文件名
		byte[] ans_img_name_3 = question_ans.answer_img_name_3.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_name_3.length);
		bbuf.put (ans_img_name_3);
				
		// ans img path 答案图片文件所在路径
		byte[] ans_img_path_4 = question_ans.answer_img_path_4.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_path_4.length);
		bbuf.put (ans_img_path_4);
				
		// ans img name 答案图片文件名
		byte[] ans_img_name_4 = question_ans.answer_img_name_4.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_name_4.length);
		bbuf.put (ans_img_name_4);
				
		// ans img path 答案图片文件所在路径
		byte[] ans_img_path_5 = question_ans.answer_img_path_5.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_path_5.length);
		bbuf.put (ans_img_path_5);
				
		// ans img name 答案图片文件名
		byte[] ans_img_name_5 = question_ans.answer_img_name_5.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_img_name_5.length);
		bbuf.put (ans_img_name_5);
				
		bbuf.putInt(question_ans.question_num);
		
	
		
		// ans string 答案图片字符内容
		byte[] ans_string = question_ans.answer_string.getBytes("UTF-8");	
		bbuf.putShort( (short) ans_string.length);
		bbuf.put (ans_string);
		
		// knowledge_poing 关联知识点
		byte[] know_p = question_ans.knowledge_point.getBytes("UTF-8");	
		bbuf.putShort( (short) know_p.length);
		bbuf.put (know_p);
		
		// type in time 输入时间
		byte[] ty_t = question_ans.type_in_time.getBytes("UTF-8");	
		bbuf.putShort( (short) ty_t.length);
		bbuf.put (ty_t);
		
		// typer name
		byte[] ty_n = question_ans.typer_name.getBytes("UTF-8");	
		bbuf.putShort( (short) ty_n.length);
		bbuf.put (ty_n);
		
		// typer id
		bbuf.putInt (question_ans.typer_id);
		
		// subject
		byte[] tbj = question_ans.subject.getBytes("UTF-8");	
		bbuf.putShort( (short) tbj.length);
		bbuf.put (tbj);
		
		//difficulty难度
		bbuf.putInt (question_ans.difficulty);
		
		//是否为作业
		bbuf.putInt(question_ans.is_homework);
		
		return bbuf.position();//长度
	}
	
	public static QA_class decode(byte[] data, int n) throws Exception
	{
		ByteBuffer bbuf = ByteBuffer.allocate(80000).wrap(data);
		QA_class QA = new QA_class();
		
		// id
		QA.question_id = bbuf.getInt();
		
		// que img path 题目图片文件所在路径
		byte[] strbuf = new byte[1000];
		int strlen = bbuf.getShort();
		bbuf.get(strbuf, 0, strlen); // 读取N个字节
		QA.question_img_path = new String(strbuf, 0, strlen, "UTF-8");
				
		// que img name 题目图片文件名
		byte[] que_name_buf = new byte[1000];	
		int strlen1 = bbuf.getShort();
		bbuf.get(que_name_buf, 0, strlen1); // 读取N个字节
		QA.question_img_name = new String(que_name_buf, 0, strlen1, "UTF-8");
		
		//
		QA.question_img_path_1 = get_string(bbuf);
		QA.question_img_name_1 = get_string(bbuf);
				
		// que string 题目文字内容
		byte[] que_string = new byte[8000];	
		int strlen2 = bbuf.getShort();
		bbuf.get(que_string, 0, strlen2); // 读取N个字节
		QA.question_string = new String(que_string, 0, strlen2, "UTF-8");
		
				
		// ans img 
		QA.answer_img_path_0 = get_string(bbuf);
		QA.answer_img_name_0 = get_string(bbuf);
			
		QA.answer_img_path_1 = get_string(bbuf);
		QA.answer_img_name_1 = get_string(bbuf);
		
		QA.answer_img_path_2 = get_string(bbuf);
		QA.answer_img_name_2 = get_string(bbuf);
		
		QA.answer_img_path_3 = get_string(bbuf);
		QA.answer_img_name_3 = get_string(bbuf);
		
		QA.answer_img_path_4 = get_string(bbuf);
		QA.answer_img_name_4 = get_string(bbuf);
		
		QA.answer_img_path_5 = get_string(bbuf);
		QA.answer_img_name_5 = get_string(bbuf);
		
		QA.question_num=bbuf.getInt();
				
		// ans string 答案图片字符内容
		byte[] ans_string = new byte[8000];	
		int strlen5 = bbuf.getShort();
		bbuf.get(ans_string, 0, strlen5); // 读取N个字节
		QA.answer_string = new String(ans_string, 0, strlen5, "UTF-8");
			
		// knowledge_poing 关联知识点
		QA.knowledge_point = get_string(bbuf);
	
				
		// type in time 输入时间
		QA.type_in_time = get_string(bbuf);
			
				
		// typer name	
		QA.typer_name = get_string(bbuf);
			
		// typer id
		QA.typer_id = bbuf.getInt();
		
		//科目
		QA.subject = get_string(bbuf);

		QA.difficulty = bbuf.getInt();
		
		QA.is_homework = bbuf.getInt();		
		
		return QA;

	}
	private static String get_string(ByteBuffer bbuf) throws Exception {
		byte[] strbuf = new byte[1000];
		int strlen = bbuf.getShort();
		bbuf.get(strbuf, 0, strlen); // 读取N个字节
		return new String(strbuf, 0, strlen, "UTF-8");
	}
}
