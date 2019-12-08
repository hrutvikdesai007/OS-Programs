import java.util.*;
import java.io.*;

class rr
{
	public static void main(String args[])
	{
		System.out.println("Please enter the number of processes you want to process: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println("Please enter the time quantum you want to assign to the processes: ");
		int q = sc.nextInt();
		String sequence = new String();
		int temp = 0;

		int t = 0;
		int pid[] = new int[n];
		int at[] = new int[n];
		int bt[] = new int[n];
		int wt[] = new int[n];
		int ct[] = new int[n];
		int tt[] = new int[n];
		int attemp[] = new int[n];
		int bttemp[] = new int[n];

		for(int i = 0; i < n; i++)
		{
			System.out.println("Please enter the PID for Process " + (i+1));
			pid[i] = sc.nextInt();
			System.out.println("Please enter the Arrival Time for Process " + (i+1));
			at[i] = sc.nextInt();
			attemp[i] = at[i];
			System.out.println("Please enter the Burst Time for Process " + (i+1));
			bt[i] = sc.nextInt();
			bttemp[i] = bt[i];
		}

		while (true) 
		{ 
            boolean flag = true; 
            for (int i = 0; i < n; i++) 
            { 
                if (attemp[i] <= t) 
                { 
                    if (attemp[i] <= q) 
                    { 
                        if (bttemp[i] > 0) 
                        { 
                            flag = false; 
                            if (bttemp[i] > q) 
                            {  
                                t = t + q; 
                                bttemp[i] = bttemp[i] - q; 
                                attemp[i] = attemp[i] + q; 
                                sequence += "->" + pid[i]; 
                            } 
                            else 
                            { 
                                t = t + bttemp[i]; 
                                ct[i] = t - at[i]; 
                                tt[i] = t - wt[i];
                                wt[i] = t - bt[i] - at[i]; 
                                bttemp[i] = 0; 
                                sequence += "->" + pid[i]; 
                            } 
                        } 
                    } 
                    else if (attemp[i] > q) 
                    { 
                        for (int j = 0; j < n; j++) 
                        { 
                            if (attemp[j] < attemp[i]) 
                            { 
                                if (bttemp[j] > 0) 
                                { 
                                    flag = false; 
                                    if (bttemp[j] > q) 
                                    { 
                                        t = t + q; 
                                        bttemp[j] = bttemp[j] - q; 
                                        attemp[j] = attemp[j] + q; 
                                        sequence += "->" + pid[j]; 
                                    } 
                                    else 
                                    { 
                                        t = t + bttemp[j]; 
                                        ct[j] = t - at[j];
                                        tt[i] = t - wt[i]; 
                                        wt[j] = t - bt[j] - at[j]; 
                                        bttemp[j] = 0; 
                                        sequence += "->" + pid[j]; 
                                    } 
                                } 
                            } 
                        } 
                      
                        if (bttemp[i] > 0) 
                        { 
                            flag = false; 

                            if (bttemp[i] > q) 
                            { 
                                t = t + q; 
                                bttemp[i] = bttemp[i] - q; 
                                attemp[i] = attemp[i] + q; 
                                sequence += "->" + pid[i]; 
                            } 
                            else 
                            { 
                                t = t + bttemp[i]; 
                                ct[i] = t - at[i]; 
                                tt[i] = t - wt[i];
                                wt[i] = t - bt[i] - at[i]; 
                                bttemp[i] = 0; 
                                sequence += "->" + pid[i]; 
                            } 
                        } 
                    } 
                } 
  
                else if (attemp[i] > t) 
                { 
                    t++; 
                    i--; 
                } 
            } 
            
            if (flag) 
            { 
                break; 
            }

        }

        for(int i=0; i<n; i++)
        {
        	for(int j=i+1; j<n; j++)
        	{
        		if(ct[j]<ct[i])
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

        int tot_wt = 0;
		int tot_tt = 0;

		System.out.println("\nProcess_ID\tArrivalTime\tBurstTime\tWaitingTime\tTurnaroundTime\tCompletionTime");
        

		for(int i = 0; i < n; i++)
		{	
			tot_wt = tot_wt + wt[i];
			tot_tt = tot_tt + tt[i];
			System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+wt[i]+"\t\t"+tt[i]+"\t\t"+ct[i]);	
    	}

    	System.out.println("\nGantt Chart : ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		
        for(int i=0;i<n;i++)
		{
		  	System.out.print("\t"+pid[i]+" \t|\t ");
		}

		System.out.println("\n----------------------------------------------------------------------------------------------------------");
		System.out.print("0");
		
        for(int i=0;i<n;i++)
		{
			System.out.print("\t\t"+ct[i]+"\t");
		}

       	System.out.println("Average waiting time = " + (float)tot_wt / (float)n); 
        System.out.println("Average turnaround time = " + (float)tot_tt / (float)n); 
        System.out.println("Sequence : " + sequence);        
	}
}