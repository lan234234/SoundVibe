package com.laioffer.spotify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Application -> MainActivity: lifecycle -> Fragment -> View
@HiltAndroidApp
class MainApplication: Application()