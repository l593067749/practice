package com.liao.practice.test01.mutithread;

import java.net.SocketException;

/**
 * 线程组和线程池是两个不同的概念，他们的作用完全不同，前者是为了方便线程的管理，后者是为了管理线程的生命周期，复用线程，减少创建销毁线程的开销。
 */
public class ThreadGroupDemo {
    public static void main(String[] args) {
		ThreadGroup spiderGroup = new SpiderThreadGroup("spiderGroup");
		//可以统一设定线程是否为守护线程
		spiderGroup.setDaemon(true);
		
		//可以设置线程组内的最大优先级
		spiderGroup.setMaxPriority(Thread.NORM_PRIORITY);
		
		//初始化线程
		Thread spiderThread = new Thread(spiderGroup, new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				throw new RuntimeException(new SocketException());
			}
			
		});
		//初始化线程
		Thread spiderThread2 = new Thread(spiderGroup, new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				throw new RuntimeException(new SocketException());
			}

		});
		
		//启动线程
		spiderThread.start();
		spiderThread2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread[] all = new Thread[spiderGroup.activeCount()];
		spiderGroup.enumerate(all);
		for (Thread t : all) {
			System.out.println(t.getName());
		}
	}


	/**
	 * 此类从ThreadGroup类继承重写了其uncaughtException方法，对于SocketException进行了特殊处理
	 * @author outofmemory.cn
	 *
	 */
	static class SpiderThreadGroup extends ThreadGroup {
		public SpiderThreadGroup(String name) {
			super(name);
		}

		public void uncaughtException(Thread t, Throwable e) {
			if (e.getCause() instanceof SocketException) {
				System.out.println("socket exception should be process");
			} else {
				super.uncaughtException(t, e);
			}
		}
	}
}