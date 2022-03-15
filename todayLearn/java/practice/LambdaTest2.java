package com.day25.etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import static java.lang.System.out;

public class LambdaTest2 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1.ȫ�浿");
		list.add("2.��浿");
		list.add("3.�̱浿");
		list.add("4.�ڱ浿");
		list.add("5.���浿");
		
		// Iterator�� ��ȸ���̱� ������ �ѹ��� ����� �� ����
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			String s = iter.next();
			System.out.println(s);
		}

		// �ٽ� ������
		iter=list.iterator();
	
		//forEachRemaining �̿�
		System.out.println("\n===========");
		
		//void forEachRemaining(Consumer<? super String> action)
		iter.forEachRemaining(n->System.out.println(n));
		
		
		System.out.println();
		iter=list.iterator();
		iter.forEachRemaining(out::println);
		
		iter=list.iterator();
		iter.forEachRemaining(new Consumer<String>() {
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		iter = list.iterator();
		Map<String, String> map = new HashMap<String, String>();
		iter.forEachRemaining(name->map.put(name, name));
		map.forEach((k, v)->System.out.println(k+":"+v));
		
		Map<Integer, String> map2 = new HashMap<Integer, String>();
		map2.put(1, "java");
		map2.put(2, "spring");
		map2.put(3, "oracle");
		
		System.out.println("\n=======map2=======");
		map2.forEach((k,v)->System.out.println(k+"=>"+v));
		map2.keySet().iterator().forEachRemaining(k->System.out.println(k+" - "+map2.get(k)));
		
		System.out.println("\n=======optional======");
		Optional<String> str = map2.values().stream().filter(s->s.equals("spring")).findAny();
		
		if(str.isPresent()) {
			System.out.println(str.get());
		}
		
		Member m = func1();
		if(m!=null) {
			m.showInfo();
		}
		
		Member m2 = func2();
		if(m2!=null) {
			m2.showInfo();
		}else {
			System.out.println("null");
		}
		
		Optional<Member> opt = func3();
		if(opt.isPresent()) {
			Member m3 = opt.get();
			m3.showInfo();
		}
		

	
	
	}
	public static Member func1() {
		Member m =new Member(1, "ȫ�浿");
		return m;
	}
	public static Member func2() {
		Member m =null;
		return m;
	}
	public static Optional<Member> func3() {
		Member m =new Member(2, "��浿");
		return Optional.ofNullable(m);
	}
	
}

class Member{
	private int no;
	private String name;
	
	
	Member(int no, String name){
		this.no = no;
		this.name = name;
	}
	public void showInfo() {
		System.out.println("��ȣ : "+no);
		System.out.println("�̸� : "+name+"\n");
	}
}