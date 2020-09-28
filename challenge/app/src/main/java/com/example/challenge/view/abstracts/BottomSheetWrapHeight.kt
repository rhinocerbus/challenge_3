package com.example.challenge.view.abstracts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import com.example.challenge.R
import com.example.challenge.viewmodel.ArmorListViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BottomSheetWrapHeight : BottomSheetDialogFragment() {

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<*>
    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismissAllowingStateLoss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val dialog = dialog as BottomSheetDialog
                val bottomSheet =
                    dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

                mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
                mBottomSheetBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
                //scale with content size changes
                mBottomSheetBehavior.isFitToContents = true
                //dont want collapse state
                mBottomSheetBehavior.skipCollapsed = true
                mBottomSheetBehavior.peekHeight = 0
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        })
    }
}