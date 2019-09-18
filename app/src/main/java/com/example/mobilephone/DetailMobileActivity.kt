package com.example.mobilephone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.mobilephone.adapter.ImageAdapter
import com.example.mobilephone.adapter.MobileAdapter
import com.example.mobilephone.model.MobileImageModel
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.MobilePhoneManager
import com.example.mobilephone.presenter.MobileIdPresenter
import com.example.mobilephone.ui.main.MobileImageInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailmobile.*
import kotlinx.android.synthetic.main.fragment_mobile.*

class DetailMobileActivity : AppCompatActivity() , MobileImageInterface {

    private val presenter = MobileIdPresenter(this, MobilePhoneManager().createService())

   // internal lateinit var viewpager: ViewPager


    companion object {
        const val EXTRA_KEY_MODEL = "MODEL"


        fun startActivity(context: Context?, model: MobileModel) =
            context?.startActivity(
                Intent(context, DetailMobileActivity::class.java).also { intent ->
                    intent.putExtra(EXTRA_KEY_MODEL, model)
                }
            )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailmobile)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)



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
        rvImage.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL, false)
    }




}