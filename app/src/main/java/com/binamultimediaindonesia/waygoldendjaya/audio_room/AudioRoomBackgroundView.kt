package com.binamultimediaindonesia.waygoldendjaya.audio_room

import android.content.Context
import android.graphics.Color
import android.text.TextUtils.TruncateAt
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.zegocloud.uikit.utils.Utils

class AudioRoomBackgroundView : FrameLayout {

    private lateinit var roomName: TextView
    private lateinit var roomID: TextView

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(-2, -2)
        val marginEnd: Int = Utils.dp2px(12F, resources.displayMetrics)
        params.setMargins(0, 0, marginEnd, 0)
        roomName = TextView(context)
        roomName.maxLines = 1
        roomName.ellipsize = TruncateAt.END
        roomName.isSingleLine = true
        roomName.paint.isFakeBoldText = true
        roomName.setTextColor(Color.parseColor("#ff1b1b1b"))
        roomName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        roomName.maxWidth = Utils.dp2px(200F, resources.displayMetrics)
        linearLayout.addView(roomName, params)
        roomID = TextView(context)
        roomID.maxLines = 1
        roomID.ellipsize = TruncateAt.END
        roomID.isSingleLine = true
        roomID.setTextColor(Color.parseColor("#ff606060"))
        roomID.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        roomID.maxWidth = Utils.dp2px(120F, resources.displayMetrics)
        linearLayout.addView(roomID, params)
        val layoutParams = LayoutParams(-2, -2)
        val marginStart: Int = Utils.dp2px(16F, resources.displayMetrics)
        val marginTop: Int = Utils.dp2px(10F, resources.displayMetrics)
        layoutParams.setMargins(marginStart, marginTop, 0, 0)
        addView(linearLayout, layoutParams)
    }

    fun setRoomName(roomName: String?) {
        this.roomName.text = roomName
    }

    fun setRoomID(roomID: String) {
        this.roomID.text = "ID: $roomID"
    }


}