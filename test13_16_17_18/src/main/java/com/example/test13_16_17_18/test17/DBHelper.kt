package com.example.test13_16_17_18.test17

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 안드로이드에서 기본으로 제공해주는 SQLite 추상화 클래스
// 사용의 편의성을 생각함
// 부모 클래스의 상속 부분 체크 -- SQLiteOpenHelper
// 2번째 매개변수가 스키마(원하는 데이터베이스)
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    // 최초 1회 호출, 테이블을 생성해줌\
    // testdb의 저장 위치
    // 에뮬레이터 익스플로러 -> data -> data -> 패키지면 -> databases -> testdb
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table TODO_TB (" +
                "_id integer primary key autoincrement," +
                "todo not null)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}