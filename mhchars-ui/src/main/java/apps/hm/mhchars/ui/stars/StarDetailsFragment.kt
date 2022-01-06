package apps.hm.mhchars.ui.stars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.ui.base.BaseFragment
import apps.hm.mhchars.ui.databinding.FragmentStarDetailsBinding


class StarDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentStarDetailsBinding
    private val starItem by lazy { arguments?.getParcelable<CharacterEntity>(ITEM) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentStarDetailsBinding.inflate(inflater, container, false)
            with(binding) {
                lifecycleOwner = this@StarDetailsFragment
                item = starItem
                sharedElementEnterTransition =
                    TransitionInflater.from(context).inflateTransition(android.R.transition.move)
                root
            }
        }
    }

    override fun subscribeUi() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        const val ITEM = "StarItem"
    }
}