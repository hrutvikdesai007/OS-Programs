import java.util.*;
class banker
{
	static int[] complete, seq;
	static boolean safeSeq(int avail[], int need[][], int alloc[][], int p, int r)
	{
		int n = p, i=0, count=0, a=0, j,z;
		boolean flag = true;
		while(count<n)
		{
			if(complete[i%p]==0)
			{
				for(j=0;j<r;j++)
				{
					if(avail[j] < need[i%p][j])
					{
						flag = false;
						break;
					}	
				}
				if(flag)
				{
					seq [a++] = i%p;
					count=0; n--;
					complete[i%p]=1;
					for(z=0;z<r;z++)
					{
						avail[z] = avail[z] + alloc[i%p][z];
						alloc[i%p][z] = 0;
					}
					System.out.println("process "+(i%p)+" added");

				}
				else{
					count++;
					System.out.println("process "+i%p+" cant be exected now");
				}
			}
			i++;
			flag = true;
		}
		for(j=0;j<p;j++)
		{
			if(complete[j]==0)
			{
				return false;
			}
		}
		return true;
	}

	static int[] check(int[][] arr, int[] max, int avail[], int p, int r)
	{
		int i,j,sum=0;
		for(j=0;j<r;j++)
		{
			for(i=0;i<p;i++)
			{
				sum += arr[i][j];
			}
			if(sum>max[j])
			{
				System.out.println("Entered resources are incorrect");
				System.exit(0);
			}
			else
			{
				avail[j] = max[j] - sum;
			}
			sum = 0;
		}
		return avail;
	}
	public static void main(String[] args) {
		int p, r, i, j, sum=0;
		boolean possible;
		int total[], avail[], claim[][], alloc[][], need[][];
		System.out.println("Bankers Algorithm\nEnter No. of resources available");
		Scanner s = new Scanner(System.in);
		r= s.nextInt();
		total = new int[r];
		avail = new int[r];
		System.out.println("Enter Total of each resource available");
		for(i=0;i<r;i++)
		{
			total[i] = s.nextInt(); 
		}
		System.out.println("Enter total number of processes");
		p = s.nextInt();
		complete = new int[p];
		claim = new int [p][r];
		alloc = new int [p][r];
		need = new int [p][r];
		seq = new int[p];	
		System.out.println("Enter the allocated matrix");
		for(i=0;i<p;i++)
		{
			for(j=0;j<r;j++)
			{
				alloc[i][j] = s.nextInt();
			}
			complete[i] = 0;
		}
		avail = check(alloc, total, avail, p, r);
		System.out.println("The avail matrix  is -");
		for(i=0;i<r;i++)
		{
			System.out.print(avail[i]+"   ");
		}
		System.out.println("\nEnter the claim matrix");
		for(i=0;i<p;i++)
		{
			for(j=0;j<r;j++)
			{
				claim[i][j] = s.nextInt();
				if(claim[i][j]>total[j])
				{
					System.out.println("Requirement of process "+j+" is greater than max available resource of that type");
					System.exit(0);
				}
			}
		}
		System.out.println("Calculating the need matrix -");
		for(i=0;i<p;i++)
		{
			for(j=0;j<r;j++)
			{
				need[i][j] = claim[i][j] - alloc[i][j];
				System.out.print(need[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println("Finding safe Sequence ");
		possible = safeSeq(avail, need, alloc, p, r);
		if(possible)
		{
			System.out.println("Safe sequence exists ");
			for(i=0;i<p;i++)
			{
				System.out.print(seq[i]+"  ");
			}

		}
		else{
			System.out.println("No safe Sequence possible.");
		}
	}
}
