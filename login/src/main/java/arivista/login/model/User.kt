package arivista.login.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
class User {
    @PrimaryKey
    @NonNull
    var userguid: String? = null
    var name: String? = null
    var role: String? = null
    var accesstoken: String? = null
}