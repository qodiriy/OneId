package uz.oneid.sdk.reg.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import okhttp3.CertificatePinner
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uz.oneid.sdk.auth.AuthState
import uz.oneid.sdk.auth.AuthViewModel
import uz.oneid.sdk.base.UserModel
import uz.oneid.sdk.databinding.FragmentAuthBinding
import uz.oneid.sdk.databinding.FragmentRegBinding
import uz.oneid.sdk.reg.RegState
import uz.oneid.sdk.reg.RegViewModel
import kotlin.math.log


class RegFragment : Fragment() {

    private val viewModel by sharedViewModel<RegViewModel>()

    private lateinit var content: FragmentRegBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        content = FragmentRegBinding.inflate(inflater)
        return content.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        content.buttonSendSms.setOnClickListener {
            val pin = content.editTextPin.text.toString()
            val doc = content.editTextDoc.text.toString()
            val email = content.editTextEmail.text.toString()
            val phone = content.editTextPhone.text.toString()

            viewModel.sendSms(pin,doc, email, phone)

        }

        viewModel.state.let {

            it.value?.let { s ->
                onState(s)
            }

            it.observe(viewLifecycleOwner) { s ->
                onState(s)
            }

        }

    }

    private fun onState(state: RegState) {

    }


    private fun onUser(user: UserModel) {
        activity?.setResult(
            Activity.RESULT_OK,
            Intent().putExtra("data", bundleOf(Pair("pin", user.pin), Pair("login", user.login)))
        )
        activity?.finish()
    }

}