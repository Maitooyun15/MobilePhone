package com.example.mobilephone.presenter

import android.content.Context
import android.content.SharedPreferences
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.service.MobileApiService
import com.example.mobilephone.view.contract.MobileInterface
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MobileListPresenterTest {
    @Mock
    lateinit var view: MobileInterface

    @Mock
    lateinit var service: MobileApiService


    lateinit var shareFav: ModelPreferences

    lateinit var presenter: MobileListPresenter
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        presenter = MobileListPresenter(view, shareFav , service)
//    }
//
//    @Test
//    fun testGetMobileListApi() {
//        //given
//        whenever(service.getMobileList()).thenReturn(mock())
//
//        //when
//        presenter.getMobileApi()
//
//        //then
//        verify(service).getMobileList()
//    }
}