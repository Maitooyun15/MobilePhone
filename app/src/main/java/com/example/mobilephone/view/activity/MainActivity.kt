package com.example.mobilephone.view.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilephone.R
import com.example.mobilephone.model.FragmentModel
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.view.adapter.SectionsPagerAdapter
import com.example.mobilephone.view.contract.MainActivityInterface
import com.example.mobilephone.view.fragment.FavoriteFragment
import com.example.mobilephone.view.fragment.MobileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityInterface {

    companion object {
        const val SORT_LOW_PRICE = "Price low to high"
        const val SORT_HIGH_PRICE = "Price high to low"
        const val SORT_HIGH_RATING = "Rating 5-1"
    }

    private var check = -1
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private val fragmentMobile = MobileFragment.newInstance()
    private val fragmentFavorite = FavoriteFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    private fun setView() {
        val homeList = listOf(
            FragmentModel(getString(R.string.tab_mobile), fragmentMobile.apply { setOnListener(this@MainActivity) }),
            FragmentModel(getString(R.string.tab_favorite), fragmentFavorite.apply { setOnListener(this@MainActivity) })
        )
        btnSort.setOnClickListener {
            val listItems = arrayOf(SORT_LOW_PRICE, SORT_HIGH_PRICE, SORT_HIGH_RATING)
            val mBuilder = AlertDialog.Builder(this@MainActivity)

            mBuilder.setSingleChoiceItems(listItems, check) { dialogInterface, sort ->
                check = sort
                when (sort) {
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
        sectionsPagerAdapter = SectionsPagerAdapter(homeList, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onAddFavorite(favorite: MobileModel) {
        fragmentFavorite.addFavorite(favorite, check)
    }

    override fun onRemoveSwipeFavorite(unFav: MobileModel) {
        fragmentMobile.removeSwipeFavorite(unFav)
    }

    override fun onRemoveHeartFavorite(unFav: MobileModel) {
        fragmentFavorite.removeHeart(unFav)
    }
}


