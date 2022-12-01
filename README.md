# 봉키(VONGKI)

## 배경

코로나19와 봉사활동의 대입 미반영 등의 여파로 MZ세대를 비롯한 봉사활동 참여율이 급감했습니다.
봉키는 MZ세대의 자원봉사 참여에 약간의 재미를 가미하여 자발적인 봉사활동 참여가 이루어지도록 만드는 플랫폼입니다.

## 관련자료

[대입 반영 안되자… 중고생 자원봉사 3년새 73% 급감](https://www.chosun.com/national/education/2022/10/25/IKO6RV3XFFBI5K7O63G5OQYHGQ/)

[코로나19 여파, 활동자원봉사자 반토막…올해 자원봉사 지원 못받은 사회복지·보건의료시설 42.9%](http://medicalworldnews.co.kr/m/view.php?idx=1510945448)

[2021 자원봉사 참여현황(20세 이상)](https://www.index.go.kr/potal/main/EachDtlPageDetail.do?idx_cd=2718)

## 개발환경

Android(Kotlin)

## 주요기능

### 나만의 집 꾸미기, 동물 캐릭터(강아지, 고양이, 토끼) 키우기

- 3종류의 알 선택에 따라 다른 동물 성장
- 누적 마일리지에 따라 봉키 성장
- 마일리지를 소모하여 인테리어 소품(지붕, 벽, 벽지, 창문, 의자, 시계, 화분, 커튼; 카테고리 별 3~6종류) 구매 가능

### 봉사 활동 인증에 따른 마일리지 지급

- 1365 봉사 활동서 캡쳐 후 파일 업로드 시 구글 MLkit의 OCR을 통해 텍스트 인식, 인식 후 1365 서버와 대조, 유효성이 확인되면 마일리지 지급
- 중복된 활동서는 인증이 거부됨
- 마일리지는 봉사활동 시간에 따라 차등 지급

### 보유 마일리지에 따라 순위 부여

- 순위별로 랭킹에 등재
- 다른 사람들의 집을 구경할 수 있음

### 구글 Firebase 연결을 통한 DB관리

- 계정 정보 및 보유 아이템, 마일리지 정보 등을 서버에 업로드

### 나의 봉사활동 기록 확인

- 지금까지 업로드했던 봉사 활동들의 내역과 마일리지를 한눈에 확인할 수 있음

## 스크린샷

### 첫 실행 후 회원가입, 봉키 알 선택, 메인화면
<p>
<img src="/screenshot/screenshot1.png" width="150"/>
<img src="/screenshot/screenshot2.png" width="150"/>
<img src="/screenshot/screenshot3.png" width="150"/>
<img src="/screenshot/screenshot4.png" width="150"/>
</p>

### 봉사 활동서 업로드로 마일리지 적립

<p>
<img src="/screenshot/screenshot5.png" width="150"/>
<img src="/screenshot/screenshot6.png" width="150"/>
<img src="/screenshot/screenshot7.png" width="150"/>
<img src="/screenshot/screenshot8.png" width="150"/>
</p>

### 봉키 성장과정 (고양이, 강아지, 토끼)
<p>
<img src="/app/src/main/res/drawable/egg1.png" width="50"/>
<img src="/app/src/main/res/drawable/cat1.png"width="30"/>
<img src="/app/src/main/res/drawable/cat2.png"width="30"/>
<img src="/app/src/main/res/drawable/cat3.png"width="50"/>
<img src="/app/src/main/res/drawable/cat4.png"width="50"/>
<img src="/app/src/main/res/drawable/cat5.png"width="50"/>
</p><p>
<img src="/app/src/main/res/drawable/egg2.png" width="50"/>
<img src="/app/src/main/res/drawable/dog1.png"width="30"/>
<img src="/app/src/main/res/drawable/dog2.png"width="30"/>
<img src="/app/src/main/res/drawable/dog3.png"width="50"/>
<img src="/app/src/main/res/drawable/dog4.png"width="50"/>
<img src="/app/src/main/res/drawable/dog5.png"width="50"/>
  </p><p>
  <img src="/app/src/main/res/drawable/egg3.png" width="50"/>
<img src="/app/src/main/res/drawable/rabit1.png"width="30"/>
<img src="/app/src/main/res/drawable/rabit2.png"width="30"/>
<img src="/app/src/main/res/drawable/rabit3.png"width="50"/>
<img src="/app/src/main/res/drawable/rabit4.png"width="50"/>
<img src="/app/src/main/res/drawable/rabit5.png"width="50"/>
  </p>
  
### 소품 카테고리 선택 및 각 카테고리

<p>
<img src="/screenshot/screenshot9.png" width="150"/>
  </p><p>
<img src="/screenshot/screenshot10.png" width="150"/>
<img src="/screenshot/screenshot11.png" width="150"/>
<img src="/screenshot/screenshot12.png" width="150"/>
  <img src="/screenshot/screenshot13.png" width="150"/>
<img src="/screenshot/screenshot14.png" width="150"/>
<img src="/screenshot/screenshot15.png" width="150"/>
<img src="/screenshot/screenshot16.png" width="150"/>
  <img src="/screenshot/screenshot17.png" width="150"/>
  </p>
  
### 아이템 구매 후 적용 모습(시계, 전체)

<p>
<img src="/screenshot/screenshot18.png" width="150"/>
<img src="/screenshot/screenshot19.png" width="150"/>
<img src="/screenshot/screenshot20.png" width="150"/>
  <img src="/screenshot/screenshot21.png" width="150"/> </p>
  
### 봉사 기록 확인
<img src="/screenshot/screenshot22.png" width="150"/>

### 봉사 랭킹 및 집들이
<p>
  <img src="/screenshot/screenshot23.png" width="150"/>
  <img src="/screenshot/screenshot24.png" width="150"/>
  <img src="/screenshot/screenshot25.png" width="150"/>
  </p>
  
### 로그아웃 및 애플리케이션 종료

<p>
  <img src="/screenshot/screenshot26.png" width="150"/>
  <img src="/screenshot/screenshot27.png" width="150"/>
  </p>

