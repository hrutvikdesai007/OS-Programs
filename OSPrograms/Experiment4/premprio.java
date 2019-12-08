import java.io.*;
import java.util.*;

class premprio
{
	public static void main(String args[])
	{
		System.out.println("Please enter the number of processes you want to process: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int completed = 0;

		int temp = 0;

		int t = 0;
		int pid[] = new int[n];
		int at[] = new int[n];
		int bt[] = new int[n];
		int pr[] = new int[n];
		int wt[] = new int[n];
		int ct[] = new int[n];
		int tt[] = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			System.out.println("Please enter the PID for Process " + (i+1));
			pid[i] = sc.nextInt();
			
			System.out.println("Please enter the Arrival Time for Process " + (i+1));
			at[i] = sc.nextInt();
			
			System.out.println("Please enter the Burst Time for Process " + (i+1));
			bt[i] = sc.nextInt();
			
			System.out.println("Please enter the Priority for Process " + (i+1));
			pr[i] = sc.nextInt();
			
		}

		for(int i=0; i<n; i++)
        {
        	for(int j=i+1; j<n; j++)
        	{
        		if(at[i]<at[j])
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
        			temp = pr[i];
        			pr[i] = pr[j];
        			pr[j] = temp;
        		}
        	}
        }

		int k = 0, j = 0;

		completed = 0;

		while(completed<n)
		{
			for(j = 0; j < n; j++)
			{
				if(at[j] > k)
				{
					break;
				}
			}

			for(int i=0; i<n; i++)
        	{
        		for(j=i+1; j<n; j++)
        		{
        			if(pr[i]>pr[j])
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
        				temp = pr[i];
        				pr[i] = pr[j];
        				pr[j] = temp;
        			}
        		}
        	}

        	if(j>0)
			{
				for(j=0;j<n;j++)
				{
					if(bt[j]!=0)
					break;
				}

				if(at[j]>k)
				{
					k+=at[j]-k;
					ct[j]=k+1;
					bt[j]--;
				}
			}
		
			k++;
			
			completed=0;
			
			for(j=0;j<n;j++)
			{
				if(bt[j]==0)
				{
					completed++;
				}
			}
		}

		int total_tt = 0;
        int total_wt = 0;

        for (int i = 0; i < n; i++) 
        { 
            total_wt = total_wt + wt[i]; 
            total_tt = total_tt + tt[i]; 
		}

		float ftotal_wt = total_wt;
		float ftotal_tt = total_tt;

		float avgwt = ftotal_wt / n; 
        float avgtt = ftotal_tt / n; 


		System.out.println("\nProcess_ID\tArrivalTime\tBurstTime\tWaitingTime\tTurnaroundTime\tCompletionTime");
        
		for(int i=0;i<n;i++)
	    {
	        System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+wt[i]+"\t\t"+tt[i]+"\t\t"+ct[i]);
        }

        System.out.println("Average waiting time = " + avgwt); 

        System.out.println("Average turn around time = " + avgtt); 
	}
}
