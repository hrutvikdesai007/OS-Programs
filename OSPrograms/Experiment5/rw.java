import java.util.*;

class sema
{
	//private final int capacity;
	private final Object lock;
	private volatile int acquiredw;
	private volatile int acquiredr;
	private volatile int reader;
	public sema() {//int capacity
		//this.capacity = capacity;
		this.reader=0;
		this.acquiredw = 1;
		this.acquiredr = 1;
		this.lock = new Object();
	}
	
	public void semwait() throws InterruptedException {
		synchronized(lock) {
			while(acquiredw == 0) {
				lock.wait();
			}
			while(acquiredr == 0) {
				lock.wait();
			}
			acquiredw=0;
		}
	}
	public int readers()
	{
		int val = reader;
		return val;
	}

	public void readwait() throws InterruptedException
	{
		synchronized(lock){
			while (acquiredw == 0){
				lock.wait();
			}
			reader++;
			if(reader>=1)
			{
				acquiredr = 0;
			}
			//acquired=0;

		}
	} 
	public void readsignal()
	{
		synchronized (lock)
		{
			reader --;
			if(reader == 0)
			{
				acquiredr = 1;
				lock.notify();
			}
			try {
				Thread.currentThread().wait();//Thread.sleep(5000);	
			} catch (Exception e) {
      			System.out.print("");//"Something went wrong. line 43"+e
      		}		
      	}
	}
	
	public void semsignal() {
		synchronized(lock) {
			acquiredw=1;
			lock.notify();
		}
	}
}
class products
{
	static int point=0;
	static sema s = new sema();
	static void read()
	{
		//s.readwait();
		if(point==0)
		{
			System.out.println("Can't read.Buffer is empty");
			try {
				Thread.currentThread().wait();	
			} catch (Exception e) {
      			System.out.print("");
      		}	
      	}
		//s.semwait();
      	try {
      		s.readwait();
      	} catch (Exception e) {
      		System.out.println("Something went wrong. "+e);
      	}
      	int check =s.readers();
      	if (check ==0){
      		point--;
      	}
      	System.out.println(Thread.currentThread().getName()+"Reading product = "+point);
      	s.readsignal();
      }
      static void write()
      {
      	if(point==7)
      	{
      		System.out.println("Can't write Product.Buffer is full");
      		try {
      			Thread.currentThread().wait();	
      		} catch (Exception e) {
      			System.out.print("");
      		}

      	}
      	try {
      		s.semwait();
      	} catch (Exception e) {
      		System.out.println("Something went wrong. line 81"+e);
      	}
		//s.semwait();
		// try{s.semwait();}
		// catch(Interrupted Exception e)
		// {System.out.println("Exception in producing");}
      	point++;
      	System.out.println(Thread.currentThread().getName()+" Writing product.  "+point);
      	s.semsignal();
      }
  }

class people implements Runnable
{
	products p; //= new products();
	people (products p)
	{
		this.p = p;
		new Thread(this, "Writer").start();
		new Thread(this, "Reader1").start();
		new Thread(this, "Reader2").start(); 
	}

	public void run() { 
		for(int i=0; i < 5; i++){  
	        // producer put items 
			if(Thread.currentThread().getName()=="Writer")
			{
				p.write();
			} 
			else if (Thread.currentThread().getName()=="Reader1" ||Thread.currentThread().getName()=="Reader2")
			{
				p.read();
			}
		try {
      		Thread.currentThread().wait();
      	} catch (Exception e) {
      		System.out.print("");//Something went wrong. line 81"+e
      	}
			
		}
	}
}	 
class rw
{
	public static void main(String args[])
	{
		products p = new products();
		new people (p);
	}
}