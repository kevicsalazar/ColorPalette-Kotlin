package com.kevicsalazar.colorpalette

import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder


class MainActivity : AppCompatActivity() {

    lateinit var adapter: ColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        adapter = ColorAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        ivColorPreview.setOnClickListener {
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("Choose color")
                    .initialColor((ivColorPreview.background as ColorDrawable).color)
                    .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                    .density(12)
                    .lightnessSliderOnly()
                    .setPositiveButton("ok") { dialog, selectedColor, allColors ->
                        val palette = ColorUtils.palette(selectedColor)
                        updateView(palette)
                        dialog.dismiss()
                    }
                    .setNegativeButton("cancel") { dialog, which -> dialog.cancel() }
                    .build()
                    .show()
        }

        val palette = ColorUtils.palette((ivColorPreview.background as ColorDrawable).color)
        updateView(palette)

    }

    fun updateView(palette: ColorPalette) {
        toolbar.setBackgroundColor(palette.C500)
        window.statusBarColor = palette.C700
        tvColorHex.text = String.format("#%06X", 0xFFFFFF and palette.C500)
        ivColorPreview.setBackgroundColor(palette.C500)
        updatePalette(palette)
    }

    fun updatePalette(palette: ColorPalette) {
        adapter.clear()
        adapter.add("50", palette.C50)
        adapter.add("100", palette.C100)
        adapter.add("200", palette.C200)
        adapter.add("300", palette.C300)
        adapter.add("400", palette.C400)
        adapter.add("500", palette.C500)
        adapter.add("600", palette.C600)
        adapter.add("700", palette.C700)
        adapter.add("800", palette.C800)
        adapter.add("900", palette.C900)
        adapter.add("A100", palette.A100)
        adapter.add("A200", palette.A200)
        adapter.add("A400", palette.A400)
        adapter.add("A700", palette.A700)
    }

}
