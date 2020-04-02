package com.sist.controller;
import java.util.*;
import java.io.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/*
 *                      XML(Path)
 *     DispatcherSetvlet =======> HandlerMapping <=========> XMLParser
 *     (서블릿)                                   List<String>:package명
 *     
 *     
 *     class A
 *     class B
 *     class C
 *     class D
 *     class E{
 *        main(){
 *        }
 *     }
 */
public class HandlerMapping {
    private List<String> list=
		    new ArrayList<String>();
    public HandlerMapping(String path)
    {
    	try
    	{
    		SAXParserFactory spf=SAXParserFactory.newInstance();
    		//Sax파서기 
    		SAXParser sp=spf.newSAXParser();
    		XMLParser xp=new XMLParser();
    		sp.parse(new File(path), xp);
    		List<String> pList=xp.getList();
    		
    		ComponentScan cs=new ComponentScan();
    		for(String pack:pList)
    		{
    			System.out.println("pack="+pack);
    			List<String> fNames=cs.getClassNameRead(pack);
    			for(String name:fNames)
    			{
    				System.out.println("name="+name);
    				list.add(name);
    			}
    			
    		}
    	}catch(Exception ex){}
    }
	public List<String> getList() {
		return list;
	}
  
   
}







