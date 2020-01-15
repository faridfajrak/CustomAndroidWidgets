package com.f.fajrak.widget.indicatorimagebutton

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.getFontOrThrow
import androidx.core.content.res.use

import kotlinx.android.synthetic.main.indicator_image_button.view.*
import java.lang.Exception

open class IndicatorImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    lateinit var disableIcon : Drawable
    lateinit var enableIcon : Drawable

    init {
        LayoutInflater.from(context).inflate(R.layout.indicator_image_button, this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomIndicatorButton, 0, 0).use {

            when(it.getInt(R.styleable.CustomIndicatorButton_text_box_alignment,1))
            {

                1->{
                    label_right.visibility = View.VISIBLE
                    label_bott.visibility = View.GONE
                    label_top.visibility = View.GONE
                    label_left.visibility = View.GONE
                }
                2->{
                    label_right.visibility = View.GONE
                    label_bott.visibility = View.GONE
                    label_top.visibility = View.GONE
                    label_left.visibility = View.VISIBLE
                }
                3->{
                    label_right.visibility = View.GONE
                    label_bott.visibility = View.VISIBLE
                    label_top.visibility = View.GONE
                    label_left.visibility = View.GONE
                }
                4->{
                    label_right.visibility = View.GONE
                    label_bott.visibility = View.GONE
                    label_top.visibility = View.VISIBLE
                    label_left.visibility = View.GONE
                }
            }

            it.getDrawable(R.styleable.CustomIndicatorButton_icon_enable)?.let {
                icon.setImageDrawable(it)
            }
            it.getDimension(R.styleable.CustomIndicatorButton_indicator_text_size,12f)?.let {
                label_right.setTextSize(TypedValue.COMPLEX_UNIT_SP,it)
                label_left.setTextSize(TypedValue.COMPLEX_UNIT_SP,it)
                label_top.setTextSize(TypedValue.COMPLEX_UNIT_SP,it)
                label_bott.setTextSize(TypedValue.COMPLEX_UNIT_SP,it)
            }

            it.getFont(R.styleable.CustomIndicatorButton_indicator_text_font_family)?.let {
                label_right.typeface = it
                label_left.typeface = it
                label_top.typeface = it
                label_bott.typeface = it
            }
            label_left.text = it.getText(R.styleable.CustomIndicatorButton_android_text)
            label_right.text = it.getText(R.styleable.CustomIndicatorButton_android_text)
            label_top.text = it.getText(R.styleable.CustomIndicatorButton_android_text)
            label_bott.text = it.getText(R.styleable.CustomIndicatorButton_android_text)

            val isComplete = it.getBoolean(R.styleable.CustomIndicatorButton_isComplete,false)
            if(isComplete)
                indicator.visibility = View.VISIBLE
            else
                indicator.visibility = View.GONE


            it.getDrawable(R.styleable.CustomIndicatorButton_icon_disable)?.let {
                disableIcon = it
            } ?: kotlin.run {
                disableIcon = context.resources.getDrawable(android.R.drawable.sym_def_app_icon)
            }
            it.getDrawable(R.styleable.CustomIndicatorButton_icon_enable)?.let {
                enableIcon = it
            }?: kotlin.run {
                enableIcon = context.resources.getDrawable(android.R.drawable.sym_def_app_icon)
            }
            it.getDrawable(R.styleable.CustomIndicatorButton_indicator_icon)?.let {

                indicator.setImageDrawable(it)
            }
            it.getDrawable(R.styleable.CustomIndicatorButton_indicator_background)?.let {
                background = it
            }

            isEnabled = it.getBoolean(R.styleable.CustomIndicatorButton_android_enabled, false)
        }
        isClickable = true
        isFocusable = true
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if(enabled)
        {
            icon.setImageDrawable(enableIcon)
        }else{
            icon.setImageDrawable(disableIcon)
        }


    }
    fun setEnabled(enabled : Boolean?)
    {
        try {

            enabled?.let {

                icon.isEnabled = enabled
            }
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
    }

    fun isComplete(compelete : Boolean)
    {
        if(compelete) {
            indicator.visibility = View.VISIBLE

        }
        else {
            indicator.visibility = View.GONE
        }
    }




}