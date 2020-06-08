package com.kdj.mlink.ezup3.shop.common;

import java.util.LinkedList;

public class ThreadPoolQueue {

	/* item�� ������ ť ���� */
	private LinkedList<Object> queue = new LinkedList<Object>();

	/* ť �ִ� ������ ���� */
	private int MAX_QUEUE_SIZE = 5;

	/* ����׸� ���� �ܼ� ��� ���� */
	private boolean DEBUG = false;

	public ThreadPoolQueue(int MAX_QUEUE_SIZE) {
		this.MAX_QUEUE_SIZE = MAX_QUEUE_SIZE;
	}

	/* ������ ���� */
	public synchronized void enqueue(Object item) throws InterruptedException {
		// ���� ť�� �ִ� ������� ����
		while( queue.size() == MAX_QUEUE_SIZE ){
			console("enqueue waiting...");
			wait();
		}

		// ���� ť�� ��������� �Ͼ��!! ����!!
		if( queue.size() == 0 ){
			console("enqueue notifyall...");
			notifyAll();
		}

		console("enqueue adding...");
		queue.add(item);
	}

	/* ������ ��ȯ */
	public synchronized Object dequeue() throws InterruptedException {
		// ��ȯ�� �������� ������ ����
		while( queue.size() == 0 ){
			console("dequeue waiting...");
			wait();
		}

		// ��ȯ�� �������� ����á��? �Ͼ�� ����!!
		if( queue.size() == MAX_QUEUE_SIZE ){
			console("dequeue notifyall...");
			notifyAll();
		}

		console("dequeue removing...");
		return queue.remove(0);
	}

	// ����� ����
	public void toggleDebug(boolean flag){
		this.DEBUG = flag;
	}

	public void console(String msg){
		if( DEBUG )
			System.out.println(msg);
	}
}