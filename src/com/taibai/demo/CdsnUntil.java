package com.taibai.demo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.HttpClient; 
public class CdsnUntil {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		List<String> list=getMailsByWeb();
		for(String mail:list){
			System.out.println(mail);
		}
	} 
	private static List<String> getMailsByWeb() throws IOException {
//-------------------------------��ȡ�����ļ�------------------------------------------		
		Properties prop = new Properties();  
		InputStream in = Object.class.getResourceAsStream("/data1.properties");
              prop.load(in);
            String s=prop.getProperty("ALLURL");  
		//System.out.println(s);
//-------------------------------------------------�ָ��ַ���----------------------------------		
		 String[] strArray = null;   
	        strArray = s.split(","); //����ַ�Ϊ"," ,Ȼ��ѽ����������strArray 
	      System.out.println(strArray.length);
	      
	  	List<String> list =new ArrayList<String>();
	  	Thread th=new Thread();
		for(int i=0;i<strArray.length-1;i++){

			
			try {
				String runUrl=strArray[i];
				System.out.println(runUrl);
				URL url=new URL(runUrl);
				th.sleep(1000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			System.out.println("���д���"+i);
//--------------------------------------------------------���Է���---------------------			
//			BufferedReader bufIn=new BufferedReader(new InputStreamReader(url.openStream()));
			
			//������ʽ����
//			String mail_regex="\\w";
//			
//			Pattern p=Pattern.compile(mail_regex);
//			String line=null;
//			while((line=bufIn.readLine())!=null){
//				Matcher m=p.matcher(line);
//				while(m.find()){
//					list.add(m.group());
//				}
//				
//			}	
		}
		System.out.println("���ʽ���");
//	----------------------------------------------------------------------------------------	
		return list ;
        }


}
