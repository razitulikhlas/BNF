package com.razitulikhlas.core.data.source.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponsePetaBusiness(

	@field:SerializedName("data")
	val data: List<DataItemPetaBusiness>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemPetaBusiness(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("longititude")
	val longititude: String? = null,

	@field:SerializedName("pemohon")
	val pemohon: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("plafond")
	val plafond: String? = null,

	@field:SerializedName("skim_kredit")
	val skimKredit: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("image1")
	val image1: String? = null
) : Parcelable
