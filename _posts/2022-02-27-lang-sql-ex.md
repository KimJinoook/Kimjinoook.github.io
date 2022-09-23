---
layout: post
title:  "ex. 기타"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

# 바인드 입력
```sql
select case (select count(*) from member where id=:id and passwd=:pwd)
        when 1 then '로그인 성공'
        else
            case (select count(*) from member where id=:id)
            when 1 then '비밀번호 불일치'
            else '해당 아이디가 존재하지 않음'
            end
        end 로그인
from dual;

--:id, :pwd에 바인드된 값
```

## 각종 참조 시스템 뷰
- USER_TABLES : 해당 사용자가 생성한 테이블 내역
- USER_CONSTRAINTS
- USER_INDEXES
- USER_OBJECTS
- user_source : 저장프로시저, 함수 확인
