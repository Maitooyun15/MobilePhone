package com.example.mobilephone.view.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobilephone.R
import com.example.mobilephone.model.FragmentModel
import com.example.mobilephone.view.fragment.FavoriteFragment
import com.example.mobilephone.view.fragment.MobileFragment
import com.example.mobilephone.view.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var check = 0
    private lateinit var sectionsPagerAdapter : SectionsPagerAdapter
    private val SAVENAME = "mobile_favorite"

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
                when(i){
                    0 -> if (check == 0){
                        val fragment = sectionsPagerAdapter.getItem(0)
                        if (fragment is MobileFragment) {
                            fragment.sortLowToHigh()
                        }

                        val fragment2 = sectionsPagerAdapter.getItem(1)
                        if (fragment2 is FavoriteFragment) {
                            fragment2.sort()
                        }


                    }
                    1 -> if (check == 0){
                        val fragment: Fragment = sectionsPagerAdapter.getItem(0)
                        if(fragment is MobileFragment){
                            fragment.sortHighToLow()
                        }

                    }
                    2-> if (check == 0){
                        val fragment: Fragment = sectionsPagerAdapter.getItem(0)
                        if(fragment is MobileFragment){
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

