---
layout: post
title:  "리액트 DB데이터로 메뉴 트리뷰 만들기"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

![m1](https://user-images.githubusercontent.com/99188096/195734264-f939cb34-9d88-48cd-9283-9538a2d37b36.PNG)   

- 흔히 볼 수 있는 메뉴의 트리뷰는 대부분 하드코딩 되어있다
- 프로젝트 간, 권한별 메뉴조회등을 위해 DB에서 메뉴를 불러올 시, 트리뷰는 하드코딩이 불가
- DB에서 불러온 데이터에 따라 트리뷰가 바뀔 수 있도록 컴포넌트 제작
- 트리뷰의 디자인은 deni-react-treeview를 사옹
  - npm으로 설치해도 좋고, 다른 라이브러리도 대부분 형태가 같기 때문에 원하는 라이브러리 설치   

***
***

# 1. 트리뷰의 기본 형태   

![m2](https://user-images.githubusercontent.com/99188096/195734965-035162e7-59b0-4815-93cc-f150817c78da.PNG)   

```javascript
import React from 'react'
import DeniReactTreeView from 'deni-react-treeview'

const TreeView = () => {
    
    const fruitsAndVegetables = [
        {
          id: 100,
          text: 'Fruits',
          children: [
            {
              id: 101,
              text: 'Orange',
              isLeaf: true
            },
            {
              id: 102,
              text: 'Banana',
              isLeaf: true,
              children:[
                {
                  id:111,
                  text:'banana',
                  isLeaf:true,
                }
              ]
            }
          ]
        },
        {
          id: 200,
          text: 'Vegetables',
          children: [
            {
              id: 201,
              text: 'Carrot',
              isLeaf: true
            },
            {
              id: 202,
              text: 'Tomato',
              isLeaf: true
            }
          ]
        }
      ];

  return <DeniReactTreeView items={ fruitsAndVegetables } />
}



export default TreeView
```

- 트리뷰에서 보여줄 메뉴 데이터는 JSON객체 형태로 되어있다
- 직접 하드코딩하면 만들기는 매우 쉽지만 우리는 DB에서 데이터를 가져와 제작하기 위해 로직처리가 필요   

***
***

# 2. 필요한 파라미터   

```sql
SELECT
  MENU_NO AS "menuNo"
  , MENU_NM AS "menuNm"
  , UPPER_MENU_NO AS "upperMenuId
  , MENU_TREE_NO AS "menuTreeNo"
FROM COMTNMENUINFO 
WHERE Menu_NO > 0 
ORDER BY MENU_NO
```

- menu_no : 메뉴 번호
- menu_nm : 메뉴 이름
- upper_menu_no : 상위 메뉴 번호
- menu_tree_no : 메뉴의 트리 단계

- 해당 데이터들은 리액트에서 fetch 혹은 axios로 서버와 통신하여 객체 형태로 가져오면 된다   

***
***

# 3. 트리뷰 로직처리   
```javascript
import React from 'react'
import DeniReactTreeView from 'deni-react-treeview'

const TreeView = (prop) => {
    const allMenu = prop || []; //메뉴배열
    const data = []; //로직처리 후 데이터를 담을 배열
    let lastTreeNo = 0; //트리 단계 초기화

    /** 트리 단계를 메뉴 데이터중 가장 하위 트리단계로 설정 */
    allMenu.forEach(function(item, index){
      var curTreeNo = item.menuTreeNo;
      console.log(curTreeNo);
      if(curTreeNo > lastTreeNo){
        lastTreeNo = curTreeNo;
      };
    })
 

    /** 해당 메뉴의 하위 트리노드 존재여부 확인 */
    function hasChildTreeNode(parentNode){

      for(var i = 0; i<allMenu.length; i++){
        if(allMenu[i].upperMenuId == parentNode) return true;
      }
      
      return false;
    }


    /** 로직처리, 임시배열에 담는다 */
    var temp=[];
    for(var j=lastTreeNo; j>0;j--){
      for(var i=0; i<allMenu.length; i++){
        if(allMenu[i].menuTreeNo == j){
          if(hasChildTreeNode(allMenu[i].menuNo)){
            var childTemp = [];

            temp.find(function(element){
              if(element.key == allMenu[i].menuNo){
                childTemp.push(element.value);
                
              }
            })

            for(var k = 0; k < temp.length; k++) {
              if(temp[k].key == allMenu[i].menuNo)  {
                temp.splice(k, 1);
                k--;
                console.log('a');
              }
            }

            temp.push({
              key:allMenu[i].upperMenuId,
              value:{
                  id: allMenu[i].menuNo,
                  text: allMenu[i].menuNm,
                  isLeaf: true,
                  children:childTemp,
              },
              
            })
          }else{
            temp.push({
              key:allMenu[i].upperMenuId,
              value:{
                  id: allMenu[i].menuNo,
                  text: allMenu[i].menuNm,
                  isLeaf: true
              },
            })
          }
        }
      }
    }

    
    temp.forEach(function(item){
      data.push(item.value);
    })
    

  return <DeniReactTreeView items={ data } />
}

export default TreeView
```   

### 컴포넌트의 시작   
```javascript
const TreeView = (prop) => {
    const allMenu = prop || []; //메뉴배열
    const data = []; //로직처리 후 데이터를 담을 배열
    let lastTreeNo = 0; //트리 단계 초기화

    /** 트리 단계를 메뉴 데이터중 가장 하위 트리단계로 설정 */
    allMenu.forEach(function(item, index){
      var curTreeNo = item.menuTreeNo;
      console.log(curTreeNo);
      if(curTreeNo > lastTreeNo){
        lastTreeNo = curTreeNo;
      };
    })
```

- 트리뷰를 보여줄 컴포넌트에서, 메뉴데이터를 TreeView 컴포넌트로 전달해 로직처리
- prop : 메뉴정보들이 담겨있는 데이터
- allMenu : 로직처리에서 prop를 사용하기 위해 초기화해주며, prop가 비어있다면, null이나 undefined가 아닌 빈 배열로 초기화   

### 하위 트리노드 존재여부 확인   
```javascript
function hasChildTreeNode(parentNode){

  for(var i = 0; i<allMenu.length; i++){
    if(allMenu[i].upperMenuId == parentNode) return true;
  }

  return false;
}
```
- 데이터중 메뉴번호를 파라미터로 받는다
- allMenu 전체 데이터정보를 순회한다
  - 각 데이터들 중 상위메뉴번호와, 파라미터가 일치하면 true를 반환
  - 파라미터로 받은 메뉴에게 하위 메뉴가 존재한다는 뜻   

### 로직처리   
```javascript
var temp=[]; //임시배열

//트리단계만큼 순회를 할 것이며, 최하위 트리부터 상위트리 순으로 순회
for(var j=lastTreeNo; j>0;j--){

  //전체 메뉴 순회
  for(var i=0; i<allMenu.length; i++){
  
    /*
      조건 : 메뉴의 트리단계가 현재 가장 상위 for문에서 순회중인 트리단계와 일치한다면
      이유 : 가장 하위 단계부터 데이터를 삽입하기 위해서
    */
    if(allMenu[i].menuTreeNo == j){
    
      /*
        조건 : 만약 하위메뉴가 존재한다면
        이유 : 하위메뉴가 존재할 경우, 해당 메뉴의 children 인자로 하위메뉴들을 담아야하기 때문
      */
      if(hasChildTreeNode(allMenu[i].menuNo)){
        var childTemp = []; //하위 메뉴들을 담을 임시 배열

        /*
          현재 temp임시배열에 담긴 메뉴들을 순회하며, 
          키값(상위메뉴번호)가 현재 메뉴의 번호와 일치하는 데이터를 childrenTemp 배열에 담는다
          트리단계가 가장 하위인 데이터들부터 순회중이기 때문에
          트리단계가 상위로 올라갈 수록, 미리 담겨진 하위메뉴들의 묶음을 가져올 것이다
        */
        temp.find(function(element){
          if(element.key == allMenu[i].menuNo){
            childTemp.push(element.value);
          }
        })


        /*
          temp배열에서 하위 메뉴들을 꺼내 childrenTemp에 담았다면
          temp배열에서는 해당 메뉴들을 삭제한다.
          temp에 있던 낱개 메뉴들이
          childrenTemp에 하나의 묶음으로 들어가기 위해
        */
        for(var k = 0; k < temp.length; k++) {
          if(temp[k].key == allMenu[i].menuNo)  {
            temp.splice(k, 1);
            k--;
            console.log('a');
          }
        }

        /*
          현재 순회에서 담을 메뉴정보와,
          해당 메뉴의 하위메뉴들을 담은 childrenTemp를
          하나의 묶음으로 temp 배열에 담는다.
          이후 다음 순회에서 이와같은 로직처리를 반복하기 위해
          key값에 해당 메뉴의 상위메뉴번호를 넣어준다
        */
        temp.push({
          key:allMenu[i].upperMenuId,
          value:{
              id: allMenu[i].menuNo,
              text: allMenu[i].menuNm,
              isLeaf: true,
              children:childTemp,
          },

        })
      }else{
      
        //하위메뉴가 없다면 그대로 담는다
        temp.push({
          key:allMenu[i].upperMenuId,
          value:{
              id: allMenu[i].menuNo,
              text: allMenu[i].menuNm,
              isLeaf: true
          },
        })
      }
    }
  }
}
```

- 대부분의 설명은 위 코드블럭에 작성
- 대략적인 전체 흐름
  - 가장 하위 트리단계부터 시작
  - 해당 트리단계의 메뉴들을 삽입할 예정
  - 만약 하위메뉴가 있다면
    - 하위메뉴들을 가져와 해당 메뉴에 children으로 넣어줌
    - 하위메뉴정보가 삽입된 메뉴를 temp에 삽입
  - 만약 하위메뉴가 없다면
    - 메뉴정보를 temp에 
