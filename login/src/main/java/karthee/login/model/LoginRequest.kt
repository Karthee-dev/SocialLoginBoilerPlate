package karthee.login.model

class LoginRequest(username: String, password: String) {
    var username = String()
    var password = String()

    init {
        this.username = username
        this.password = password
    }
}
