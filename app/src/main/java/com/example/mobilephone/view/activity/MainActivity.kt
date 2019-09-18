package com.example.mobilephone.view.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilephone.R
import com.example.mobilephone.model.FragmentModel
import com.example.mobilephone.view.fragment.FavoriteFragment
import com.example.mobilephone.view.fragment.MobileFragment
import com.example.mobilephone.view.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val SORT1 = "Price low to high"
        const val SORT2 = "Price high to low"
        const val SORT3 = "Rating 5-1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()

        btnSort.setOnClickListener {
            val listItems = arrayOf(SORT1, SORT2, SORT3)
            val mBuilder = AlertDialog.Builder(this@MainActivity)
            mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                dialogInterface.dismiss()
            }

            val mDialog = mBuilder.create()
            mDialog.show()
        }

    }

    private fun setView() {

        val homeList: List<FragmentModel> = listOf(
            FragmentModel("Mobile List", MobileFragment.newInstance()),
            FragmentModel("Favorite List", FavoriteFragment.newInstance())
        )

        // viewPager คือตัวที่ hold หน้าสองหน้า fragment tab รับ adaptor เข้ามา ต้องการ viewpagger
        val sectionsPagerAdapter =
            SectionsPagerAdapter(homeList, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }
}