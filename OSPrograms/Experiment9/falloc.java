import java.util.*;
class falloc
{
	static void print(int arr[])
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+"  ");
		}
		System.out.println();
	}
	
	static void linked(int blocks)
	{
		Scanner sc = new Scanner(System.in);
		int i, j, k=0,bl,files, fsize,id,c=0,pos,f=0,loop,sum=0;;
		int [] memblocks = new int[blocks];
		int[][] details = new int [blocks][5] ;
		int [][] index = new int [blocks][2];
		Random r = new Random();
		boolean flag =true;
		for(i=0;i<blocks;i++)
		{
			memblocks[i] =-1;
		}
		System.out.println("/////INSERTING FILES////// ");
		System.out.println("Enter number of files to insert ");
		files = sc.nextInt();
		for(i=0;i<files;i++)
		{
			System.out.println("Enter file id");
			id = sc.nextInt();
			System.out.println("Enter number of blocks required by file "+id);
			bl = sc.nextInt();
			if((sum+bl)>blocks)
			{
				System.out.println("File cant be inserted ");
				//sum += blocks;
				continue;
			}
			sum += bl;
			j=0;
			pos = r.nextInt(blocks);
			flag = true;
			while(flag)
			{
				if(memblocks[pos]==-1)
				{
					//System.out.println(" ins at pos"+pos);
					details[f][0]=id;
					details[f][2]=pos;
					details[f][1] = bl;
					details [f][4]=c;
					f++;
					memblocks[pos]=id;
					index[c][0]=pos;
					pos = r.nextInt(blocks);
					while(j<bl-1)
					{
						if(memblocks[pos]==-1)
						{
							//System.out.println(f+" ins at pos"+pos);
							
							memblocks[pos] = id;
							index[c][1] = pos;
							c++;
							index[c][0] = pos;
							j++;
						}
						pos = r.nextInt(blocks); 
					}
					index[c][1] = -1;
					details[f][3] = index[c][0];

					flag=false; 
				}
				else
				{
					pos =r.nextInt(blocks);
				}
			}
			print(memblocks);
		}
		System.out.println("///////DELETING FILES////////");
		System.out.println("Enter no of files to delete ");
		files = sc.nextInt();
		for(i=0;i<files;i++)
		{
			System.out.println("Enter file id which exists that you want to delete ");
			flag=false;
			id = sc.nextInt();
			for(j=0;j<blocks;j++)
			{
				if(details[j][0]==id)
				{
					flag = true;
					loop = details[j][1];
					f=details[j][4];
					for( k=0;k<loop;k++)
					{
						memblocks[index[f][0]] = -1;
						f++;
					}
				}
			}
			if(flag){

			System.out.println("file "+id+" deleted");
			}
			else
			{
				System.out.println("file not found");
			}
			print(memblocks);
		}
	}


	static void sequential(int blocks)
	{
		Scanner sc = new Scanner(System.in);
		int [] memblocks = new int[blocks];
		int i, j, k=0,bl,f=0;
		boolean flag;
		for(i=0;i<blocks;i++)
		{
			memblocks[i] =-1;
		}
		int files, fsize,id;
		System.out.println("/////INSERTING FILES////// ");
		System.out.println("Enter number of files to insert ");
		files = sc.nextInt();
		for(i=0;i<files;i++)
		{
			System.out.println("Enter file id");
			id = sc.nextInt();
			System.out.println("Enter number of blocks required by file "+id);
			bl = sc.nextInt();
			if((f+bl)>blocks)
			{
				System.out.println("File cant be inserted ");
				continue;
			}
			f+=bl;
			for(j=k;j<k+bl;j++)
			{
				memblocks[j] = id;
			}
			k=j;
			System.out.println("The memory content is -");
			print(memblocks);
		}
		System.out.println("///////DELETING FILES////////");
		System.out.println("Enter no of files to delete ");
		files = sc.nextInt();
		for(i=0;i<files;i++)
		{
			System.out.println("Enter file id which exists that you want to delete ");
			flag=false;
			id = sc.nextInt();
			for(j=0;j<blocks;j++)
			{
				if(memblocks[j]==id)
				{
					memblocks[j]=-1;
					flag =true;
				}
			}
			if(flag)
			{
				print(memblocks);
				System.out.println("file id "+id+" has been deleted");
			}
			else{
				System.out.println("file not found");
			}
		}

	}
	static void printv(Vector v[],int blocks)
	{
		Object id;
		for(int i=0;i<blocks;i++)
		{
			if(v[i].isEmpty())
			{
				continue;
			}
			System.out.print("Index Block of file id - "+v[i].get(0)+"  ");
			id = v[i].remove(0);
			System.out.print(v[i]);
			v[i].add(0,id);
			System.out.println("");
		}
	}

	static void indexed(int blocks)
	{
		Vector[] v = new Vector[blocks];
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		int [] memblocks = new int[blocks];
		Object ob;
		int i, j, k=0,bl,pos,f;
		boolean flag;
		for(i=0;i<blocks;i++)
		{
			memblocks[i] =-1;
			v[i] = new Vector<>();
		}
		int files, fsize,id;
		System.out.println("/////INSERTING FILES////// ");
		System.out.println("Enter number of files to insert ");
		files = sc.nextInt();
		for(i=0;i<files;i++)
		{
			System.out.println("Enter file id");
			id = sc.nextInt();
			System.out.println("Enter number of blocks required by file "+id);
			bl = sc.nextInt();
			k=0;
			v[i].add(id);
			while(k<bl)
			{
				pos = r.nextInt(blocks);
				if (memblocks[pos]==-1)
				{
					memblocks[pos] = id;
					v[i].add(pos);
					k++;
				}
			}
		}
		printv(v,blocks);
		print(memblocks);
		System.out.println("");
		System.out.println("///////DELETING FILES////////");
		System.out.println("Enter no of files to delete ");
		files = sc.nextInt();
		for(i=0;i<files;i++)
		{
			System.out.println("Enter file id which exists that you want to delete ");
			flag=false;
			id = sc.nextInt();
			for(j=0;j<blocks;j++)
			{
				if(v[j].contains(id))
				{
					ob = v[j].remove(0);
					int l;
					int[] varr = new int[v[j].size()];
					//varr = (int[])v[j].toArray(varr);
					for(f=0;f<varr.length;f++)
					{
						l = (int)v[j].get(0);
						//System.out.println(l);
						memblocks[l]=-1;
						v[j].remove(0);
					}
					v[j].clear();
					flag =true;
				}
			}
			if(flag)
			{
				System.out.println("file id "+id+" has been deleted");
				printv(v,blocks);
				print(memblocks);
			}
			else{
				System.out.println("file not found");
			}	
		}
		
	}
	public static void main(String args[])
	{
		int tmem, blocksize, blocks, opt, i,files,fsize;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select allocation method -\n1.Sequential\t2.Linked\t3.Indexed");
		opt = sc.nextInt();
		System.out.println("Enter total size of memory ");
		tmem = sc.nextInt();
		System.out.println("Enter size of each block");
		blocksize = sc.nextInt();
		blocks = tmem / blocksize;
		switch(opt)
		{
			case 1:sequential(blocks);
			break;
			case 2:linked(blocks);
			break;
			case 3:indexed(blocks);
			break;
		}


	} 
}
