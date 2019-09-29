package com.example.mobilephone.view.contract

import com.example.mobilephone.model.MobileModel

interface MainActivityInterface {
    fun onAddFavorite(favorite: MobileModel)
    fun onRemoveSwipeFavorite(unFav: MobileModel)
    fun onRemoveHeartFavorite(unFav: MobileModel)
}