package com.justudin.testbigdata;

import java.util.concurrent.atomic.AtomicInteger;

public class threadData {
	private int threadNum;
	private int status;
	public static long n1 = 50000;
	public static long n2 = 100000;
	public static long n3 = 150000;
	public static long n4 = 200000;
	public static long n5 = 250000;
	public static int t1 = 1;
	public static int t2 = 5;
	public static int t3 = 10;
	
	private AtomicInteger num = new AtomicInteger();

    public int get() {
        return num.get();
    }
    
    public void decrease() {
        this.num.set(num.get()-1);
    }

    public void set(int value) {
        this.num.set(value);
    }

    public int addAndGet(int value) {
        return this.num.addAndGet(value);
    }
	
	public int getThreadNum() {
		return threadNum;
	}
	
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
	
	public void setDoneThreadNum() {
		this.threadNum = getThreadNum()-1;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	

}
