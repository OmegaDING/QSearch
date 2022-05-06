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
		// ע��MySQL���� 
		Class.forName("com.mysql.jdbc.Driver");

		// ����MySQL������
		String username= "root";
		String password = "a1b2c3";
		String connectionUrl = "jdbc:mysql://127.0.0.1:3306/que_ans?useUnicode=true&characterEncoding=UTF-8";
		
		Connection conn = DriverManager.getConnection(connectionUrl, username, password);
		System.out.println("���ӳɹ�!");
		
		///////////////////////////////////////////////
		
		// ���ݿ��ѯ: Statement��� ,  ResultSet�����
		String sql = "UPDATE `que_ans`.`user` SET `today_ser_count` = '0' ; ";
		Statement stmt = conn.createStatement(); 
		stmt.execute(sql);
		System.out.println("�Ѿ�������������������");
		

	     
	    //////////////////////////////////////////////
	    conn.close();
		System.out.println("�ر�����!");
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
        String user = "root"; //���ݿ���û���
        String password = "a1b2c3";//���ݿ������
        String database = "que_ans";//Ҫ���ݵ����ݿ���
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
          System.out.println("�Ѿ����浽 " + filepath + " ��");
        } catch (IOException e) {
         e.printStackTrace();
        }
        return filepath;
       }
}
