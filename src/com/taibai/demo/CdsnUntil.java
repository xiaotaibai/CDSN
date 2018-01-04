package com.taibai.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * @author ��̫��
 * @version V1.0
 * @Date 2018/1/4 18:00:00
 * 
 */

public class CdsnUntil {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		List<String> list = getMailsByWeb();
		for (String list1 : list) {
			System.out.println(list1);//������������������ļ�
		}
	}

	private static List<String> getMailsByWeb() throws IOException {
		// -------------------------------��ȡ�����ļ�------------------------------------------
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/data1.properties");
		prop.load(in);
		String s = prop.getProperty("ALLURL");
		String homeString=prop.getProperty("HOMEURL");
		// System.out.println(s);
		// -------------------------------------------------�ָ��ַ���----------------------------------
		String[] strArray = null;
		strArray = s.split(","); // ����ַ�Ϊ"," ,Ȼ��ѽ����������strArray
		System.out.println(strArray.length);
		// ---------------------------------------------------------------
		List<String> list = new ArrayList<String>();   //���ڴ洢��ҳ������������
		Thread th = new Thread();
		HttpURLConnection conn = null;
		for (int i = 0; i < strArray.length - 1; i++) {

			try {

				String runUrl = strArray[i];
				System.out.println(runUrl);
				URL url = new URL(runUrl);

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setUseCaches(false);
				conn.setReadTimeout(8000);
				conn.setConnectTimeout(8000);
				conn.setInstanceFollowRedirects(false);
				conn.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
				int code = conn.getResponseCode();
				if (code == 200) {
					String num=pv(homeString);
					System.out.println("���ʳɹ�,����������"+num);
				}

				th.sleep(100);  //���·���̫�죬������IP�ˣ��Ƽ�1000��һ��һ��ѽ

			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			System.out.println("���д���" + i);
		}
		System.out.println("���ʽ���");
		// ----------------------------------------------------------------------------------------
		return list;
	}
//---------------------------------------------------------------------------------
	/**
	 * �����鿴������
	 * @param homeString
	 * @return
	 * @throws IOException
	 */
	
	private static String pv(String homeString) throws IOException {

		String home=homeString;
		
		URL homeUrl = new URL(home);    
		BufferedReader bufIn=new BufferedReader(new InputStreamReader(homeUrl.openStream()));
		//String regex="\\d{1,3}\\,\\d{3}";  //��Ŀƥ��
		String regex="([1-9]\\d{0,2}\\,)?(\\d{0,3}\\,)\\d{1,3}";//ƥ���ܷ�����
		
		Pattern p=Pattern.compile(regex);
		String line=null;
		while((line=bufIn.readLine())!=null){
			Matcher m=p.matcher(line);
			while(m.find()){
				line=m.group();
				//System.out.println(line);
				return line;
			}
			
		}		
		
		
		return line;
	}

	

}
