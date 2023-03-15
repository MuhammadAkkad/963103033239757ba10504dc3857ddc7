package com.example.a963103033239757ba10504dc3857ddc7.ui.binding

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList.SatelliteListFragmentDirections


object BindingAdapters {

    @BindingAdapter("android:styleString")
    @JvmStatic
    fun styleString(textView: TextView, string: String) {
        textView.text = try {
            val index = string.indexOf(':')
            val sb = SpannableStringBuilder(string)
            sb.setSpan(StyleSpan(Typeface.BOLD), 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            SpannableString(sb)
        } catch (e: Exception) {
            string
        }
    }

    @BindingAdapter("android:navigateToDetailFragment")
    @JvmStatic
    fun navigateToDetailFragment(view: ConstraintLayout, id: Int) {
        view.setOnClickListener {
            view.findNavController()
                .navigate(
                    SatelliteListFragmentDirections.actionSatelliteListFragmentToSatelliteDetailFragment(
                        id
                    )
                )
        }
    }
}