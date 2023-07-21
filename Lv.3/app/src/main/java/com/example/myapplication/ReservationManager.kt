package com.example.myapplication
import java.time.LocalDate

class Reservation(
    val name: String?,
    val phoneSuffix: String,
    val roomNumber: Int,
    val checkIn: Int,
    val checkout: Int
)
var reservationList = mutableListOf<Reservation>()
class ReservationManager(private val reservationList: MutableList<Reservation>) {
    fun reserve() {
        println("휴대폰 뒷번호를 입력해주세요 (뒷 4자리)")
        val myPhoneSuffix = readLine()!!.trim()
        if (myPhoneSuffix!!.length != 4 || !myPhoneSuffix.matches(Regex("\\d+"))) {
            println("올바른 휴대폰 뒷번호를 입력해주세요.")
        } else if (reservationList.any { it.phoneSuffix == myPhoneSuffix }) {
            println("이미 예약한 번호가 존재합니다.")
        } else {
            println("예약하실 분 성함을 입력해주세요")
            val myName = readLine()?.trim()
            if (myName.isNullOrBlank()) {
                println("올바른 이름을 입력해주세요.")
            } else {
                println("예약하실 방 호실를 입력해주세요 (100 ~ 999)")
                var myRoomNumber = readLine()!!.toInt()
                if (myRoomNumber in 100..999) {
                    val existingReservations = reservationList.filter { it.roomNumber == myRoomNumber }
                    if (existingReservations.isNotEmpty()) {
                        println("해당 호실은 다음 날짜에 예약되어 있습니다:")
                        for (reservation in existingReservations) {
                            println("${reservation.checkIn}부터 ${reservation.checkout}까지")
                        }
                    }

                    println("체크인 날짜를 입력해주세요 ex)20230720")
                    var myCheckInDate = readLine()!!.toInt()

                    val today = LocalDate.now()

                    if (myCheckInDate >= today.year * 10000 + today.monthValue * 100 + today.dayOfMonth) {
                        println("체크아웃 날짜를 입력해주세요 ex)20230721")
                        var myCheckOutDate = readLine()!!.toInt()

                        if (reservationList.any {
                                it.roomNumber == myRoomNumber && (myCheckInDate in it.checkIn until it.checkout
                                        || myCheckOutDate in it.checkIn until it.checkout)
                            }) {
                            println("해당 날짜에 이미 방이 예약되어 있습니다. 다시 입력해주세요.")
                        } else {
                            if (myCheckOutDate > myCheckInDate) {
                                val newReservationList = Reservation(myName, myPhoneSuffix, myRoomNumber, myCheckInDate, myCheckOutDate)
                                reservationList.add(newReservationList)
                                println("예약이 완료되었습니다.")
                            } else {
                                println("올바른 체크아웃 날짜를 입력해주세요.")
                            }
                        }
                    } else {
                        println("오늘 날짜 이전의 체크인은 불가능합니다. 다시 입력해주세요.")
                    }
                } else {
                    println("호실번호는 100~999까지 존재합니다.")
                }
            }
        }
    }

    fun printReservationList() {
        if (reservationList.isEmpty()) {
            println("예약된 내역이 없습니다.")
        } else {
            println("[예약자 목록]")
            for (reservation in reservationList) {
                println("이름: ${reservation.name}, 휴대폰 뒷번호: ${reservation.phoneSuffix}, 방 호실: ${reservation.roomNumber}, 체크인 날짜: ${reservation.checkIn}, 체크아웃 날짜: ${reservation.checkout}")
            }
        }
    }
    fun sortedReservationList() {
        if (reservationList.isEmpty()) {
            println("예약된 내역이 없습니다.")
        } else {
            println("[예약자 목록. (체크인날짜 오름차순으로 정렬 완료)]")
            val sortedList = reservationList.sortedBy { it.checkIn }
            for (reservation in sortedList) {
                println("이름: ${reservation.name}, 휴대폰 뒷번호: ${reservation.phoneSuffix}, 방 호실: ${reservation.roomNumber}, 체크인 날짜: ${reservation.checkIn}, 체크아웃 날짜: ${reservation.checkout}")
            }
        }
    }




}