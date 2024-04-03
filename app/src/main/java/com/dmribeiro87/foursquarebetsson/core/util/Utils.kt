package com.dmribeiro87.foursquarebetsson.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.ImageView
import androidx.annotation.IntDef
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dmribeiro87.foursquarebetsson.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object Utils {

    @IntDef(View.GONE, View.INVISIBLE)
    annotation class HideTypeDef

    fun hasInternetConnection(@ApplicationContext appContext: Context): Boolean {

        val connectivityManager: ConnectivityManager = appContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> return false
        }
    }

    fun ImageView.loadImageRounded(imageUrl: String, context: Context, radius: Int) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(CenterCrop(), RoundedCorners(radius))
            .into(this)
    }

    fun ImageView.loadImage(imageUrl: String, context: Context) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    fun String.toAmericanDateFormat(): String {
        val iso8601Parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val date = iso8601Parser.parse(this)
        val americanDateFormat = SimpleDateFormat("MM/dd/yyyy, hh:mm a", Locale.getDefault())
        return americanDateFormat.format(date)
    }


    fun View.show() = run { this.visibility = View.VISIBLE }
    fun View.hide(@HideTypeDef hideType: Int = View.GONE) = run { this.visibility = hideType }
}