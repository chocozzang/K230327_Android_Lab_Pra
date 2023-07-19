package com.example.k230327_android_lab

import com.example.k230327_android_lab.LoginTest.Companion.login
import java.util.Scanner

class User (val ID : String, val PW : String, val email : String = "", val phone : String = ""){
    fun Login() {
        println("""
            로그인 성공 !!
            ID :    $ID
            PW :    $PW
            이메일 : $email
            tel :   $phone
            """.trimIndent())
    }
}

class LoginTest {
    companion object {
        // 클래스 함수 -> 클래스명. 함수
        fun login(myUser : MyUser) {
            if(myUser.id.equals("admin") && myUser.pw.equals("1234")) {
                println("==================")
                println("로그인  성공")
            }
        }
    }
}

// data class 임시 DTO(= VO) 데이터를 전달하기 위한 객체
// 클래스에 만들어서 사용할 떄, 주 생성자를 이용하는데,
// 매개 변수 부분에 변수 앞에 val 키워드를 사용해서 전역으로 사용하는 부분
data class MyUser(val id : String, val pw : String) {

}

fun isValidUser(userlist : MutableList<User>) : Boolean {
    val scanner : Scanner = Scanner(System.`in`)
    print("ID를 입력하시오 : ")
    val findID = scanner.nextLine()
    print("비밀번호를 입력하시오 : ")
    val findPW = scanner.nextLine()
    val finduser = User(findID, findPW)
    for(user in userlist) {
        if(user.ID == finduser.ID && user.PW == finduser.PW) {
            println("=========user login=======")
            user.Login()
            return true
        }
    }
    return false
}

fun regitser(userlist : MutableList<User>) {
    val scanner : Scanner = Scanner(System.`in`)
    print("ID를 입력하시오 : ")
    val tempID = scanner.nextLine()
    print("비밀번호를 입력하시오 : ")
    val tempPW = scanner.nextLine()
    print("이메일을 입력하시오 : ")
    val tempEM = scanner.nextLine()
    print("전화번호를 입력하시오 : ")
    val tempTE = scanner.nextLine()
    val user1 = User(tempID, tempPW, tempEM, tempTE)
    userlist.add(user1)
}

fun main() {

    // data class를 활용
    val scanner : Scanner = Scanner(System.`in`)
    print("ID를 입력하시오 : ")
    val myid = scanner.nextLine()
    print("비밀번호를 입력하시오 : ")
    val mypw = scanner.nextLine()
    val myuser : MyUser = MyUser(myid, mypw)
    LoginTest.login(myuser)


    // class를 활용
    val userlist : MutableList<User> = mutableListOf()
    for(i in 1..5) {
        println("========register==========")
        regitser(userlist)
    }
    //val user1 = User("admin", "1234", "test@naver.com", "010-5521-6236")
    println("=========user check=======")
    val isValid : Boolean = isValidUser(userlist)
    if(!isValid) println("로그인에 실패했습니다.")
}