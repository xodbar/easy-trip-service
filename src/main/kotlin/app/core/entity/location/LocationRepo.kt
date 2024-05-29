package app.core.entity.location

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepo : JpaRepository<LocationModel, Long> {
  fun findByTitle(title: String): LocationModel?
}
