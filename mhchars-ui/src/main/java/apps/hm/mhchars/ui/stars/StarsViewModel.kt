package apps.hm.mhchars.ui.stars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.domain.usecase.CharactersUseCase
import apps.hm.mhchars.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarsViewModel @Inject constructor(private val charactersUseCase: CharactersUseCase) :
    BaseViewModel() {

    private val _starsList = MutableLiveData<Output<List<CharacterEntity>>>()
    val starsList: LiveData<Output<List<CharacterEntity>>> = _starsList

    init {
        fetchStars()
    }

    /**
     * Method to fetch the characters data.
     */
    fun fetchStars() {
        viewModelScope.launch {
            charactersUseCase.execute().collect {
                _starsList.value = it
            }
        }
    }
}