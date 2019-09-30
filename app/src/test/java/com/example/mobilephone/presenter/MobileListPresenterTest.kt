package com.example.mobilephone.presenter

import com.example.mobilephone.model.DataModel
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.service.MobileApiService
import com.example.mobilephone.view.contract.MobileInterface
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresenterTest {
    @Mock
    lateinit var view: MobileInterface

    @Mock
    lateinit var service: MobileApiService

    @Mock
    lateinit var shareFav: ModelPreferences

    lateinit var presenter: MobileListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MobileListPresenter(view, shareFav, service)
    }

    @Test
    fun testGetMobileListApi() {
        //given
        whenever(service.getMobileList()).thenReturn(mock())

        //when
        presenter.getMobileApi()

        //then
        verify(service).getMobileList()
    }

    @Test
    fun testGetMobileListApiApiFailed() {
        //given
        val call = mock<Call<List<MobileModel>>>()
        whenever(service.getMobileList()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileModel>>>(0).onFailure(mock(), mock())
        }

        //when
        presenter.getMobileApi()

        // verify(view).showErrorMsg("Failed")
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testGetMobileListApiResponseBodyNull() {
        //given
        val call = mock<Call<List<MobileModel>>>()
        whenever(service.getMobileList()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileModel>>>(0).onResponse(mock(), Response.success(null))
        }

        //when
        presenter.getMobileApi()

        //then
        verifyZeroInteractions(view)
    }

    @Test
    fun testGetMobileListApiResponseBodyEmptyList() {
        //given
        val call = mock<Call<List<MobileModel>>>()
        whenever(service.getMobileList()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileModel>>>(0).onResponse(mock(), Response.success(listOf()))
        }

        //when
        presenter.getMobileApi()

        //then
        verifyZeroInteractions(view)
    }

    @Test
    fun testGetMobileListApiResponseBodyMobileModel() {
        //given
        var mobileModel = DataModel.getMobileList()
        val call = mock<Call<List<MobileModel>>>()
        whenever(service.getMobileList()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileModel>>>(0).onResponse(mock(), Response.success(mobileModel))
        }

        //when
        presenter.getMobileApi()

        //then
        verify(view).setMobile(eq(mobileModel))
    }

    @Test
    fun testGetMobileLowToHigh() {
        val sort = DataModel.getMobileList().sortedBy { it.price }
        presenter.addData(sort)

        presenter.getMobileSortLowToHigh()

        verify(view).setMobile(eq(sort))
    }

    @Test
    fun testGetMobileHighToLow() {
        val sort = DataModel.getMobileList().sortedByDescending { it.price }
        presenter.addData(sort)

        presenter.getMobileHighToLow()

        verify(view).setMobile(eq(sort))
    }

    @Test
    fun testGetMobileRating() {
        val sort = DataModel.getMobileList().sortedByDescending { it.rating }
        presenter.addData(sort)

        presenter.getMobileSortRating()

        verify(view).setMobile(eq(sort))
    }
}