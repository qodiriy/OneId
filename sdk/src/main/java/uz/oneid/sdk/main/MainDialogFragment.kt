package uz.oneid.sdk.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.oneid.sdk.R
import uz.oneid.sdk.base.BaseFragment
import uz.oneid.sdk.databinding.FragmentMainBinding

class MainDialogFragment : BaseFragment() {

    private lateinit var content: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        content = FragmentMainBinding.inflate(inflater)
        return content.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        content.cardViewAuth.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_main_to_fragment_auth)
        }

        content.cardViewReg.setOnClickListener {

        }

    }

}