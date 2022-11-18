package com.example.cleancryptocurrency.presentation.coins_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cleancryptocurrency.databinding.FragmentCoinsListBinding
import com.example.cleancryptocurrency.domain.models.Coin
import com.example.cleancryptocurrency.presentation.adapters.CoinsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CoinsListFragment : Fragment() {

    private lateinit var binding: FragmentCoinsListBinding
    private lateinit var coinsAdapter: CoinsListAdapter
    private val viewModel: CoinsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinsListBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            coinsAdapter = CoinsListAdapter(object : CoinsListAdapter.OnCoinsClickListener {
                override fun onClick(coin: Coin) {
                    findNavController().navigate(
                        CoinsListFragmentDirections.actionCoinsListFragmentToCoinDetailsFragment(
                            coin.id
                        )
                    )
                }
            })
            adapter = coinsAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.coinsListUiState.collectLatest { state ->
                binding.apply {
                    if (state.isLoading) {
                        progressBar.visibility = View.VISIBLE
                        tvError.visibility = View.GONE
                    }
                    if (state.coinsList.isNotEmpty()) {
                        tvError.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        coinsAdapter.differ.submitList(state.coinsList)
                    }
                    if (state.error.isNotEmpty()) {
                        tvError.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

}