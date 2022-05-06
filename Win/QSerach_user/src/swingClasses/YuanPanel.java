package swingClasses;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;


/** 
 * YuanPanel: 用于快速布局的一个容器
 */
public class YuanPanel extends JPanel{

	private Color bgColor;

	public YuanPanel()
	{
		this.setOpaque(false); // 默认背景透明 
	}
		
	///////////// padding /////////////
	public YuanPanel padding( int size)
	{
		return padding(size, size, size, size);
	}
	
	public YuanPanel padding( int top, int left, int bottom, int right)
	{
		YuanBorder.addPadding(this, top,left, bottom,right);
		return this;
	}

	////////////// margin ////////////////
	public YuanPanel margin( int size)
	{
		return margin(size, size, size, size);
	}
	
	public YuanPanel margin( int top, int left, int bottom, int right)
	{
		YuanBorder.addMargin(this, top,left, bottom,right);
		return this;
	}

	// 附加一个外边框
	public void addOuterBorder(JComponent c, Border outerBorder)
	{
		YuanBorder.addOuterBorder(this, outerBorder);
	}

	// 附加一个内边框
	public void addInnerBorder(JComponent c, Border innerBorder)
	{
		YuanBorder.addInnerBorder(this, innerBorder);
	}
	
	//////////// perferred size ///////////
	public YuanPanel preferredSize(int w, int h)
	{
		this.setPreferredSize(new Dimension(w, h));
		return this;
	}
	
	public YuanPanel preferredWidth(int w)
	{
		Dimension size = this.getPreferredSize();
		if(size == null)
			size = new Dimension(0,0);
		size.width = w;
		this.setPreferredSize( size);
		return this;
	}
	
	public YuanPanel preferredHeight(int h)
	{
		Dimension size = this.getPreferredSize();
		if(size == null)
			size = new Dimension(0,0);
		size.height = h;
		this.setPreferredSize( size);
		return this;
	}
	
	public void setBgColor(Color color)
	{
		this.bgColor = color;
		this.repaint();
	}
	
	// 背景色绘制 
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// 绘制背景色
		if( bgColor != null)
		{
			Graphics2D g2d = (Graphics2D)g;	
			g2d.setPaint(bgColor);
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}	
	}
	
}
