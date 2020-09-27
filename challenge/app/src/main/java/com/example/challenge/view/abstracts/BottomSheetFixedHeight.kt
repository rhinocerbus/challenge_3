package com.example.challenge.view.abstracts

import android.app.Dialog
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.challenge.view.getWindowDimensions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BottomSheetFixedHeight : BottomSheetDialogFragment() {

    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) {

            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismissAllowingStateLoss()
            }

        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) { }
    }

    abstract fun getLayoutId() : Int
    abstract fun getScreenHeightRatio(): Float

    override fun setupDialog(dialog: Dialog, style: Int) {
        val view = View.inflate(context, getLayoutId(), null)
        dialog.setContentView(view)

        val params = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.skipCollapsed = true
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
            val height = (getScreenHeightRatio() * getWindowDimensions(requireActivity()).y).toInt()
            behavior.peekHeight = height
            //mContent.layoutParams.height = height
            view.layoutParams.height = height
        }
    }


}
