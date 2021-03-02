package de.rki.coronawarnapp.ui.eventregistration.checkin

import androidx.lifecycle.MutableLiveData
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import de.rki.coronawarnapp.eventregistration.common.decodeBase32
import de.rki.coronawarnapp.server.protocols.internal.evreg.EventOuterClass
import de.rki.coronawarnapp.util.viewmodel.CWAViewModel
import de.rki.coronawarnapp.util.viewmodel.SimpleCWAViewModelFactory

class ConfirmCheckInViewModel @AssistedInject constructor() : CWAViewModel() {
    private val eventLiveData = MutableLiveData<EventOuterClass.Event>()
    val eventData = eventLiveData
    fun decodeEvent(encodedEventString: String) = launch {
        val decodedEventString = encodedEventString.split(".")[0].decodeBase32()
        eventLiveData.postValue(
            EventOuterClass.Event.parseFrom(decodedEventString.toByteArray())
        )
    }

    @AssistedFactory
    interface Factory : SimpleCWAViewModelFactory<ConfirmCheckInViewModel>
}
