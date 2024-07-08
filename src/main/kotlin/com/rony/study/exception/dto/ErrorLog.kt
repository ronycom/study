package com.rony.study.exception.dto

import com.rony.study.util.HttpRequestUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus

data class ErrorLog(
    val httpStatus: HttpStatus,
    val method: String,
    val requestUri: String,
    val errorCode: String,
    val errorMsg: String,
    val clientIp: String,
    val stackTrace: String
) {
    constructor(request: HttpServletRequest, httpStatus: HttpStatus, failureResponse: FailureResponse, throwable: Throwable): this(
        httpStatus = httpStatus,
        method = request.method,
        requestUri = request.requestURI,
        errorCode = failureResponse.errorCode,
        errorMsg = failureResponse.message,
        clientIp = HttpRequestUtil.getClientIp(request),
        stackTrace = throwable.toString()
    )
}
