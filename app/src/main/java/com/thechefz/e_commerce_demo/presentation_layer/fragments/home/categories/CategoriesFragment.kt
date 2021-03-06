package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.thechefz.e_commerce_demo.R
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesFragment : Fragment() {


    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val args : CategoriesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesViewModel.handleDeepLink(args)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()

    }

    private fun observer() {
        categoriesViewModel.ordersData.observe(viewLifecycleOwner, Observer {
            recycerlPastOrder.adapter = PastOrderItemAdapter(it)
        }, loadingObserver = Observer { }, commonErrorObserver = Observer {
            Toast.makeText(requireContext(), it.getMessage(), Toast.LENGTH_SHORT).show()
        })

        categoriesViewModel.categoryData.observe(viewLifecycleOwner, Observer {
            recycler.adapter = CategoryAdapter(it) {
                activity?.let { it1 ->
                    Navigation.findNavController(it1, R.id.nav_host_fragment).navigate(
                        R.id.navigation_dashboard,
                        bundleOf("category" to it,)
                    )
                }
            }
        }, loadingObserver = Observer { }, commonErrorObserver = Observer {
            Toast.makeText(requireContext(), it.getMessage(), Toast.LENGTH_SHORT).show()
        })

        categoriesViewModel.handlePromotionDialog.observe(viewLifecycleOwner, Observer {
            showDialog(it)
        })

    }

    private fun showDialog(it: String?) {
        context?.let { it1 ->
            MaterialAlertDialogBuilder(it1)
                .setMessage(it)
                .setNegativeButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}