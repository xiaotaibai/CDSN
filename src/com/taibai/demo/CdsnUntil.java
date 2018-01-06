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
 * @author 晓太白
 * @version V1.0
 * @Date 2018/1/4 18:00:00
 * 
 */

public class CdsnUntil {

	public static void main(String[] args) throws IOException {
		List<String> list = getByWeb();
		for (String list1 : list) {
			 System.out.println(list1);// 可扩展添加输出流，输出至文件
		}
	}

	private static List<String> getByWeb() throws IOException {
		// -------------------------------读取配置文件------------------------------------------
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/data1.properties");
		prop.load(in);
		String s = prop.getProperty("ALLURL");
		String homeString = prop.getProperty("HOMEURL");
		// -------------------------------------------------分割字符串----------------------------------
		String[] strArray = null;
		strArray = s.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
		List<String> list = new ArrayList<String>(); // 用于存储网页扒下来的数据
		HttpURLConnection conn = null;
		String state = "访问成功"; // 状态标识符
		for (int i = 0; i < strArray.length - 1; i++) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");//设置访问格式
			String runUrl = strArray[i];
			URL url = new URL(runUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			conn.setReadTimeout(10000);// 设置从主机读取数据超时
			conn.setConnectTimeout(10000); // 设置连接主机超时
			conn.setInstanceFollowRedirects(false);
			int code = conn.getResponseCode();
			if (code == 200) {
				String num = pv(homeString);
				if (num == null) {
					state = "访问失败";
				}
				System.out.println("访问" + runUrl + "        " + state + "        " + "访问量——" + num + "        访问时间："+ sdf.format(date) + "        运行次数" + i);
			}

		}
		System.out.println("访问结束");
		return list;
	}

	// ---------------------------------------------------------------------------------
	/**
	 * 新增查看访问量,扒静态网页中访问数量的数据，
	 * 
	 * @param homeString
	 * @return line 访问量
	 * @throws IOException
	 */

	private static String pv(String homeString) throws IOException {

		String home = homeString;
		URL homeUrl = new URL(home);
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(homeUrl.openStream()));
		String regex = "([1-9]\\d{0,2}\\,)?(\\d{0,3}\\,)\\d{1,3}";// 正则表达式匹配网页源码中的总访问量
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
