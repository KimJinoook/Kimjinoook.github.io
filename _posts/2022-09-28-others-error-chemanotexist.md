---
layout: post
title:  "스키마/테이블 접근 에러, does not exist"
subtitle:   ""
categories: others
tags: error postgresql springboot
comments: false
header-img: 
---

## 스키마 혹은 테이블 접근에러

- 환경 : springboot / postgresql
- 에러 : relation "테이블명" does not exist   

![error](https://user-images.githubusercontent.com/99188096/192695135-0c28bc21-ab6f-4351-8fd9-ffde41df728a.PNG)   

'테이블명' 이름의 관계가 없다
보통 이름에 해당하는 스키마나 테이블이 업을 경우 오류 발생
그런 경우에는 스키마나 테이블을 만들어주면 되지만
스키마, 테이블 모두 만든 생태에서 이런 오류가 발생하면 
원인이 정말 다양해진다
