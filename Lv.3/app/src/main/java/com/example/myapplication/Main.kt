package com.example.myapplication

fun main() {
    val introduce = "호텔예약 프로그램 입니다"
    val menu = "[메뉴]"
    val menuList = "1. 방예약 2. 예약목록 출력 3. 예약목록 (정렬) 4. 시스템 종료 5. 금액 입금,출금 내역 목록 출력 6. 예약 변경/취소"


    val reservationManager = ReservationManager(reservationList)

    while (true) {
        println(introduce)
        println(menu)
        println(menuList)
        println("원하시는 메뉴의 번호를 입력해주세요")

        val selectedNumber = readLine()?.toInt()

        when (selectedNumber) {
            1 -> reservationManager.reserve()
            2 -> reservationManager.printReservationList()
            3 -> reservationManager.sortedReservationList()
            4 -> {
                println("시스템을 종료합니다.")
                return
            }
            5->reservationManager.searchReservationList()
            else -> {
                println("올바른 메뉴 번호를 선택해주세요.")
            }
        }
    }
}
