package karthee.login.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull


//{
//    "expires": "4\/3\/2021 7:19:46 AM",
//    "userguid": "f5d7c77a-8495-401c-b726-14f11bd7ee2e",
//    "role": 3,
//    "name": "venki",
//    "accesstoken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJhZG1pbiIsImV4cCI6MTYxNzQzNDM4NiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoyNDg2LyIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6MjQ4Ni8ifQ.kCdV6rmvdD-ZD9JvRlmnmp_2A6LBiG4MWBJkQxjGhtQ"
//}

@Entity
class User {

    @PrimaryKey
    @NonNull
    var userguid: String? = null

    var name: String? = null
    var role: String? = null
    var accesstoken: String? = null

}