package com.rony.study.aop

import com.rony.study.util.HttpRequestUtil
import jakarta.servlet.http.HttpServletRequest

data class AopLog(
    val messageId: String,
    val requestUrl: String,
    val requestUri: String,
    val method: String,
    val ip: String,
    val data: String
) {
    companion object{
        fun from(request: HttpServletRequest,
                 parameters: Array<Any>,
                 messageId: String): AopLog {
            return AopLog(
                messageId = messageId,
                requestUrl = request.requestURL.toString(),
                requestUri = request.requestURI,
                method = request.method,
                ip = HttpRequestUtil.getClientIp(request),
                data = parameters.joinToString()
            )
        }
    }
}
