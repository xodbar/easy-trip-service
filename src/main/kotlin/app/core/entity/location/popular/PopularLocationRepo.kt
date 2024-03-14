package app.core.entity.location.popular

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PopularLocationRepo : JpaRepository<PopularLocationModel, Long>
