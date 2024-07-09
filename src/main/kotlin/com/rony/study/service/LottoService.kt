package com.rony.study.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.rony.study.config.ConstProperties
import com.rony.study.domain.entity.LottoMain
import com.rony.study.domain.reponse.LottoDetailResponse
import com.rony.study.domain.reponse.LottoListResponse
import com.rony.study.domain.reponse.LottoMaxCountResponse
import com.rony.study.domain.request.LottoSyncDto
import com.rony.study.domain.request.LottoSyncDto.Companion.toLottoMain
import com.rony.study.exception.BaseException
import com.rony.study.exception.type.NotFoundTypes
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class LottoService(private val lottoMainRepoService: LottoMainRepoService,
                   private val constProperties: ConstProperties,
                   private val restService: RestService) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getLottoPage(eventDate: Optional<String>, page: Int, limit: Int): LottoListResponse {
        return if(!eventDate.isEmpty) {
            LottoListResponse.from(lottoMainRepoService.findByEventDate(eventDate.get(), page, limit))
        } else {
            LottoListResponse.from(lottoMainRepoService.findAllPage(page, limit))
        }
    }
    fun getLotto(drawNo: Int): LottoDetailResponse = LottoDetailResponse.from(lottoMainRepoService.findByDrawNo(drawNo))

    fun getMaxLottoNo(): LottoMaxCountResponse {
        val result = lottoMainRepoService.findMaxCountDrawNos()
        return LottoMaxCountResponse.from(result)
    }

    fun getMaxLottoNoByDate(eventDate: LocalDate): LottoMaxCountResponse {
        val result = lottoMainRepoService.findMaxCountDrawNosByEventDate(eventDate)
        return LottoMaxCountResponse.from(result)
    }

    @Transactional
    fun setLottoData(drawNo : Int) {
        val apiUrl      = constProperties.lottoApiUrl + drawNo

        val lottoSyncDto = getApiLottoServer(apiUrl)
        
        if(lottoSyncDto.returnValue == "success") {
            lottoMainRepoService.deleteByDrawNo(drawNo)
            lottoMainRepoService.save(lottoSyncDto.toLottoMain())
        } else {
            throw BaseException(NotFoundTypes.NOT_FOUND_COMMON)
        }
    }

    @Transactional
    fun initializeLottoMainDatas() {
        var i = 1;
        val lottoMainList: MutableList<LottoMain> = mutableListOf()

        lottoMainRepoService.deleteAll()

        while(true) {
            val apiUrl = constProperties.lottoApiUrl + i++

            val lottoSyncDto = getApiLottoServer(apiUrl)

            if(lottoSyncDto.returnValue == "success") {
                println("drwNo :: " + lottoSyncDto.drwNo)
                lottoMainList.add(lottoSyncDto.toLottoMain())
            } else {
                break
            }
        }

        lottoMainRepoService.saveAll(lottoMainList)
    }

    @Scheduled(cron = "0 0 5 ? * 1")
    @Transactional
    fun setLottoDataSchedule() {
        val maxDrawNo   = lottoMainRepoService.findMaxDrawNo()
        val apiUrl      = constProperties.lottoApiUrl + maxDrawNo + 1
        val lottoSyncDto = getApiLottoServer(apiUrl)

        if(lottoSyncDto.returnValue == "success") {
            lottoMainRepoService.deleteByDrawNo(maxDrawNo + 1)
            lottoMainRepoService.save(lottoSyncDto.toLottoMain())
        }
    }

    fun getApiLottoServer(url: String): LottoSyncDto {
        val response = restService.callRestUrl(url)
        val lottoSyncDto: LottoSyncDto = jacksonObjectMapper().readValue(response)
        return lottoSyncDto;
    }
}