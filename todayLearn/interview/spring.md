# 스프링
***
# 1. 스프링 프레임워크란?
- 자바 플랫폼을 위한 오픈소스 애플리케이션 프레임워크
- 동적인 웹 사이트 개발하기 위한 여러 가지 서비스를 제공함
- 대한민국 공공기관의 웹 서비스 개발 시 사용을 권장하고 있는 전자정부 표준프레임워크의 기반 기술.

***

# 2. 스프링프레임워크의 특징은?
- DI (Dependency Injection) 의존성 주입
  - 설정 파일이나 어노테이션을 통해 객체간의 의존 관계를 설정하여 개발자가 직접 의존하는 객체를 생성할 필요없음
- Spring AOP(Aspect Oriented Programming) 관점 지향 프로그래밍
  - 트랜잭션, 로깅, 보안 등 여러 모듈, 여러 계층에서 공통으로 필요로 하는 기능의 경우 해당 기능을 분리해 관리.
- POJO(Plain Old Java Object)
  - 일반적인 J2EE 프레임워크에 비해 특정 라이브러리를 사용할 필요가 없어 개발이 쉬움
  - 기존 라이브러리의 지원이 용이
- IOC(Inversion of Control) 제어 반전
  - 컨트롤의 제어권이 개발자가 아니라 프레임워크에 있다는 뜻.
  - 객체의 생성부터 모든 생명주기의 관리까지 프레임워크가 주도하고 있음.
  - 객체를 생성하고, 직접 호출하는 프로그램이 아니라, 만들어둔 자원을 호출해서 사용.

***

# 3.Spring AOP가 무엇인지 OOP와 AOP를 비교하여 설명하시오.
- AOP는 Aspect Oriented Programming의 약자로, 관점 지향 프로그래밍이라고 합니다. 애플리케이션의 핵심적인 기능과 부가적인 기능을 분리해 Aspect라는 모듈로 만들어 설계하고 개발하는 방법입니다. OOP는 Object Oriented Programming의 약자로 객체 지향 프로그래밍이라고 합니다. OOP와 AOP는 서로 상반되는 개념은 아니며 오히려 OOP를 더욱 OOP답게 사용할 수 있도록 하는 것이 AOP입니다.

- 공통적 기능을 모든 모듈에 적용하기 위한 방법으로 상속을 이용하는데 Java에서는 다중 상속이 불가능합니다. 그리고 기능 구현 부분에서 핵심 코드와 공통 기능 코드가 섞여있어서 보기에도 불편하고, 효율성이 떨어집니다. 이러한 이유로 AOP가 등장했습니다.

***

# 4. Spring MVC
- 데이터와 화면간의 의존관계를 벗어날 수 있게하는 개발 기법

- Model - 논리적 데이터 기반 구조를 표현, 사용자 인터페이스에 관한 어떠한 정보도 가지고 있지 않음 (data 처리와 접근을 담당)
- View - 사용자 인터페이스 내의 구성요소들을 표현 (사용자에게 보여지는 화면)
- Controller - Model과 View를 연결하고 있는 클래스를 대표 (Model과 View 내의 클래스들 간 정보교환)

***

# 5. WAS와 WS의 차이
- WAS(Web Application Server) : 비지니스 로직을 넣을 수 있음 (Tomcat, PHP, ASP, Net 등)
- WS(Web Server) : 비지니스 로직을 넣을 수 없음 (Nginx, Apache 등)

***

# 6. Spring 동작방식이란?
1. DispatcherServlet이 브라우저로부터 요청을 받는다.
2. DispatcherServlet은 요청된 URL을 HandlerMapping 객체에 넘기고,
호출해야 할 Controller 메소드(핸들러) 정보를 얻는다. 
3. DispatcherServlet이 HandlerAdapter 객체를 가져온다. 
4. HandlerAdapter 객체의 메소드를 실행한다.  
5. Controller 객체는 비즈니스 로직을 처리하고, 그 결과를 바탕으로 뷰(ex. JSP)에 전달할 객체를 Model 객체에 저장한다. DispatcherServlet에게 view name을 리턴한다.
6. DispatcherServlet은 view name을 View Resolver에게 전달하여 View 객체를 얻는다.
7. DispatcherServlet은 View 객체에 화면 표시를 의뢰한다.
8. View 객체는 해당하는 뷰(ex. JSP, Thymeleaf)를 호출하며, 뷰는 Model 객체에서 화면 표시에 필요한 객체를 가져와 화면 표시를 처리한다.

***

# 7. model1과 model2 패턴의 차이를 설명하라.
- model1은 뷰와 로직을 모두 JSP페이지 하나에서 처리하는 구조를 말하며
- model2는 JSP페이지와 서블릿, 그리고 로직을 위한 클래스가 나뉘어 브라우저 요청을 한다.

***

# 8. Servlet vs JSP
- Servlet
  - 자바 언어로 웹 개발을 위해 만들어진 것
  - Container가 이해할 수 있게 구성된 순수 자바 코드로만 이루어진 것

- JSP
  - html기반에 JAVA코드를 블록화하여 삽입한 것
  - Servlet을 좀 더 쉽게 접근할 수 있도록 만들어 진 것

***

# 9. JSTL은 무엇이고 사용하는 이유는 무엇인가?
- JSP 표준 태그 라이브러리의 약어로써, 자신만의 태그를 추가할 수 있는 기능을 제공한다.
- 주로 JSTL의 core에서 c를 사용하여 <c:if> <c:forEach> 등으로 사용한다.

***

# 10. json VS xml
- json
  - JSON은 경량(Lightweight)의 DATA-교환 형식, 데이터를 저장하고 전달하는 메타언어입니다.
  - Javascript에서 객체를 만들 때 사용하는 표현식을 의미한다.
  - 장점 : JSON은 문자열을 전송받은 후에 해당 문자열을 바로 파싱하므로, XML보다 빠른 속도를 가지고 있습니다.
  - 단점 : JSON은 개발자가 문자열 데이터의 무결성을 검증해야 합니다.

- xml
  - XML은 HTML과 매우 비슷한 문자 기반의 마크업 언어(text-based markup language)
  - 장점 : 스키마를 사용하여 데이터의 무결성을 검증할 수 있습니다.
  - 단점 : XML은 배열을 사용할 수 없고 (JSON은 배열 사용 가능), 데이터를 읽고 쓰는 것이 JSON 대비 느립니다.

***

# 11. 동기 VS 비동기식
- 동기식 - 요청과 결과가 동시에 이루어지는 것. 설계가 간단하지만 결과가 주어질 때까지 아무것도 못하고 대기해야 하므로 비동기식 보다 비효율적이다.

- 비동기식 - 요청과 결과가 동시에 이루어지지 않는 것. 하나의 요청을 처리하는 동안 다른 요청도 처리가능. 동기보다 복잡하고 결과가 주어지는데 시간이 걸리더라도 그동안 다른 작업을 할 수 있으므로 자원을 효율적으로 사용할 수 있음.

***

# 12. AJAX란? 장단점
- Ajax는 JavaScript의 라이브러리중 하나이며 Asynchronous Javascript And Xml(비동기식 자바스크립트와 xml)의 약자이다. 전체 페이지를 새로 고치지 않고도 페이지의 일부만을 위한 데이터를 로드하는 기법 이며 Ajax를 한마디로 정의하자면 JavaScript를 사용한 비동기 통신, 클라이언트와 서버간에 XML 데이터를 주고받는 기술이라고 할 수 있겠습니다.

- 장점
  - 전체 페이지를 갱신하지 않고 일부분만 업데이트 가능
  - 사용자에게 즉각적인 반응과 풍부한 UI경험 제공 가능
  - ActiveX나 플러그인 프로그램 설치 없이 이용 가능
  - javascript방식, jQuery방식으로 구현 가능

- 단점
  - JavaScript이므로 브라우저에 따른 크로스 브라우저 처리 필요
  - 오픈 소스로 차별화가 어려움
  - 연속적인 데이터 요청 시 서버 부하 증가하여 페이지가 느려짐
  - 페이지 내 복잡도가 증가하여 에러 발생 시 디버깅이 어려움

***

# 13. JQuery란?
- 자바스크립트 라이브러리로 간단한 문법으로 ajax를 사용할 수 있음.

***

# 14. 스프링 VS 스프링 부트
- Embed Tomcat을 사용하기 때문에, (Spring Boot 내부에 Tomcat이 포함되어있다.) 따로 Tomcat을 설치하거나 매번 버전을 관리해 주어야 하는 수고로움을 덜어준다.

- starter을 통한 dependency 자동화 :
  - 아마 Spring 유저들이 가장 열광한 기능이 아닐까 싶다. 과거 Spring framework에서는 각각의 dependency들의 호환되는 버전을 일일이 맞추어 주어야 했고, 때문에 하나의 버전을 올리고자 하면 다른 dependeny에 까지 영향을 미쳐 version관리에 어려움이 많았다. 하지만, 이제 starter가 대부분의 dependency를 관리해주기 때문에 이러한 걱정을 많이 덜게 되었다.
  - XML설정을 하지 않아도 된다.
  - jar file을 이용해 자바 옵션만으로 손쉽게 배포가 가능하다.
  - Spring Actuaor를 이용한 애플리케이션의 모니터링과 관리를 제공한다.
