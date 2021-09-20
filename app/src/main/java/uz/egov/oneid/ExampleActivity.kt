package uz.egov.oneid

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import uz.egov.oneid.databinding.ActivityMainBinding
import uz.oneid.sdk.OneIDSDK


class ExampleActivity : AppCompatActivity() {

    private lateinit var content : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(content.root)

        content.cardViewOneId.setOnClickListener {
            OneIDSDK.startAuth(this)
        }
    }

}