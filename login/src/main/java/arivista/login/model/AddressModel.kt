package arivista.login.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class AddressModel : Serializable {
    @SerializedName("stateName")
    @Expose
     var stateName: String? = null
    @SerializedName("districtName")
    @Expose
     var districtName: String? = null
    @SerializedName("taluk")
    @Expose
     var taluk: String? = null
    @SerializedName("villageName")
    @Expose
     var villageName: List<String>? = null
    @SerializedName("pincode")
    @Expose
     var pincode: String? = null

}
