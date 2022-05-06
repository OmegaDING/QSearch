package swingClasses;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class AnyWhereLayout implements LayoutManager2
{
	private static class Item
	{
		Component comp;
		Margin margin;
	}
	
	List<Item> itemList = new ArrayList<>();
	
	@Override
	public void addLayoutComponent(String name, Component comp)
	{	
		Item item = new Item();
		item.comp = comp;
		item.margin = new Margin(-1);
		itemList.add( item );
	}
	
	@Override
	public void addLayoutComponent(Component comp, Object constraints)
	{		
		Item item = new Item();
		item.comp = comp;
		if( constraints instanceof Margin)
		{
			item.margin = (Margin)constraints;
		}
		else
		{
			System.out.println("AfLayerLayout: 甯冨眬鍙傛暟椤讳负AfMargin绫诲瀷!");
			item.margin = new Margin(-1); // 灞呬腑鏄剧ず
		}
		
		itemList.add( item );
	}
	
	@Override
	public void removeLayoutComponent(Component comp)
	{
		Iterator<Item> iter = itemList.iterator();
		while(iter.hasNext())
		{
			Item item = iter.next();
			if(comp == item.comp)
			{
				iter.remove();
				break;
			}
		}
	}
	
	@Override
	public void layoutContainer(Container parent)
	{	
		Insets insets = parent.getInsets();
		if(insets == null)
			insets = new Insets(0,0,0,0);
		int x = insets.left;
		int y = insets.top;
		int w = parent.getWidth() - insets.left - insets.right;
		int h = parent.getHeight() - insets.top - insets.bottom;
		
		for(int i=0; i<itemList.size(); i++)
		{
			Item item = itemList.get(i);
			Component comp = item.comp;
			Margin margin = item.margin;
			if(! comp.isVisible()) continue;
			
			Dimension preferedSize = comp.getPreferredSize();						
			int[] hh = getPosition(margin.left, margin.right, w, preferedSize.width);
			int[] vv = getPosition(margin.top, margin.bottom, h, preferedSize.height);
			
			Rectangle bounds = new Rectangle(hh[0]+x, vv[0]+y, hh[1]-hh[0], vv[1]-vv[0]);
			comp.setBounds( bounds );
			//System.out.println("** AfAnyWhere: " + i + ": " + bounds);
		}
	}

	private int[] getPosition(int startMargin, int endMargin, int size, int preferedSize)
	{
		int[] values = {0,0};
		
		if(startMargin <0 && endMargin <0)
		{
			// 灞呬腑鏄剧ず
			values[0] = (size-preferedSize)/2;
			values[1] = values[0] + preferedSize;
		}
		else if(startMargin<0)
		{
			// 闈犲彸 鎴栬�� 闈犱笅
			values[0] = size - endMargin - preferedSize;
			values[1] = size - endMargin;
		}
		else if(endMargin <0)
		{
			// 闈犲乏 鎴栬�� 闈犱笂
			values[0] = startMargin;
			values[1] = startMargin + preferedSize;
		}
		else
		{
			// 涓ょ瀵归綈
			values[0] = startMargin;
			values[1] = size - endMargin;
		}
		
		return values;
	}
	
	
	//////////////////////////////////
	
	@Override
	public float getLayoutAlignmentX(Container target)
	{
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target)
	{
		return 0;
	}

	@Override
	public void invalidateLayout(Container target)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return new Dimension(30,30);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return new Dimension(30,30);
	}
	
	@Override
	public Dimension maximumLayoutSize(Container target)
	{
		return new Dimension(9999,9999);
	}
	
}
