package arivista.login.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AddressData:Serializable {

    @SerializedName("data")
    @Expose
    var address: String? = null

}
