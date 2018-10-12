package arivista.login.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.support.annotation.NonNull
import arivista.login.db.local.VillageConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class AddressModel : Serializable {

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("stateName")
    @Expose
    var stateName: String? = null
    @SerializedName("districtName")
    @Expose
    var districtName: String? = null
    @SerializedName("taluk")
    @Expose
    var taluk: String? = null

    @TypeConverters(VillageConverter::class)
    @SerializedName("villageName")
    @Expose
    var villageName: List<String>? = null

    @SerializedName("village")
    @Expose
    var village: String? = null

    @PrimaryKey
    @NonNull
    @SerializedName("pincode")
    @Expose
    var pincode: String? = null

    @SerializedName("houseno")
    @Expose
    var houseno: String? = null

    @SerializedName("street")
    @Expose
    var street: String? = null


}
