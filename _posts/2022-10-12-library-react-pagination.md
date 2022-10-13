---
layout: post
title:  "17. 리액트 페이징처리"
subtitle:   ""
categories: library
tags: react
comments: true
header-img: 
---

![p1](https://user-images.githubusercontent.com/99188096/195512954-471bbbf2-7406-4e1c-af67-59edf73c5928.PNG)   

- 리액트의 페이징 처리
- 하단 페이지의 경우 웹페이지의 여러부분에서 공통으로 사용되는 기능이기 때문에, 컴포넌트로 만들어 재사용
- 페이지네이션 컴포넌트에 파라미터 전달 후, 페이지 버튼들을 리턴   
- 백단(스프링부트), 페이지번호를 노출할 부모컴포넌트, 페이지번호를 리턴해주는 pagination컴포넌트 순으로 설명   


***
***

# 1. 백단에서의 데이터 조회   
```java
@RequestMapping(value = "/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do")
@ResponseBody
public HashMap<String, Object> selectCmmnDetailCodeList(@RequestBody CmmnDetailCodeVO searchVO) throws Exception {

    /** 
        페이지번호 클릭 시 searchVO에 이동할 pageIndex가 담아져 올 것이다.
        그 외 검색조건이 있다면, 검색 조건과 키워드가 같이 searchVO에 담아져 온다.
    */
    HashMap<String, Object> result = new HashMap<>(); //리턴값을 담을 map
    
    /** 
        searchVO에 기본 설정파일에서 설정한 상수를 담는다
        기본설정파일이 없다면, 그냥 상수 입력
        pageUnit : 각 페이지당 보여줄 레코드 수 (10)
        pageSize : 각 블럭당 보여줄 페이지 수 (10일 경우 1~10페이지, 11~20페이지...)
    */
    searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    searchVO.setPageSize(propertiesService.getInt("pageSize"));


    /** pageing */
    /** 
        프론트단으로 되돌려줄 페이지 정보
        currentPageNo : 현재페이지를 뜻하며, 우리가 이동할 페이지 (pageIndex)를 삽입한 후 이를 토대로 데이터를 불러올 것이다
        recordCountPerPage : 페이지당 보여줄 레코드 수
        pageSize : 각 블럭당 보여줄 페이지 수
    */
    PaginationInfo paginationInfo = new PaginationInfo();
    paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
    paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
    paginationInfo.setPageSize(searchVO.getPageSize());

    
    /**
        데이터를 불러오기 위한 searchVO설정
        페이지의 첫번째 레코드 인덱스 설정
        페이지의 마지막 레코드 인덱스 설정
        페이지당 보여줄 레코드 수 설정
        페이지인포메이션 클래스에서 내부적으로 계산 후 설정
    */
    searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());  // (현재페이지번호 - 1) * 각페이지당 보여줄 레코드 수;
    searchVO.setLastIndex(paginationInfo.getLastRecordIndex()); // 현재페이지번호 * 각 페이지당 보여줄 레코드 수;
    searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    
    /** 
        searchVO를 이용해 DB에서 데이터 조회
        sql문은 하단 참고
    */
    List<?> CmmnCodeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchVO);
    result.put("resultList", CmmnCodeList);

    
    /** 전체 레코드 수 */
    int totCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(searchVO);
    paginationInfo.setTotalRecordCount(totCnt);
    result.put("paginationInfo", paginationInfo);

    
    /** 반환값에는 현재 페이지 정보와, 레코드를 담아 리턴한다 */
    return result;
}
```   

```xml
<select id="selectCmmnDetailCodeList" parameterType="egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO"   
    resultType="egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO">
		
    <![CDATA[
        SELECT  *
        FROM  (
        	SELECT ROWNUM RNUM, ALL_LIST.*
          	FROM  (
        		/* 구현 Sql */
        		SELECT  A.CODE_ID
             			,  A.CODE
             			,  A.CODE_NM
             			,  A.USE_AT
          		FROM  COMTCCMMNDETAILCODE A
             			,  COMTCCMMNCODE       B
         		WHERE 	B.USE_AT  = 'Y'
           		AND  A.CODE_ID = B.CODE_ID
  ]]>

        <if test="searchCondition == 1">	
		<![CDATA[	
			AND A.CODE_ID LIKE '%' || #{searchKeyword} || '%'	
		]]>
        </if>
        <if test="searchCondition == 2">	
		<![CDATA[	
			AND A.CODE    LIKE '%' || #{searchKeyword} || '%'	
		]]>
        </if>
        <if test="searchCondition == 3">	
		<![CDATA[	
			AND A.CODE_NM LIKE '%' || #{searchKeyword} || '%'	
		]]>
        </if>

   	<![CDATA[
        /* 구현 Sql
            searchVO에 담겨있던 파라미터를 이용해 현재 페이지에 불러올 레코드만 조회한다
        */
                ) ALL_LIST
	 )
         WHERE  RNUM  > #{firstIndex}
         AND  RNUM <= #{firstIndex} + #{recordCountPerPage}
 ]]>

</select>



<!-- 전체 레코드 수 -->
<select id="selectCmmnDetailCodeListTotCnt" parameterType="egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO" resultType="int">

    <![CDATA[
        SELECT  COUNT(*) totcnt
          FROM  COMTCCMMNDETAILCODE A
             ,  COMTCCMMNCODE       B
         WHERE 	B.USE_AT  = 'Y'
           AND  A.CODE_ID = B.CODE_ID
           ]]>

        <if test="searchCondition == 1">	<![CDATA[	AND
            A.CODE_ID LIKE '%' || #{searchKeyword} || '%'	]]>
        </if>
        <if test="searchCondition == 2">	<![CDATA[	AND
            A.CODE    LIKE '%' || #{searchKeyword} || '%'	]]>
        </if>
        <if test="searchCondition == 3">	<![CDATA[	AND
            A.CODE_NM LIKE '%' || #{searchKeyword} || '%'	]]>
        </if>
</select>
```

***
***

# 2. 데이터를 조회할 리액트 페이지   
```javascript
import React from 'react'
import {
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CForm,
  CFormCheck,
  CFormInput,
  CFormLabel,
  CFormSelect,
  CPagination,
  CPaginationItem,
  CTable,
  CTableBody,
  CTableCaption,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
} from '@coreui/react'
import { DocsExample } from 'src/components'
import { useState, useEffect, useContext } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import * as EgovNet from 'src/context/egovFetch';
import {COM013} from 'src/context/CmmCodeDetail';
import {LoginContext} from 'src/App';
import {Pagination} from 'src/context/Pagination'


function DetailCodeManager() {
  const location = useLocation();
  const navigate = useNavigate();
  const [searchVO, setSearchVO] = useState(location.state?.searchVO || { pageIndex: 1, searchCnd: '0', searchKeyword: '' });// 기존 조회에서 접근 했을 시 || 신규로 접근 했을 시
  const [resultList, setResultList] = useState();
  const [pageButton, setPageButton] = useState();


  const retrieveList = (searchVO)=>{
    var url = '/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do'
    var options = {
      
      method: 'POST',
      headers: {
          'Content-type': 'application/json'
      },
      body: JSON.stringify(searchVO)
      
    }

    EgovNet.requestFetch(url,options,
      (resp) => {

          let mutResultList = [];
          
          
          resp.resultList.forEach(function (item, index) {
            if (index === 0) mutResultList = []; // 목록 초기화
            //let listIdx = resultCnt + 1 - ((currentPageNo - 1) * pageSize + index + 1);


            mutResultList.push(
              <CTableRow onClick={()=>navigate('/system_manager/code_manager/EgovCcmCmmnDetailCodeDetail',{state:{codeId:item.codeId, code:item.code}})}>
                <CTableHeaderCell scope="row">{index+resp.paginationInfo.firstRecordIndex+1}</CTableHeaderCell>
                <CTableDataCell>{item.codeId}</CTableDataCell>
                <CTableDataCell>{item.code}</CTableDataCell>
                <CTableDataCell>{item.codeNm}</CTableDataCell>
                <CTableDataCell>{item.useAt}</CTableDataCell>
                
              </CTableRow>
            );
          });
          setResultList(mutResultList);

          setPageButton(
            Pagination(resp.paginationInfo,searchVO,setSearchVO,retrieveList)
          )


          

          
      },
      function (resp) {
          console.log("err response : ", resp);
      }
    )
  } //retrieveList()

  
  useEffect(() => {
    retrieveList(searchVO);
    return () => {
    }
  }, []);

    
  return (
    <CRow>
      
      <CCol xs={12}>
        <CCard className="mb-4">
          <CCardHeader>
            <strong>공통상세코드 목록</strong> <small></small>
          </CCardHeader>
          <CCardBody>
          <CForm className="row g-3 d-flex justify-content-end">
          
            <CCol md={2}>
            <CFormSelect onChange={(e)=>setSearchVO({...searchVO,searchCondition:e.target.value})}>
              <option value="">선택하세요</option>
              <option value="1">코드ID</option>
              <option value="2">상세코드</option>
              <option value="3">상세코드명</option>
            </CFormSelect>
            </CCol>
            <CCol md={5}>
            <CFormInput  name="searchKeyword" type="text" size="35" 
                    value={searchVO && searchVO.searchKeyword}
                    onChange={(e)=>setSearchVO({...searchVO,searchKeyword:e.target.value})}
                    maxLength="255"/>
            </CCol>
            <CCol md={1}>
            <CFormInput  type="button"
                    value="조회"
                    onClick={()=>retrieveList(searchVO)}/>
            </CCol>
            <CCol md={1}>
            <CFormInput  type="button"
                    value="등록"
                    onClick={()=>navigate('/system_manager/code_manager/EgovCcmCmmnDetailCodeRegist')}/>
            </CCol>
            </CForm>
            

            

            <DocsExample >
              <CTable hover>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell scope="col">번호</CTableHeaderCell>
                    <CTableHeaderCell scope="col">코드ID</CTableHeaderCell>
                    <CTableHeaderCell scope="col">상세코드</CTableHeaderCell>
                    <CTableHeaderCell scope="col">상세코드명</CTableHeaderCell>
                    <CTableHeaderCell scope="col">사용여부</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>

                    {resultList}
                    
 
                </CTableBody>
              </CTable>
            </DocsExample>
            
                {pageButton}
              
          </CCardBody>
        </CCard>
      </CCol>
      
    </CRow>
  )
}

export default DetailCodeManager

```
## 사용할 변수   
```javascript
import { useState, useEffect } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import * as EgovNet from 'src/context/egovFetch';
import {Pagination} from 'src/context/Pagination'


function DetailCodeManager() {

  const location = useLocation();
  const navigate = useNavigate();
  const [searchVO, setSearchVO] = useState();
  const [resultList, setResultList] = useState();
  const [paginationInfo, setPaginationInfo] = useState();
  const [pageButton, setPageButton] = useState();
```   
- import
	- Pagination
		- 직접 제작한 페이지네이션 컴포넌트
		- 해당 컴포넌트에 페이지정보 등의 파라미터를 전달한 후 페이지 버튼들을 리턴받는다
		- 하단에서 해당 컴포넌트 설명
- const 변수
	- location, navigate
		- 페이지 이동이 아닌, 기타 버튼 클릭시 이동을 위해 사용할 변수, 이 글에서는 신경쓰지 않아도 된다
	- searchVO
		- 검색조건과, 이동할 페이지 index를 담을 변수
	- resultList
		- 조회한 데이터를 담을 배열
	- pageButton
		- Pagination컴포넌트를 통해 가져온 페이지 버튼을 담을 배열   

## 데이터 조회 함수   
```javascript
//searchVO를 파라미터로, 위에 작성한 백단과 비동기적으로 통신하여, 데이터를 받아온다
//searchVO에는 필요시 검색 조건과 검색키워드가 담아지며
//페이지 버튼 클릭 시 이동할 페이지인덱스를 담는다
  const retrieveList = (searchVO)=>{
    var url = '/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do'
    var options = {
      
      method: 'POST',
      headers: {
          'Content-type': 'application/json'
      },
      body: JSON.stringify(searchVO)
      
    }

    //fetch 컴포넌트를 따로 만들어 불러왔으며, axios나 fetch를 사용하여 데이터를 조회해오면 된다
    EgovNet.requestFetch(url,options,
      
      //성공했을 때의 함수
      (resp) => {

          let mutResultList = [];
         
          resp.resultList.forEach(function (item, index) {
            if (index === 0) mutResultList = []; // 목록 초기화
            //let listIdx = resultCnt + 1 - ((currentPageNo - 1) * pageSize + index + 1);


            mutResultList.push(
              <CTableRow onClick={()=>navigate('/system_manager/code_manager/EgovCcmCmmnDetailCodeDetail',{state:{codeId:item.codeId, code:item.code}})}>
                <CTableHeaderCell scope="row">{index+resp.paginationInfo.firstRecordIndex+1}</CTableHeaderCell>
                <CTableDataCell>{item.codeId}</CTableDataCell>
                <CTableDataCell>{item.code}</CTableDataCell>
                <CTableDataCell>{item.codeNm}</CTableDataCell>
                <CTableDataCell>{item.useAt}</CTableDataCell>
                
              </CTableRow>
            );
          });
          setResultList(mutResultList);

	  //페이지 버튼들을 state로 관리하며, import한 Pagination 컴포넌트를 통해 리턴받은 버튼들을 pagebutton state에 담는다
	  //Pagination 파라미터는 아래 기술
          setPageButton(
            Pagination(resp.paginationInfo,searchVO,retrieveList)
          )
       
      },
      function (resp) {
          console.log("err response : ", resp);
      }
    )
  } //retrieveList()
```   
- Pagination(resp.paginationInfo, searchVO, retrieveList)
	- 해당 컴포넌트에 파라미터를 전달 후 페이지 버튼들을 리턴받는다
	- resp.paginationInfo
		- 백단과 통신후 받아온 HashMap 내의 paginationInfo 객체를 담는다
		- 현재 페이지 번호, 페이지 사이즈 등의 정보가 담겨있다
	- searchVO
		- 검색조건이나 이동할 페이지인덱스를 담을 객체
		- 페이지번호 클릭 시 searchVO를 백단 파라미터로 보내 데이터를 받아올 것이다
	- retrieveList
		- 페이지 번호 클릭시 호출할 함수

***
***

# 3. pagination.js / 페이지네이션 컴포넌트   

```javascript
import React from 'react';
import {
    CPagination,
    CPaginationItem,
  } from '@coreui/react'
  import { DocsExample } from 'src/components'

export function Pagination(pi,searchVO,retrieveList) {

    


    var totalPageCount = Math.floor((pi.totalRecordCount - 1) / pi.recordCountPerPage) + 1
    var firstPageNoOnPageList = Math.floor((pi.currentPageNo - 1) / pi.pageSize) * pi.pageSize + 1;
    var lastPageNoOnPageList = firstPageNoOnPageList + pi.pageSize -1;
    if(lastPageNoOnPageList > pi.totalPageCount){
        lastPageNoOnPageList = pi.totalPageCount;
    }
    var firstRecordIndex = (pi.currentPageNo - 1) * pi.recordCountPerPage;
    var lastRecordIndex = (pi.currentPageNo) * pi.recordCountPerPage;

    const paginationInfo = {
        currentPageNo : pi.currentPageNo,
        recordCountPerPage : pi.recordCountPerPage,
        pageSize : pi.pageSize,
        totalRecordCount : pi.totalRecordCount,
        totalPageCount : totalPageCount,
        firstPageNoOnPageList : firstPageNoOnPageList,
        lastPageNoOnPageList : lastPageNoOnPageList,
        firstRecordIndex : firstRecordIndex,
        lastRecordIndex : lastRecordIndex
    };

    const paginationButton = [];
    
    if(paginationInfo.currentPageNo > 1){
        paginationButton.push(
            <CPaginationItem aria-label="Previous" 
                onClick={(e)=>pageFirst()}
            >
                <span aria-hidden="true">&laquo;</span>
            </CPaginationItem>
        )
        paginationButton.push(
            <CPaginationItem aria-label="Previous" 
                id={'prev'}
                onClick={(e)=>pagePrev()}
            >
                <span aria-hidden="true">&lt;</span>
            </CPaginationItem>
        )
    }
    
    for(var i=paginationInfo.firstPageNoOnPageList-1;i<paginationInfo.lastPageNoOnPageList;i++){

        if(i==paginationInfo.currentPageNo-1){
            paginationButton.push(
                <CPaginationItem active>{i+1}</CPaginationItem>
            )
        }else{
            paginationButton.push(
                <CPaginationItem 
                    id={i+1}
                    onClick={(e)=>pageMove(e.target.id)
                }>{i+1}</CPaginationItem>
            )
        }
    }

    if(paginationInfo.currentPageNo != paginationInfo.totalPageCount){
        paginationButton.push(
            <CPaginationItem aria-label="Next"
                onClick={(e)=>pageNext()}
            >
                <span aria-hidden="true">&gt;</span>
            </CPaginationItem>
        )
        paginationButton.push(
            <CPaginationItem aria-label="Next"
                onClick={(e)=>pageLast()}
            >
                <span aria-hidden="true">&raquo;</span>
            </CPaginationItem>
        )
    }
    

    function pageMove(index){

        searchVO={...searchVO,pageIndex:index};

        retrieveList(searchVO);  
    }
    function pagePrev(){
        
        var pageIndex = paginationInfo.firstPageNoOnPageList-1;
        if(pageIndex < 1) {
            pageIndex = 1;
        }
        retrieveList({...searchVO,pageIndex:pageIndex});  
    }
    function pageNext(){
        
        var pageIndex = paginationInfo.lastPageNoOnPageList+1;
        if(pageIndex > paginationInfo.totalPageCount) pageIndex = paginationInfo.totalPageCount;
        retrieveList({...searchVO,pageIndex:pageIndex});  
    }function pageFirst(){
        
        var pageIndex = 1;
        retrieveList({...searchVO,pageIndex:pageIndex});  
    }function pageLast(){
        
        var pageIndex = paginationInfo.totalPageCount;
        retrieveList({...searchVO,pageIndex:pageIndex});  
    }
    
    return (
        <DocsExample href="components/pagination#disabled-and-active-states" >
              <CPagination aria-label="Page navigation example" className='d-flex justify-content-center'>
                {paginationButton}
            </CPagination>
        </DocsExample>
    );
};

```

## 변수의 계산
```javascript
    var totalPageCount = Math.floor((pi.totalRecordCount - 1) / pi.recordCountPerPage) + 1
    var firstPageNoOnPageList = Math.floor((pi.currentPageNo - 1) / pi.pageSize) * pi.pageSize + 1;
    var lastPageNoOnPageList = firstPageNoOnPageList + pi.pageSize -1;
    if(lastPageNoOnPageList > pi.totalPageCount){
        lastPageNoOnPageList = pi.totalPageCount;
    }
    var firstRecordIndex = (pi.currentPageNo - 1) * pi.recordCountPerPage;
    var lastRecordIndex = (pi.currentPageNo) * pi.recordCountPerPage;

    const paginationInfo = {
        currentPageNo : pi.currentPageNo,
        recordCountPerPage : pi.recordCountPerPage,
        pageSize : pi.pageSize,
        totalRecordCount : pi.totalRecordCount,
        totalPageCount : totalPageCount,
        firstPageNoOnPageList : firstPageNoOnPageList,
        lastPageNoOnPageList : lastPageNoOnPageList,
        firstRecordIndex : firstRecordIndex,
        lastRecordIndex : lastRecordIndex
    };
```

- 컴포넌트를 불러올 js에서 객체(pi)에 기본 파라미터를 담아, pagination 컴포넌트에 전달한다
  - pi 객체의 파라미터
    - currentPageNo : 현재페이지
    - recordCountPerPage : 페이지당 보여줄 레코드 수
    - pageSize : 블럭당 보여줄 페이지 수 (10이면 1부터 10페이지, 11부터 20페이지)
    - totalRecordCount : 전체 데이터 수
  - 계산을 통해 구한 값
    - totalPageCount : 전체 페이지 수
      - Math.floor((pi.totalRecordCount - 1) / pi.recordCountPerPage) + 1
    - firstPageNoOnPageList : 각 블럭의 첫번째 페이지 번호 (1, 11, 21..)
      - Math.floor((pi.currentPageNo - 1) / pi.pageSize) * pi.pageSize + 1
    - lastPageNoOnPageList : 각 블럭의 마지막 페이지 번호
      - firstPageNoOnPageList + pi.pageSize -1
      - if문을 통해, 마지막 페이지번호가 전체 페이지수(totalPageCount)보다 크다면, 마지막 페이지번호를 재설정
      - if(lastPageNoOnPageList > pi.totalPageCount) { lastPageNoOnPageList = pi.totalPageCount; }
    - firstRecordIndex : 페이지당 첫번째 레코드의 인덱스
      - (pi.currentPageNo - 1) * pi.recordCountPerPage
    - lastRecordIndex : 페이지당 마지막 레코드의 인덱스
        - (pi.currentPageNo) * pi.recordCountPerPage;   

## 페이지 버튼의 생성   
- 페이지버튼을 담을 배열 생성
    - const paginationButton = \[\];
- 조건에 따라 배열에 페이지 버튼 삽입 (push)
    - 디자인을 위해 coreui의 페이지버튼 컴포넌트를 삽입했지만, 기본 input type button을 넣는 것과 동일   
- 배열에 페이지버튼을 담기 때문에, 각 버튼의 생성 순서에 주의한다
    - 첫페이지이동버튼, 10페이지 앞 이동버튼, 각 페이지 버튼, 10페이지 뒤 이동버튼, 마지막페이지 이동버튼 순서   

```javascript
//첫번째 페이지, 10페이지 앞 이동 버튼
//현재 페이지가 2페이지 이상일 경우에만 생성되게 조건을 건다
if(paginationInfo.currentPageNo > 1){
    paginationButton.push(
        <CPaginationItem aria-label="Previous" 
            onClick={(e)=>pageFirst()}
        >
            <span aria-hidden="true">&laquo;</span>
        </CPaginationItem>
    )
    paginationButton.push(
        <CPaginationItem aria-label="Previous" 
            id={'prev'}
            onClick={(e)=>pagePrev()}
        >
            <span aria-hidden="true">&lt;</span>
        </CPaginationItem>
    )
}


//각 페이지 이동 버튼
//해당 버튼이 가리키는 페이지번호를 파라미터로, onClick함수에 담아준다
for(var i=paginationInfo.firstPageNoOnPageList-1;i<paginationInfo.lastPageNoOnPageList;i++){

    if(i==paginationInfo.currentPageNo-1){
        paginationButton.push(
            <CPaginationItem active>{i+1}</CPaginationItem>
        )
    }else{
        paginationButton.push(
            <CPaginationItem 
                id={i+1}
                onClick={(e)=>pageMove(e.target.id)
            }>{i+1}</CPaginationItem>
        )
    }
}

//10페이지 뒤로 이동버튼, 마지막페이지 이동버튼
//현재페이지가 마지막페이지가 아닐 경우에만 생성
if(paginationInfo.currentPageNo != paginationInfo.totalPageCount){
    paginationButton.push(
        <CPaginationItem aria-label="Next"
            onClick={(e)=>pageNext()}
        >
            <span aria-hidden="true">&gt;</span>
        </CPaginationItem>
    )
    paginationButton.push(
        <CPaginationItem aria-label="Next"
            onClick={(e)=>pageLast()}
        >
            <span aria-hidden="true">&raquo;</span>
        </CPaginationItem>
    )
}
```

## 페이지버튼 클릭시 호출할 함수   
```javascript
//각 페이지버튼 클릭시 이동 함수
//페이지 버튼이 가리키는 페이지번호를 index로 받아온다
//searchVO에 해당 pageIndex를 지정 후 데이터 조회함수 (retrieveList)를 호출한다
//searchVO와 retrieveList는 모두 부모 컴포넌트에서 pagination 컴포넌트를 호출할 때 받아온 파라미터
function pageMove(index){

    searchVO={...searchVO,pageIndex:index};

    retrieveList(searchVO);  
}



//10페이지씩 이동하는 함수
function pagePrev(){
    var pageIndex = paginationInfo.firstPageNoOnPageList-1;
    if(pageIndex < 1) {
        pageIndex = 1;
    }
    retrieveList({...searchVO,pageIndex:pageIndex});  
}

function pageNext(){
    var pageIndex = paginationInfo.lastPageNoOnPageList+1;
    if(pageIndex > paginationInfo.totalPageCount) pageIndex = paginationInfo.totalPageCount;
    retrieveList({...searchVO,pageIndex:pageIndex});  
}


//첫,마지막페이지 이동하는 함수
function pageFirst(){
    var pageIndex = 1;
    retrieveList({...searchVO,pageIndex:pageIndex});  
}

function pageLast(){
    var pageIndex = paginationInfo.totalPageCount;
    retrieveList({...searchVO,pageIndex:pageIndex});  
}
```

