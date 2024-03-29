---
layout: post
title:  "8. I/O"
subtitle:   ""
categories: lang
tags: java2
comments: false
header-img: 
---

## 1. 자바에서의 입출력
- I/O (input, output)
  - 컴퓨터 내부 또는 외부의 장치와 프로그램 간의 데이터를 주고받는 것
  - System.our.println 등
- Stream
  - 입출력, 즉 어느 한쪽에서 다른쪽으로 데이터를 전달
  - 두 대상을 연결하고 데이터를 전송할 수 있는 통로 = 스트림
  - 단방향 통신만 가능
  - 하나의 스트림으로 입력과 출력을 동시에 할 수 없다   
  > 데이터 소스 - 입력스트림 - 자바프로그램 - 출력스트림 - 데이터목적지   

### 스트림의 구분
- 다루는 데이터에 따라
  - byte 기반 스트림 (1byte 단위로 데이터 이동)
    - InputStream / OutputStream
  - 문자(char)기반 스트림 (2byte단위로 데이터 이동)
    - Reader / Writer
- 데이터 가공에 따라
  - Node 스트림
    - 데이터 소스와 직접 연결 가능한 스트림
  - Filter 스트림
    - 데이터 소스에 직접 연결 불가
    - 노드스트림을 가공하는 역할
    - 스트림의 기능을 보완하기 위한 보조스트림
    - 노드스트림과 연결해서 써야한다   
![구분](https://user-images.githubusercontent.com/99188096/162901880-b8330f9f-7cdd-4cba-a12e-5b6bdce94ac4.PNG)   


***

## 2. 바이트 기반 스트림
![바이트기반스트림](https://user-images.githubusercontent.com/99188096/162896288-8d63fdd8-181a-45a9-ac50-ba1f02193618.PNG)   

- 스트림은 바이트 단위로 데이터를 전송한다
- inputStream, OutputStream의 자손클래스들 보유
- 읽고 쓰는데 필요한 추상메서드를 자신에 맞게 구현   
- flush()
	- 버퍼가 있는 출력스트림의 경우에만 의미가 있으며, outputStram에 저장된 flush()는 아무런 일도 하지 않는다
- 프로그램이 종료될 때. 사용하고 닫지 않은 스트림을 jvm이 자동적으로 닫아준다
	- 권장 : 작업을 마친후 close()를 호출해 반드시 닫아준다   



```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class iotest1 {

	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("text/poetry2.txt");
			int data = 0;
			
			//int read() throws IOException
			// - inputStream 에서 1바이트씩 읽어온다
			// - 아스키코드를 리턴한다
			// - 더이상 읽어올 것이 없으면 -1을 리턴한다
			
			while((data=fis.read())!=-1) {
				char ch = (char)data;
				System.out.println(ch);//char 단위로 출력
        //1바이트씩 읽어오지만, 한글은 2바이트라 한글은 깨진다
				//System.out.println(data); //아스키코드 출력
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}

```

### InputStream
![인풋스트림](https://user-images.githubusercontent.com/99188096/162898715-9501550b-ca98-466b-a966-c4fa1a986251.PNG)   
```java
package com.io.day1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStream {

	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("text/poetry2.txt");
			int data = 0, count = 0;
			while((data=fis.read())!=-1) {
				char ch = (char)data;
				System.out.println(ch);
				count++;
			}
			System.out.println("반복횟수:"+count);
			File file = new File("text/poetry2.txt");
			System.out.println("파일의 바이트수 : " +file.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis!=null)
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
		}

	}

}

```



### OutputStream
![아웃풋스트림](https://user-images.githubusercontent.com/99188096/162902101-f07e2e32-6b6c-4180-af8e-0cb693de9188.PNG)   

```java
package com.io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputTest {

	public static void main(String[] args) {
		//파일에서 읽어서 파일로 출력, 바이트 기반
		/*
		 	입력 : FileInputStream
		 	출력 : FileOutputStream
		 */
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("text/poetry2.txt");
			fos = new FileOutputStream("text/poetry2.bak");
			
			int data = 0;
			while((data=fis.read())!=-1) {
				fos.write(data); //1바이트씩 출력
				//void write(int b) : 바이트 단위로 출력
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) fis.close();
				if(fos!=null) fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

```
```java
package com.io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputTest2 {

	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("text/poetry4.txt");
			fos = new FileOutputStream("text/poetry4.bak");
			
			int cnt = 0, count = 0, total = 0;
			byte[] buf = new byte[1024];
			while ((cnt=fis.read(buf))!=-1) { //inputStream에서 읽어서 byte배열에 넣는다
				System.out.write(buf,0,cnt); //바이트배열에서 시작위치 0에서 cnt개만큼 출력
				//buf배열에서 0부터 cnt개 읽어 출력소스에 쓴다
				fos.write(buf,0,cnt);
				
				System.out.println("cnt : "+cnt);
				total+=cnt;//1024+1009
				count++; //1
				System.out.println("total : "+total);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

```

***

## 3. 바이트기반 보조 스트림
- 스트림의 기능을 보완하기 위한 보조스트림
- 데이터를 입출력할 수 있는 기능이 없다
- 스트림을 먼저 생성한 후 이를 이용해 보조스트림 생성   
> 데이터 소스 - 입력스트림 - 보조스트림(필터스트림) - 자바프로그램   
> 자바프로그램 - 보조스트림(필터스트림) - 출력스트림 - 데이터 목적지   

![보조스트림](https://user-images.githubusercontent.com/99188096/162897541-637b2f2f-38fe-44db-836f-1bafe5531b40.PNG)   

- Filter i/o Stream의 자손클래스들

### Buffered I/O Stream
- 스트림의 입출력 효율을 높이기 위해 버퍼를 사용하는 보조스트림
- 한바이트씩 입력하는 거보다 버퍼를 이용해 한번에 여러 바이트를 입출력하는 것이 빠르다
- BufferedInputStream (InputStream in, int size)
	- 주어진 in을 입력소스로 하며, 지정된 크기의 버퍼를 갖는 인스턴스를 생성
	- 사이즈의 기본값은 2048byte
	- 버퍼의 크기는 입력소스로부터 한번에 가져올 수 있는 데이터의 크기가 좋다
	- read() 호출시 소스로부터 버퍼크기만큼 데이터를 읽어 자신의 **내부버퍼**에 저장
	- 프로그램에서는 bufferedinputstream의 버퍼에 저장된 데이터를 읽는다
	- 외부가 아닌, 내부버퍼로부터 읽기 때문에 속도가 빠르다
- BufferedOutputStream (OutputStream out, int size)
	- flush() : 버퍼의 모든 내용을 출력소스에 출력한 다음, 버퍼를 비운다
	- close() : flush()를 호출해 출력한 후 인스턴스가 사용하던 자원을 반환
	- write를 이용한 출력이 bufferedOutputStream의 버퍼에 저장된다
	- 버퍼가 가듣차면 그 때 버퍼의 모든 내용을 출력소스에 출력한다
	- 버퍼를 비우고 다시 프로그램의 출력을 저장할 준비를 한다
	- 가득찼을 때만 출력소스에 출력하기 때문에, close나 flush를 이용해야한다   

```java
package com.io.day2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferdTest1 {

	public static void main(String[] args) {
		/*
		 *파일에서 데이터를 읽어와서 화면출력 - 보조스트림 이용, 바이트 기반
		 * 
		 */
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		try {
			fis=new FileInputStream("text/poetry2.txt");
			bis = new BufferedInputStream(fis,1024);
			
			int data = 0;
			while((data=bis.read())!=-1) {
				System.out.println((char)data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(bis!=null) bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

```
```java
package com.io.day2;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutTest {

	public static void main(String[] args) {
		//파일 123.txt에 1부터 9까지 출력, 바이트기반, 버퍼이용
		/*
		 * FileOutputStream
		 * BufferedOutputStream
		 */
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("text/123.txt");
			bos = new BufferedOutputStream(fos, 5); //버퍼크기 5
			
			for(int i='1' ; i<= '9' ; i++) {
				bos.write(i);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bos.close(); //fos.close로 닫을 시 12345만 출력
				// 버퍼가 가득차야 출력하기 때문에, 12345 이후
				// 6789는 버퍼가 가득차지않아 출력핮 않는다
				//bos로 닫으며 버퍼에 남아있는 나머지 6789를 출력
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("파일저장");
	}

}
```

### Data I/O Stream
- byte가 아닌, 기본자료형의 단위로 읽고 쓸 수 있다
- 각 기본자료형 값을 16진수로 표현하여 저장
	- int 값을 출력한다면 4byte의 16진수로 출력
- ![캡처](https://user-images.githubusercontent.com/99188096/163082752-8fd3c2b2-cfa1-4ff3-997a-1bcc0921313d.PNG)   
- ![캡처2](https://user-images.githubusercontent.com/99188096/163082765-3e863331-f144-4aa7-b50d-fb498c6abb5b.PNG)   

```java
package com.io.day2;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputTest {

	public static void main(String[] args) {
		// 파일에 출력, 기본자료형 단위로, 바이트기반
		// FileOutStream
		// DataOutputStream
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			fos = new FileOutputStream("text/sample.dat");
			dos = new DataOutputStream(fos);
			
			dos.writeInt(20); //4byte
			dos.writeFloat(30.0f); //4byte
			dos.writeBoolean(true); //1byte
			dos.writeChar('A'); //2b
			dos.writeLong(123); //8b
			dos.writeUTF("Hello"); //유니코드의 utf-8형식으로 문자열을 출력
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(dos!=null) dos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		// 2진 데이타로 저장
		System.out.println("저장완료");
		
	}

}

```
```java
package com.io.day2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataInputTest {

	public static void main(String[] args) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream("text/sample.dat");
			dis = new DataInputStream(fis);
			
			System.out.println(dis.readInt());
			System.out.println(dis.readFloat());
			System.out.println(dis.readBoolean());
			System.out.println(dis.readChar());
			System.out.println(dis.readLong()); //저장순서로 읽어온다
			
			String str = dis.readUTF();
			System.out.println(str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(dis!=null) dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

```
- EOFException 활용   

```java
package com.io.day2;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputTest2 {

	public static void main(String[] args) {
		int [] score = {99,85,77,62,50,100};
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			fos = new FileOutputStream("text/score.dat");
			dos = new DataOutputStream(fos);
			
			for (int i=0; i<score.length; i++) {
				dos.writeInt(score[i]);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(dos!=null) dos.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		System.out.println("저장");
	}

}

```
```java
package com.io.day2;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataInputTest2 {

	public static void main(String[] args) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		int sum = 0;
		try {
			fis = new FileInputStream("text/score.dat");
			dis = new DataInputStream(fis);
			
			while(true) {
				int score = dis.readInt();
				System.out.println(score + "\t");
				
				sum+=score;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(EOFException e) {
			System.out.println("점수 총합 : "+ sum);
			//readInt()와 같이 데이터를 읽는 메서드는
			//읽을 데이터가 업으면 EOFException을 발새시킨다
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}

```

***

## 4. 문자 기반 스트림
- 문자 데이터를 다루는데 사용
- 여러 종류의 인코딩과 자바에서 사용하는 유니코드간의 변환을 자동으로 처리
	- Reader : 특정 인코딩을 읽어 유니코드로 변환
	- Writer : 유니코드를 특정 인코딩으로 변환하여 저장
- Reader, Writer
	- 문자 기반 스트림의 조상클래스
	- byte 대신 char배열을 이용하는것 외에는 바이트기반과 동일
### FileReader, FileWriter
```java
package com.io.day2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderTest1 {

	public static void main(String[] args) {
		String fileName = "text/test.txt";
		FileInputStream fis = null;
		
		int data = 0;
		try {
			fis = new FileInputStream(fileName);
			while((data=fis.read())!=-1) {
				System.out.print((char)data);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("\n==문자기반==");
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(fileName);
			
			while((data=fr.read())!=-1) {
				System.out.print((char)data);
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}finally {
			try {
				if(fr!=null) fr.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}

```

- reader, writer   

```java
package com.io.day2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriterTest1 {

	public static void main(String[] args) {
		//문자기반, 파일에서 읽어서 파일에 출력
		// FileReader
		// FileWriter
		
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			fr = new FileReader("text/poetry.txt");
			fw = new FileWriter("text/poetry.bak");
			
			int data = 0;
			while((data=fr.read())!=-1) {
				fw.write(data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(fr!=null) fr.close();
				if(fw!=null) fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("저장");
		
		FileReader fr2 = null;
		FileWriter fw2 = null;
		try {
			fr2 = new FileReader("text/poetry4.txt");
			fw2 = new FileWriter("text/poetry4.bak");
			
			char[] buf = new char[1024];
			int cnt = 0;
			while((cnt=fr2.read(buf))!=-1) {
				fw2.write(buf,0,cnt);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(fr2!=null) fr2.close();
				if(fw2!=null) fw2.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}

```

***

## 5. 문자기반 보조스트림
### BufferedReader / BufferedWriter
- 버퍼를 이용해 입출력 효율 증가
- Reader 의 readLine()을 사용하면 데이터를 라인단위로 읽어올 수 있다
- Writer의 newLine() : 줄바꿈 메서드   

```java
package com.io.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedTest {

	public static void main(String[] args) {
		// 문자단위, 파일에서 읽어서 화면 출력, 버퍼이용
		// FileReader
		// BufferedReader
		String fileName = "text/WriterTest1.java";
		
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			int data = 0;
			while((data=br.read())!=-1) { //문자 하나씩 읽어온다
				System.out.print((char)data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\n\n==============");
		FileReader fr;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			String line = "";
			while((line = br.readLine())!=null) { //한줄씩 입력
				System.out.print(line+"\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

```

### InputStreamReader / OutputStreamWriter
- 바이트 기반 스트림을 문자기반 스트림으로 연결
- 바이트기반 스트림의 데이터를 지정된 인코딩의 문자데이터로 변환
- 한글 윈도우에서 중국어로 작성된 파일을 읽을 때
	- 일반적인 사용으론 파일이 깨진다
	- InputStreamReader (InputStream in, String encoding)
	- 인코딩이 중국어임을 지정
- os에서 사용하는 인코딩의 종류 확인   
> Properties prop = System.getProperties();   
> System.out.println(prop.get("sun.jnu.encoding"));   

![캡처](https://user-images.githubusercontent.com/99188096/163096179-83dc0894-0cba-407c-a0c5-6535b147e1e4.PNG)   
![캡처2](https://user-images.githubusercontent.com/99188096/163096191-2c6809a9-9cf1-41db-871d-1609eb14787d.PNG)   

- 기본
```java
package com.io.day2;

import java.io.IOException;

public class ReadTest {

	public static void main(String[] args) {
		System.out.println("문자 입력");
		
		try {
			int data=0;
			//키보드로부터 입력받은 값 출력
			while((data = System.in.read())!=-1) { //1바이트씩 읽어옴
				System.out.println(data + ", char = " + (char)data); //한글은 2바이트라 깨져서 나온다
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
```

```java
package com.io.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InReaderTest {

	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = null;
		
		br = new BufferedReader(isr);
		System.out.println("사용중인 os의 인코딩 : "+isr.getEncoding());
		
		System.out.println("\n문장 입력!");
		String line = "";
		try {
			while((line=br.readLine())!=null) {
				System.out.println("입력한 문장 : "+line);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(br!=null) br.close();
			}catch(IOException e) { 
				e.printStackTrace();
			}
		}
		System.out.println("종료");
	}

}

```


***
## 6. 비교
![비교1](https://user-images.githubusercontent.com/99188096/162901990-d4d55aa9-f2bd-4806-92c1-3246d7c41cf4.PNG)   
![비교2](https://user-images.githubusercontent.com/99188096/162901995-03eb39e0-00f2-425a-9265-3cad516f3b7b.PNG)

