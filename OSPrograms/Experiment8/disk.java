import java.util.*;
class Main
{
	static int head, dir, seek = 0, ind=0, grt = 0, les = 0, max, min = Integer.MAX_VALUE;
	static int[] acc,diff;
	static void fcfs(int[] loc)
	{
		int i, pos = head, seek = 0;
		System.out.println("FCFS scheduling");
		for (i=0;i<loc.length;i++)
		{
			System.out.println("Accessing location "+loc[i]+" from head = "+pos);
			seek += Math.abs(pos - loc[i]);
			pos = loc[i];
		}
		System.out.println("Total seek time = "+seek);
	}

	static void sstf(int[] loc)
	{
		int i, j, diff, pos = head;
		for(j=0;j<loc.length;j++)
		{
			for(i=0;i<loc.length;i++)
			{
				diff = Math.abs(pos - loc[i]);
				if(min>diff && acc[i]!=1)
				{
					min = diff;
					ind = i;
				}
			}
			acc[ind] = 1;
			System.out.println("Accessing location "+loc[ind]+" from head = "+pos);	
			seek += Math.abs(pos - loc[ind]);
			pos = loc[ind];
			min = Integer.MAX_VALUE;
		}
		System.out.println("Total seek time = "+seek);
	}

	static void calcdiff(int[] loc)
	{
		int pos = head, i;
		for(i=0;i<loc.length;i++)
		{
			if(dir==0)
			{
				diff[i] = pos - loc[i];
				if(diff[i]>0)
					grt++;
				else
					les++;
			}
			else if(dir==1)
			{
				diff[i] = loc[i] - pos;
				if(diff[i]>0)
					grt++;
				else
					les++;
			}
		}	
	}

	static void great(int [] loc, int pos)
	{
		int i;
		while(grt>0)
		{
			for(i=0;i<loc.length;i++)
			{
				if(min>diff[i] && acc[i]!=1 && diff[i]>=0)
				{
					min = diff[i];
					ind = i;
				}
			}
			acc[ind] = 1;
			System.out.println("Accessing location "+loc[ind]+" from head = "+pos);	
			seek += Math.abs(pos - loc[ind]);
			pos = loc[ind];
			min = Integer.MAX_VALUE;
			grt--;
		}
	}

	static void less(int[] loc, int pos)
	{
		int i;
		while(les>0)
		{
			min = -max;
			for(i=0;i<loc.length;i++)
			{
				if(min<diff[i] && acc[i]!=1 && diff[i]<0)
				{
					min = diff[i];
					ind = i;
				}
			}
			acc[ind] = 1;
			System.out.println("Accessing location "+loc[ind]+" from head = "+pos);	
			seek += Math.abs(pos - loc[ind]);
			pos = loc[ind];
			les--;
		}
	}

	static void scan(int[] loc)
	{
		int i, pos = head;
		calcdiff(loc);
		great(loc, pos);
		if(dir==0)
		{
			System.out.println("Head moves to track 0 before reversing direction");
			seek += loc[ind];
			pos = 0;
		}
		else if(dir==1)
		{
			System.out.println("Head moves to track "+max+" before reversing direction");
			seek += max - loc[ind];
			pos = max;
		}
		less(loc, pos);
		System.out.println("Total seek time = "+seek);
	}

	static void cles(int[] loc,int pos)
	{
		while(les>0)
		{
			min = 0;
			for(int i=0;i<loc.length;i++)
			{
				if(min>diff[i] && acc[i]!=1 && diff[i]<0)
				{
					min = diff[i];
					ind = i;
				}
			}
			acc[ind] = 1;
			System.out.println("Accessing location "+loc[ind]+" from head = "+pos);	
			seek += Math.abs(pos - loc[ind]);
			pos = loc[ind];
			les--;
		}
	}

	static void cscan(int[] loc)
	{
		int i,j,pos = head;
		calcdiff(loc);
		great(loc, pos);
		if(dir==0)
		{
			System.out.println("Head moves to track 0 before moving to track "+max+" seek = "+seek);
			seek += loc[ind];
			pos = max;
		}
		else if(dir==1)
		{
			System.out.println("Head moves to track "+max+" before moving to track 0"+" seek = "+seek);
			seek += max - loc[ind];
			pos = 0;
		}
		cles(loc,pos);
		System.out.println("Total seek time = "+seek);
	}

	static void look(int[] loc)
	{
		int i, pos = head;
		calcdiff(loc);
		great(loc, pos);
		pos = loc[ind];
		System.out.println("Reversing direction");
		less(loc, pos);
		System.out.println("Total seek time = "+seek);
	}
	
	static void clook(int[] loc)
	{
		int i, pos = head;
		calcdiff(loc);
		great(loc, pos);
		pos = loc[ind];
		System.out.println("Reversing direction");
		cles(loc, pos);
		System.out.println("Total seek time = "+seek);
	}

	public static void main(String[] args) {
		int n, i, opt;
		int loc[]; 
		Scanner s = new Scanner (System.in);
		System.out.println("Select the disk scheduling algorithm -\n1.FCFS  2.SSTF  3.SCAN  4.C-SCAN  5.LOOK  6.C-LOOK");
		opt = s.nextInt();
		System.out.println("Enter the number of tracks on the disk");
		max = s.nextInt();
		System.out.println("Enter the number of disk accesses");
		n = s.nextInt();
		loc = new int[n];
		acc = new int[n];
		diff = new int [n];
		System.out.println("Enter the memory locations to be accessed");
		for(i=0;i<n;i++)
		{
			loc[i] = s.nextInt();
			if (loc[i]>max || loc[i]<0)
			{System.out.println("Track not available on disk. Exiting");System.exit(0);}
			acc[i] = 0;
		}
		System.out.println("Enter the direction in which the head moves - (Left - 0 / Right - 1)");
		dir = s.nextInt();
		System.out.println("Enter the current position of the head");
		head = s.nextInt();
		switch(opt)
		{
			case 1: fcfs(loc);
			break;
			case 2: sstf(loc);
			break;
			case 3: scan(loc);
			break;
			case 4: cscan(loc);
			break;
			case 5: look(loc);
			break;
			case 6: clook(loc);
			break;
			default: System.out.println("Wrong option entered. Exit");
			System.exit(0);
		}
	}
}