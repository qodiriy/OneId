package uz.oneid.sdk.reg.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uz.oneid.sdk.main.User
import uz.oneid.sdk.databinding.FragmentRegBinding
import uz.oneid.sdk.reg.RegViewModel


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

            viewModel.sendSms(pin, doc, email, phone)

        }

        content.buttonCheckSms.setOnClickListener {
            val pin = content.editTextPin.text.toString()
            val code = content.editTextCode.text.toString()
            viewModel.checkSms(pin, code)
        }

        content.buttonSignUp.setOnClickListener {
            val pin = content.editTextPin.text.toString()
            val login = content.editTextLogin.text.toString()
            val password = content.editTextLogin.text.toString()
            val result = viewModel.signUp(pin,login, password)
            result.observe(viewLifecycleOwner) {
                onUser(it)
            }
        }


    }

    private fun onUser(user: User) {
        activity?.setResult(
            Activity.RESULT_OK,
            Intent().putExtra("data", user)
        )
        activity?.finish()
    }


}