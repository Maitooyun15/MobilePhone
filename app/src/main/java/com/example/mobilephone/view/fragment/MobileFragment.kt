package com.example.mobilephone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.presenter.*
import com.example.mobilephone.service.MobilePhoneManager
import com.example.mobilephone.view.MobileInterface
import com.example.mobilephone.view.activity.DetailMobileActivity
import com.example.mobilephone.view.adapter.MobileAdapter
import com.example.mobilephone.view.adapter.OnMobileClickListener
import kotlinx.android.synthetic.main.fragment_mobile.*


class MobileFragment : Fragment(), MobileInterface {

    private val presenter = MobileListPresenter(this, MobilePhoneManager().createService())
    private val presenter2 = MobileSortLowToHighPresenter(this, MobilePhoneManager().createService())
    private val presenter3 = MobileSortHighToLowPresenter(this , MobilePhoneManager().createService())
    private val presenter4= MobileSortRatingPresenter(this , MobilePhoneManager().createService())
    private lateinit var mobileAdapter: MobileAdapter



    private val listener = object : OnMobileClickListener {
        override fun onMobileClick(mobileModelList: MobileModel) {
            DetailMobileActivity.startActivity(context, mobileModelList)
        }
    }

    companion object {
        // ส่งหน้าตัวเอง
        fun newInstance(): MobileFragment =
            MobileFragment()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mobile, container, false)
    }


    override fun setMobile(mobileModelList: List<MobileModel>) {

        mobileAdapter.addMobile(mobileModelList)
//            }
//        }


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mobileAdapter = MobileAdapter(listener)
        rvMobile.adapter = mobileAdapter
        rvMobile.layoutManager = LinearLayoutManager(context)
        presenter.getMobileApi()

    }

    fun sort() {
        presenter2.getMobileSortLowToHigh()
    }

    fun sort2(){
        presenter3.getMobileHighToLow()
    }

    fun sort3(){
        presenter4.getMobileSortRating()
    }




}

