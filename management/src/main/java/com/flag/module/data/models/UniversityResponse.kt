package com.flag.module.data.models


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "universities")
data class University (
    @TypeConverters(Converters::class)
    val domains: List<String>,
    @SerializedName("state-province")
    val stateProvince: String?,
    @PrimaryKey  val name: String,
    @TypeConverters(Converters::class)
    @SerializedName("web_pages")
    val webPages: List<String>?,
    val country: String?,
    @SerializedName("alpha_to_code")
    val alphaTwoCode: String?
): Parcelable {
    constructor() : this(emptyList(), null, "", null, null, null)

    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList()!!,
        parcel.readString(),
        parcel.readString().toString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(domains)
        parcel.writeString(stateProvince)
        parcel.writeString(name)
        parcel.writeStringList(webPages)
        parcel.writeString(country)
        parcel.writeString(alphaTwoCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<University> {
        override fun createFromParcel(parcel: Parcel): University {
            return University(parcel)
        }

        override fun newArray(size: Int): Array<University?> {
            return arrayOfNulls(size)
        }
    }
}