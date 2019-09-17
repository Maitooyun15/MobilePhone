package com.example.mobilephone.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilephone.R
import com.example.mobilephone.adapter.MobileAdapter
import com.example.mobilephone.adapter.OnMobileClickListener
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.MobilePhoneManager
import com.example.mobilephone.presenter.MobileListPresenter
import kotlinx.android.synthetic.main.fragment_mobile.*


class MobileFragment : Fragment(), MobileInterface {

    private val presenter = MobileListPresenter(this, MobilePhoneManager().createService())
    // private lateinit var listMobile: RecyclerView
    private var model: List<MobileModel> = listOf()
    private lateinit var mobileAdapter: MobileAdapter

    companion object {
        // ส่งหน้าตัวเอง
        fun newInstance(): MobileFragment = MobileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mobile, container, false)
    }


    override fun setMobile(mobileModelList: List<MobileModel>) {
        val listener = object : OnMobileClickListener {
            override fun onMobileClick(mobile: MobileModel) {
//                BeerDetailActivity.startActivity(context, beerModel)
            }
        }

        mobileAdapter = MobileAdapter(mobileModelList , listener)
        listMobile.adapter = mobileAdapter
        listMobile.layoutManager = LinearLayoutManager(context)
        listMobile.itemAnimator = DefaultItemAnimator()


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getMobileApi()
    }


}
