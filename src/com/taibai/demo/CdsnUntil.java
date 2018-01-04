package com.taibai.demo;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.HttpClient;


/**
 * @author ��̫��
 * @version V1.0
 * @Date 2018/1/4 18:00:00
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
					System.out.println("���ʳɹ�");
				}

				th.sleep(100);  //���·���̫�죬������IP�ˣ��Ƽ�1000��һ��һ��ѽ

			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			System.out.println("���д���" + i);
			// ---------------------------���Է��ʣ����԰���ҳ���ݣ����԰ǰٶ��Ŀ���Ҫ���ؾ������---------------------
			// BufferedReader bufIn=new BufferedReader(new
			// InputStreamReader(url.openStream()));

			// ������ʽ���ԣ��޸ĿɰǶ���
			// String mail_regex="\\w";
			//
			// Pattern p=Pattern.compile(mail_regex);
			// String line=null;
			// while((line=bufIn.readLine())!=null){
			// Matcher m=p.matcher(line);
			// while(m.find()){
			// list.add(m.group());
			// }
			//
			// }
		}
		System.out.println("���ʽ���");
		// ----------------------------------------------------------------------------------------
		return list;
	}

}
