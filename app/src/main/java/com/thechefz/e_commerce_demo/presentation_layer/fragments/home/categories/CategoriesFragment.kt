package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thechefz.e_commerce_demo.R

class CategoriesFragment : Fragment() {

    private lateinit var categoriesViewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


}