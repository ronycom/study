package com.rony.study.aop

import com.rony.study.config.MessageConstants
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

@Aspect
@Component
class LogAspect {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Around("within(com.rony.study.controller..*)")
    fun logging(joinPoint: ProceedingJoinPoint): Any? {
        val request: HttpServletRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request

        val messageId = UUID.randomUUID().toString()
        
        request.setAttribute(MessageConstants.MESSAGE_ID, messageId)

        val startAt = LocalDateTime.now()
        val param = AopLog.from(request, joinPoint.args, messageId)
        log.info("Start messageId: $messageId \n Start At: $startAt \n Param: $param")

        val proceed = joinPoint.proceed()

        val endAt = LocalDateTime.now()
        log.info("End messageId: $messageId \n End At: $startAt Duration: ${Duration.between(startAt, endAt).toMillis()}ms")

        return proceed
    }
}