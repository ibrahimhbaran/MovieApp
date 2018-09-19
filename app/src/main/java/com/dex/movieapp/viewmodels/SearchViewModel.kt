package com.dex.movieapp.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR

class SearchViewModel : BaseObservable(){

    @get:Bindable
    var searchTerm: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.searchTerm)
        }

}