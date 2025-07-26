package com.ecommerce.swift.ui.fragments.adminpanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerce.swift.databinding.FragmentOrderListBinding
import com.ecommerce.swift.ui.adapters.OrderListAdapter
import com.ecommerce.swift.ui.viewmodels.OrderListViewModel
import com.ecommerce.swift.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllOrderListFragment : Fragment() {

    private lateinit var binding: FragmentOrderListBinding
    private val viewModel by viewModels<OrderListViewModel>()
    private val orderListAdapter by lazy { OrderListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOrdersRv()

        binding.apply {
            imageCloseOrders.setOnClickListener {
                findNavController().navigateUp()
            }

            orderListAdapter.onClick = {
                val action =
                    AllOrderListFragmentDirections.actionAllOrderListFragmentToOrderDetailsFragment(
                        it
                    )
                findNavController().navigate(action)
            }

            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    lifecycleScope.launch {
                        viewModel.allOrders.collectLatest {
                            when (it) {
                                is Resource.Loading -> {
                                    progressbarAllOrders.visibility = View.VISIBLE
                                }

                                is Resource.Success -> {
                                    progressbarAllOrders.visibility = View.INVISIBLE
                                    orderListAdapter.differ.submitList(it.data)

                                    if (it.data.isNullOrEmpty()) {
                                        rvAllOrders.visibility = View.GONE
                                        tvEmptyOrders.visibility = View.VISIBLE
                                    } else {
                                        tvEmptyOrders.visibility = View.GONE
                                        rvAllOrders.visibility = View.VISIBLE
                                    }
                                }

                                is Resource.Error -> {
                                    progressbarAllOrders.visibility = View.INVISIBLE
                                    Toast.makeText(
                                        requireContext(),
                                        it.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    Unit
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupOrdersRv() {
        binding.rvAllOrders.apply {
            adapter = orderListAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}