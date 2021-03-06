@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.QuickJournal

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
import androidx.navigation.NavController
import com.example.QuickJournal.Views.TabItem
import com.example.QuickJournal.ui.theme.EstimoteProximity102Theme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

//private const val TAG = "PROXIMITY"

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ClientPageView(navController: NavController) {
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
                    Text(text = stringResource(id = (R.string.app_name)))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("ClientList")
                        Toast.makeText(
                            context,
                            "Tilbage til listevisning af borgere",
                            Toast.LENGTH_LONG
                        ).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

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
                            contentDescription = "Nyt Notat",
                        )
                    },
                    label = { Text(text = "Opret Notat")},
                    unselectedContentColor = Color.White,
                    selectedContentColor = Color.Gray,
                    onClick = { navController.navigate("CreateNote") },
                    selected = false
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Tjek Ind"
                        )
                    },
                    label = { Text(text = "Tjek ind")},
                    unselectedContentColor = Color.White,
                    selectedContentColor = Color.Gray,
                    onClick = { navController.navigate("CheckIn") },
                    selected = false
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
        val info = remember { mutableListOf<String>().toMutableStateList() }
        val onInfoChanged = { newInfo: ArrayList<String> ->
            info.clear()
            for (inf in newInfo) {
                info.add(inf)
            }
        }
        GetClientData(onInfoChanged)
        ShowClientData(info.toMutableStateList())

    }
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
        ViewNotes()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    EstimoteProximity102Theme {

    }
}

