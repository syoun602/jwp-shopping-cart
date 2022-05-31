# 장바구니 미션

## 기능 요구사항

#### 액세스 토큰 발급 기능

- [ ] JWT를 활용해서 인증 인가한다.

#### 공통 기능

- [ ] 액세스 토큰이 유효한 토큰인지 확인한다. (만료된 경우, 인증이 안되는 경우)
- [ ] 엑세스 토큰으로 사용자를 확인한다.

`예외`

- [ ] 존재하지 않는 회원일 경우(토큰이랑 pathvariable id랑 비교) - **404**
- [ ] 인가되지 않은 요청일 경우 - **403**

#### 회원가입 기능

- [x] 이름, 이메일, 비밀번호를 입력받는다.
- [x] 이미 존재하는 이메일인지 확인한다.
- [ ] 비밀번호는 암호화해서 저장한다.
- [ ] 비밀번호가 8자 이상인지 검증한다.

`예외`

- [ ] 이름, 이메일, 비밀번호가 입력되지 않은 경우 - **400**
- [ ] 이메일이 이미 존재하는 경우 - **400**
- [ ] 이메일 형식이 맞지 않는 경우  - **400**
- [ ] 비밀번호 길이가 8자 미만인 경우 - **400**

#### 로그인 기능

- [ ] 이메일, 비밀번호를 입력받는다.
- [ ] 입력된 비밀번호가 암호화된 비밀번호와 일치하는지 확인한다.
- [ ] 일치한 경우 액세스 토큰을 반환한다.

`예외`

- [ ] 이메일, 비밀번호가 입력되지 않은 경우 - **400**
- [ ] 이메일이 존재하지 않는 경우 - **400**
- [ ] 비밀번호가 일치하지 않는 경우 - **400**

#### 회원 수정 기능

- [ ] 변경할 이름과 비밀번호를 입력받는다.
- [ ] 사용자의 이름과 비밀번호 정보를 수정한다.


#### 회원 조회 기능

- [ ] 회원 정보를 확인한다.

`예외`

- [ ] 존재하지 않는 회원일 경우 - **404**

#### 회원 삭제 기능

- [ ] 비밀번호를 입력받는다.
- [ ] 비밀번호가 일치할 경우 회원 정보를 삭제한다.

`예외`

- [ ] 비밀번호가 일치하지 않는 경우 - **400**

## 사용자 시나리오

### Feature: 회원 관리 기능

```
Scenario: 회원을 등록한다.
	when 이름, 이메일, 비밀번호를 입력해서 회원 등록 요청한다.
	then 회원이 성공적으로 가입된다.
```

```
Scenario: 로그인을 한다.
	given 회원을 등록한다.
	when 이메일, 비밀번호를 입력해서 로그인 요청을 한다.
	then 로그인이 성공적으로 된다.
```

```
Scenario: 회원 정보를 수정한다.
	given 회원을 등록한다.
	when 변경할 이메일, 비밀번호를 입력해서 회원 수정을 요청한다.
	then 회원 정보가 성공적으로 변경된다.
```

```
Scenario: 회원 정보를 조회한다.
	given 회원을 등록한다.
	when 등록된 회원을 조회 요청한다.
	then 조회 요청에 성공한다.
	and 회원 이름과 이메일을 확인할 수 있다.
```

```
Scenario: 회원을 삭제한다.
	given 회원을 등록한다.
	when 비밀번호를 입력해서 회원삭제 요청을 한다. 
	then 회원 정보가 성공적으로 삭제된다. 
```

## 순항중인 클레이 🧑🏻‍💻 & 썬 🧑🏻‍💻 & 라라 👩🏻‍💻 페어 컨벤션
### 소셜
- 10분씩 드라이버 역할
- 서로의 의견을 끝까지 듣고 존중해주기
  - 의견을 물어볼 땐 본인의 생각을 먼저 말하고 물어보기
  - 상관없을 경우 제안한 사람의 의견에 따르기
- 우리는 다 비슷하니깐 부담 갖지 않기 !!!
- 3인 페어니깐 드라이버가 가운데 자리에 앉고, 네비게이터가 양쪽에서 속삭이기
- 다른 코드에 흔들리지 않고 우리의 코드를 만들어가기
- 칭찬, 리액션 많이 해주기 !!!!


### 코드 컨벤션
- TDD 지키기! 테스트 코드를 작성 후 프로덕션 코드 작성
- final 키워드
  - read-only로 선언하여 side effect 줄이기
- 메서드 10줄 넘기지 않기
  - 해당 메서드의 책임이 너무 많을 수 있음
- 클래스 선언부 첫 줄 띄기
- 테스트 컨벤션
  - 메서드 : `(테스트 메서드 네임)_(상황)_(결과)`
  - given / when / then 주석 작성
