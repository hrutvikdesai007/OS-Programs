import java.util.*;

 class fork
{
	int status;
}
 class philo extends Thread
{
	private final Object lock = new Object();
	int id,n;
	Random r = new Random();
	philo(int id, int n)
	{
		this.id =id;
		this.n=n;
	}
	public void run()
	{
		while(true)
		{
			synchronized(lock){

				if(dining.f[id%n].status ==0 && dining.f[(id+1)%n].status==0)
				{
					dining.f[id%n].status = 1;

					dining.f[(id+1)%n].status = 1;
					System.out.println("Philosopher "+id+" is now eating");
					dining.f[id%n].status = 0;
					//lock.notify();
					dining.f[(id+1)%n].status = 0;
					System.out.println("Philosopher "+id+" finished eating");
					//lock.notify();
					try {
						Thread.currentThread().sleep(r.nextInt(2000));//Thread.sleep(5000);	
					} catch (Exception e) {
      					System.out.print("");//"Something went wrong. line 43"+e
      				}
				}
				else {
					try {
						Thread.currentThread().sleep(r.nextInt(2000));//Thread.sleep(5000);	
					} catch (Exception e) {
      					System.out.print("");//"Something went wrong. line 43"+e
      				}
					// try {
					// 	System.out.println("wait "+id);
	    // 			//lock.wait();
	    // 			System.out.println("wait2");	
	    // 			} catch (Exception e) {
	    //   				System.out.print("Exception");
	    // 			}	
				}	
			}
		}
	}
}
class dining
{
	static fork[] f;
	public static void main(String [] args)
	 
	{
		int n, i;
		Random r = new Random();
		Scanner s = new Scanner (System.in);
		System.out.println("Enter number of philosphers");
		n = s.nextInt();
		philo[] p = new philo[n];
		f = new fork[n];
		for(i=0;i<n;i++)
		{
			p[i] = new philo(i,n);
			f[i] = new fork();
			f[i].status = 0;
		}
		for(i=0;i<n;i++)
		{
			p[i].start();
		}
	}
}