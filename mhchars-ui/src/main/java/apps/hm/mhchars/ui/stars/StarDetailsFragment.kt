package apps.hm.mhchars.ui.stars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.ui.base.BaseFragment
import apps.hm.mhchars.ui.databinding.FragmentStarDetailsBinding


class StarDetailsFragment : BaseFragment() {
    private var binding: FragmentStarDetailsBinding? = null
    private val starItem by lazy { Args.fromBundle(arguments) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentStarDetailsBinding.inflate(inflater, container, false).let {
        binding = it
        with(it) {
            lifecycleOwner = this@StarDetailsFragment
            item = starItem.detail
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            root
        }
    }

    override fun subscribeUi() {
        binding?.btnBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    class Args(val detail: CharacterEntity) {
        companion object {
            const val ARG_ITEM = "StarItem"
            fun fromBundle(bundle: Bundle?): Args {
                if (bundle == null)
                    throw IllegalStateException("Arguments must be passed to fragment")
                val details = bundle.getParcelable<CharacterEntity>(ARG_ITEM)
                    ?: throw IllegalStateException("CharacterEntity must be passed as an argument to fragment")
                return Args(details)
            }
        }

        fun toBundle() = bundleOf(ARG_ITEM to detail)
    }

}