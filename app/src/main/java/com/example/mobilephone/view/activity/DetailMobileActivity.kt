package com.example.mobilephone.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileImageModel
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.presenter.MobileImagePresenter
import com.example.mobilephone.service.MobilePhoneManager
import com.example.mobilephone.view.adapter.ImageAdapter
import com.example.mobilephone.view.contract.MobileImageInterface
import kotlinx.android.synthetic.main.activity_detail_mobile.*

class DetailMobileActivity : AppCompatActivity(), MobileImageInterface {

    companion object {
        const val EXTRA_KEY_MODEL = "MODEL"

        fun startActivity(context: Context?, model: MobileModel) =
            context?.startActivity(
                Intent(context, DetailMobileActivity::class.java).also { intent ->
                    intent.putExtra(EXTRA_KEY_MODEL, model)
                }
            )
    }

    private val presenter = MobileImagePresenter(this, MobilePhoneManager().createService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mobile)
        setView()
    }

    private fun setView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val model: MobileModel = intent.getParcelableExtra(EXTRA_KEY_MODEL)
        presenter.getIdMobile(model.id)

        txtName.text = model.name
        txtBand.text = model.band
        txtDescription.text = model.description
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setImageMobile(imageModel: List<MobileImageModel>) {
        val mobileAdapter = ImageAdapter(imageModel)
        rvImage.adapter = mobileAdapter
        rvImage.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvImage.itemAnimator = DefaultItemAnimator()
    }

    override fun showErrorMsg(msg: String) {
        println(msg)
    }
}