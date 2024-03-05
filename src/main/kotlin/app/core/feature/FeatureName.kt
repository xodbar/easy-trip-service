package app.core.feature

/**
 * Фича приложения, которую можно выключить или замокать
 */
internal enum class FeatureName(
	val description: String,
	val defaultState: FeatureState = FeatureState.MOCKED
) {
	ESTATE_EXPLORER(description = "Сервис для поиска жилья"),
	GMAIL_SERVICE(description = "Сервис для отправки email")
}
