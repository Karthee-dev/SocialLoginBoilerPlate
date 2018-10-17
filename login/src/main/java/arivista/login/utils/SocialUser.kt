package arivista.login.utils

import android.os.Parcel
import android.os.Parcelable

class SocialUser : Parcelable {

    var userId: String? = null
    var accessToken: String? = null
    var profilePictureUrl: String? = null
    var username: String? = null
    var fullName: String? = null
    var email: String? = null
    var pageLink: String? = null

    constructor()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as SocialUser?

        return if (userId != null) userId == that!!.userId else that!!.userId == null
    }

    override fun hashCode(): Int {
        return if (userId != null) userId!!.hashCode() else 0
    }

    override fun toString(): String {
        val sb = StringBuilder("SocialUser {").append("\n\n")
        sb.append("userId=").append(userId).append("\n\n")
        sb.append("username=").append(username).append("\n\n")
        sb.append("fullName=").append(fullName).append("\n\n")
        sb.append("email=").append(email).append("\n\n")
        sb.append("profilePictureUrl=").append(profilePictureUrl).append("\n\n")
        sb.append("pageLink=").append(pageLink).append("\n\n")
        sb.append("accessToken=").append(accessToken).append("\n\n")
        sb.append('}')
        return sb.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.userId)
        dest.writeString(this.accessToken)
        dest.writeString(this.profilePictureUrl)
        dest.writeString(this.username)
        dest.writeString(this.fullName)
        dest.writeString(this.email)
        dest.writeString(this.pageLink)
    }

    protected constructor(`in`: Parcel) {
        this.userId = `in`.readString()
        this.accessToken = `in`.readString()
        this.profilePictureUrl = `in`.readString()
        this.username = `in`.readString()
        this.fullName = `in`.readString()
        this.email = `in`.readString()
        this.pageLink = `in`.readString()
    }



    companion object CREATOR : Parcelable.Creator<SocialUser> {
        override fun createFromParcel(parcel: Parcel): SocialUser {
            return SocialUser(parcel)
        }

        override fun newArray(size: Int): Array<SocialUser?> {
            return arrayOfNulls(size)
        }
    }

}
