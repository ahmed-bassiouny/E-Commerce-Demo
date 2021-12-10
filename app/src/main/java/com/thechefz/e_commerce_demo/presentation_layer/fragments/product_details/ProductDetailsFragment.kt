package com.thechefz.e_commerce_demo.presentation_layer.fragments.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.thechefz.e_commerce_demo.R
import com.thechefz.e_commerce_demo.data_layer.entities.ProductEntity
import com.thechefz.e_commerce_demo.utils.extensions.showImage
import com.thechefz.e_commerce_demo.utils.extensions.visibleOrGone
import kotlinx.android.synthetic.main.product_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }


    private val productDetailsViewModel: ProductDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_details_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productDetailsViewModel.init(args.product)
        observe()
        listener()
    }

    private fun listener() {
        btnPlus.setOnClickListener {
            productDetailsViewModel.addCount()
        }

        btnMin.setOnClickListener {
            productDetailsViewModel.removeCount()
        }
    }

    private fun observe() {
        productDetailsViewModel.countLiveData.observe(viewLifecycleOwner, Observer {
            tvCount.text = it.toString()
        })

        productDetailsViewModel.promotionErrorLiveData.observe(viewLifecycleOwner, Observer {
            tvError.text = it.toString()
            tvError.visibleOrGone(it.isNullOrEmpty().not())
        })

        productDetailsViewModel.productLiveData.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
    }

    val args: ProductDetailsFragmentArgs by navArgs()

    private fun setData(item: ProductEntity) {
        tvProductName.text = item.name
        ivProduct.showImage(item.img)
        ratingProduct.rating = item.rating.toFloatOrNull() ?: 0.0f
        tvProductDesc.text = item.description
        tvSubTotal.text = "Sub Total : ${item.totalPrice}"
        tvDiscount.visibleOrGone(item.promotionPrice.isNullOrEmpty().not())
        tvDiscount.text = "Discount: ${item.promotionPrice}"
        tvTotal.visibleOrGone(item.totalPriceAfterPromotion.isNullOrEmpty().not())
        tvTotal.text = "Total : ${item.totalPriceAfterPromotion}"
    }


}