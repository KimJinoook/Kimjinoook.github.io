---
layout: post
title:  "13. 네트워크"
subtitle:   ""
categories: lang
tags: java2
comments: false
header-img: 
---

## 1. 네트워킹
- 두 대 이상의 컴퓨터를 연결하여 네트워크를 구성하는 것
- 컴퓨터들을 서로 연결하여 데이터를 주고받거나 자원을 공유
- java.net 패키지 이용
- 통신의 3대 요소
  - 서버 (Server)
  - 클라이언트 (Client)
  - 네트워크 (Network) : 서버와 클라이언트 연결

## 2. 클라이언트 / 서버
- 컴퓨터간의 관계를 역할로 구분
- 서버 : 서비스를 제공하는 컴퓨터
- 클라이언트 : 서비스를 사용하는 컴퓨터
- 서비스 : 서버가 클라이언트로부터 요청받은 작업을 처리하여 결고를 제공
- IP주소
  - 컴퓨터를 구별하는데 사용되는 고유 값
  - 4byte (32bit)의 정수, 4개의 정수 구분
  - 네트워크 주소, 호스트 주소로 구성

## 3. InetAddress
- IP주소를 다루기 위한 클래스   

![캡처](https://user-images.githubusercontent.com/99188096/163501632-05d127ce-fa11-4253-b79c-a84ba14e3bc6.JPG)   
 
 ```java
 package com.nw.day1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetTest {

	public static void main(String[] args) {
		// InetAddress클래스 : IP주소를 다루기 위한 클래스
		System.out.println("------www.naver.com-------");
		String host = "www.naver.com";
		InetAddress ip;
		try {
			ip = InetAddress.getByName(host);
			System.out.println(ip);
			
			System.out.println("hostname : "+ ip.getHostName());
			System.out.println("hostAddress : "+ ip.getHostAddress());
			
			System.out.println("\n-----local host------");
			ip=InetAddress.getLocalHost();
			System.out.println("hostname : "+ ip.getHostName());
			System.out.println("hostAddress : "+ ip.getHostAddress());
			
			InetAddress[] ipArr = InetAddress.getAllByName(host);
			
			for(InetAddress ip2 : ipArr) {
				System.out.println(ip2);
			}
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}

/*
------www.naver.com-------
www.naver.com/223.130.195.200
hostname : www.naver.com
hostAddress : 223.130.195.200

-----local host------
hostname : DESKTOP-56VTHAK
hostAddress : 192.168.0.66
www.naver.com/223.130.195.200
www.naver.com/223.130.195.95
*/

 ```

## 4. URL
- 인터넷에 존재하는 여러 서버들이 제공하는 자원에 접근할 수 있는 주소
- 프로토콜://호스트명:포트번호/경로명/파일명?쿼리스트링#
	- 프로토콜 : 서버와 통신하는데 사용되는 통신규약(http)
	- 호스트명 : 자원을 제공하는 서버의 이름 (www.naver.com)
	- 포트번호 : 통신에 사용되는 서버의 포트번호 (80)
	- 경로명 : 접근하려는 자원이 저장된 서버상의 위치 (/book/source/)
	- 파일명 : 접근하려는 자원의 이름 (test.jsp)
	- 쿼리스트링 : url 에서 ?이후의 부분(id=hong)
	- 참조 : url에서 #이후 부분 (index1)   

- URL클래스   

![캡처](https://user-images.githubusercontent.com/99188096/163502852-a9bf1a62-02bc-4828-a6c4-bb50e4640501.JPG)   
![캡처2](https://user-images.githubusercontent.com/99188096/163502857-1b9b247a-1ced-4a33-88e3-ac67489ec91c.JPG)   

```java
package com.nw.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

public class UrlTest1 {

	public static void main(String[] args) {
		BufferedReader br = null;
		
		try {
			URL url
			= new URL("http://www.yes24.com/24/category/bestseller");
			System.out.println("protocol : "+url.getProtocol());
			System.out.println("host : "+url.getHost());
			System.out.println("port : "+url.getPort());
			//-1 반환 : 기본포트 사용시
			
			System.out.println("file : "+url.getFile());
			
			URLConnection conn = url.openConnection();
			System.out.println("\n\n파일크기 : "+conn.getContentLength());
			System.out.println("contentType : "+conn.getContentType());
			
			//url과 연결된 urlconnection의 inputstream을 얻는다
			InputStream is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			
			String data = "";
			while((data=br.readLine()) != null) {
				System.out.println(data);
			}
			
			
		}catch (MalformedInputException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(br!=null) br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}

```
### URLConnection
- 어플리케이션과 url간 통신연결을 나타내는 클래스
- HttpURLConnection / JarURLConnection
- URL의 프로토콜이 http 프로토콜이라면
	- openConnection()은 HttpURLConnection 반환   


```java
package com.nw.day1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class URLGuiTest extends JFrame implements ActionListener{
	private JLabel lb;
	private JTextField tfUrl;
	private JButton btOk;
	private JTextArea taResult;
	private JScrollPane scrollPane;
	private JPanel pnl, pnl2;
	private JComboBox<String> cbEncoding;
	public URLGuiTest() {
		super("URL - GUI 이용");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pnl=new JPanel(new BorderLayout());
		pnl2=new JPanel(new BorderLayout());
		lb=new JLabel("URL : ");
		tfUrl=new JTextField(15);
		btOk=new JButton("확인");
		cbEncoding = new JComboBox<String>();
		cbEncoding.addItem("EUC-KR");
		cbEncoding.addItem("UTF-8");
		pnl2.add(tfUrl,"Center");
		pnl2.add(cbEncoding,"East");
		pnl.add(lb,"West");
		pnl.add(pnl2,"Center");
		pnl.add(btOk,"East"); 
		taResult=new JTextArea();
		scrollPane=new JScrollPane(taResult);
		add(scrollPane,"Center");
		add(pnl,"North");
		setSize(700, 700);
		setVisible(true);
		btOk.addActionListener(this);
		tfUrl.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btOk || e.getSource()==tfUrl){
			readUrl();
		}
	}
	private void readUrl() {
		//url을 읽어서 해당 웹페이지 정보를 TextArea에 출력하고, file에도 출력
		BufferedReader br=null;
		BufferedWriter bw=null;
		StringWriter sw=null;
		try {
			URL url = new URL(tfUrl.getText());
			InputStream in = url.openStream();
			br=new BufferedReader(new InputStreamReader(in, 
					cbEncoding.getSelectedItem().toString())); 
			bw=new BufferedWriter(new FileWriter("text/url.txt"));
			sw=new StringWriter(); 
			String line="";
			while((line=br.readLine())!=null){
				bw.write(line);
				bw.newLine();
				sw.write(line+"\n");
			}
			taResult.setText(sw.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null) br.close();
				if(bw!=null) bw.close();
				if(sw!=null) sw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new URLGuiTest();
	}
}
```
