package com.example.cleancryptocurrency.presentation.coin_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cleancryptocurrency.databinding.FragmentCoinDetailsBinding
import com.example.cleancryptocurrency.presentation.adapters.TagsAdapter
import com.example.cleancryptocurrency.presentation.adapters.TeamAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CoinDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailsBinding
    private val viewModel: CoinDetailsViewModel by viewModels()
    private lateinit var tagsAdapter: TagsAdapter
    private lateinit var teamAdapter: TeamAdapter
    private val args: CoinDetailsFragmentArgs by navArgs()
    private val coinId by lazy { args.coinId }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailsBinding.inflate(layoutInflater, container, false)
        setupTagsRecyclerView()
        setupTeamRecyclerView()
        return binding.root
    }

    private fun setupTeamRecyclerView() {
        binding.teamRecyclerView.apply {
            teamAdapter = TeamAdapter()
            adapter = teamAdapter
        }
    }

    private fun setupTagsRecyclerView() {
        binding.tagsRecyclerView.apply {
            tagsAdapter = TagsAdapter()
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = tagsAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCoinDetails(coinId)

        lifecycleScope.launchWhenStarted {
            viewModel.coinUiState.collectLatest { state ->
                binding.apply {
                    if (state.isLoading) {
                        layout.visibility = View.GONE
                        tvError.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    if (state.error.isNotEmpty()) {
                        layout.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        tvError.visibility = View.VISIBLE
                        tvError.text = state.error
                    }
                    state.coin?.let {
                        layout.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        tvError.visibility = View.GONE

                        tvRank.text = "${it.rank}."
                        tvName.text = it.name
                        tvDescription.text = it.description
                        if (it.is_active)
                            tvIsActive.text = "active"
                        else
                            tvIsActive.text = "inactive"
                        tagsAdapter.differ.submitList(it.tags)
                        teamAdapter.differ.submitList(it.team)
                    }
                }
            }
        }

    }

}

