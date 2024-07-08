package com.rony.study.domain.reponse

import com.rony.study.domain.entity.LottoMain

data class LottoDetailResponse(
    val data: LottoMain
) {
    companion object {
        fun from(lottoMain: LottoMain): LottoDetailResponse {
            return LottoDetailResponse(
                data = lottoMain
            )
        }
    }
}
