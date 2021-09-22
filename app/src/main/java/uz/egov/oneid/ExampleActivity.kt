package uz.egov.oneid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import uz.egov.oneid.databinding.ActivityMainBinding
import uz.oneid.sdk.OneIDActivity
import uz.oneid.sdk.OneIDSDK


class ExampleActivity : AppCompatActivity() {

    private lateinit var content: ActivityMainBinding

    private val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.getBundleExtra("data")
            val pin = data?.getString("pin")
            val login = data?.getString("login")

            Log.e("Result", "pin : $pin login : $login")

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(content.root)

        content.cardViewOneId.setOnClickListener {
            launcher.launch(Intent(this, OneIDActivity::class.java))
        }
    }

}