package arivista.login.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiResponse {

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("desc")
    @Expose
    var desc: String? = null


}
