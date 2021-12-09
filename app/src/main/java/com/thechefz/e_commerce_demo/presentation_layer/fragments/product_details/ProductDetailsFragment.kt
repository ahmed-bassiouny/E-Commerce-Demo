package com.thechefz.e_commerce_demo.presentation_layer.fragments.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.thechefz.e_commerce_demo.R
import com.thechefz.e_commerce_demo.utils.extensions.showImage
import kotlinx.android.synthetic.main.product_details_fragment.*

class ProductDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }

    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_details_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    val args: ProductDetailsFragmentArgs by navArgs()

    private fun initView() {
        val item = args.product
        tvProductName.text = item.name
        ivProduct.showImage(item.img)
        ratingProduct.rating = item.rating.toFloatOrNull() ?: 0.0f
        tvProductDesc.text = item.description
    }


}