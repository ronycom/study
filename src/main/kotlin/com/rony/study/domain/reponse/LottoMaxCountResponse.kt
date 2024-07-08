package com.rony.study.domain.reponse

data class LottoMaxCountResponse(
    val maxDrawNo1: Int?,
    val maxDrawNo2: Int?,
    val maxDrawNo3: Int?,
    val maxDrawNo4: Int?,
    val maxDrawNo5: Int?,
    val maxDrawNo6: Int?,
    val maxBonusNo: Int?,
) {
    companion object {
        fun from(map : List<*>): LottoMaxCountResponse {
            if (map.isNotEmpty()) {
                val row = map[0] as Array<*>
                return LottoMaxCountResponse(
                    maxDrawNo1 = row[0] as Int,
                    maxDrawNo2 = row[1] as Int,
                    maxDrawNo3 = row[2] as Int,
                    maxDrawNo4 = row[3] as Int,
                    maxDrawNo5 = row[4] as Int,
                    maxDrawNo6 = row[5] as Int,
                    maxBonusNo = row[6] as Int
                )
            } else {
                throw RuntimeException("No Data Found")
            }
        }
    }
}