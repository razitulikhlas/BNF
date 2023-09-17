package com.razitulikhlas.core.data.source.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseInsertLocationHome(

	@field:SerializedName("data")
	val data: DataHome? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataHome(

	@field:SerializedName("id_disposisi")
	val idDisposisi: String? = null,

	@field:SerializedName("image3")
	val image3: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("image4")
	val image4: String? = null,

	@field:SerializedName("longititude")
	val longititude: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("image1")
	val image1: String? = null,

	@field:SerializedName("image2")
	val image2: String? = null
) : Parcelable
