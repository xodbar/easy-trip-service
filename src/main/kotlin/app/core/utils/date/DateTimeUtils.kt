package app.core.utils.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId


private const val TIME_ZONE = "Asia/Almaty"

fun getCurrentAlmatyLocalDateTime(): LocalDateTime = LocalDateTime.now(ZoneId.of(TIME_ZONE))
fun getCurrentAlmatyLocalDate(): LocalDate = LocalDate.now(ZoneId.of(TIME_ZONE))
