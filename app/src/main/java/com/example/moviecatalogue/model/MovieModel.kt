package com.example.moviecatalogue.model
import android.os.Parcelable
import com.example.moviecatalogue.viewModel.DetailViewModel
import kotlinx.android.parcel.Parcelize
import kotlin.reflect.KProperty

@Parcelize
class MovieModel (
    var id: String,
    var title: String,
    var desc: String,
    var genre: String,
    var realese: String,
    var poster: Int
):Parcelable

