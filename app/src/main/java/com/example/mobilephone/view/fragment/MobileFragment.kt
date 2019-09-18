package com.example.mobilephone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilephone.view.activity.DetailMobileActivity
import com.example.mobilephone.R
import com.example.mobilephone.view.adapter.MobileAdapter
import com.example.mobilephone.view.adapter.OnMobileClickListener
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.service.MobilePhoneManager
import com.example.mobilephone.presenter.MobileListPresenter
import com.example.mobilephone.view.MobileInterface
import kotlinx.android.synthetic.main.fragment_mobile.*


class MobileFragment : Fragment(), MobileInterface {

    private val presenter = MobileListPresenter(this, MobilePhoneManager().createService())

    companion object {
        // ส่งหน้าตัวเอง
        fun newInstance(): MobileFragment =
            MobileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mobile, container, false)
    }


    override fun setMobile(mobileModelList: List<MobileModel>) {
        val listener = object : OnMobileClickListener {
            override fun onMobileClick(mobileModelList: MobileModel) {
                DetailMobileActivity.startActivity(context, mobileModelList)
            }
        }


        val mobileAdapter = MobileAdapter(mobileModelList, listener)
        rvMobile.adapter = mobileAdapter
        rvMobile.layoutManager = LinearLayoutManager(context)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getMobileApi()

    }

}
