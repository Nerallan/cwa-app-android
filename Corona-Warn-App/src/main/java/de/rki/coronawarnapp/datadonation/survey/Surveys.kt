package de.rki.coronawarnapp.datadonation.survey

import de.rki.coronawarnapp.datadonation.safetynet.DeviceAttestation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Surveys @Inject constructor(
    private val deviceAttestation: DeviceAttestation,
    private val settings: SurveySettings,
    private val surveyHighRiskUrlProvider: SurveyHighRiskUrlProvider
) {

    val availableSurveys: Flow<Collection<Type>> = emptyFlow()

    suspend fun requestDetails(type: Type): Survey {
        return when (type) {
            Type.HIGH_RISK_ENCOUNTER -> {
                Survey(
                    type = Type.HIGH_RISK_ENCOUNTER,
                    surveyLink = surveyHighRiskUrlProvider.provideUrl()
                )
            }
        }
    }

    suspend fun resetSurvey(type: Type) {
        // TODO
    }

    enum class Type {
        HIGH_RISK_ENCOUNTER
    }

    data class Survey(
        val type: Type,
        val surveyLink: String
    )
}
