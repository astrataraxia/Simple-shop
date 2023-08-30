# Simple-Shop
>간단한 상품 거래 사이트</br></br>
개발 기간: 2023.01.23 - 2023.02.06
<br/><br/>

### 기술 소개
 <img src="https://img.shields.io/badge/Java17-orange"> <img src="https://img.shields.io/badge/Spring-green"> <img src="https://img.shields.io/badge/Spring-Boot-green"> <img src="https://img.shields.io/badge/Spring-security-green"> <img src="https://img.shields.io/badge/JPA-orange"> <img src="https://img.shields.io/badge/MySQL-blue">  <img src="https://img.shields.io/badge/thymeleaf-biggren"> 
 
### 1. 프로젝트 구성 소개
* <b>홈 화면</b> </br>
 인증되지 않은 유저는 로그인과 회원가입화면을 보여준다.로그인한 유저의 권한에 따라 판매자, 소비자, 관리자로 나뉘어 화면이 보인다.
</br></br>
* <b>회원 역할</b> </br>
  - 판매자는 상품을 등록하고, 등록한 상품을 확인할 수 있으며 수정과 삭제를 할 수 있다. 여기에 페이징 기능과 검색기능을 넣었다. 
  - 소비자는 상품을 살 수 있으며 구입한 주문을 취소 할 수 있다.
  - 관리자는 상품의 등록을 삭제 할 수 있으며, 유저를 삭제 시킬 수 있다.
</br></br>
  
* <b>로그인,로그아웃,회원가입,유저권한</b> </br>
 Spring security를 적용하여 타임리프와 연계되어 로그인, 로그아웃화면이 자동으로 구현되는 탬플릿을 이용하였다. 유저 권한또한 Security를 이용하여설정하였다. 이런 설정들은 config로 따로 관리하며 securityFilterchain을 사용하여 설정하였다. 유저 권한에 따라 홈 화면에서 보이는 인터페이스가 달라진다.
</br></br>

* <b>구매 구현</b> <br>
상품을 구입하면 개수가 차감되고, 차감된 만큼 상품 개수에 반영되며, 상품을 취소하면 다시 개수가 증가한다. 상품의 개수가 0개밑이 되면 예외를 날리고 주문이 되지 않도록 설정하였다.


### 2. 프로젝트의 목적

* 학습하고 익힌것을 활용해보기 위하여 심플한 상품 거래 사이트를 설계하여 만들어 보았다. 이를통해 java와 spring에대한 학습을 더 심도있게 해보았다. 이번 프로젝트를 통하여 Spring에대한 경험이 확실하게 더 늘어난것을 느꼈다. 특히 Spring Legacy로 진행한 이전 프로젝트와 다르게 Spring boot, JPA, Java17등 나름 최신의 버전들을 사용한 개발방법을 사용하며 무엇이 달라졌고, 어떤 식의로 개발의 방향이 바뀌어 왔다는것을 익히면서 프로그래밍에 대한 이해도가 높아졌다.
  
 
### 3. 기술을 사용하며 느낀점
  - JPA: 공부를 하면서 배운 JPA 기술을 활용하여 Java에서 Entitiy를 통해 데이터베이스를 관리하는 방식을 사용했다. 여기서 Spring JPA까지 더해서 사용하자 정말 반복적으로 필요한 쿼리문의 작성이 획기적으로 줄어드는 경험을 할 수 있었다. 이전 프로젝트에서 Mybtis를 쓰며 일일이 작성하던 쿼리문 작성이 대폭 줄어든것 만으로도 생산성이 높아졌다. 여기에 QueryDsl을 같이 사용해보니 자바 코드로 작성되는 쿼리문으로 인해 단순한 오타같은 에러를 바로 CheckedException으로 잡아줘서 그런 실수를 하지 않게 되었다. Entity를 직접적으로 Controller단에서 사용하면 요구 스펙의 변경사항에 대응하기 힘든점에의해 DTO 클래스들을 이용하여 데이터를 관리하려고 하였다.
  - spring boot : spring boot를 사용함으로 버전관리, 내장된 Servlet등으로인해 편리하게 관리되며 Library dependency등도 다 관리 해줌으로인해 Spring을 이용해 xml에 설정을 할때보다 훨씬 편리하게 되었다. 여기다 start.spring.io 사이트를 이용하여 쉽게 라이브러리들을 추가해서 프로젝트를 시작 할수 도 있으니 아주 쉽게 실행되는 프로젝트를 만들 수있었다. 다만 부트에 대한 공부가 아직은 좀 부족하므로 더 깊은 공부가 필요하다.
  

### 4.ERD
![poster](/img/boardERD.png)<br/>





