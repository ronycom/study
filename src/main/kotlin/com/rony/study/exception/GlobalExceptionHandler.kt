package com.rony.study.exception

import com.rony.study.exception.dto.ErrorLog
import com.rony.study.exception.dto.FailureResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)


    @ExceptionHandler(BaseException::class)
    fun handlerBaseException(exception: BaseException, request: HttpServletRequest): ResponseEntity<FailureResponse> {

        printLog(ErrorLog(request, exception.httpStatus, exception.failureResponse, exception).toString())

        return ResponseEntity
            .status(exception.httpStatus)
            .body(exception.failureResponse)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handlerNotAllowedException(exception: HttpRequestMethodNotSupportedException, request: HttpServletRequest): ResponseEntity<FailureResponse> {
        val failureResponse = FailureResponse(
            errorCode = "MNT-405",
            message = exception.message.toString()
        )

        printLog(ErrorLog(request, HttpStatus.METHOD_NOT_ALLOWED, failureResponse, exception).toString())

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(failureResponse)
    }

    private fun printLog(message: String) {
        log.error(message)
    }
}