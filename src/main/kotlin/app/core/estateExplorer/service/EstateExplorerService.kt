package app.core.estateExplorer.service

import app.core.estateExplorer.dto.EstateExplorerListRequest
import app.core.estateExplorer.dto.EstateExplorerListResponse
import app.core.feature.EnabledFeature
import app.core.feature.Feature
import app.core.feature.FeatureName
import app.core.feature.MockedFeature
import app.core.feature.ToggleableFeature
import app.core.feature.service.FeatureFlagService
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

interface EstateExplorerService : Feature<EstateExplorerService> {
	fun getEstateList(request: EstateExplorerListRequest): EstateExplorerListResponse
}

@Primary
@Component
internal class ToggleableEstateExplorerService(
	enabled: EnabledFeature<EstateExplorerService>,
	mocked: MockedFeature<EstateExplorerService>,
	featureFlagService: FeatureFlagService
) : EstateExplorerService, ToggleableFeature<EstateExplorerService>(enabled, mocked, featureFlagService) {

	override val featureName: FeatureName = FeatureName.ESTATE_EXPLORER

	override fun getEstateList(request: EstateExplorerListRequest): EstateExplorerListResponse {
		return delegate.getEstateList(request)
	}
}

@Component
class EnabledEstateExplorerService() : EstateExplorerService, EnabledFeature<EstateExplorerService> {

	override fun getEstateList(request: EstateExplorerListRequest): EstateExplorerListResponse {
		TODO("Not yet implemented")
	}
}

@Component
class MockedEstateExplorerService : EstateExplorerService, MockedFeature<EstateExplorerService> {

	override fun getEstateList(request: EstateExplorerListRequest): EstateExplorerListResponse {
		TODO("Not yet implemented")
	}
}
