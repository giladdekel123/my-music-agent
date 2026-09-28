# My Music Agent 🎵

My Music Agent is a simple Android app that connects Shazam sharing with Spotify.

## What it does

1. Identify a song with Shazam.
2. Tap **Share**.
3. Choose **My Music Agent**.
4. The app receives the song information and Shazam link.
5. It extracts the song title and artist.
6. Spotify opens automatically with a search for that song.

## Why I built it

I wanted a faster way to move from discovering a song in Shazam to finding it in Spotify.

## Current version

This first working version uses the Android Share system and Spotify search.

It does not use the Spotify Web API, so it works without Spotify developer API access.

## Built with

- Kotlin
- Android Studio
- Jetpack Compose
- Android Intents
- Git & GitHub