package apps.hm.mhchars.ui.stars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var binding: FragmentListStarsBinding? = null
    private lateinit var starsAdapter: StarsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentListStarsBinding.inflate(inflater, container, false).let {
        binding = it
        with(it) {
            headerTitle = getString(R.string.label_p_language_stars)
            root
        }
    }


    override fun subscribeUi() {
        binding?.let {
            it.swipeRefresh.applyTheme()
            starsAdapter = StarsAdapter(arrayListOf(), onStarClick)
            it.rvQuestions.adapter = starsAdapter
            it.swipeRefresh.setOnRefreshListener {
                starsViewModel.fetchStars()
            }
        }
        starsViewModel.starsList.observe(viewLifecycleOwner) { result ->

            binding?.swipeRefresh?.isRefreshing = when (result.status) {
                Output.Status.SUCCESS -> {
                    result.data?.let { list ->
                        starsAdapter.update(list)
                    }
                    false
                }
                Output.Status.ERROR -> {
                    result.message?.let {
                        showError(it) {
                            starsViewModel.fetchStars()
                        }
                    }
                    false
                }
                Output.Status.LOADING -> true

            }
        }
    }

    /**
     * @property onStarClick to handle the characters item click.
     */
    private val onStarClick: (star: CharacterEntity, view: View) -> Unit =
        { star, view ->
            val extras = FragmentNavigatorExtras(
                view to star.image
            )
            findNavController().navigate(
                R.id.stars_to_details,
                StarDetailsFragment.Args(star).toBundle(),
                null,
                extras
            )
        }
}