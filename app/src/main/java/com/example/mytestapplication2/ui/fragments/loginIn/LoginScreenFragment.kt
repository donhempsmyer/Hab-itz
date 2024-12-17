package com.example.mytestapplication2.ui.fragments.loginIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.mytestapplication2.R


class LoginScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        val imageView: ImageView = rootView.findViewById(R.id.login_icon)


        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.habitz_icon)
        val resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, 150, 120, false)
        imageView.setImageBitmap(resizedBitmap)


        return rootView
    }

}