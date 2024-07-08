package com.rony.study.domain.reponse

import com.rony.study.domain.entity.LottoMain
import org.springframework.data.domain.Page

data class LottoListResponse(
    val data: Page<LottoMain>
) {
    companion object {
        fun from(page: Page<LottoMain>): LottoListResponse {
            return LottoListResponse(
                data = page
            )
        }
    }
}