package com.rony.study.domain.reponse

import com.rony.study.domain.entity.LottoMain
import com.rony.study.exception.BaseException
import com.rony.study.exception.type.NotFoundTypes
import java.util.Optional

data class LottoDetailResponse(
    val data: LottoMain
) {
    companion object {
        fun from(lottoMain: Optional<LottoMain>): LottoDetailResponse {
            return LottoDetailResponse(
                data = lottoMain.orElseThrow{throw BaseException(NotFoundTypes.NOT_FOUND_COMMON)}
            )
        }
    }
}
