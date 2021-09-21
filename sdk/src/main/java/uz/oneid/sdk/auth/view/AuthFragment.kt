package uz.oneid.sdk.auth.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uz.oneid.sdk.auth.AuthState
import uz.oneid.sdk.auth.AuthViewModel
import uz.oneid.sdk.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {

    private val viewModel by sharedViewModel<AuthViewModel>()

    private lateinit var content: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        content = FragmentAuthBinding.inflate(inflater)
        return content.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.state.let {

            it.value?.let { s ->
                onState(s)
            }

            it.observe(viewLifecycleOwner) { s ->
                onState(s)
            }

        }

        content.buttonAuth.setOnClickListener {

            val login = content.editTextLogin.text.toString()
            val password = content.editTextPassword.text.toString()

            when {

                login.isNotEmpty() && password.isNotEmpty() -> {
                    tryToAuth(login, password)
                }
            }

        }

    }

    private fun onState(state: AuthState) {

    }

    private fun tryToAuth(login: String, password: String) {
        val result = viewModel.authWithLoginAndPass(login, password)
    }

}