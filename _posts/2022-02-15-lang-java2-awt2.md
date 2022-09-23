---
layout: post
title:  "4. 메뉴컴포넌트"
subtitle:   ""
categories: lang
tags: java2
comments: false
header-img: 
---

## 1. 메뉴만들기
- 폴더에 폴더를 담듯 menu에 menu를 담아 계층형으로 구성
- 화면에 나타나는 최상위 menybar를 frame을 추가함으로써 구성
  - menu에 menuitem 추가
  - menubar에 menu 추가
  - frame에 menubar 포함
- ChectboxMenuItem
  - 메뉴를 클릭할 때마다 메뉴 앞에 체크표시가 설정되거나 해제된다
  - 생성자
    - CheckboxMenuItem(String name, boolean status)
- 메뉴 컴포넌트 포함관계   
  - ![menutree](https://user-images.githubusercontent.com/99188096/161675461-34c98802-1ee0-4ddd-a126-f0d55203d662.PNG)   

- 메뉴 예시   

```java
import java.awt.CheckboxMenuItem;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class MenuTest {

	public static void main(String[] args) {
		Frame f = new Frame("menu");
		f.setSize(300,200);
		
		MenuBar mb = new MenuBar();
		Menu mFile = new Menu("File");
		
		MenuItem miNew = new MenuItem("New");
		MenuItem miOpen = new MenuItem("Open");
		Menu mOthers = new Menu("Othesrs"); //하위 메뉴 추가를 위해 menu
		MenuItem miExit = new MenuItem("Exit");
		
		mFile.add(miNew); //menu에 menuitem 추가
		mFile.add(miOpen);
		mFile.add(mOthers); //menu에 menu 추가
		mFile.addSeparator(); //menu경계선 추가
		mFile.add(miExit);
		
		MenuItem miPrint = new MenuItem("Print...");
		MenuItem miPreview = new MenuItem("Print Preview");
		MenuItem miSetup = new MenuItem("Print setup");
		mOthers.add(miPrint);
		mOthers.add(miPreview);
		mOthers.add(miSetup);
		
		Menu mEdit = new Menu("Edit");
		Menu mView = new Menu("View");
		Menu mHelp = new Menu("Help");
		CheckboxMenuItem miStatus = new CheckboxMenuItem("Statusbar");
		mView.add(miStatus);
		
		mb.add(mFile); //menubar에 menu 추가
		mb.add(mEdit);
		mb.add(mView);
		mb.setHelpMenu(mHelp);
		
		f.setMenuBar(mb); //frame에 menubar 추가
		f.setVisible(true);
	}
}

```
![menutest](https://user-images.githubusercontent.com/99188096/161676297-97e79de9-4d8d-4460-b5c5-2d7b0ae29b35.png)   

***
### PopupMenu
- frame에 고정적인 menubar와는 달리, 마우스 우클릭 시 나타나는 메뉴   

```java
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupMenuTest {

	public static void main(String[] args) {
		Frame f = new Frame("Popup menu");
		f.setSize(300,300);
		
		PopupMenu pMenu = new PopupMenu("Edit");
		MenuItem miCut = new PopupMenu("Cut");
		MenuItem miCopy = new PopupMenu("Copy");
		MenuItem miPaste = new PopupMenu("Paste");
		
		pMenu.add(miCut);
		pMenu.add(miCopy);
		pMenu.add(miPaste);
		
		f.add(pMenu);
		f.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getModifiers()==e.BUTTON3_MASK) {    // 1.왼쪽버튼, 2.휠버튼, 3.오른쪽버튼
					pMenu.show(f, e.getX(), e.getY());
				}
			}
			
		});
		
		f.setVisible(true);
	}
}
```
![popupmenu](https://user-images.githubusercontent.com/99188096/161677259-ef1d695a-9727-4400-96e5-98c036649963.png)


