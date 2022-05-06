package dataBase_mySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import dataClasses.QA_class;

/**
 * 
 * @author DingKunYuan
 *
 */
public class MySQL {

	// 一个DataSource指代一个数据源, 内部有一个连接池, 自动读取 c3p0-config.xml的配置
	static ComboPooledDataSource pool;
	
	public MySQL() {
		pool = new ComboPooledDataSource();
		
//		// 手工指定连接参数
//		pool.setDriverClass("com.mysql.jdbc.Driver");
//		pool.setJdbcUrl("jdbc:mysql://127.0.0.1/af_school?useUnicode=true&characterEncoding=UTF-8");
//		pool.setUser("root");
//		pool.setPassword("a1b2c3");
//		
//		// 手工指定性能参数
//		pool.setInitialPoolSize(2);
//		pool.setMinPoolSize(2);
//		pool.setMaxPoolSize(10);
//		pool.setMaxIdleTime(30);
//		pool.setIdleConnectionTestPeriod(300);
		
	}
	
	public Connection getConn() throws SQLException {
		Connection conn = pool.getConnection();
		return conn;
	}
	
	//执行sql返回结果集
	public ResultSet exeQuery(String sql) throws SQLException {
		//进行sql转义
		sql= this.escape(sql);
		Connection conn = pool.getConnection();
		Statement stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);	
		//conn.close();
		return rs;
	}
	
	//执行sql返回主键
	public int exeSQL(String sql) throws SQLException {
		//进行sql转义
		//sql= this.escape(sql);
		
		Connection conn = this.getConn();
		//System.out.println("数据库连接成功!");
		Statement stmt = conn.createStatement(); 
		//System.out.println(sql);
		stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
		
		// 取得自动生成的主键的值
		ResultSet rs = stmt.getGeneratedKeys(); 		
		while(rs.next())
		{
			int id = rs.getInt(1);
			conn.close();
			return id;
			       
		}    
		//////////////////////////////////////////////
		conn.close();
		
		return -1;
	}
	
	public void closePool() {
		pool.close(); 
	}

	public static void testQuery() throws Exception
	
	{
		// 一个Connection代表一次访问
		Connection conn = pool.getConnection();
		
		// 查询操作		
		Statement stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery("SELECT * FROM student");		
	    while(rs.next())
	    {
	    	// 取出这一行记录
	    	int id = rs.getInt("id");
	    	String name = rs.getString("name");
	    	String phone = rs.getString("phone"); // 可能为null
	    	Date birthday = rs.getDate("birthday");
	    	System.out.println(id + "\t" + name + "\t" + phone );	           
	    }    
		
		conn.close(); // 连接放回池子
		
		pool.close(); // 数据源如果关闭，相应的池子也就销毁了
	}
	
	//插入问题到数据库
	public int insertQue(QA_class QA) throws SQLException {
		//有可能出错的字段
		String question_string =this.escape(QA.question_string);
		String answer_string = this.escape(QA.answer_string);
		
		String sql="INSERT INTO `que_ans`.`que_ans` (`question_img_path`, `question_img_path_1`, `question_img_name`, `question_img_name_1`, `question_string`, `question_num`, `answer_img_path_0`, `answer_img_path_1`, `answer_img_path_2`, `answer_img_path_3`, `answer_img_path_4`, `answer_img_path_5`, `answer_img_name_0`, `answer_img_name_1`, `answer_img_name_2`, `answer_img_name_3`, `answer_img_name_4`, `answer_img_name_5`, `answer_string`, `knowledge_point`, `type_in_time`, `typer_name`, `typer_id`, `subject`, `difficulty`, `is_homework`) VALUES"
				 + " ('"+QA.question_img_path+"', '"+QA.question_img_path_1+"', '"+QA.question_img_name+"', '"+QA.question_img_name_1+"', '"+question_string+"', '"+QA.question_num+"', '"+QA.answer_img_path_0+"','"+QA.answer_img_path_1+"', '"+QA.answer_img_path_2+"', '"+QA.answer_img_path_3+"', '"+QA.answer_img_path_4+"', '"+QA.answer_img_path_5+"', '"+QA.answer_img_name_0+"', '"+QA.answer_img_name_1+"', '"+QA.answer_img_name_2+"', '"+QA.answer_img_name_3+"', '"+QA.answer_img_name_4+"', '"+QA.answer_img_name_5+"', '"+answer_string+"', '"+QA.knowledge_point+"', '"+QA.type_in_time+"', '"+QA.typer_name+"', '"+QA.typer_id+"', '"+QA.subject+"', '"+QA.difficulty+"', '"+QA.is_homework+"'); ";
		
		int id = exeSQL(sql);
		
		//将问题图片文件名插入数据库
		String sql_name_que ="UPDATE `que_ans`.`que_ans` SET `question_img_name` = '"+id+"_que.jpg"+"' WHERE `question_id` = '"+id+"';";
		exeSQL(sql_name_que);
		String sql_name_que_1 ="UPDATE `que_ans`.`que_ans` SET `question_img_name_1` = '"+id+"_1_que.jpg"+"' WHERE `question_id` = '"+id+"';";
		exeSQL(sql_name_que_1);
		
		//将答案图片文件名插入数据库
		String sql_name_ans_0 ="UPDATE `que_ans`.`que_ans` SET `answer_img_name_0` = '"+id+"_0_ans.jpg"+"' WHERE `question_id` = '"+id+"';";
		exeSQL(sql_name_ans_0);
		String sql_name_ans_1 ="UPDATE `que_ans`.`que_ans` SET `answer_img_name_1` = '"+id+"_1_ans.jpg"+"' WHERE `question_id` = '"+id+"';";
		exeSQL(sql_name_ans_1);
		String sql_name_ans_2 ="UPDATE `que_ans`.`que_ans` SET `answer_img_name_2` = '"+id+"_2_ans.jpg"+"' WHERE `question_id` = '"+id+"';";
		exeSQL(sql_name_ans_2);
		String sql_name_ans_3 ="UPDATE `que_ans`.`que_ans` SET `answer_img_name_3` = '"+id+"_3_ans.jpg"+"' WHERE `question_id` = '"+id+"';";
		exeSQL(sql_name_ans_3);
		String sql_name_ans_4 ="UPDATE `que_ans`.`que_ans` SET `answer_img_name_4` = '"+id+"_4_ans.jpg"+"' WHERE `question_id` = '"+id+"';";
		exeSQL(sql_name_ans_4);
		String sql_name_ans_5 ="UPDATE `que_ans`.`que_ans` SET `answer_img_name_5` = '"+id+"_5_ans.jpg"+"' WHERE `question_id` = '"+id+"';";
		exeSQL(sql_name_ans_5);
		//System.out.println(sql);
		return id;
	}
	
	
	
	
	
	
	//根据id搜题
	public QA_class QSerach_id(int id) throws SQLException {
		String sql ="SELECT * FROM `que_ans`.`que_ans` WHERE question_id = "+id;
		
		
		Connection conn = pool.getConnection();
		Statement stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);	
		
		QA_class QA = new QA_class();
		QA.question_id=0;
		while(rs.next())
		{
			QA=new QA_class(rs.getInt("question_id"), rs.getString("question_img_path"), rs.getString("question_img_name"), rs.getString("question_img_path_1"), rs.getString("question_img_name_1"),
					rs.getString("question_string"), rs.getString("answer_img_path_0"), rs.getString("answer_img_name_0"), rs.getString("answer_img_path_1"), rs.getString("answer_img_name_1"),
					rs.getString("answer_img_path_2"), rs.getString("answer_img_name_2"), rs.getString("answer_img_path_3"), rs.getString("answer_img_name_3"), rs.getString("answer_img_path_4"),
					rs.getString("answer_img_name_4"), rs.getString("answer_img_path_5"), rs.getString("answer_img_name_5"), rs.getInt("question_num"), rs.getString("answer_string"), 
					rs.getString("knowledge_point"), rs.getString("type_in_time"), rs.getString("typer_name"), rs.getInt("typer_id"), rs.getString("subject"), rs.getInt("difficulty"), 
					rs.getInt("is_homework"));
		}    
		
		
		conn.close();
		
		return QA;
	}
	
	//keyword搜题
	public ResultSet QSerach_keyword(String keyword, int user) throws SQLException {
		//防止sql注入
		String key = this.escape(keyword);
		String sql ="SELECT * FROM `que_ans`.`que_ans` WHERE question_string LIKE '"+key+"'";
		String update ="update `que_ans`.`user` set today_ser_count=today_ser_count+1 where id = "+user;
		
		Connection conn = pool.getConnection();
		Statement stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);	
		Statement stmt2 = conn.createStatement(); 
		stmt2.execute(update);
//		QA_class QA = null;
//		while(rs.next())
//		{
//			QA=new QA_class(rs.getInt("question_id"), rs.getString("question_img_path"), rs.getString("question_img_name"), rs.getString("question_string"), rs.getString("answer_img_path"), rs.getString("answer_img_name"), rs.getString("answer_string"), rs.getString("knowledge_point"), rs.getString("knowledge_point"), rs.getString("knowledge_point"), rs.getInt("typer_id"), rs.getString("subject"), rs.getInt("difficulty"), rs.getInt("is_homework"));
//		}    
		
//		
//		conn.close();
		
		return rs;
	}
	
	//获取科目
	public ArrayList<String> get_subject() throws SQLException{
		ArrayList<String> name=new ArrayList<String>();
		String sql ="SELECT * FROM `que_ans`.`subject`";
		Connection conn = pool.getConnection();
		Statement stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);	
		while(rs.next())
		{
			name.add(rs.getString("subject"));
		} 
		
		return name;
	}

	//防止sql注入，进行sql转义
	public static String escape(String sql)
	{
		StringBuffer sb = new StringBuffer(sql.length() * 2);
		for(int i=0; i<sql.length(); i++)
		{
			char ch = sql.charAt(i);
			if(ch=='\'' || ch == '\\')
			{
				sb.append('\\')	;		
			}

			sb.append(ch);			
		}
		return sb.toString();
	}
}
