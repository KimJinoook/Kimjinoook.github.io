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
