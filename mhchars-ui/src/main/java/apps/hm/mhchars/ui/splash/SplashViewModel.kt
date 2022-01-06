package apps.hm.mhchars.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apps.hm.mhchars.domain.model.Output
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Android Questions
 */

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _isOk = MutableLiveData<Output<Boolean>>()
    val isOk: LiveData<Output<Boolean>> = _isOk

    init {
        _isOk.value = Output.loading()
        viewModelScope.launch {
            delay(1000)
            _isOk.postValue(Output.success(true))
        }
    }
}