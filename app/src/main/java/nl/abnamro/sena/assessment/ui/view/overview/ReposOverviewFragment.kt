package nl.abnamro.sena.assessment.ui.view.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import nl.abnamro.sena.assessment.data.paging.asMergedLoadStates
import nl.abnamro.sena.assessment.databinding.FragmentOverviewBinding
import nl.abnamro.sena.assessment.ui.view.overview.adapter.ReposLoadStateAdapter
import nl.abnamro.sena.assessment.ui.view.overview.adapter.RepoItemAdapter
import nl.abnamro.sena.assessment.ui.view.overview.adapter.RepoItemClickListener
import nl.abnamro.sena.assessment.ui.viewmodel.overview.ReposOverviewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReposOverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReposOverviewViewModel by viewModel()
    private val adapter = RepoItemAdapter(RepoItemClickListener { id ->
        repoItemClickListener(id)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeToRefresh()
        setupRecyclerView()
        initAdapter()
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
    }

    private fun setupRecyclerView() {
        binding.repoList.layoutManager = LinearLayoutManager(requireContext())
        binding.repoList.adapter = adapter
    }

    private fun initAdapter() {
        binding.repoList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter(adapter),
            footer = ReposLoadStateAdapter(adapter)
        )

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.repos.collectLatest {
                adapter.submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                .asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.repoList.scrollToPosition(0) }
        }
    }

    private fun repoItemClickListener(id: Int) {
        val action =
            ReposOverviewFragmentDirections.actionReposOverviewFragmentToDetailsFragment(id)
        binding.root.findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}