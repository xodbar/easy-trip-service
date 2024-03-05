package app.web

import app.core.cache.CacheService
import app.core.email.GmailService
import app.core.minio.MinioService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
	private val gmailService: GmailService,
	private val cacheService: CacheService,
	private val minioService: MinioService
) {

	@PostMapping("/send")
	fun send(@RequestParam receipent: String, @RequestParam text: String) {
		return gmailService.sendEmail(receipent, "check test", text)
	}

	@PostMapping("/put")
	fun put(@RequestParam key: String, @RequestBody content: String) {
		return cacheService.putObject(key, content)
	}

	@PostMapping("/upload")
	fun upload(@RequestParam filename: String, @RequestBody body: ByteArray): String {
		return minioService.upload(filename, body)
	}
}
