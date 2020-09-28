package com.example.challenge.view.abstracts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.example.challenge.view.getWindowDimensions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BottomSheetFixedHeight : BottomSheetDialogFragment() {

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<*>
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
    abstract fun getConetntViewGroup(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val dialog = dialog as BottomSheetDialog
                val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

                mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
                mBottomSheetBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)

                //dont want collapse state
                mBottomSheetBehavior.skipCollapsed = true
                mBottomSheetBehavior.peekHeight = 0
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                    val height = (getScreenHeightRatio() * getWindowDimensions(requireActivity()).y).toInt()
                    view.layoutParams.height = height
                    //view.findViewById<ViewGroup>(getConetntViewGroup()).layoutParams.height = height
            }
        })
    }
}
