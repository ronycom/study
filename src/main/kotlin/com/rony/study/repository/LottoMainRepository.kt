package com.rony.study.repository

import com.rony.study.domain.entity.LottoMain
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface LottoMainRepository : JpaRepository<LottoMain, Long> {

    fun findByEventDate(eventDate: String, pageable: Pageable): Page<LottoMain>
    fun findByDrawNo(drawNo: Int): LottoMain
    fun deleteByDrawNo(drawNo: Int)

    @Query("SELECT MAX(drawNo) FROM lotto_main")
    fun findMaxDrawNo(): Int

    @Query(value = """
        SELECT 
            (SELECT draw_no1 FROM lotto_main GROUP BY draw_no1 ORDER BY COUNT(draw_no1) DESC LIMIT 1) AS max_draw_no1,
            (SELECT draw_no2 FROM lotto_main GROUP BY draw_no2 ORDER BY COUNT(draw_no2) DESC LIMIT 1) AS max_draw_no2,
            (SELECT draw_no3 FROM lotto_main GROUP BY draw_no3 ORDER BY COUNT(draw_no3) DESC LIMIT 1) AS max_draw_no3,
            (SELECT draw_no4 FROM lotto_main GROUP BY draw_no4 ORDER BY COUNT(draw_no4) DESC LIMIT 1) AS max_draw_no4,
            (SELECT draw_no5 FROM lotto_main GROUP BY draw_no5 ORDER BY COUNT(draw_no5) DESC LIMIT 1) AS max_draw_no5,
            (SELECT draw_no6 FROM lotto_main GROUP BY draw_no6 ORDER BY COUNT(draw_no6) DESC LIMIT 1) AS max_draw_no6,
            (SELECT bonus_no FROM lotto_main GROUP BY bonus_no ORDER BY COUNT(bonus_no) DESC LIMIT 1) AS max_bonus_no
        FROM dual
        """, nativeQuery = true)
    fun findMaxCountDrawNos(): List<Any>

    @Query(value = """
        SELECT 
            (SELECT draw_no1 FROM lotto_main WHERE event_date >= :date GROUP BY draw_no1 ORDER BY COUNT(draw_no1) DESC LIMIT 1) AS max_draw_no1,
            (SELECT draw_no2 FROM lotto_main WHERE event_date >= :date GROUP BY draw_no2 ORDER BY COUNT(draw_no2) DESC LIMIT 1) AS max_draw_no2,
            (SELECT draw_no3 FROM lotto_main WHERE event_date >= :date GROUP BY draw_no3 ORDER BY COUNT(draw_no3) DESC LIMIT 1) AS max_draw_no3,
            (SELECT draw_no4 FROM lotto_main WHERE event_date >= :date GROUP BY draw_no4 ORDER BY COUNT(draw_no4) DESC LIMIT 1) AS max_draw_no4,
            (SELECT draw_no5 FROM lotto_main WHERE event_date >= :date GROUP BY draw_no5 ORDER BY COUNT(draw_no5) DESC LIMIT 1) AS max_draw_no5,
            (SELECT draw_no6 FROM lotto_main WHERE event_date >= :date GROUP BY draw_no6 ORDER BY COUNT(draw_no6) DESC LIMIT 1) AS max_draw_no6,
            (SELECT bonus_no FROM lotto_main WHERE event_date >= :date GROUP BY bonus_no ORDER BY COUNT(bonus_no) DESC LIMIT 1) AS max_bonus_no
        FROM dual
        """, nativeQuery = true)
    fun findMaxCountDrawNosByEventDate(date: LocalDate): List<Any>
}