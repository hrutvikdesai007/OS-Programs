import java.util.*;


public class priority 
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println ("Enter no of process:");
        int n = sc.nextInt();
        int pr[] = new int[n];
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int ta[] = new int[n];
        int wt[] = new int[n];
        int prs[] = new int[n];
        int ats[] = new int[n];
        int bts[] = new int[n];
        int cts[] = new int[n];
        int tas[] = new int[n];
        int wts[] = new int[n];
        int f[] = new int[n];

        int st=0, tot=0;
        float avgwt=0, avgta=0;

        for(int i=0;i<n;i++)
        {
            System.out.println ("Enter process " + (i+1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println ("Enter process " + (i+1) + " priority:");
            pr[i] = sc.nextInt();
            System.out.println ("Enter process " + (i+1) + " burst time:");
            bt[i] = sc.nextInt();
            pid[i] = i+1;
            f[i] = 0;
        }

        int k = 0;
        

        while(true)
        {
            int c=n, min = 999999;

            if (tot == n)
                break;

            for (int i=0; i<n; i++)
            {

                if ((at[i] <= st) && (f[i] == 0) && (pr[i]<=min))
                {
                    min=bt[i];
                    c=i;
                }
            }
            if (c==n)
                st++;
            else
            {
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                k = c;
                pid[tot] = c + 1;
                ats[tot] = at[k];
                bts[tot] = bt[k];
                prs[tot] = pr[k];
                cts[tot] = ct[k];
                tas[tot] = ta[k];
                wts[tot] = wt[k]; 
                tot++;
            }
        }

        int avtat = 0;
        int avwt = 0;

        for(int i=0;i<n;i++)
        {
          avtat = avtat + wts[i];
          avwt = avwt + tas[i];
        }


        System.out.println("\nGantt Chart : ");
        System.out.println("-------------------------------------------------------------------------");
        
        for(int i=0;i<n;i++)
        {
          System.out.print("\tP"+pid[i]+" \t|\t ");
        }

        System.out.println("\n-----------------------------------------------------------------------");
        System.out.print("0");

        for(int i=0;i<n;i++)
        {
            System.out.print("\t\t"+ct[i]+"\t");
        }

        System.out.println("\nProcess ID\tArrival Time\tPriority\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        
        for(int i=0;i<n;i++)
        {
            System.out.println(pid[i]+"\t\t"+ats[i]+"\t\t"+prs[i]+"\t\t"+bts[i]+"\t\t"+cts[i]+"\t\t"+tas[i]+"\t\t"+wts[i]);
        }

        
        System.out.println ("\nAverage tat is "+ (float)(avtat/n));
        System.out.println ("Average wt is "+ (float)(avwt/n));
        sc.close();
        for(int i=0;i<n;i++)
        {
            System.out.print(pid[i] + " ");
        }
    }
}