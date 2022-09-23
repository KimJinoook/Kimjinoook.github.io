---
layout: post
title:  "3. DI / 종속 객체 주입"
subtitle:   ""
categories: lang
tags: spring
comments: false
header-img: 
---

## DI란
- Dependency Injection
- 실제 어플리케이션에서는 두 개 이상의 클래스가 서로 협력하여 비즈니스 로직 수행
- 이 때 각 객체는 협력하는 객체에 대한 레퍼런스(종속객체)를 얻어야 한다
- 그 결과 결합도가 높아지고, 테스트하기 힘든 코드가 만들어지기 쉽다
  > private dao;   
  > dao = new ProductDAO();   
  > ProductDAO가 강하게 결합된다   
- DI 이용
  - 각 객체를 조율하는 제 3자에 의해 생성시점에 종속객체가 부여된다
  - 객체는 종속객체를 생성하거나 얻지 않는다
  - 종속객체는 종속객체가 필요한 객체에 주입된다  
    > private dao;   
    > 이후 dao는 조립기가 di 주입   
  - 객체가 스스로 종속객체를 획득하는 것과는 반대로, 객체에 종속객체가 부여된다   

***
## 스프링 설정파일을 이용한 의존관계 설정
- 조립기가 종속객체를 자동으로 주입해주기 위해 객체 사이의 의존관계를 설정
- 와이어링
### 예시   

```java
public class ArticleServiceImpl{
  private ArticleDao articleDao;
  
  //setter, 주입기가 파라미터에 articleDao를 넣어줄 것이다
  public setArticleDao(ArticleDao articleDao){
    this.articleDao = articleDao;
  }
}

//위 클래스가 전달받게 될 의존객체
public class MySQLArticleDao implements ArticleDao{
  @Override
  public void insert(Article article{
    System.out.println("");
  }
}
```

### 의존관계 설정파일
- applicationContext.xml, 파일을 클래스패스에 위치

```html
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.springframework.org/schema/beans 
  http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

  <bean name=“articleService" class="mysite.spring.di.ArticleServiceImpl">
    <constructor-arg>
      <ref bean="articleDao" />
    </constructor-arg>
  </bean>

  <bean name="articleDao" class="mysite.spring.di.MySQLArticleDao">
  </bean>

</beans>
```
- bean엘리먼트는 스프링에서 가장 기본적인 설정단위, 스프링에게 객체를 만들어달라는 의미
- 스프링은 각 객체를 bean으로 관리한다
- beans
  - 스프링 설정파일의 루트태그
- bean
  - 스프링이 관리할 하나의 객체를 설정하는데 사용
  - name : 빈의 이름을 의미
  - class : 생성될 객체의 클래스 타입
- constructor-arg
  - 빈 객체를 생성할 때 생성자에 전달할 파라미터를 명시하기 위해 사용
    > MySqlArticleDao articleDao = new MySQLArticleDao();   
    > ArticleServiceImpl articleService = new ArticleServiceImpl(articleDao);   


### 클래스패스 설정 및 빈 객체 사용
- 설정파일로부터 bean factory를 생성, bean객체를 가져온다   

```java
package mysite.spring.di;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Main {
  public static void main(String[] args) {
  
    Resource resource = new ClassPathResource("applicationContext.xml");
    BeanFactory beanFactory = new XmlBeanFactory(resource); // 스프링 컨텍스트 로드 (컨테이너)
    
    ArticleServiceImpl articleService = (ArticleServiceImpl) beanFactory.getBean(“articleService"); //ArticleService 빈 얻기
    articleService.write(new Article()); //ArticleService 사용
  }
}
```   
- 또는   

```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml"); // 스프링 컨텍스트 로드

ArticleServiceImpl articleService = (ArticleServiceImpl)context.getBean("articleService"); // ArticleService 빈 얻기

articleService.write(new Article()); // articleService 사용

```   
***
## 흐름
- main()메서드는 applicationContext.xml 파일을 기반으로 스프링 애플리케이션 컨텍스트를 생성
- ID가 articleService인 빈을 조회하기 위해 백토리로 애플리케이션 컨텍스트 사용
- ArticleService 객체에 대한 레퍼런스를 얻은 후 간단히 write()메서드로 호출해 작업 처리
- 이 클래스에서는 ArticleDAO가 어떤 유형의 dao인지 알지 못하며, MySQLArticleDao를 다룬다는 사실도 알지 못한다
  - applicationContext.xml파일로만 알 수 있다

***

## 전체 파일 예시
### Main.java
```java
public static void main(String[] args) {

  /*ArticleServiceImpl1service = new ArticleServiceImpl1();
  Article article = new Article();
  article.setNo(1);
  article.setTitle("안녕");
  service.write(article); */
  
  
  //applicationContext.xml 스프링 설정파일에서 해당 빈 객체를 얻어온다
  //1. 스프링 컨텍스트 로드
  ApplicationContext context 
    =new ClassPathXmlApplicationContext("applicationContext.xml");
  //Resource resource = new ClassPathResource("applicationContext.xml");
  //BeanFactory context = new XmlBeanFactory(resource);
  
  
  //2. 빈(ArticleServiceImpl2) 얻어오기
  ArticleService articleService
    = (ArticleService)context.getBean("articleServiceImpl2");
  
  
  //3. 빈 사용하기
  Article article = new Article();
  article.setNo(2);
  article.setTitle("생성자에서 종속객체 주입 받는 경우");
  articleService.write(article);
  
  //3. setter에서 종속객체를 주입한 경우
  ApplicationContext context 
    = new ClassPathXmlApplicationContext("applicationContext.xml");
  ArticleService articleService3 
    = (ArticleService) context.getBean("articleServiceImpl3");
  Article bean = new Article();
  bean.setNo(3);
  bean.setTitle("setter 에서 종속객체 주입");
  articleService3.write(bean);
}
```   

### applicationContext.xml
```html
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:p="http://www.springframework.org/schema/p"
  xsi:schemaLocation="http://www.springframework.org/schema/beans 
  http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

   <bean name="articleServiceImpl2" class="mysite.spring.di.ArticleServiceImpl2">
    <constructor-arg>
      <ref bean="mySqlArticleDAO"/>
    </constructor-arg>
  </bean>

  <!-- <bean name="articleServiceImpl3" class="mysite.spring.di.ArticleServiceImpl3">
          <property name="articleDao" ref ="mySqlArticleDAO"/>
        </bean> -->

  <bean name="articleServiceImpl3" class="mysite.spring.di.ArticleServiceImpl3" p:articleDao-ref ="mySqlArticleDAO"/>
  <bean name="oracleArticleDAO" class="mysite.spring.di.OracleArticleDAO"></bean>
  <bean name="mySqlArticleDAO" class="mysite.spring.di.MySqlArticleDAO"></bean>
```   

### Article, ArticleDao   

```java
package mysite.spring.di;

public class Article {
  public int no;
  public String title;
  
  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }
}


public interface ArticleDao{
  void insert(Article article);
}

```   

### 클래스들
```java

package mysite.spring.di;

public class MySqlArticleDAO implements ArticleDAO{
  @Override
  public void insert(Article article) {
    System.out.println("MySql db에서 테이블에 insert한다 : mySqlDao.insert()");
  }
}

public class OracleArticleDAO implements ArticleDAO{
  @Override
  public void insert(Article article) {
    System.out.println("oracle db에 접속해서 테이블에 insert한다 : oracleDao.insert()");
  }
}

public class ArticleServiceImpl{ implements WriteArticleService{
  private ArticleDAO articleDao;
  //생성자
  public ArticleServiceImpl(){
    //dao 객체가 필요하므로 직접 객체 생성해서 사용함
    articleDao = new OracleArticleDAO();
  }
  @Override
  public void write(Article article) {
    System.out.println("articleServiceImpl.write() 메서드 호출");
    articleDao.insert(article);
  }
}

public class ArticleServiceImpl2{
  private ArticleDAO articleDao;
  //DI를 이용하여 dao객체를 직접 생성하지 않고, 외부 조립기가 주입하도록 한다
  //생성자 방식 - 생성자를 이용한 종속객체 주입
  public ArticleServiceImpl2(ArticleDAO articleDao){
  //생성자의 매개변수에 필요한 객체를 지정함
    this.articleDao = articleDao; 
  }
  @Override
  public void write(Article article) {
    System.out.println("articleServiceImpl2.write() 메서드 호출");
    articleDao.insert(article);
  }
}

public class ArticleServiceImpl3 {
  //멤버변수-프로퍼티
  private ArticleDAO articleDao;
  //DI를 이용하여 dao객체를 직접 생성하지 않고, 외부 조립기가 주입하도록 한다
  //프로퍼티 설정 방식 - setter를 이용한 종속객체 주입
  public void setArticleDao(ArticleDAO articleDao){
    //setter의 매개변수에 필요한 객체를 지정함
    this.articleDao = articleDao; 
  }
  @Override
  public void write(Article article) {
    System.out.println("articleServiceImpl3.write() 메서드 호출");
    articleDao.insert(article);
  }
}
```
