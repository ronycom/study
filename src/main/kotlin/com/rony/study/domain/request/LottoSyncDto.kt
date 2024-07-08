package com.rony.study.domain.request

import com.rony.study.domain.entity.LottoMain

data class LottoSyncDto(
    val totSellamnt: Long?,
    val returnValue: String,
    val drwNoDate: String?,
    val firstWinamnt: Long?,
    val drwtNo6: Int?,
    val drwtNo4: Int?,
    val firstPrzwnerCo: Int?,
    val drwtNo5: Int?,
    val bnusNo: Int?,
    val firstAccumamnt: Long?,
    val drwNo: Int?,
    val drwtNo2: Int?,
    val drwtNo3: Int?,
    val drwtNo1: Int?
) {
    companion object {
        fun LottoSyncDto.toLottoMain(): LottoMain {
            return LottoMain(
                totalSellAmount = this.totSellamnt!!,
                eventDate = this.drwNoDate!!,
                fistWinAmount = this.firstWinamnt!!,
                fistWinCount = this.firstPrzwnerCo!!,
                drawNo = this.drwNo!!,
                drawNo1 = this.drwtNo1!!,
                drawNo2 = this.drwtNo2!!,
                drawNo3 = this.drwtNo3!!,
                drawNo4 = this.drwtNo4!!,
                drawNo5 = this.drwtNo5!!,
                drawNo6 = this.drwtNo6!!,
                bonusNo = this.bnusNo!!
            )
        }
    }
}