import java.util.*;

class sema
{
	private final Object lock;
	private volatile int acquired;
	
	public sema() {
		this.acquired = 1;
		this.lock = new Object();
	}
	
	public void semwait() throws InterruptedException {
		synchronized(lock) {
			if(acquired == 0) {
				lock.wait();
			}
			acquired=0;
		}
	}
	
	public void semsignal() {
		synchronized(lock) {
			acquired=1;
			lock.notify();
		}
	}
}
class products
{
	static int point=0;
	static sema s = new sema();
	private static Object l2=new Object();
	static Random r = new Random();
	static int size;
	products(int size)
	{
		this.size = size;
	}
	static void consume()
	{
		synchronized(l2){
			if(point==0)
		{
			System.out.println("Can't consume product.Buffer is empty");
			try {
    			l2.wait();	
    		} catch (Exception e) {
      			System.out.print("");
    		}	
		}
		}
		//s.semwait();
		try {
    		s.semwait();
    	} catch (Exception e) {
      System.out.println("Something went wrong. "+e);
    }
		synchronized(l2){
			point--;
		l2.notify();
		}
		System.out.println("Consuming product. remaining = "+point);
		s.semsignal();
		try {
    			Thread.currentThread().sleep(r.nextInt(2000));	
    		} catch (Exception e) {
      			System.out.print("");
    		}	
	}
	static void produce()
	{
		synchronized(l2)
		{
			if(point==size)
		{
			System.out.println("Can't add Product.Buffer is full");
			try {
    			l2.wait();	
    		} catch (Exception e) {
      			System.out.print("");
    		}

		}
		}
		
		try {
    		s.semwait();
    	} catch (Exception e) {
      System.out.println("Something went wrong. line 81"+e);
    }
		synchronized(l2){
			point++;
		l2.notify();	
		}
		System.out.println("Adding product. remaining = "+point);
		s.semsignal();
		try {
    			Thread.currentThread().sleep(r.nextInt(2000));	
    		} catch (Exception e) {
      			System.out.print("");
    		}	
	}
}
class people implements Runnable
{
	products p; //= new products();
	people (products p)
	{
		this.p = p;
		new Thread(this, "Producer").start();
		new Thread(this, "Consumer").start(); 
	}
	
	public void run() { 
        for(int i=0; i < 10; i++){  
            // producer put items 
            if(Thread.currentThread().getName()=="Producer")
            {
            	p.produce();
            } 
            else if (Thread.currentThread().getName()=="Consumer")
            {
            	p.consume();
            }
    	}
    }	 
}

class ProCon
{
	public static void main(String args[])
	{
		System.out.println("Enter size of buffer");
		Scanner sc = new Scanner (System.in);
		int size = sc.nextInt();
		products p = new products(size);
		new people (p);
	}
}