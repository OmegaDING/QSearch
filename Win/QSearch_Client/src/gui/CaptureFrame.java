package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author DingKunYuan
 *
 */
public class CaptureFrame extends JFrame
{
	MainFrame mainFrame;
	BufferedImage image ;
	
	public CaptureFrame(MainFrame mainFrame, BufferedImage image)
	{		
		// 设为全屏显示
		this.setUndecorated(true); 	
		
		// 某些电脑不支持这种全屏方式绘制
		//GraphicsDevice gd = getGraphicsConfiguration().getDevice();
		//gd.setFullScreenWindow(this);		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize( screenSize );
		
		//
		this.mainFrame = mainFrame;
		this.image = image;
		CapturePanel root = new CapturePanel();
		this.setContentPane(root);
			
	}
	
	private class CapturePanel extends JPanel
	{
		boolean pressed = false;
		Point startPos;
		Point cursorPos ;
		
		public CapturePanel()
		{
			MyMouseAdapter mouseAdapter = new MyMouseAdapter();
			this.addMouseListener(mouseAdapter);
			this.addMouseMotionListener(mouseAdapter);
		}
		
		private Rectangle getArea(Point p1, Point p2)
		{
			int x = p1.x;
			int y = p1.y;
			int w = p2.x - p1.x;
			int h = p2.y - p1.y;
			if(w < 0)
			{
				x = p2.x;
				w = -w;
			}
			if(h <0)
			{
				y = p2.y;
				h = -h;
			}
			return new Rectangle(x,y,w,h);
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			int width = getWidth();
			int height = getHeight();
			Graphics2D g2d = (Graphics2D)g;
			
			// 绘制图片
			if(image != null)
			{
				g2d.drawImage(image, 0, 0, null);
			}
			
			// 参考线
			if(cursorPos != null)
			{
				int cx = (int)cursorPos.getX();
				int cy = (int)cursorPos.getY();
				g2d.setColor(Color.GRAY);
				g2d.drawLine(0,cy, width, cy);
				g2d.drawLine(cx, 0, cx, height);
				
				// 中间画一个小圆圈
				Ellipse2D circle = new Ellipse2D.Double(cx-2,cy-2,4,4);
				g2d.setPaint(Color.WHITE);
				g2d.fill(circle);
				g2d.setPaint(Color.gray);
				g2d.draw(circle);
			}			
			
			if(pressed)
			{
				Rectangle area = getArea(startPos, cursorPos);
//				System.out.println(area);

				g2d.setPaint(new Color(100,100,100,100));	
				g2d.fill(area);
			}
		}
		
		private class MyMouseAdapter extends MouseAdapter
		{
			long lastPaint = 0;
			
			@Override
			public void mousePressed(MouseEvent e)
			{				
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					//System.out.println("mousePressed");
					pressed = true;
					cursorPos = startPos = e.getPoint();
					
//					CaptureFrame.this.setVisible(false);					
//					//Rectangle area = getArea(startPos,cursorPos);
//					mainFrame.setVisible(true);	
//					//mainFrame.showImage(image, area);
//					System.out.println("exit draw...");
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				//System.out.println("mouseReleased");
		
				if(pressed)
				{
					pressed = false;
					cursorPos = e.getPoint();
					
					// 取得图片
					CaptureFrame.this.setVisible(false);					
					Rectangle area = getArea(startPos,cursorPos);
					mainFrame.setVisible(true);	
					mainFrame.showImage(image, area);
					mainFrame.showOCRtransferResult(image.getSubimage(area.x, area.y, area.width, area.height));

				}										
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{				
				if(pressed)
				{
					long now = System.currentTimeMillis();
					// if(now - lastPaint > 100)
					{
						lastPaint = now;
						cursorPos = e.getPoint();
						repaint();
					}
				}
			}

			@Override
			public void mouseMoved(MouseEvent e)
			{
				//System.out.println("mouseMoved");	
				
				long now = System.currentTimeMillis();
				// if(now - lastPaint > 100)
				{
					//System.out.println("move....");
					cursorPos = e.getPoint();
					repaint();
				}
			}
			
		}
		
	}	
}
