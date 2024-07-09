package com.rony.study.domain.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "lotto_main")
data class LottoMain(
    val totalSellAmount: Long,
    val eventDate: String,
    val fistWinAmount: Long,
    val fistWinCount: Int,
    val drawNo: Int,
    val drawNo1: Int,
    val drawNo2: Int,
    val drawNo3: Int,
    val drawNo4: Int,
    val drawNo5: Int,
    val drawNo6: Int,
    val bonusNo: Int,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    val idx: Long = 0
}
