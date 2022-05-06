package ocr_bd;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

/**
 * 
 * @author DingKunYuan
 *
 */

public class YuanOCR {

	 //设置APPID/AK/SK
    public static final String APP_ID = "24098151";
    public static final String API_KEY = "2XGWsywNYxb5howRkAPuQBNR";
    public static final String SECRET_KEY = "7R8fRTTcAXxApjVMil4S23uGqNz0rM5Q";

    public  JSONObject getTransfer(Image ti) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.basicGeneral(imageToBytes(ti), new HashMap<String, String>());
       // System.out.println("识别结果为："+res.toString(2));
        return res;
    }
    
    private static byte[] imageToBytes(Image ti) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write((RenderedImage) ti, "png", out);
        } catch (IOException e) {
            //log.error(e.getMessage());
        }
        return out.toByteArray();
    }
    
//    //测试用
//    public static void main(String[] args) {
//        YuanOCR yo = new YuanOCR();
//        Image ti = null;
//		try {
//			ti = yo.imageFromResource("/test.jpg");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        JSONObject js = yo.getTransfer(ti);
//        System.out.println("识别结果为："+js.toString(2));
//    }
//
//
//	private Image imageFromResource(String imagePath) throws Exception
//	{
//		URL imageUrl = this.getClass().getResource(imagePath);
//		BufferedImage image = ImageIO.read(imageUrl);
//		return image;
//	}
	
}

