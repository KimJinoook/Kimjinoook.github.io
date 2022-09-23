---
layout: post
title:  "14. 소켓"
subtitle:   ""
categories: lang
tags: java2
comments: false
header-img: 
---

## 1. 소켓 프로그래밍
- 소켓을 이용한 통신 프로그래밍
- 클라이언트와 서버간의 일대일 통신
- 소켓 : 프로세스간 통신에 사용되는 양쪽 끝단을 의미
- java.net 패키지 이용

### 통신방식
- TCP 통신방식
  - 양방향 연결 기반 통신방식 (Connection Oriented)
  - 신뢰성 있는 데이터 전송 (수신여부 확인)
  - 연결 후 통신
  - 데이터 손실 시 재전송
- UDP 통신방식
  - 비연결 기반 통신 방식
  - 신뢰성 없는 데이터 전송 (수신여부 미확인)
  - 연결 없이 통신
  - 데이터가 손실될 수 있다
  - 전송속도가 빠르다   

![캡처](https://user-images.githubusercontent.com/99188096/163509434-eeeb1884-81ce-47f6-90c5-5841c3e80ee6.JPG)   

## 2. TCP 소켓 프로그래밍
- 클라이언트와 서버 간 일대일 통신
- 먼저 프로그램이 실행되어 클라이언트 프로그램의 연결요청을 기다려야 한다
- 통신과정
  - 서버프로그램 : 서버소켓을 사용, 특정 포트에서 클라이언트의 연결 요청을 처리할 준비
  - 클라이언트프로그램 : 접속할 서버의 IP주소와 포트정보를 가지고 소켓 생성, 서버에 연결 요청
  - 서버소켓 : 연결요청을 받으면 서버에 새로운 소켓을 생성, 클라이언트의 소켓과 연결
- 서버소켓
  - 포트와 결합되어 포트를 통해 사용자의 연결요청을 대기
  - 연결 요청이 올때마다 새로운 소켓을 생성, 상대편 소켓과 연결
  - 실질 데이터통신은 서버소켓과 관계없이, 새로 생성된 소켓과 클라이언트 소켓간에 이루어진다
- 포트
  - 컴퓨터가 외부와 통신을 하기 위한 통로
  - 하나의 호스트(컴퓨터)가 65,536개의 포트를 소유
  - 0~1024 사이 값은 시스템이 사용하는 포트번호
- 소켓은 입력스트림과 출력스트림 보유
  - 상대 소켓의 스트림과 교차연결   


### 서버소켓 생성
```java
package com.nw.day1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer1 {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataOutputStream dos = null;
		
		
		try {
			//서버소켓을 생성하여 7777번 포트와 결합(bind)
			serverSocket = new ServerSocket(7777);
			//ServerSocket(int port)
			
			
			System.out.println(MyUtil.getTime()+"서버 준비 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}


		while(true) {
			System.out.println(MyUtil.getTime()+"연결요청 대기");
			//서버소켓은 클라이언트의 연결요청이 올때까지 실행을 멈추고 기다린다
			//클라이언트의 연결요청이 오면 클라이언트 소켓과 통신할 새로운 소켓을 생성한다
		
			try {
				socket = serverSocket.accept();
				System.out.println(MyUtil.getTime()+socket.getInetAddress()+"로부터 연결요청");
				
				
				//소켓의 출력스트림
				OutputStream os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				
				//원격 소켓에 데이터를 보낸다
				dos.writeUTF("서버가 데이터 전송");
				System.out.println(MyUtil.getTime()+"데이터 전송");
				
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(dos!=null) dos.close();
					if(socket!=null) socket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

```

### 클라이언트 소켓 생성
```java
package com.nw.day1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient1 {

	public static void main(String[] args) {
		String serverIp = "192.168.0.66";
		System.out.println("서버에 연결중입니다. 서버 ip : "+serverIp);
		
		Socket socket = null;
		DataInputStream dis = null;
		
		try {
			//소켓을 생성하여 연결 요청
			//Socket(String host, int port)
			 socket = new Socket(serverIp, 7777);
			
			//소켓의 입력스트림을 얻는다
			InputStream is = socket.getInputStream();
			 dis = new DataInputStream(is);
			
			//소켓으로부터 받은 데이터를 출력한다
			System.out.println("서버로부터 받은 메시지 : "+dis.readUTF());
			System.out.println("연결종료");
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(dis!=null) dis.close();
				if(socket!=null) socket.close();
				System.out.println("연결종료 완료");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
```
### 결과
```java
/* 
- 서버
[12:33:06]서버 준비 완료
[12:33:06]연결요청 대기
[12:34:32]/192.168.0.66로부터 연결요청
[12:34:32]데이터 전송
[12:34:32]연결요청 대기

- 클라이언트
서버에 연결중입니다. 서버 ip : 192.168.0.66
서버로부터 받은 메시지 : 서버가 데이터 전송
연결종료
연결종료 완료
*/
```

## 채팅
### 서버
```java
package com.nw.day1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TcpGuiServer extends JFrame implements ActionListener {
	
	JTextArea taList;
	JScrollPane scrollPane;
	JTextField tfChat;
	JButton btSend;
	JPanel pl;
	
	//Socket socket;
	DataOutputStream dos;
	String name;
	
	public TcpGuiServer() {
		super("[Server]");
		
		taList = new JTextArea();
		taList.setEditable(false);
		
		scrollPane = new JScrollPane(taList);
		pl=new JPanel(new BorderLayout());
		tfChat = new JTextField();
		btSend = new JButton("전송");
		pl.add(btSend,"East");
		pl.add(tfChat,"Center");
		
		add(scrollPane, "Center");
		add(pl,"South");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		
		tfChat.addActionListener(this);
		btSend.addActionListener(this);
	}
	
	public static void main(String[] args) {
		TcpGuiServer f = new TcpGuiServer();
		f.setVisible(true);
		f.startMain();

	}

	private void startMain() {
		System.out.println("[=======server======]\n\n");
		
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println(MyUtil.getTime()+"서버 준비 완료 \n");
			
			//클라이언트에서 요청 들어오면 쓰레드 생성
			System.out.println("연결요청 기다림");
			Socket socket = serverSocket.accept();
			System.out.println(socket.getInetAddress()+", "+socket.getPort()+"에서 연결요청 들어옴");
			
			name = "Server";
			
			//출력용 스트림
			dos = new DataOutputStream(socket.getOutputStream());
			
			//쓰레드 실행
			ServerReceiver th = new ServerReceiver(socket);
			th.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	class ServerReceiver extends Thread{
		Socket socket;
		DataInputStream dis;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			//입력용 스트림
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void run() {
				try {
					while(dis!=null) {
						String data = dis.readUTF();
						taList.append(data+"\n");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btSend || e.getSource()==tfChat) {
			try {
				if(dos!= null) {
					dos.writeUTF("["+name + "] " + tfChat.getText()+ "\n");
					tfChat.setText("");
					tfChat.requestFocus();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}

```

### 클라이언트
```java
package com.nw.day1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TcpGuiClient extends JFrame implements ActionListener {

	JTextArea taList;
	JScrollPane scrollPane;
	JTextField tfChat;
	JButton btSend;
	JPanel pl;
	
	//Socket socket;
	DataOutputStream dos;
	String name;
	
	
	public TcpGuiClient(){
		super("[Client");
		
		taList = new JTextArea();
		taList.setEditable(false);
		
		scrollPane = new JScrollPane(taList);
		pl=new JPanel(new BorderLayout());
		tfChat = new JTextField();
		btSend = new JButton("전송");
		pl.add(btSend,"East");
		pl.add(tfChat,"Center");
		
		add(scrollPane, "Center");
		add(pl,"South");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		
		tfChat.addActionListener(this);
		btSend.addActionListener(this);
	}

	public static void main(String[] args) {
		TcpGuiClient f = new TcpGuiClient();
		f.setVisible(true);
		f.startMain();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btSend || e.getSource()==tfChat) {
				try {
					if(dos!=null) {
						dos.writeUTF("["+name+"]"+tfChat.getText()+"\n");
						tfChat.setText("");
						tfChat.requestFocus();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
	}
	public void startMain() {
		name = JOptionPane.showInputDialog(this, "닉네임 입력");
		this.setTitle("[client -"+name+"]");
		
		try {
			Socket socket = new Socket("192.168.0.66",8888);
			taList.append(MyUtil.getTime()+"서버에 연결됨");
			
			dos = new DataOutputStream(socket.getOutputStream());
			
			//입력용 쓰레드 실행
			ClientReceiver th = new ClientReceiver(socket);
			th.start();
		}catch (IOException e) {
			
		}
	}
	class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream dis;
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				//입력용 스트림
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void run() {
			while(dis!=null) {
				String data;
				try {
					data = dis.readUTF();
					taList.append(data+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

```

## 3. UDP 소켓프로그래밍
- TCP에서는 Socket과 ServerSocket 사용
- UDP에서는 DatagramSocket과 DatagramPacket을 사용
- 연결요청을 받아줄 서버소켓이 필요 없다
- DatagramPacket을 주고받는다   

### 클라이언트
```java
package com.nw.day1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {

	public static void main(String[] args) {
		/*
		 * 데이터 그램 소켓 생성 후 소켓 객체의 send() 메서드에
		 * 데이터 그램 패킷 객체를 매개변수로 넣어서 전송
		 */
		
		int port = 3000;
		try {
			InetAddress inet = InetAddress.getByName("192.168.0.66");
			
			Scanner sc= new Scanner(System.in);
			String msg = "";
			System.out.println("보낼 내용 입력:");
			DatagramPacket packet = null;
			DatagramSocket socket = new DatagramSocket();
			
			while((msg=sc.nextLine())!=null) {
				if(msg.equalsIgnoreCase("x")) break;
				
				byte[] data = msg.getBytes();
				packet = new DatagramPacket(data,data.length,inet,port);
				socket.send(packet);
				System.out.println("보낼내용 입력:");
			}
					
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

```
### 서버
```java
package com.nw.day1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {
		byte[] buffer = new byte[100];
		//클라이언트가 보낸 데이터를 담아줄 바이트 배열 생성
		
		
		try {
			DatagramSocket socket = new DatagramSocket(3000);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			while(true) {
				//datagramsocket의 receive()메서드를 이용하여 패킷을 받는다
				socket.receive(packet);
				
				byte bmsg[] = packet.getData();
				//버퍼배열에 담긴 데이터를 문자열로 만들어 출력
				String msg = new String(bmsg, 0, packet.getLength());
				//String(byte[] bytes, int offset, int length)
				
				System.out.println(packet.getAddress()+"로부터 "+packet.getPort()+"번 포트에서 온 메세지:"+msg);
			}
		}catch(SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

```
