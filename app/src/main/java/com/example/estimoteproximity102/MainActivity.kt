package com.example.estimoteproximity102

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.Requirement
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory
import com.estimote.proximity_sdk.api.*
import com.example.estimoteproximity102.Beacons.CloudCredentials.APP_ID
import com.example.estimoteproximity102.Beacons.CloudCredentials.APP_TOKEN
import com.example.estimoteproximity102.Beacons.ZoneEventViewModel
import com.example.estimoteproximity102.Beacons.ZoneName
import com.example.estimoteproximity102.Clients.ClientScreen
import com.example.estimoteproximity102.Clients.ClientScreenView
import com.example.estimoteproximity102.Clients.ClientViewModel
import com.example.estimoteproximity102.core.Constants

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


                    if (userLoggedIn.value){
                        //Message(zoneViewModel)
                        //Message(zoneEventViewModel)
                        //ClientScreen() //viser borgere
                        //ClientScreen(clientViewModel = clientViewModel)
                        NavDemo()
                    } else {
                        LoginSide(onUserLoggedIn)
                    }

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

//Ud fra mainActivity
@Composable
fun NavDemo() {
    val navController = rememberNavController()
    NavDemoHost(navController)
}

@Composable
fun ClientScreenView2(navController: NavController) {
    val clientViewModel= ClientScreenView(navController = navController )
}
@Composable
fun NavDemoHost(navController: NavHostController) {
  val clientViewModel= ClientScreenView(navController = navController ) //Zakir
    NavHost(
        navController = navController,
        startDestination = "ClientScreen"
    ) {
        composable("ClientScreen") {
        clientViewModel
        //ClientScreenView2(navController = navController)

        }
        composable("ClientInfo") {
            ClientInfoView(navController = navController)
        }
        composable("NyNotat") {
            OpretNotatView(navController = navController)
        }
        composable("TjekInd") {
            TjekInd(navController = navController)
        }
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

    }
}
