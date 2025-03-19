package geeky.saif.composektordi.utility.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

object ToastUtils {

    fun showCustomToast(
        context: Context,
        message: String,
      /*  backgroundColor: Int = android.R.color.holo_blue_light,
        textColor: Int = android.R.color.white,
        gravity: Int = Gravity.BOTTOM,
        xOffset: Int = 0,
        yOffset: Int = 100*/
    ) {
        val toast = Toast(context)

        // Inflate a default layout
        val inflater = LayoutInflater.from(context)
        val toastView = inflater.inflate(android.R.layout.simple_list_item_1, null)

        // Customize the TextView
        val textView = toastView.findViewById<TextView>(android.R.id.text1)
        textView.text = message
        textView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.darker_gray))
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        textView.setPadding(16, 16, 16, 16)

        // Set the view to the Toast
        toast.view = toastView
        toast.duration = Toast.LENGTH_SHORT

        // Set position
        toast.setGravity(Gravity.BOTTOM, 0, 100)
        toast.show()
    }
}
