package com.example.mobilephone.presenter

import com.example.mobilephone.model.DataModel
import com.example.mobilephone.model.MobileImageModel
import com.example.mobilephone.service.MobileApiService
import com.example.mobilephone.view.contract.MobileImageInterface
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileImagePresenterTest {
    @Mock
    lateinit var view: MobileImageInterface

    @Mock
    lateinit var service: MobileApiService

    lateinit var presenter: MobileImagePresenter
    private val idImage = 3

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MobileImagePresenter(view, service)
    }

    @Test
    fun testGetMobileImageApi() {
        //given
        whenever(service.getMobileById(idImage)).thenReturn(mock())

        //when
        presenter.getIdMobile(idImage)

        //then
        verify(service).getMobileById(idImage)
    }

    @Test
    fun testGetMobileImageApiFailed() {
        //given
        val call = mock<Call<List<MobileImageModel>>>()
        whenever(service.getMobileById(idImage)).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileImageModel>>>(0).onFailure(mock(), mock())
        }

        //when
        presenter.getIdMobile(idImage)

        //then
        // verify(view).showErrorMsg("Failed")
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testGetMobileImageApiResponseBodyNull() {
        //given
        val call = mock<Call<List<MobileImageModel>>>()
        whenever(service.getMobileById(idImage)).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileImageModel>>>(0).onResponse(mock(), Response.success(null))
        }

        //when
        presenter.getIdMobile(idImage)

        //then
        verifyZeroInteractions(view)
    }

    @Test
    fun testGetMobileImageApiResponseBodyEmptyList() {
        //given
        val call = mock<Call<List<MobileImageModel>>>()
        whenever(service.getMobileById(idImage)).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileImageModel>>>(0).onResponse(mock(), Response.success(listOf()))
        }

        //when
        presenter.getIdMobile(idImage)

        //then
        verifyZeroInteractions(view)
    }

    @Test
    fun testGetMobileImageApiResponseBodyMobileImageModel() {
        //given
        var mobileImageModel = DataModel.getMobileImageId()
        val call = mock<Call<List<MobileImageModel>>>()
        whenever(service.getMobileById(idImage)).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileImageModel>>>(0).onResponse(mock(), Response.success(mobileImageModel))
        }

        //when
        presenter.getIdMobile(idImage)

        //then
        verify(view).setImageMobile(eq(mobileImageModel))
    }
}