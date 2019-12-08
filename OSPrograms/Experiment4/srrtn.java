import java.util.*;
import java.io.*;

class srrtn
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("Please enter the number of processes: ");
		int n = sc.nextInt();

		int pid[] = new int[n];
		int at[] = new int[n];
		int bt[] = new int[n];
		int wt[] = new int[n];
		int tt[] = new int[n];
		int ct[] = new int[n];
		int pids[] = new int[n];
		int ats[] = new int[n];
		int bts[] = new int[n];
		int wts[] = new int[n];
		int tts[] = new int[n];
		int cts[] = new int[n];

		int bttemp[] = new int[n];

		for(int i=0; i<n ; i++)
		{
			System.out.println("Please enter the PID for Process " + i + ": ");
			pid[i] = sc.nextInt();
			System.out.println("Please enter the Arrival Time of Process " + i + ": ");
			at[i] = sc.nextInt();
			System.out.println("Please enter the Burst Time of Process " + i + ": ");
			bt[i] = sc.nextInt();
			bttemp[i] = bt[i];
		}

		int count = 0, t = 0, minbt = 99999, smallestproc = 0, ft = 0, inc = 0;
		boolean flag;

		while(count!=n)
		{
			for(int i = 0; i < n; i++)
			{
				if(at[i] < t && bttemp[i] < minbt && bttemp[i] > 0)
				{
					minbt = bttemp[i];
					smallestproc = i;
					flag = true;
				}
			}

			if(flag = false)
			{
				t++;
				continue;
			}

			bttemp[smallestproc]--;

			minbt = bttemp[smallestproc];

			if(minbt == 0)
			{
				minbt = 99999;
			}

			if(bttemp[smallestproc] == 0)
			{
				ft = t + 1;

				wt[smallestproc] = ft - (bt[smallestproc] - at[smallestproc]);
				wts[inc] = wt[smallestproc];
				tt[smallestproc] = bt[smallestproc] + wt[smallestproc];
				ct[smallestproc] = tt[smallestproc] + at[smallestproc];
				tts[inc] = tt[smallestproc];
				pids[inc] = pid[smallestproc];
				ats[inc] = at[smallestproc];
				bts[inc] = bt[smallestproc];
				cts[inc] = ct[smallestproc];

				count++;
				flag = false;
				inc++;

				if (wt[smallestproc] < 0)
				{
					wt[smallestproc] = 0;
				}
			}

			t++;
		}

		int tot_wt = 0;
		int tot_tt = 0;

		System.out.println("\nProcess_ID\tArrivalTime\tBurstTime\tWaitingTime\tTurnaroundTime\tCompletionTime");
        

		for(int i = 0; i < n; i++)
		{
				
			tot_wt = tot_wt + wt[i];
			tot_tt = tot_tt + tt[i];
			System.out.println(pids[i]+"\t\t"+ats[i]+"\t\t"+bts[i]+"\t\t"+wts[i]+"\t\t"+tts[i]+"\t\t"+cts[i]);
        	
    	}

    	System.out.println("\nGantt Chart : ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		
        for(int i=0;i<n;i++)
		{
		  System.out.print("\t"+pids[i]+" \t|\t ");
		}

		System.out.println("\n----------------------------------------------------------------------------------------------------------");
		System.out.print("0");
		
        for(int i=0;i<n;i++)
		{
			System.out.print("\t\t"+cts[i]+"\t");
		}

        System.out.println("Average waiting time = " + (float)tot_wt / (float)n); 
        System.out.println("Average turnaround time = " + (float)tot_tt / (float)n); 
	}
}