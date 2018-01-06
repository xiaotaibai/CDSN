package com.taibai.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		List<String> list = getByWeb();
		for (String list1 : list) {
			 System.out.println(list1);// ����չ����������������ļ�
		}
	}

	private static List<String> getByWeb() throws IOException {
		// -------------------------------��ȡ�����ļ�------------------------------------------
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/data1.properties");
		prop.load(in);
		String s = prop.getProperty("ALLURL");
		String homeString = prop.getProperty("HOMEURL");
		// -------------------------------------------------�ָ��ַ���----------------------------------
		String[] strArray = null;
		strArray = s.split(","); // ����ַ�Ϊ"," ,Ȼ��ѽ����������strArray
		List<String> list = new ArrayList<String>(); // ���ڴ洢��ҳ������������
		HttpURLConnection conn = null;
		String state = "���ʳɹ�"; // ״̬��ʶ��
		for (int i = 0; i < strArray.length - 1; i++) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");//���÷��ʸ�ʽ
			String runUrl = strArray[i];
			URL url = new URL(runUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			conn.setReadTimeout(10000);// ���ô�������ȡ���ݳ�ʱ
			conn.setConnectTimeout(10000); // ��������������ʱ
			conn.setInstanceFollowRedirects(false);
			int code = conn.getResponseCode();
			if (code == 200) {
				String num = pv(homeString);
				if (num == null) {
					state = "����ʧ��";
				}
				System.out.println("����" + runUrl + "        " + state + "        " + "����������" + num + "        ����ʱ�䣺"+ sdf.format(date) + "        ���д���" + i);
			}

		}
		System.out.println("���ʽ���");
		return list;
	}

	// ---------------------------------------------------------------------------------
	/**
	 * �����鿴������,�Ǿ�̬��ҳ�з������������ݣ�
	 * 
	 * @param homeString
	 * @return line ������
	 * @throws IOException
	 */

	private static String pv(String homeString) throws IOException {

		String home = homeString;
		URL homeUrl = new URL(home);
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(homeUrl.openStream()));
		String regex = "([1-9]\\d{0,2}\\,)?(\\d{0,3}\\,)\\d{1,3}";// ������ʽƥ����ҳԴ���е��ܷ�����
		Pattern p = Pattern.compile(regex);
		String line = null;
		while ((line = bufIn.readLine()) != null) {
			Matcher m = p.matcher(line);
			while (m.find()) {
				line = m.group();
				return line;
			}

		}
		return line;
	}
}
