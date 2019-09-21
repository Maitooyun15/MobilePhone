package com.example.mobilephone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilephone.ModelPreferences
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.presenter.FragmentViewModel
import com.example.mobilephone.presenter.MobileListPresenter
import com.example.mobilephone.service.MobilePhoneManager
import com.example.mobilephone.view.MobileInterface
import com.example.mobilephone.view.activity.DetailMobileActivity
import com.example.mobilephone.view.adapter.MobileAdapter
import com.example.mobilephone.view.adapter.OnMobileClickListener
import kotlinx.android.synthetic.main.fragment_mobile.*


class MobileFragment : Fragment(), MobileInterface, OnMobileClickListener {

    private val presenter = MobileListPresenter(this, MobilePhoneManager().createService())
    private lateinit var mobileAdapter: MobileAdapter
    private var sentData: FragmentViewModel? = null



    companion object {
        fun newInstance(): MobileFragment = MobileFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mobile, container, false)

    }

    override fun onFavoriteClick(favorite: MobileModel) {
        sentData = ViewModelProviders.of(activity!!).get(FragmentViewModel::class.java)
        sentData!!.setMsgCommunicator(favorite)
    }


    override fun onMobileClick(mobile: MobileModel) {
        DetailMobileActivity.startActivity(context, mobile)
    }


    override fun setMobile(mobileModelList: List<MobileModel>) {
        mobileAdapter.addMobile(mobileModelList)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var modelPreferences: ModelPreferences? = context?.let { ModelPreferences(it) }
        mobileAdapter = MobileAdapter(this, modelPreferences)
        rvMobile.adapter = mobileAdapter
        rvMobile.layoutManager = LinearLayoutManager(context)
        presenter.getMobileApi()

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

}



