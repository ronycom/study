package com.rony.study.exception

import com.rony.study.exception.dto.FailureResponse
import com.rony.study.exception.type.ExceptionType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
open class BaseException(
    val httpStatus: HttpStatus,
    val failureResponse: FailureResponse
): RuntimeException(failureResponse.message) {
        constructor(exceptionType: ExceptionType): this(
            httpStatus = exceptionType.httpStatus,
            failureResponse = FailureResponse(
                errorCode = exceptionType.errorCode,
                message = exceptionType.message
            )
        )
}