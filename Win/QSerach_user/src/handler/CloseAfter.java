package handler;

import netService.MyClient;

public class CloseAfter extends Thread{
	Thread fuh;
	MyClient mc;
	public CloseAfter(Thread fuh,MyClient mc) {
		this.fuh=fuh;
		this.mc=mc;
	}
	@Override
	public void run() {
		while(true) {
			if(!fuh.isAlive()) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					System.out.println("上一线程结束，等待300毫秒关闭sock失败");
					e.printStackTrace();
				}
				mc.close();

				System.out.println("关闭连接");
				break;
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println("等待线程结束出错");
				e.printStackTrace();
			}
		}
	}
	
	
	
}
