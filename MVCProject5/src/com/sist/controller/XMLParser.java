package com.sist.controller;
/*
 *   1) applicationContext.xml 읽기 
 *      ======================
 *       package ===> XMLParser
 *       ======================
 *   2) ComponentScan => Class.forName(class명)
 *   
 *   3) 클래스 관리자 => HandleMapping (메모리 할당된 클래스를 가지고 있다)
 *   
 *   4) DispatcherServlet <=> HandlerMapping 
 *   
 *   5) ViewResolver : JSP찾기 
 *   
 *   6) Model을 통해서  데이터를 JSP로 전송 
 *   
 *   7) forward/sendRedirect
 */
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*  
 *    SAX => 읽기 전용 
 *    
 *    <?xml version="1.0" ?> => startDocument
 *    <book>  => startElement
 *      <list> => startElement
 *        <author> => startElement
 *           sss   => characters
 *        </author> => endElement
 *        <title>  => startElement
 *           aaaa  => characters
 *        </title> => endElement
 *      </list> => endElement
 *    </book> => endElement
 *               endDocument
 *    
 * 
 */
public class XMLParser extends DefaultHandler{
    private List<String> list=new ArrayList<String>();
    
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try
		{
			if(qName.equals("context:component-scan"))
			{
				String pack=attributes.getValue("base-package");
				System.out.println(pack);
				list.add(pack);
			}
		}catch(Exception ex){}
	}

	public List<String> getList() {
		return list;
	}
}











