package com.example.mobilephone.view.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilephone.R
import com.example.mobilephone.model.FragmentModel
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.view.adapter.SectionsPagerAdapter
import com.example.mobilephone.view.fragment.FavoriteFragment
import com.example.mobilephone.view.fragment.MobileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), onListener {

    private var check = -1
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    lateinit var homeList: List<FragmentModel>

    private val fragmentMobile = MobileFragment.newInstance()
    private val fragmentFavorite = FavoriteFragment.newInstance()


    companion object {
        const val SORT1 = "Price low to high"
        const val SORT2 = "Price high to low"
        const val SORT3 = "Rating 5-1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    private fun setView() {
        homeList = listOf(
            FragmentModel("Mobile List", fragmentMobile.apply { setOnListener(this@MainActivity) }),
            FragmentModel("Favorite List", fragmentFavorite.apply { setOnListener(this@MainActivity) })
        )
        //set listener ให้เมนรู้จักในหน้า fragment ต่างๆ

        btnSort.setOnClickListener {
            val listItems = arrayOf(SORT1, SORT2, SORT3)
            val mBuilder = AlertDialog.Builder(this@MainActivity)


            mBuilder.setSingleChoiceItems(listItems, check) { dialogInterface, i ->
                check = i
                when (i) {
                    0 -> {
                        fragmentMobile.sortLowToHigh()
                        fragmentFavorite.sortLowToHigh()
                    }
                    1 -> {
                        fragmentMobile.sortHighToLow()
                        fragmentFavorite.sortHighToLow()
                    }
                    2 -> {
                        fragmentMobile.sortRating()
                        fragmentFavorite.sortRating()

                    }
                }
                dialogInterface.dismiss()
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        sectionsPagerAdapter =
            SectionsPagerAdapter(homeList, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }

    override fun onFavorite(favorite: MobileModel) {
        fragmentFavorite.addFavorite(favorite, check)
    }

    override fun onRemoveFavorite(unFav: MobileModel) {
        val b = homeList[0].fragment as MobileFragment
        b.notifyto(unFav)
    }

    override fun onRemoveHeart(model: MobileModel) {
        val a = homeList[1].fragment as FavoriteFragment
        a.removeHeart(model)
    }

}

interface onListener {
    fun onFavorite(favorite: MobileModel)
    fun onRemoveFavorite(unFav: MobileModel)
    fun onRemoveHeart(model: MobileModel)
}

