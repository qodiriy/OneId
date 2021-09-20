package uz.oneid.sdk.auth.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.oneid.sdk.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {

    private lateinit var content : FragmentAuthBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        content  =  FragmentAuthBinding.inflate(inflater)
        return content.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}