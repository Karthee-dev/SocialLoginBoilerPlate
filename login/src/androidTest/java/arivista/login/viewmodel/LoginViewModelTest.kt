package arivista.login.viewmodel

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by Karthee on 15/10/18.
 */
class LoginViewModelTest {

    val viewModel = LoginViewModel()


    @Test
    fun onBtnLoginClick() {

        viewModel.email.set("good game")
        viewModel.onEmailChange()
        assertEquals("Invalid Email", viewModel.errorEmail.get())
    }

}