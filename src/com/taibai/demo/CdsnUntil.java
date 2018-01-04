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
		// TODO 自动生成的方法存根
		List<String> list=getMailsByWeb();
		for(String mail:list){
			System.out.println(mail);
		}
	} 
	private static List<String> getMailsByWeb() throws IOException {
//-------------------------------读取配置文件------------------------------------------		
		Properties prop = new Properties();  
		InputStream in = Object.class.getResourceAsStream("/data1.properties");
              prop.load(in);
            String s=prop.getProperty("ALLURL");  
		//System.out.println(s);
//-------------------------------------------------分割字符串----------------------------------		
		 String[] strArray = null;   
	        strArray = s.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			System.out.println("运行次数"+i);
//--------------------------------------------------------测试访问---------------------			
//			BufferedReader bufIn=new BufferedReader(new InputStreamReader(url.openStream()));
			
			//正则表达式测试
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
		System.out.println("访问结束");
//	----------------------------------------------------------------------------------------	
		return list ;
        }


}
