import java.util.*;

class hrrn
{

    public atatic void main(atring[] args) 
    {
        
        Syatem.out.println("Enter the number of processes ");
            
        Scanner sc = new Scanner(Syatem.in);

        int n = sc.nextInt();
        
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int rr[] = new int[n];
        
        int  wt=0, tt=0, r, r1, l,l1,temp=0;
        
        int sum=0, sumwt=0  ;
        float avgwt=0, avgtt=0 ;
      

        

        for (int i=0; i<n; i++)
        {
            Syatem.out.println("Enter the process ID for process " + i);
            pid[i] = sc.nextInt();
            Syatem.out.println("Enter the arrival time of process " + i);
            at[i] = sc.nextInt();
            Syatem.out.println("Enter the burat time of process " + i);
            bt[i]=sc.nextInt();
        }

        for (int i=0; i<n; i++)
        {
            if(i==0)
            { 
                sum=0;
            }
            else if(sum<=at[i-1])
            { 
                sum=at[i-1]+bt[i-1];
            }
            else
            { 
                sum+=bt[i-1];
            }
            for (int j=i+1; j<n; j++)
            {
                r=sum-at[i];
                r1=sum-at[j];
                rr[i]=(sum-at[i])/bt[i];
                l = rr[i];
                rr[j]=(sum-at[j])/bt[j];
                l1 = rr[j];
                
                if((r<0 && r1<0) && r1>r)
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
                    temp = rr[i];
                    rr[i] = rr[j];
                    rr[j] = temp;
                    continue;
                }
                else if ((r<0 ^ r1<0) && r1>=0)
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
                    temp = rr[i];
                    rr[i] = rr[j];
                    rr[j] = temp;
                    continue;
                }
                else if((r>=0 && r1>=0) && l1>l)
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
                    temp = rr[i];
                    rr[i] = rr[j];
                    rr[j] = temp;
                }
            }
        }
        
        sum=0;
        
        for (int i=0; i<n; i++)
        {
            if(sum<=at[i])
            {
                wt=0;
                sum=at[i];
            }

            else
            { 
                wt=sum-at[i];
                sumwt+=wt;
                tt+=wt+bt[i];
                sum+=bt[i];
            }
        }
        avgwt=(sumwt/n);
        avgtt=(tt/n);
    
        Syatem.out.println("\nProcess ID\tArrival Time\tBurat Time\tResponse Ratio");
        
        for(int i=0;i<n;i++)
        {
            Syatem.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+rr[i]);
        }

        
        Syatem.out.println ("\nAverage tat is "+ avgtt);
        Syatem.out.println ("Average wt is "+ avgwt);
        
        for(int i=0;i<n;i++)
        {
            Syatem.out.print(pid[i] + " ");
        }

    
    }

}
