package nl.abnamro.sena.assessment.ui.view.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import nl.abnamro.sena.assessment.databinding.FragmentOverviewBinding
import nl.abnamro.sena.assessment.ui.view.overview.adapter.RepoItemAdapter
import nl.abnamro.sena.assessment.ui.view.overview.adapter.RepoItemClickListener
import nl.abnamro.sena.assessment.ui.viewmodel.ReposOverviewEvent
import nl.abnamro.sena.assessment.ui.viewmodel.ReposOverviewUiState
import nl.abnamro.sena.assessment.ui.viewmodel.ReposOverviewViewModel
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
        initObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.repoList.layoutManager = LinearLayoutManager(requireContext())
        binding.repoList.adapter = adapter
    }

    private fun initObservers() {
        viewModel.dispatch(ReposOverviewEvent.LoadData)
        viewModel.uiState.observe(viewLifecycleOwner) { renderUi(it) }
    }

    private fun renderUi(uiState: ReposOverviewUiState) {
        when (uiState) {
            is ReposOverviewUiState.ReposLoaded -> showSuccessState(uiState)
            is ReposOverviewUiState.ShowError -> showErrorState(uiState)
            is ReposOverviewUiState.ShowLoading -> showLoadingState()
        }
    }

    private fun showLoadingState() {
        binding.errorText.visibility = View.GONE
        binding.repoList.visibility = View.GONE
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun showErrorState(uiState: ReposOverviewUiState.ShowError) {
        binding.loadingProgress.visibility = View.GONE
        binding.repoList.visibility = View.GONE
        binding.errorText.text = uiState.message
        binding.errorText.visibility = View.VISIBLE
    }

    private fun showSuccessState(state: ReposOverviewUiState.ReposLoaded) {
        binding.errorText.visibility = View.GONE
        binding.loadingProgress.visibility = View.GONE
        adapter.submitList(state.repos)
        binding.repoList.visibility = View.VISIBLE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun repoItemClickListener(id: Int) {
        val action = ReposOverviewFragmentDirections.actionReposOverviewFragmentToDetailsFragment(id)
        binding.root.findNavController().navigate(action)
    }

}