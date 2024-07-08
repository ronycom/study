package com.rony.study.exception.type

import org.springframework.http.HttpStatus

enum class NotFoundTypes(
    override val errorCode: String,
    override val message: String,
    override val httpStatus: HttpStatus = HttpStatus.NOT_FOUND
) : ExceptionType {
    NOT_FOUND_COMMON("NF-0001", "요청하신 URL을 찾을 수 없습니다."),
}