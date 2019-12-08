import java.util.*;
import java.io.*;

class fcfs
{
	public static void main(String args[])
    {		
		int pid[] = new int[1000];
		int at[] = new int[1000];
		int bt[] = new int[1000];
		int wt[] = new int[1000];
		int tt[] = new int[1000];
		int svt[] = new int[1000];
		int ct[] = new int[1000];

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of processes:- ");
		int nop = sc.nextInt();
		
		for(int i=0; i<nop; i++)
		{
			System.out.println("Enter the PID of the process " + (i+1) + " :- ");
			pid[i] = sc.nextInt();
			System.out.println("Enter the Arrival Time of the process " + (i+1) + " :- ");
			at[i] = sc.nextInt();
			System.out.println("Enter the Burst Time of the process " + (i+1) + " :- ");
			bt[i] = sc.nextInt();
		}


		System.out.println("\nProcess_ID\tArrival Time\tBurst Time");
        
		for(int i=0;i<nop;i++)
	    {
	        System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]);
        }

        for (int i = 1; i < nop; i++) 
        { 
           	svt[i] = svt[i-1] + bt[i-1];  
        	wt[i] = svt[i] - at[i]; 
        } 

        for (int i = 0; i < nop; i++) 
        { 
            tt[i] = bt[i] + wt[i]; 
            ct[i] = tt[i] + at[i]; 
        } 

        int total_tt = 0;
        int total_wt = 0;

        for (int i = 0; i < nop; i++) 
        { 
            total_wt = total_wt + wt[i]; 
            total_tt = total_tt + tt[i]; 
		}

		float ftotal_wt = total_wt;
		float ftotal_tt = total_tt;

		float avgwt = ftotal_wt / nop; 
        float avgtt = ftotal_tt / nop; 

        int temp;

        System.out.println("\nGantt Chart : ");
		System.out.println("---------------------------------------------------------");
		
        for(int i=0;i<nop;i++)
		{
		  System.out.print("\tP"+(i+1)+" \t|\t ");
		}

		System.out.println("\n---------------------------------------------------------");
		System.out.print("0");
		
        for(int i=0;i<nop;i++)
		{
			System.out.print("\t\t"+ct[i]+"\t");
		}


        for(int i=0; i<nop; i++)
        {
        	for(int j=i+1; j<nop; j++)
        	{
        		if(at[j]<at[i])
        		{
        			temp = pid[i];
        		    pid[i] = pid[j];
        			pid[j] = temp;
        			temp = at[i];
        			at[i] = at[j];
        			at[j] = temp;
        			temp = bt[i];
        			bt[i] = bt[j];
        			bt[j] = temp;
        			temp = wt[i];
        			wt[i] = wt[j];
        			wt[j] = temp;
        			temp = tt[i];
        			tt[i] = tt[j];
        			tt[j] = temp;
        			temp = ct[i];
        			ct[i] = ct[j];
        			ct[j] = temp;
        		}
        	}
        }

        System.out.println("");

        System.out.println("Order in which process gets executed"); 
        
        for (int i = 0 ; i < nop; i++) 
        {
            System.out.print(pid[i] + " "); 
        }

        System.out.println("");

        System.out.println("\nProcess_ID\tArrivalTime\tBurstTime\tWaitingTime\tTurnaroundTime\tCompletionTime");
        
		for(int i=0;i<nop;i++)
	    {
	        System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+wt[i]+"\t\t"+tt[i]+"\t\t"+ct[i]);
        }

        System.out.println("Average waiting time = " + avgwt); 

        System.out.println("Average turn around time = " + avgtt); 

	}
}