---
layout: post
title:  "4. db연결"
subtitle:   ""
categories: framework
tags: springboot
comments: false
header-img: 
---

```java
package com.metaus.configuration;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
public class DBConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/config/mybatis/mapper/oracle/*.xml"));
		factoryBean.setTypeAliasesPackage("com.metaus");
		factoryBean.setConfiguration(mybatisConfg());
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfg() {
		return new org.apache.ibatis.session.Configuration();
	}

	//tx:annotation-driven 설정-@Transactional를 선언하여 트랜잭션 처리를 할 수 있다.
	@Bean
	public PlatformTransactionManager txManager() throws Exception{
		return new DataSourceTransactionManager(dataSource());
	}
		
}

```

## 어노테이션 및 메서드
- @Configuration
  - 해당 어노테이션이 지정된 클래스를 자바 기반의 설정파일로 인식
- @PropertySource
  - 해당 클래스에서 참조할 properties 파일 위치를 지정
- @Autowired
  - 빈으로 등록된 인스턴스를 클래스에 주입하는데 사용
  - @Resource, @Inject 등
- ApplicationContext
  - 스프링 컨테이너 중 하나
  - 빈의 생성과 사용, 관계, 생명 주기등을 관리
- @bBean
  - Configuration 클래스의 메서드 레벨에만 지정 가능
  - 컨테이너에 의해 관리되는 빈으로 등록된다
- @ConfigurationProperties
  - 인자에 prefix 속성 지정 가능
  - prefix에 spring.datasource.hikari를 지정
    - @PropertySource에 지정된 설정파일(application.properties)에서 prefix에 해당하는, spring,datasource.hikari로 시작하는 설정을 모두 읽어들여 해당 메서드에 매핑한다
    - 해당 어노테이션은 메서드 뿐 아니라 클래스 레벨에도 지정 가능



