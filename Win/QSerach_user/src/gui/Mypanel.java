package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.JPanel;
/**
 * 
 * @author DingKunYuan
 *
 */
public class Mypanel extends JPanel{

	private int period=100;
	private int range =50;
    private int step = 1;
    Image image ;
    public Mypanel() {
    	try {
			image = imageFromResource("/images/picture_1.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	protected void paintComponent(Graphics g) {
		// 本控件的宽度和高度
		int width = this.getWidth();
		int height = this.getHeight();
		
		g.clearRect(0, 0, width, height);

		int imgW = image.getWidth(null);
		int imgH = image.getHeight(null);
		int fitW = width;
		int fitH = width * imgH / imgW;
		if( fitH > height )
		{
			// 若图片高度fitH超出宽度高度，就以窗口高度为图片高度，按比例绘制图片
			fitH = height;
			fitW = height * imgW / imgH;
		}
		
		// 绘制图片
		int x = (width - fitW ) /2;
		int y = (height - fitH ) /2;
		g.drawImage(image, x, y, fitW, fitH, null);
		g.setColor(new Color(192,192,192,200));
		g.fillRect(0, 0, width, height);
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
