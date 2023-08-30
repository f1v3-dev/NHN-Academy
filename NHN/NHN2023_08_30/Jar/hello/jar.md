## package 설정
- com.nhnacademy.hello

```java
package com.nhnacademy.hello;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello java!");
    }
}
```

## jar Command
### create hello.jar
```shell
cd out/production/hello

jar --create --file hello.jar --main-class com.nhnacademy.hello.Main -c ./com
```
> 여러개의 클래스가 존재하면 Main Class를 `--main-class {main class 위치}`와 같이 지정 해야 함.

### execute hello.jar
```shell
java -jar hello.jar
```

### jar file 내용 확인
```shell
jar -tf hello.jar
```

### jar 압축 풀기
```shell
unzip hello.jar
```
- 다음과 같이 진행할 경우 `META-INF/MANIFEST.MF`에 다음과 같이 생성 되어 있는 것을 확인할 수 있음.
```shell
seungjo@Seungjos-MacBook-Air META-INF % cat MANIFEST.MF 

Manifest-Version: 1.0
Created-By: 11.0.20 (Eclipse Adoptium)
Main-Class: com.nhnacademy.hello.Main
```
