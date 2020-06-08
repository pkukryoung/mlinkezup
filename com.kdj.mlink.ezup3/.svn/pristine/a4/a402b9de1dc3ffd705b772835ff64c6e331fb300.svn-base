package com.kdj.mlink.ezup3.shop.common;

public class ThreadPoolRunnable implements Runnable {

	private int id; // Runnable ID
	private ThreadPoolQueue queue;
	private volatile boolean running = true;
	private boolean DEBUG = false;

	// Runnable 초기화
	public ThreadPoolRunnable(int THREAD_ID, ThreadPoolQueue queue) {
		this.id = THREAD_ID;
		this.queue = queue;
		console("ThreadPoolRunnable["+id+"] is created.");
	}

	// Runnable 실행
	@Override
	public void run() {
		while( running ){
			try{
				Thread.sleep(10);
				console("ThreadPoolRunnable["+id+"] is working.");
				Runnable r = (Runnable) queue.dequeue();
				
				r.run();
			}catch( InterruptedException e ){
				stop(); // 인터럽트 예외 발생시 해당 Runnable 정지
			}
		}
		console("ThreadPoolRunnable["+id+"] is dead.");
	}

	// Runnable 정지
	public void stop() {
		running = false;
		console("ThreadPoolRunnable["+id+"] is stopped.");
	}

	// 현재 Runnable 디버깅 설정
	public void toggleDebug(boolean flag){
		this.DEBUG = flag;
	}

	public void console(String msg){
		if( DEBUG )
			System.out.println(msg);
	}
}