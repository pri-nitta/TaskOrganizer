package com.example.tasksorganizer.ui.intoscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.tasksorganizer.R
import com.example.tasksorganizer.data.models.IntroView
import com.example.tasksorganizer.ui.MainActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    lateinit var introView: List<IntroView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        addToIntroView()

        viewPager2.adapter = ViewPagerAdapter(introView)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        circleIndicator.setViewPager(viewPager2)

        viewPager2.unregisterOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 2) {
                    animationButton()
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun animationButton() {
        btn_start_app.visibility = View.VISIBLE
        btn_start_app.animate().apply {
            duration = 1400
            alpha(1f)

            btn_start_app.setOnClickListener {
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }.start()
    }

    private fun addToIntroView() {
        introView = listOf(
            IntroView(getString(R.string.page_1), R.drawable.ic_pixeltrue_icons_seo_ads_on_google_1),
            IntroView(getString(R.string.page_2), R.drawable.ic_pixeltrue_icons_receipt_1),
            IntroView(getString(R.string.page_3), R.drawable.ic_pixeltrue_icons_seo_business_location_3)
        )
    }
}