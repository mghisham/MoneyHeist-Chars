package apps.hm.mhchars.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.ui.R
import apps.hm.mhchars.ui.base.BaseFragment
import apps.hm.mhchars.ui.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashBinding

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentSplashBinding.inflate(inflater, container, false)
            with(binding) {
                viewModel = splashViewModel
                lifecycleOwner = this@SplashFragment
                root
            }
        }
    }

    override fun subscribeUi() {
        splashViewModel.isOk.observe(viewLifecycleOwner) { result ->
            if (result.status == Output.Status.SUCCESS) {
                binding.loading.hide()
                gotoCharacters()
            }
            else if (result.status == Output.Status.LOADING) {
                binding.loading.show()
            }
        }
    }

    /**
     * Method to navigate Fragment to Characters Screen.
     */
    private fun gotoCharacters() {
        val extras = FragmentNavigatorExtras(
            binding.profPic to getString(R.string.app_name)
        )
        findNavController().navigate(R.id.splash_to_characters, null, null, extras)
    }
}