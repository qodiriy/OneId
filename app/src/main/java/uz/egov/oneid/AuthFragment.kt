package uz.egov.oneid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.egov.oneid.databinding.FragmentExampleBinding
import uz.oneid.sdk.main.OneIDActivity
import uz.oneid.sdk.main.User


class AuthFragment : Fragment() {

    private val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.getSerializableExtra("data")
            val user = data as User?
            user?.let {
                Log.e("User", it.toString())
                findNavController().navigate(
                    R.id.action_fragment_auth_to_fragment_info, bundleOf(
                        Pair("user", it)
                    )
                )
            }

        }
    }

    private lateinit var content: FragmentExampleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        content = FragmentExampleBinding.inflate(inflater)
        return content.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        content.cardViewOneId.setOnClickListener {
            launcher.launch(Intent(activity, OneIDActivity::class.java))
        }

    }


}