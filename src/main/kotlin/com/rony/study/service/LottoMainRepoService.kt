package com.rony.study.service

import com.rony.study.domain.entity.LottoMain
import com.rony.study.repository.LottoMainRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LottoMainRepoService(private val lottoMainRepository: LottoMainRepository) {

    fun findByEventDate(eventDate: String, page: Int, limit: Int): Page<LottoMain> = lottoMainRepository.findByEventDate(eventDate, PageRequest.of(page - 1, limit, Sort.by("idx").descending()))
    fun findAllPage(page: Int, limit: Int): Page<LottoMain> = lottoMainRepository.findAll(PageRequest.of(page - 1, limit, Sort.by("idx").descending()))
    fun findByDrawNo(drawNo: Int): LottoMain = lottoMainRepository.findByDrawNo(drawNo)
    fun deleteAll() { lottoMainRepository.deleteAll() }
    fun deleteByDrawNo(drawNo : Int): Unit = lottoMainRepository.deleteByDrawNo(drawNo)
    fun findMaxDrawNo(): Int = lottoMainRepository.findMaxDrawNo()
    fun findMaxCountDrawNos(): List<Any> = lottoMainRepository.findMaxCountDrawNos()
    fun findMaxCountDrawNosByEventDate(localDate: LocalDate): List<Any> = lottoMainRepository.findMaxCountDrawNosByEventDate(localDate);
    fun save(lottoMain: LottoMain): LottoMain = lottoMainRepository.save(lottoMain)
    fun saveAll(lottoMains : List<LottoMain>): MutableList<LottoMain> = lottoMainRepository.saveAll(lottoMains)
}