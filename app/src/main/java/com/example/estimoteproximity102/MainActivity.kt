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
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
import com.example.estimoteproximity102.Clients.ClientScreen
import com.example.estimoteproximity102.ui.theme.EstimoteProximity102Theme
import com.example.estimoteproximity102.ui.theme.LoginSide

//import dtu.engtech.iabr.stateincompose.ui.theme.StateInComposeTheme

//YEAHYEAH
//import dtu.engtech.iabr.stateincompose.ui.theme.StateInComposeTheme

private const val TAG = "PROXIMITY"

class MainActivity : ComponentActivity() {

    private lateinit var proximityObserver: ProximityObserver
    private var proximityObservationHandler: ProximityObserver.Handler? = null

    private val cloudCredentials = EstimoteCloudCredentials(
        APP_ID,
        APP_TOKEN
    )

    val zoneEventViewModel by viewModels<ZoneEventViewModel>()


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
                    color = MaterialTheme.colors.background
                ) {

                    if (userLoggedIn.value){
                        //Message(zoneEventViewModel)
                        //ClientScreen() //viser borgere
                        NavDemo()
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
    }

    override fun onDestroy() {
        super.onDestroy()
        proximityObservationHandler?.stop()
    }

    private fun startProximityObservation() {
        proximityObserver = ProximityObserverBuilder(applicationContext, cloudCredentials)
            .onError(displayToastAboutError)
            .withTelemetryReportingDisabled()
            .withAnalyticsReportingDisabled()
            .withEstimoteSecureMonitoringDisabled()
            .withBalancedPowerMode()
            .build()

        val proximityZones = ArrayList<ProximityZone>()
        proximityZones.add(zoneBuild("515"))
        proximityZones.add(zoneBuild("517"))
        proximityZones.add(zoneBuild("518"))

        proximityObservationHandler = proximityObserver.startObserving(proximityZones)
    }

    private fun zoneBuild(tag: String): ProximityZone {
        return ProximityZoneBuilder()
            .forTag(tag)
            .inNearRange()
            .onEnter {
                Log.d(TAG, "Enter: ${it.tag}")
                //skal hente staff fra firebase på baggrund af beaconTag-værdien igennem en viewmodel.
            }
            .onExit {
                Log.d(TAG, "Exit: ${it.tag}")
            }
            .onContextChange {
                Log.d(TAG, "Change: ${it}")
            zoneEventViewModel.updateZoneContexts(it)
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
    /////////add////
    /*
    setContent {
        StateInComposeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                //modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                StaffScreen()
            }
        }
    }

     */
}

@Composable
fun NavDemo() {
    val navController = rememberNavController()
    NavDemoHost(navController)
}

@Composable
fun NavDemoHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "ClientScreen"
    ) {
        composable("ClientScreen") {
            ClientScreen(navController = navController)
        }
        composable("ClientInfo") {
            ClientInfoView(navController = navController)
        }
        /*
        composable("Login"){
            LoginView(navController = navController)
        }
                 */
    }

}


@Composable
fun Message(zoneEventViewModel: ZoneEventViewModel) {
    Text(text = zoneEventViewModel.tag, fontSize = 30.sp)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EstimoteProximity102Theme {
        //Message("Android")

    }


}
//////add///////
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateInComposeTheme {
        StaffScreen()
    }
}

 */