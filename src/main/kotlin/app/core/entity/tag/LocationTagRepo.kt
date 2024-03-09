package app.core.entity.tag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationTagRepo : JpaRepository<LocationTagModel, Long> {
  fun getByName(name: String)
  fun getAllByNameIn(names: List<String>): List<LocationTagModel>
}
