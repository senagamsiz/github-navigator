package nl.abnamro.sena.assessment.ui.view.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import nl.abnamro.sena.assessment.data.local.model.RepoItem
import nl.abnamro.sena.assessment.databinding.FragmentDetailsBinding
import nl.abnamro.sena.assessment.ui.viewmodel.details.RepoDetailsEvent
import nl.abnamro.sena.assessment.ui.viewmodel.details.RepoDetailsNavigationTarget
import nl.abnamro.sena.assessment.ui.viewmodel.details.RepoDetailsUiState
import nl.abnamro.sena.assessment.ui.viewmodel.details.RepoDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RepoDetailsViewModel by viewModel()
    private val args : DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.loadDetails(args.repoId)
        viewModel.uiState.observe(viewLifecycleOwner) { renderUi(it) }
        viewModel.navigation.observe(viewLifecycleOwner) { navigate(it)}
    }

    private fun navigate(target: RepoDetailsNavigationTarget) {
        when(target){
            is RepoDetailsNavigationTarget.OpenRepoUrl -> navigateToRepoInBrowser(target.url)
        }
    }

    private fun navigateToRepoInBrowser(url: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }

    private fun renderUi(state: RepoDetailsUiState) {
        when(state){
            is RepoDetailsUiState.RepoDetails -> populateUi(state.repoItem)
        }
    }

    private fun populateUi(repoItem: RepoItem) {
        with(binding){
            nameText.text = repoItem.name
            fullNameText.text = repoItem.fullName
            descriptionText.text = repoItem.description
            visibilityText.text = repoItem.visibility
            isPrivateText.text = if (repoItem.isPrivate) "Private" else "Public"
            urlButton.setOnClickListener{
                viewModel.dispatch(RepoDetailsEvent.RepoUrlButtonClick(repoItem.repoUrl))
            }
        }
        Glide.with(binding.root.context).load(repoItem.avatarImageUrl)
            .into(binding.ownerAvatarImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}