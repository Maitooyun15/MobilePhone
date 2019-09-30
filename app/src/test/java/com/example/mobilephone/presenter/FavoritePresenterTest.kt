package com.example.mobilephone.presenter

import com.example.mobilephone.model.DataModel
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.view.contract.FavoriteInterface
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FavoritePresenterTest {

    @Mock
    lateinit var view: FavoriteInterface

    @Mock
    lateinit var shareFav: ModelPreferences

    lateinit var presenter: FavoritePresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = FavoritePresenter(view, shareFav)
    }

    @Test
    fun testGetFavoriteList() {
        val favorites = DataModel.getFavoriteList()
        whenever(shareFav.readFavorite("model")).thenReturn(favorites)
        presenter.getFavorite()
        verify(view).setMobile(favorites)
    }

    @Test
    fun testGetFavoriteEmpty() {
        whenever(shareFav.readFavorite(any())).thenReturn(arrayListOf())
        presenter.getFavorite()
        verifyZeroInteractions(view)
    }

    @Test
    fun testGetFavoriteLowToHigh() {
        val favorites = DataModel.getFavoriteList()
        val sort = favorites.sortedBy { it.price }
        whenever(shareFav.readFavorite("model")).thenReturn(ArrayList(sort))
        presenter.getSortLowToHigh()
        verify(view).setMobile(sort)
    }

    @Test
    fun testGetFavoriteLowToHighEmpty() {
        whenever(shareFav.readFavorite(any())).thenReturn(arrayListOf())
        presenter.getSortLowToHigh()
        verifyZeroInteractions(view)
    }

    @Test
    fun testGetFavoriteHighToLow() {
        val favorites = DataModel.getFavoriteList()
        val sort = favorites.sortedByDescending { it.price }
        whenever(shareFav.readFavorite("model")).thenReturn(ArrayList(sort))
        presenter.getSortHighToLow()
        verify(view).setMobile(sort)
    }

    @Test
    fun testGetFavoriteHighToLowEmpty() {
        whenever(shareFav.readFavorite(any())).thenReturn(arrayListOf())
        presenter.getSortHighToLow()
        verifyZeroInteractions(view)
    }

    @Test
    fun testGetFavoriteRating() {
        val favorites = DataModel.getFavoriteList()
        val sort = favorites.sortedByDescending { it.rating }
        whenever(shareFav.readFavorite("model")).thenReturn(ArrayList(sort))
        presenter.getSortRating()
        verify(view).setMobile(sort)
    }

    @Test
    fun testGetFavoriteRatingEmpty() {
        whenever(shareFav.readFavorite(any())).thenReturn(arrayListOf())
        presenter.getSortRating()
        verifyZeroInteractions(view)
    }
}