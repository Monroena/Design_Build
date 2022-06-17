@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.estimoteproximity102

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.estimoteproximity102.Clients.Notes
import com.example.estimoteproximity102.ui.theme.EstimoteProximity102Theme
import com.google.accompanist.pager.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

private const val TAG = "PROXIMITY"

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ClientInfoView(navController: NavController) {
    val context = LocalContext.current
    val list = listOf(
        TabItem.Info,
        TabItem.Log,
        TabItem.Notes
    )
    val pagerState = rememberPagerState(initialPage = 0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "QuickJournal")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("ClientScreen")
                        Toast.makeText(
                            context,
                            "tilbage til Borgerliste",
                            Toast.LENGTH_LONG
                        ).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.MailOutline,
                            contentDescription = "Nyt Notat"
                        )
                    },
                    unselectedContentColor = Color.White,
                    selectedContentColor = Color.White,
                    onClick = { navController.navigate("NyNotat") },
                    selected = true
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Tjek Ind"
                        )
                    },
                    unselectedContentColor = Color.White,
                    selectedContentColor = Color.White,
                    onClick = { navController.navigate("TjekInd") },
                    selected = true
                )

            }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Tabs(
                    tabs = list,
                    pagerState = pagerState
                )
                TabContent(
                    tabs = list,
                    pagerState = pagerState
                )
            }
        }
    )
}


@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.primary,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(
                pagerState = pagerState,
                tabPositions = tabPositions
            )
        }) {
        tabs.forEachIndexed { index, tabItem ->

            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(tabItem.title) },
                icon = { Icon(imageVector = tabItem.icons, contentDescription = null) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.White,
                enabled = true
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screens()
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val info = remember {mutableListOf<String>().toMutableStateList()}
        val onInfoChanged  = { newInfo: ArrayList<String> ->
            info.clear()
            for (inf in newInfo) {
                info.add(inf)
            }
        }
            HentInfo(onInfoChanged)
            SeInfo(info.toMutableStateList())

    }
}

    @Composable
    fun ClientInformation() {
        /*val name = remember{ mutableStateOf("")}
        val birthday = remember{ mutableStateOf("")}
        val adress = remember{ mutableStateOf("")}


        Column() {
            Text(text = name.value )
            Text(text = birthday.value)
            Text(text = adress.value)
*/
            //HentInfo()
        }


@Composable
fun LogScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Log Screen")
        Text(text = "Vis log fra firebase her")
    }
}

@Composable
fun NotesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            SeNyNotat()
        }
    }
@Composable
fun HentInfo(onInfoChanged: (ArrayList<String>) -> Unit){
    Button(
        onClick = {
            Log.i("clients", "Info tekst: " + 515)
            val db = Firebase.firestore
            val ID = "515"

            db.collection("clients").whereEqualTo("beaconTag", ID)
                .get()
                .addOnSuccessListener { result ->
                    val informationer = ArrayList<String>()

                    Log.d(TAG, result.toString())
                    for (document in result) {
                        informationer.add(document.data.get("name").toString())
                        informationer.add(document.data.get("address").toString())
                        informationer.add(document.data.get("birthdate").toString())
                        informationer.add(document.data.get("beaconTag").toString())



                        Log.d(TAG, "${document.id} => ${document.data}")
                    }

                    onInfoChanged(informationer)
                    Log.d(TAG, informationer.toString())
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }
        },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = (R.string.se_info))
        )

    }
}


@Composable
fun SeInfo(seInfo: MutableList<String>){
    for (inf in seInfo){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = inf,
            //Text(
            //    text = clients?.name ?: ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    EstimoteProximity102Theme {

    }
}

