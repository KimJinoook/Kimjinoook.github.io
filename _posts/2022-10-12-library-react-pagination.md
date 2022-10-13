---
layout: post
title:  "17. 리액트 페이징처리"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

![p1](https://user-images.githubusercontent.com/99188096/195512954-471bbbf2-7406-4e1c-af67-59edf73c5928.PNG)   

- 하단 페이지의 경우 웹페이지의 여러부분에서 공통으로 사용되는 기능이기 때문에, 컴포넌트로 만들어 재사용
- 페이지네이션 컴포넌트에 파라미터 전달 후, 페이지 버튼들을 리턴   

***

# pagination.js / 리액트의 페이지네이션 컴포넌트   

```javascript
import React from 'react';
import {
    CPagination,
    CPaginationItem,
  } from '@coreui/react'
  import { DocsExample } from 'src/components'

export function Pagination(pi,searchVO,setSearchVO,retrieveList) {

    


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

