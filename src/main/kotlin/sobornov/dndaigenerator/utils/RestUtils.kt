package sobornov.dndaigenerator.utils

import org.springframework.web.client.RestTemplate

inline fun <reified T> RestTemplate.getForObject(url: String, vararg uriVariables: Any): T? =
    getForObject(url, T::class.java, *uriVariables)
