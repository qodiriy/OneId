package uz.oneid.sdk.base

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import timber.log.Timber

open class FixableDialogFragment : BaseFragmentDialog() {

    private var contentView : View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentView = view
    }

    override fun onResume() {
        super.onResume()
        update()
    }

    fun update(){
        contentView?.let {
            it.post {
                Timber.e("updated")
                val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(it.parent as View)
                behavior.isFitToContents = true
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.peekHeight = it.height
            }
        }
    }

}