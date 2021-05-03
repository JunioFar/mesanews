package com.example.android.mesanews.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.android.mesanews.databinding.NewsFeedFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFeedFragment : Fragment() {

    private val viewModel: NewsFeedViewModel by viewModels()
    private var searchJob: Job? = null
    private lateinit var binding: NewsFeedFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = NewsFeedFragmentBinding.inflate(inflater, container, false)
        binding.isLoading = true

        val adapter = HighlightsAdapter()
        binding.highlightsRecyclerView.adapter = adapter

        viewModel.getHighlights(requireContext()).observe(viewLifecycleOwner, { response ->
            binding.isLoading = false
            if (response.isSuccessful){
                response.body()?.let {
                    adapter.submitList(it.highlightList)
                }
            } else {
                Snackbar.make(binding.root, response.message(), Snackbar.LENGTH_SHORT).show()
            }
        })

        val newsAdapter = NewsAdapter()
        binding.newsRecyclerView.adapter = newsAdapter

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getNews(requireContext()).collectLatest {
                newsAdapter.submitData(it)
            }
        }
        return binding.root
    }

}