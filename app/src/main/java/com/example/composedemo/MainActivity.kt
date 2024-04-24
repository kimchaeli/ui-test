package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // 네비게이션 컨트롤러 생성
                val navController = rememberNavController()

                // NavHost 설정
                NavHost(navController = navController, startDestination = "splashScreen") {
                    composable("splashScreen") { SplashScreenToMainScreen(navController) }
                    // "bottomNav" 목적지 추가
                    composable("bottomNav") { /* 운전자 모드 스크린 컴포저블 */ }
                    // 추가적인 목적지는 여기에 정의할 수 있습니다.
                }
            }
        }
    }
}

@Composable
fun SplashScreenToMainScreen(navController: NavHostController) {
    var showSplashScreen by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        delay(2000) // 2초 지연 시간
        showSplashScreen = false
    }

    Crossfade(targetState = showSplashScreen) { showSplash ->
        if (showSplash) {
            SplashScreen()
        } else {
            // SplashScreen이 아닐 때, CenteredButtons 함수에 navController 인자를 전달
            CenteredButtons(navController)
        }
    }
}

@Composable
fun SplashScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text("스몸비파인더", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CenteredButtons(navController: NavHostController) {
    // Box를 사용하여 버튼들을 화면 중앙에 배치
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = { /* 보행자 버튼 클릭 시 수행할 동작 */ },
                modifier = Modifier.size(width = 300.dp, height = 250.dp).padding(40.dp)
            ) {
                Text("보행자모드", fontSize = 35.sp, fontWeight = FontWeight.Bold)
            }
            Button(
                // 운전자모드 버튼 클릭 시 수행할 동작에 네비게이션 이동 로직 추가
                onClick = { navController.navigate("bottomNav") },
                modifier = Modifier.size(width = 300.dp, height = 250.dp).padding(40.dp)
            ) {
                Text("운전자모드", fontSize = 35.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

@Preview(showBackground = true)
@Composable
fun CenteredButtonsPreview() {
    val navController = rememberNavController()
    CenteredButtons(navController)
}
