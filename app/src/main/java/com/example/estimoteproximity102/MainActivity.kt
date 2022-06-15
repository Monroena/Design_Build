package com.example.estimoteproximity102

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.Requirement
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory
import com.estimote.proximity_sdk.api.*
import com.example.estimoteproximity102.Beacons.CloudCredentials.APP_ID
import com.example.estimoteproximity102.Beacons.CloudCredentials.APP_TOKEN
import com.example.estimoteproximity102.Beacons.ZoneEventViewModel
import com.example.estimoteproximity102.Beacons.ZoneName
//import com.example.estimoteproximity102.core.CloudCredentials.APP_ID
//import com.example.estimoteproximity102.core.CloudCredentials.APP_TOKEN

import com.example.estimoteproximity102.Clients.ClientScreen
import com.example.estimoteproximity102.Clients.ClientViewModel
import com.example.estimoteproximity102.core.Constants
//import com.example.estimoteproximity102.model.ZoneEventViewModel
import com.example.estimoteproximity102.ui.theme.EstimoteProximity102Theme
import com.example.estimoteproximity102.ui.theme.LoginSide

private const val TAG = "PROXIMITY"

class MainActivity : ComponentActivity() {

    private lateinit var proximityObserver: ProximityObserver
    private var proximityObservationHandler: ProximityObserver.Handler? = null
    private val cloudCredentials = EstimoteCloudCredentials(
        APP_ID,
        APP_TOKEN
    )

    private val clientViewModel by viewModels<ClientViewModel>()
    val zoneViewModel by viewModels<ZoneEventViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //i følgende er brugeren som udgangspunkt ikke logget ind da value er sat til false.
            //Hvis userLoggedIn.value fra signInButton-komponentens funktion userloggedind er kørt så værdien true og der vises næste side
            val userLoggedIn = remember { mutableStateOf(false)}
            val onUserLoggedIn = {
                userLoggedIn.value = true
            }

            EstimoteProximity102Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    ClientScreen(clientViewModel = clientViewModel) //Added


                    if (userLoggedIn.value){
                        Message(zoneViewModel)
                        ClientScreen() //viser borgere
                    } else {
                        LoginSide(onUserLoggedIn)
                    }

                   // StaffScreen() //viser Staff

                }
            }
        }

        // Requirements check
        RequirementsWizardFactory.createEstimoteRequirementsWizard().fulfillRequirements(
            this,
            onRequirementsFulfilled = { startProximityObservation() },
            onRequirementsMissing = displayToastAboutMissingRequirements,
            onError = displayToastAboutError
        )
        clientViewModel.clientRepository.addListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        proximityObservationHandler?.stop()
    }

    private fun startProximityObservation() {
        Log.d(Constants.BEACONLOGTAG, "StartObserving")
        proximityObserver = ProximityObserverBuilder(applicationContext, cloudCredentials)
            .onError(displayToastAboutError)
            .withTelemetryReportingDisabled()
            .withAnalyticsReportingDisabled()
            .withEstimoteSecureMonitoringDisabled()
            .withBalancedPowerMode()
            .build()


        val proximityZones = ArrayList<ProximityZone>()
        proximityZones.add(zoneBuild(ZoneName.TAG515))
        proximityZones.add(zoneBuild(ZoneName.TAG517))
        proximityZones.add(zoneBuild(ZoneName.TAG518))
        proximityZones.add(zoneBuild(ZoneName.TAG508))

        proximityObservationHandler = proximityObserver.startObserving(proximityZones)
    }

    private fun zoneBuild(tag: String): ProximityZone {
        return ProximityZoneBuilder()
            .forTag(tag)
            .inNearRange()
            .onEnter {
                Log.d(TAG, "Enter: ${it.tag}")
                clientViewModel.getClient(it.tag)


            }
            .onExit {
                Log.d(TAG, "Exit: ${it.tag}")

            }
            .onContextChange {
                Log.d(TAG, "Change: ${it}")
            zoneViewModel.updateZoneContexts(it)
            }
            .build()

    }

    // Lambda functions for displaying errors when checking requirements
    private val displayToastAboutMissingRequirements: (List<Requirement>) -> Unit = {
        Toast.makeText(
            this,
            "Unable to start proximity observation. Requirements not fulfilled: ${it.size}",
            Toast.LENGTH_SHORT
        ).show()
    }
    private val displayToastAboutError: (Throwable) -> Unit = {
        Toast.makeText(
            this,
            "Error while trying to start proximity observation: ${it.message}",
            Toast.LENGTH_SHORT
        ).show()
    }

}

@Composable
fun Message(zoneEventViewModel: ZoneEventViewModel) {
    Text(text = zoneEventViewModel.tag, fontSize = 40.sp)
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EstimoteProximity102Theme {

       // Message("")

    }


}



