package apps.hm.mhchars.ui.stars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.ui.R
import apps.hm.mhchars.ui.base.BaseFragment
import apps.hm.mhchars.ui.databinding.FragmentListStarsBinding
import apps.hm.mhchars.ui.widgets.applyTheme

class StarsFragment : BaseFragment() {
    private val starsViewModel: StarsViewModel by viewModels()
    private lateinit var binding: FragmentListStarsBinding
    private lateinit var starsAdapter: StarsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentListStarsBinding.inflate(inflater, container, false)
            with(binding) {
                headerTitle = this@StarsFragment.getString(R.string.label_p_language_stars)
                root
            }
        }
    }

    override fun subscribeUi() {
        binding.swipeRefresh.applyTheme()
        starsAdapter = StarsAdapter(arrayListOf(), onStarClick)
        binding.rvQuestions.adapter = starsAdapter
        binding.swipeRefresh.setOnRefreshListener {
            starsViewModel.fetchStars()
        }
        starsViewModel.starsList.observe(viewLifecycleOwner) { result ->

            when (result.status) {
                Output.Status.SUCCESS -> {
                    result.data?.let { list ->
                        starsAdapter.update(list)
                    }
                    binding.swipeRefresh.isRefreshing = false
                }

                Output.Status.ERROR -> {
                    result.message?.let {
                        showError(it) {
                            starsViewModel.fetchStars()
                        }
                    }
                    binding.swipeRefresh.isRefreshing = false
                }

                Output.Status.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private val onStarClick: (star: CharacterEntity, view: View) -> Unit =
        { star, view ->
            val extras = FragmentNavigatorExtras(
                view to star.image
            )
            findNavController().navigate(
                R.id.stars_to_details,
                bundleOf(StarDetailsFragment.ITEM to star),
                null,
                extras
            )
        }
}