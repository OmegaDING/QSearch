package swingClasses;

public class Margin {

	// 按照 Swing 的排列顺序，应该是 上 左 下 右
		public int top, left, bottom, right;
		
		// 9个常见的位置
		public static final Margin FULL = new Margin(0, 0, 0,0);
		public static final Margin TOP_LEFT = new Margin(0, 0, -1, -1);
		public static final Margin TOP_CENTER = new Margin(0, -1, -1, -1);
		public static final Margin TOP_RIGHT = new Margin(0, -1, -1, 0);
		public static final Margin CENTER_LEFT = new Margin(-1, 0, -1, -1);
		public static final Margin CENTER = new Margin(-1, -1, -1, -1);
		public static final Margin CENTER_RIGHT = new Margin(-1, -1, -1, 0);
		public static final Margin BOTTOM_LEFT = new Margin(-1, 0, 0, -1);
		public static final Margin BOTTOM_CENTER = new Margin(-1, -1, 0, -1);
		public static final Margin BOTTOM_RIGHT = new Margin(-1, -1, 0, 0);
		
		public Margin()
		{		
		}
		
		public Margin(int value)
		{
			top = left = bottom = right;
		}
		
		public Margin(int hValue, int vValue)
		{
			left = right = hValue;
			top = bottom = vValue;
		}
		
		public Margin(int top, int left, int bottom, int right)
		{
			this.top = top;
			this.left = left;
			this.bottom = bottom;
			this.right = right;
		}
}
