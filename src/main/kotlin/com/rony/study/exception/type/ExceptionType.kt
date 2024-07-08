package com.rony.study.exception.type

import org.springframework.http.HttpStatus

interface ExceptionType {
    val httpStatus: HttpStatus
    val errorCode: String
    val message: String
}