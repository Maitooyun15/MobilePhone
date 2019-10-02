package com.example.mobilephone.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.presenter.MobileListPresenter
import com.example.mobilephone.service.MobilePhoneManager
import com.example.mobilephone.view.activity.DetailMobileActivity
import com.example.mobilephone.view.adapter.MobileAdapter
import com.example.mobilephone.view.contract.MainActivityInterface
import com.example.mobilephone.view.contract.MobileInterface
import kotlinx.android.synthetic.main.fragment_mobile.*


class MobileFragment : Fragment(), MobileInterface, MobileInterface.OnClickMobileList {

    companion object {
        fun newInstance(): MobileFragment = MobileFragment()
    }

    private lateinit var presenter: MobileListPresenter
    private lateinit var mobileAdapter: MobileAdapter
    private lateinit var shareFavorite: ModelPreferences
    private var onListener: MainActivityInterface? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            shareFavorite = ModelPreferences(it)
            presenter = MobileListPresenter(this, shareFavorite, MobilePhoneManager().createService())
            presenter.getMobileApi()
        }
        mobileAdapter = MobileAdapter(this, shareFavorite)
        rvMobile.adapter = mobileAdapter
        rvMobile.layoutManager = LinearLayoutManager(context)
        rvMobile.itemAnimator = DefaultItemAnimator()
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

    fun removeSwipeFavorite(unFav: MobileModel) {
        mobileAdapter.removeSwipeFavorite(unFav)
    }

    override fun onRemoveHeart(remove: MobileModel) {
        onListener?.onRemoveHeartFavorite(remove)
    }

    fun setOnListener(onListener: MainActivityInterface) {
        this.onListener = onListener
    }

    override fun onFavoriteClick(favorite: MobileModel) {
        onListener?.onAddFavorite(favorite)
    }

    override fun setMobile(mobileList: List<MobileModel>) {
        mobileAdapter.mobileList(mobileList)
        if (mobileList.isNotEmpty()) {
            loading.visibility = View.GONE
        }
    }

    override fun onMobileDetailClick(mobile: MobileModel) {
        DetailMobileActivity.startActivity(context, mobile)
    }

    override fun showErrorMsg(msg: String) {
        Log.e("error", msg)
    }
}



