package com.rony.study.controller

import com.rony.study.domain.reponse.LottoDetailResponse
import com.rony.study.domain.reponse.LottoListResponse
import com.rony.study.domain.reponse.LottoMaxCountResponse
import com.rony.study.service.LottoService
import org.apache.logging.log4j.util.InternalApi
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*


@RequestMapping("/lotto")
@RestController
class LottoController(private val lottoService: LottoService) {

    @GetMapping
    fun getLotto(@RequestParam page: Int = 1,
                 @RequestParam limit: Int = 20,
                 @RequestParam date: Optional<String>): ResponseEntity<LottoListResponse> {
        return ResponseEntity.ok(lottoService.getLottoPage(date, page, limit))
    }

    @GetMapping("/{drawNo}")
    fun getLottoDetail(@PathVariable drawNo: Int): ResponseEntity<LottoDetailResponse> {
        return ResponseEntity.ok(lottoService.getLotto(drawNo))
    }

    @GetMapping("/max")
    fun getMaxLottoNo(): ResponseEntity<LottoMaxCountResponse> {
        return ResponseEntity.ok(lottoService.getMaxLottoNo());
    }

    @GetMapping("/max/{date}")
    fun getMaxLottoNo(@PathVariable @NotNull date: LocalDate): ResponseEntity<LottoMaxCountResponse> {
        return ResponseEntity.ok(lottoService.getMaxLottoNoByDate(date));
    }

    @InternalApi
    @PostMapping("/all")
    fun setLottoNoAll(): ResponseEntity<Unit> {
        lottoService.initializeLottoMainDatas()

        return ResponseEntity.ok().build()
    }

    @InternalApi
    @PostMapping("/{drawNo}")
    fun setLottoNo(@PathVariable drawNo: Int): ResponseEntity<Unit> {
        lottoService.setLottoData(drawNo)

        return ResponseEntity.ok().build()
    }
}