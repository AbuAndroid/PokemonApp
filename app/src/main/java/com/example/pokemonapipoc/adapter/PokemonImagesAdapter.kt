package com.example.pokemonapipoc.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.pokemonapipoc.R


class PokemonImagesAdapter(private val context: Context,private var imageList: ArrayList<String>): PagerAdapter() {



    override fun getCount(): Int {
       return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =  (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.pokemoncustomimages, null)
        val ivImages = view.findViewById<ImageView>(R.id.uiIvCustomImages)

        imageList[position].let {
            Glide.with(context)
                .load(it)
                .into(ivImages)
        }

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view

    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}