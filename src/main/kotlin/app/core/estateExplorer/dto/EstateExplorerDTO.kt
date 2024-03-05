package app.core.estateExplorer.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class EstateExplorerListRequest(
  val city: SupportedCity,
  val estateType: EstateExplorerEstateType,
  val publicationType: EstateExplorerPublicationType,
  val params: EstateExplorerEstateParams?
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class EstateExplorerEstateParams(
  val fromAgent: Boolean?,
  val hasPhoto: Boolean?,
  val flat: FlatDetails?,
  val house: HouseDetails?,
  val live: LivingAreaDetails?,
  val mapComplex: Int?,
  val newBuildings: Boolean?,
  val price: PriceRange?,
  val fromOwner: Boolean?
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class FlatDetails(
  val buildingTypes: List<String>?,
  val floorRange: Range?,
  val floorNotFirst: Boolean?,
  val floorNotLast: Boolean?
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class HouseDetails(
  val floorNumRange: Range?,
  val yearRange: Range?
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class LivingAreaDetails(
  val rooms: List<EstateExplorerEstateRoomNumber>?,
  val squareRange: Range?,
  val kitchenSquareRange: Range?
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class PriceRange(
  val from: Int?,
  val to: Int?
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Range(
  val from: Int?,
  val to: Int?
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class EstateExplorerListResponse(
  val list: List<EstateExplorerEstateElement>
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class EstateExplorerEstateElement(
  val dataId: String,
  val previewImage: String,
  val title: String,
  val price: String,
  val location: String,
  val roomsAndArea: String,
  val floorInfo: String,
  val additionalDetails: String
) : Serializable
