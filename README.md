Pixel Game
=============
## 게임 소개
+ 턴제 게임
+ 하단 플레이어를 날려서 획득한 아이템으로 상단 몬스터를 무찌르자.
## 진행 상황
| 1주차   | 2주차       | 3주차        | 4주차         | 5주차     | 6주차            | 7주차          | 8주차  | 9주차  |
|:-------:|:-----------:|:------------:|:-------------:|:---------:|:----------------:|:--------------:|:------:|:------:|
| ui<br>배치  | 아이템<br>랜덤 배치 | 캐릭터 이동<br>구현 | 캐릭터<br>충돌 처리 | 몬스터<br>구현 | 스테이지1,2<br>구현 | 보스 스테이지<br>구현 |디버깅|디버깅|
| 100%  | 100% | 100% | 100% |100% | 100% | 100% | 100% | 100% |



![스크린샷 2024-05-09 204809](https://github.com/ojh6507/SPGTermProject/assets/45549589/932f09a0-0003-4739-8c5b-1dede3257266)

## 주별 커밋 수
| 1주차   | 2주차       | 3주차        | 4주차         | 5주차     | 6주차            | 7주차          | 8주차  | 9주차  |
|:-------:|:-----------:|:------------:|:-------------:|:---------:|:----------------:|:--------------:|:------:|:------:|
| 4  | 18 | 15 | 21 | 25 | 16 |5 |16|30
*******

### 사용된 기술

### 수업 내용에서 차용한 것
+ Scene 전환
+ Scene class
+ Sound class
+ CollisionHelper
+ sprite / anim sprite class
+ button class
+ Score class
+ Metrics class
+ GameView


### 직접 개발
+ Turn Based Controller (턴 관리 컨트롤러) 
+ Player 날리기 (드래그로 targetX, targetY 정하고 dx, dy를 유클리드 거리 공식으로 구함.)
+ Player와 장애물(바위) 충돌시 반사각으로 날리기
+ 랜덤으로  보드판 위에 아이템 및 장애물 배치 (플레이어와 겹치지 않게)
+ Sound Pool 개선 (Sound 클래스가 사용될 사운드를 미리 Pool에 저장할 수 있도록 MainScene 생성자에서 함수 호출) -> 사운드 재생시 딜레이 되는 문제 해결
+ sprite hp bar 감소 구현 

