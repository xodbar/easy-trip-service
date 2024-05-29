package app.core.entity.media.location

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationMediaRepo : JpaRepository<LocationMediaModel, Long> {
  fun findAllByLocationModelId(locationId: Long): List<LocationMediaModel>
}
