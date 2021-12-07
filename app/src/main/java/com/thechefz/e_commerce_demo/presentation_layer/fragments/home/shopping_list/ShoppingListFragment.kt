package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.shopping_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.thechefz.e_commerce_demo.R
import com.thechefz.e_commerce_demo.presentation_layer.fragments.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListFragment : Fragment() {


    private val shoppingListViewModel: ShoppingListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        listener()
    }

    private fun listener() {
        btnPlaceOrder.setOnClickListener {
            context?.let { it1 -> showConfirmationDialog(it1) }
        }
    }

    private fun observer() {
        shoppingListViewModel.cartData.observe(viewLifecycleOwner, Observer {
            recyclerCart.adapter = ShoppingItemAdapter(it)
        })
    }

    private fun showConfirmationDialog(context: Context){
        MaterialAlertDialogBuilder(context)
            .setMessage("Are you sure you want to placeOrder")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                showCreatedDialog(context)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showCreatedDialog(context: Context){
        MaterialAlertDialogBuilder(context)
            .setMessage("Order Created")
            .setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}