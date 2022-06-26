package nl.abnamro.sena.assessment.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nl.abnamro.sena.assessment.databinding.FragmentOverviewBinding
import nl.abnamro.sena.assessment.ui.viewmodel.ReposOverviewEvent
import nl.abnamro.sena.assessment.ui.viewmodel.ReposOverviewUiState
import nl.abnamro.sena.assessment.ui.viewmodel.ReposOverviewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReposOverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReposOverviewViewModel by viewModel()

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
    }

    private fun initObservers() {
        viewModel.dispatch(ReposOverviewEvent.LoadData)
        viewModel.uiState.observe(viewLifecycleOwner) { renderUi(it) }
    }

    private fun renderUi(uiState: ReposOverviewUiState) {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}