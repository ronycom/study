package com.rony.study.aop

import com.sun.management.UnixOperatingSystemMXBean
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean
import java.util.*

@RequiredArgsConstructor
@Component
@Aspect
class LogAspect {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Around("within(com.rony.study.controller..*)")
    @Throws(Throwable::class)
    fun requestLog(proceedingJoinPoint: ProceedingJoinPoint): Any {
        val messageId = UUID.randomUUID().toString()

        log.info("messageId : " + messageId + " request : " + proceedingJoinPoint.args.contentToString())

        val startTime = System.currentTimeMillis() // Aspect 시작 시간 기록

        val result = proceedingJoinPoint.proceed()

        val endTime = System.currentTimeMillis() // Aspect 종료 시간 기록

        val executionTime = endTime - startTime // 실행 시간 계산

        log.info("messageId : " + messageId + " - Duration time : " + executionTime + "ms")

        return result
    }
}