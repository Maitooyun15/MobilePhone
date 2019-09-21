package com.example.mobilephone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.ModelPreferences
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.presenter.FragmentViewModel
import com.example.mobilephone.presenter.SwipeToDeleteCallback
import com.example.mobilephone.view.activity.DetailMobileActivity
import com.example.mobilephone.view.adapter.FavoriteAdapter
import com.example.mobilephone.view.adapter.OnMobileClickListener
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {


    private lateinit var favoriteAdapter: FavoriteAdapter
    private var favoriteMobile: ArrayList<MobileModel> = arrayListOf()


    private val listener = object : OnMobileClickListener {
        override fun onFavoriteClick(favorite: MobileModel) {

        }

        override fun onMobileClick(mobileModelList: MobileModel) {
            DetailMobileActivity.startActivity(context, mobileModelList)
        }
    }

    companion object {
        fun newInstance(): FavoriteFragment =
            FavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var modelPreferences: ModelPreferences? = context?.let { ModelPreferences(it) }
        var model = ViewModelProviders.of(activity!!).get(FragmentViewModel::class.java)

        model.data.observe(this, Observer<MobileModel> { fav ->

            if (fav.chacked) {
                favoriteMobile.add(fav)
                favoriteMobile.toList().reversed()
            } else {
                favoriteMobile.remove(fav)
                favoriteMobile.toList().reversed()
            }
            favoriteAdapter.addFavorite(favoriteMobile)
        })

        favoriteAdapter = FavoriteAdapter(listener, modelPreferences)
        rvFavorite.adapter = favoriteAdapter
        rvFavorite.layoutManager = LinearLayoutManager(context)

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvFavorite.adapter as FavoriteAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvFavorite)


    }




}
