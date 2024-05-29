package app.useCase.location.details

import app.core.entity.location.LocationWithMedia
import app.core.entity.review.LocationReviewWithMedia
import app.core.estateExplorer.dto.EstateExplorerEstateElement
import java.io.Serializable

data class ViewLocationDetailsOutput(
    val location: LocationWithMedia,
    val estateRecommendation: List<EstateExplorerEstateElement>,
    val reviews: List<LocationReviewWithMedia>
) : Serializable
