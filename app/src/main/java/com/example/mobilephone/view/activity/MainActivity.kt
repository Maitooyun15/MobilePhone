package com.example.mobilephone.view.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobilephone.R
import com.example.mobilephone.model.FragmentModel
import com.example.mobilephone.view.adapter.SectionsPagerAdapter
import com.example.mobilephone.view.fragment.FavoriteFragment
import com.example.mobilephone.view.fragment.MobileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var check = -1
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

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
            mBuilder.setSingleChoiceItems(listItems, check) { dialogInterface, i ->
                check = i
                when (i) {
                    0 -> {
                        val fragment = sectionsPagerAdapter.getItem(0)

                        if (fragment is MobileFragment) {
                            fragment.sortLowToHigh()
                        }


                    }
                    1 -> {
                        val fragment: Fragment = sectionsPagerAdapter.getItem(0)
                        if (fragment is MobileFragment) {
                            fragment.sortHighToLow()
                        }

                    }
                    2 -> {
                        val fragment: Fragment = sectionsPagerAdapter.getItem(0)
                        if (fragment is MobileFragment) {
                            fragment.sortRating()
                        }

                    }
                }
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
        sectionsPagerAdapter =
            SectionsPagerAdapter(homeList, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }


}

