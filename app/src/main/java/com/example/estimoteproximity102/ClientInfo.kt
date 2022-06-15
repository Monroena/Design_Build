package com.example.estimoteproximity102

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.estimoteproximity102.ui.theme.EstimoteProximity102Theme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class ClientInfo {
}

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
            TopAppBar (
                title = { 
                    Text(text = "QuickJournal")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("ClientScreen")
                        Toast.makeText(
                            context,
                            "tilbage til Borgerliste",
                            Toast.LENGTH_LONG
                            ).show()
                    }){
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back")}
                }
                )
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                ),

            ) {
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("TjekInd")
                    Toast.makeText(
                    context,
                    "Tjekker ind",
                    Toast.LENGTH_LONG).show() }
            ){
                Icons.Outlined.CheckCircle
                Color.Transparent
                //Text(text = "+")

            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content ={
            Column(
                modifier = Modifier.fillMaxSize()) {
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
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Navn:")
        Text(text = "Klaus Klausen")
        Text(text = "Fødselsdato")
        Text(text = "10 nov 1933")
        Text(text = "Adresse:")
        Text(text = "Borgervej 122")
        Text(text = "Hent data fra firebase ved relevant tag")

    }
}

@Composable
fun ClientInformation(){
    Column() {
        Text(text = "Navn:")
        Text(text = "Klaus Klausen")
        Text(text = "Fødselsdato")
        Text(text = "10 nov 1933")
        Text(text = "Adresse:")
        Text(text = "Borgervej 122")
        Text(text = "Hent data fra firebase ved relevant tag")
    }
}

@Composable
fun LogScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //TjekInd()
        Text(text = "Log Screen")
        Text(text = "Vis log fra firebase her")
    }
}

@Composable
fun NotesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Text(text = "Notes Screen")
        Text(text = "Hent noter fra sub-collection i firebase")
        Text(text = "Opret button til oprettelse af ny note")

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    EstimoteProximity102Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            //MainContent()
        }
    }
}