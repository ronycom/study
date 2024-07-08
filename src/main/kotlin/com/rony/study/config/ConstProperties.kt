package com.rony.study.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app.const")
class ConstProperties {
    var lottoApiUrl: String? = null
    var restConnectTimeoutSeconds: Int? = 5
    var restReadTimeoutSeconds: Int? = 5
}