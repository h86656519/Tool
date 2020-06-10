package com.example.signatureview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //    var signatureViewKt: SignatureViewKt = SignatureViewKt(this)
    lateinit var btn: Button
    lateinit var undobtn: Button
    lateinit var singView: CompanyView
//    lateinit var singView: SignatureViewKt

    //    var singView: SignatureViewKt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setCompanyView()
        btn = findViewById(R.id.clearView)
        undobtn = findViewById(R.id.undo)
        btn.setOnClickListener(this)
        undobtn.setOnClickListener(this)

        setSignatureViewKt()
    }

    fun setCompanyView() {

        singView = findViewById(R.id.signatureView)
        singView.setBackgroundColor(Color.BLUE)
    }

    fun setSignatureViewKt() {

        singView = findViewById(R.id.signatureView)
        singView.setBackgroundColor(Color.WHITE)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.clearView -> {
                singView.clearCanvas()
            }

            R.id.undo -> {
                singView.undo()
            }

        }


    }
}
