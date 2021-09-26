package uz.egov.oneid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.egov.oneid.databinding.FragmentInfoBinding
import uz.oneid.sdk.main.User


class InfoFragment : Fragment() {


    private lateinit var content: FragmentInfoBinding

    private val user by lazy {
        arguments?.getSerializable("user") as User?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        content = FragmentInfoBinding.inflate(inflater)
        return content.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user?.let { onUser(it) }

    }

    private fun onUser(user: User) {

        content.textViewPin.text = user.pin
        content.textViewDoc.text = user.document
        content.textViewBirthday.text = user.birthDate

    }


}