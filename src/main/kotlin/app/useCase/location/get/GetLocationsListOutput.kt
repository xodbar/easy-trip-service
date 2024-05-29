package app.useCase.location.get

import app.core.entity.location.LocationWithMedia
import java.io.Serializable

data class GetLocationsListOutput(
    val popular: List<LocationWithMedia>,
    var favorite: List<LocationWithMedia>?,
    var recentlyViewed: List<LocationWithMedia>?
) : Serializable
