package karthee.login.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

class LoginResponse {

    var isSuccess: Boolean? = null

    var user: User? = null


    @Entity
    class User {

        @PrimaryKey
        var id: Int? = null

        var firstName: String? = null
        var lastName: String? = null
        var jobTitle: String? = null

    }
}