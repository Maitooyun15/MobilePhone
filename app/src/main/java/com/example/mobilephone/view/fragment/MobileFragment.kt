package com.example.mobilephone.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilephone.ModelPreferences
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.presenter.MobileListPresenter
import com.example.mobilephone.service.MobilePhoneManager
import com.example.mobilephone.view.MobileInterface
import com.example.mobilephone.view.activity.DetailMobileActivity
import com.example.mobilephone.view.activity.onListener
import com.example.mobilephone.view.adapter.MobileAdapter
import com.example.mobilephone.view.adapter.OnMobileClickListener
import kotlinx.android.synthetic.main.fragment_mobile.*


class MobileFragment : Fragment(), MobileInterface, OnMobileClickListener {

    private val presenter = MobileListPresenter(this, MobilePhoneManager().createService())
    private lateinit var mobileAdapter: MobileAdapter
    private var onListener: onListener? = null
    private lateinit var remove: ModelPreferences

    companion object {
        fun newInstance(): MobileFragment = MobileFragment()
    }

    override fun onRemoveHeart(remove: MobileModel) {
        onListener?.onRemoveHeart(remove)
    }

    override fun onRemoveClick(unFav: MobileModel) {
        //presenter.addData(unFav)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mobile, container, false)
    }

    fun setOnListener(onListener: onListener) {
        this.onListener = onListener
    }// set listener ให้ นี้รู้จัก main

    override fun onFavoriteClick(favorite: MobileModel) {
        onListener?.onFavorite(favorite)
    }// ส่งค่าไปให้ main

    override fun onMobileClick(mobile: MobileModel) {
        DetailMobileActivity.startActivity(context, mobile)
    }

    override fun setMobile(mobileModelList: List<MobileModel>) {
        mobileAdapter.addMobile(mobileModelList)
    }

    fun sortLowToHigh() {
        presenter.getMobileSortLowToHigh()
    }

    fun sortHighToLow() {
        presenter.getMobileHighToLow()
    }

    fun sortRating() {
        presenter.getMobileSortRating()
    }

    fun notifyto(un: MobileModel) {
        //  mobileAdapter.notifyDataSetChanged()
        mobileAdapter.updateFavorite(un)
        // mobileAdapter.notifyDataSetChanged()
        //  Log.e("test", "remove")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            remove = ModelPreferences(it)
            presenter.getMobileApi(remove)
        }
        mobileAdapter = MobileAdapter(this, remove)
        rvMobile.adapter = mobileAdapter
        rvMobile.layoutManager = LinearLayoutManager(context)
        rvMobile.itemAnimator = DefaultItemAnimator()

    }
}



