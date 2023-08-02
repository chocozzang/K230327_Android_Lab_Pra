package com.example.firebasetest20.model

class ItemData {
    // docId -> 파이어스토어-NOSQL <-> RDBMS(MYSQL, Oracle)처럼 저장하는 기능만 비슷하고 실제 종류는 다름
    // 컬렉션(테이블처럼 사용), 문서(행처럼 사용), docId(문서번호 -> PK처럼 사용)
    // 문서번호, 자동으로 생성해서 사용중이지만 임의로 작성할 수는 있음 (이때는 유니크 속성을 고려하여야함)
    var docId: String? = null
    // 인증시 해당 이메일이 인증 객체에 등록됨
    var email: String? = null
    // 메세제 -> 파이어 스토어에 저장되는 문자열
    var content: String? = null
    // 기본 날짜, -> SimpleFormat 함수를 이용해서 원하는 형식으로 날짜를 사용함
    var date: String? = null
}