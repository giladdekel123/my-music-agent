package com.example.mymusicagent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mymusicagent.ui.theme.MyMusicAgentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sharedText = if (intent?.action == android.content.Intent.ACTION_SEND) {
            intent.getStringExtra(android.content.Intent.EXTRA_TEXT) ?: "Nothing received"
        } else {
            "Nothing received"
        }
        val shazamUrl = Regex("""https?://\S+""").find(sharedText)?.value ?: ""
        val songInfo = sharedText.replace(shazamUrl, "").trim()
        val parts = songInfo.split(" by ", limit = 2)
        val songTitle = parts.getOrElse(0) { "" }
        val artist = parts.getOrElse(1) { "" }
        val spotifySearch = "$songTitle $artist"
        val spotifyUri = android.net.Uri.parse(
            "spotify:search:${android.net.Uri.encode(spotifySearch)}"
        )
        val spotifyIntent = android.content.Intent(
            android.content.Intent.ACTION_VIEW,
            spotifyUri
        )
        if (intent?.action == android.content.Intent.ACTION_SEND && songTitle.isNotEmpty()) {
            startActivity(spotifyIntent)
        }
        setContent {
            MyMusicAgentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Song:\n$songTitle\n\nArtist:\n$artist\n\nLink:\n$shazamUrl",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "My Music Agent 🎵\n\nReceived:\n$name",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMusicAgentTheme {
        Greeting("Android")
    }
}