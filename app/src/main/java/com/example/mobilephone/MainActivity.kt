package com.example.mobilephone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilephone.model.FragmentModel
import com.example.mobilephone.ui.main.FavoriteFragment
import com.example.mobilephone.ui.main.MobileFragment
import com.example.mobilephone.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()

    }

    private fun setView() {

        val homeList: List<FragmentModel> = listOf(
            FragmentModel("Mobile List", MobileFragment.newInstance()),
            FragmentModel("Favorite List", FavoriteFragment.newInstance())
        )

        // viewPager คือตัวที่ hold หน้าสองหน้า fragment tab รับ adaptor เข้ามา ต้องการ viewpagger
        val sectionsPagerAdapter = SectionsPagerAdapter(homeList, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }
}