package karthee.login.model

class LoginRequest(username: String, password: String) {
    var email = String()
    var password = String()

    init {
        this.email = username
        this.password = password
    }
}
