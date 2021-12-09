package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.product_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.thechefz.e_commerce_demo.R
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.inputmethod.EditorInfo

import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import com.thechefz.e_commerce_demo.utils.extensions.hideKeyboard


class ProductListFragment : Fragment() {


    private val productListViewModel: ProductListViewModel by viewModel()

    private val productAdapter by lazy { ProductAdapter(ArrayList()) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        listener()
        observe()
        productListViewModel.init(getData())

    }

    private fun listener() {
        etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                productListViewModel.fetchData(etSearch.text.toString())
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun initViews() {
        recycler.adapter = productAdapter
    }

    private fun observe() {
        productListViewModel.title.observe(viewLifecycleOwner, Observer {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = it
        })

        productListViewModel.data.observe(viewLifecycleOwner, Observer {
            productAdapter.list = it
            productAdapter.notifyDataSetChanged()
        })
    }

    private fun getData(): CategoryEntity? {
        return arguments?.let { ProductListFragmentArgs.fromBundle(it).category }
    }


}