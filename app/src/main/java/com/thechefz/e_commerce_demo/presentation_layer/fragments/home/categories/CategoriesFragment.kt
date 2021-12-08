package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.thechefz.e_commerce_demo.R
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesFragment : Fragment() {


    private val categoriesViewModel: CategoriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
        observer()
    }

    private fun observer() {
        categoriesViewModel.ordersData.observe(viewLifecycleOwner, Observer {
            recycerlPastOrder.adapter = PastOrderItemAdapter(it)
        }, loadingObserver = Observer { }, commonErrorObserver = Observer {
            Toast.makeText(requireContext(), it.getMessage(), Toast.LENGTH_SHORT).show()
        })

        categoriesViewModel.categoryData.observe(viewLifecycleOwner, Observer {
            recycler.adapter = CategoryAdapter(it)
        }, loadingObserver = Observer { }, commonErrorObserver = Observer {
            Toast.makeText(requireContext(), it.getMessage(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun listener() {

    }

}