package dataBase;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author DingKunYuan
 *
 */
public class Refresh {


	public static void testQuery() throws Exception
	{
		// 注册MySQL驱动 
		Class.forName("com.mysql.jdbc.Driver");

		// 连接MySQL服务器
		String username= "root";
		String password = "a1b2c3";
		String connectionUrl = "jdbc:mysql://127.0.0.1:3306/que_ans?useUnicode=true&characterEncoding=UTF-8";
		
		Connection conn = DriverManager.getConnection(connectionUrl, username, password);
		System.out.println("连接成功!");
		
		///////////////////////////////////////////////
		
		// 数据库查询: Statement语句 ,  ResultSet结果集
		String sql = "UPDATE `que_ans`.`user` SET `today_ser_count` = '0' ; ";
		Statement stmt = conn.createStatement(); 
		stmt.execute(sql);
		System.out.println("已经将当日搜索次数清零");
		

	     
	    //////////////////////////////////////////////
	    conn.close();
		System.out.println("关闭连接!");
	}

	
	public static void main(String[] args)
	{
		try
		{
			testQuery();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static String backup() throws IOException{
        String user = "root"; //数据库的用户名
        String password = "a1b2c3";//数据库的密码
        String database = "que_ans";//要备份的数据库名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String filepath = "d:\\"+sdf.format(date)+".sql";
        File file = new File("d:\\",sdf.format(date)+".sql");
        if(!file.exists()){
         file.createNewFile();  
        }
        String stmt1 = "mysqldump " + database +" -h 127.0.0.1 "+ " -u " + user + " -p" +
        password + " --default-character-set=UTF-8 --result-file=" + filepath;
        try {
          Runtime.getRuntime().exec(stmt1);
          System.out.println("已经保存到 " + filepath + " 中");
        } catch (IOException e) {
         e.printStackTrace();
        }
        return filepath;
       }
}
