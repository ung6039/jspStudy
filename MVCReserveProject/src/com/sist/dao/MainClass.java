package com.sist.dao;

import java.util.Arrays;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=1;i<=15;i++)
		{
			String res=movieRandomData(15);
			System.out.println(i+":"+res);
		    ReserveTheaterVO vo=new ReserveTheaterVO();
		    vo.setTno(i);
		    vo.setTdate(res);
		    MovieDAO.movieDateUpdate(vo);
		}

	}
	
	public static String movieRandomData(int count)
	{
		String result="";
		int no=(int)(Math.random()*6)+10;
		int[] com=new int[no];
		int rand=0;
		boolean bCheck=false;
		for(int i=0;i<no;i++)
		{
			bCheck=true;
			while(bCheck)
			{
				rand=(int)(Math.random()*31)+1;
				bCheck=false;
				for(int j=0;j<i;j++)
				{
					if(com[j]==rand)
					{
						bCheck=true;
						break;
					}
				}
			}
			com[i]=rand;
		}
		
		Arrays.sort(com);
		
		for(int i=0;i<com.length;i++)
		{
			result+=com[i]+",";
		}
		result=result.substring(0,result.lastIndexOf(","));
		return result;
	}

}











