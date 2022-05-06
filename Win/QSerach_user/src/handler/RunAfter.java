package handler;

import netService.MyClient;

public class RunAfter extends Thread{
	Thread t_running;
	Thread T_runAfter;
	public RunAfter(Thread t_running ,Thread T_runAfter) {
		this.t_running=t_running;
		this.T_runAfter=T_runAfter;
	}
	@Override
	public void run() {
		while(true) {
			if(!t_running.isAlive()) {
				T_runAfter.start();
				//System.out.println("开始运行");
				break;
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println("等待上一个线程结束出错，下一个线程运行失败");
				e.printStackTrace();
			}
		}
	}
	
	
	
}
