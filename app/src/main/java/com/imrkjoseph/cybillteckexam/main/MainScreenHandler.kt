package com.imrkjoseph.cybillteckexam.main

import com.imrkjoseph.cybillteckexam.app.foundation.BaseActivity
import com.imrkjoseph.cybillteckexam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenHandler : BaseActivity<ActivityMainBinding>(bindingInflater = ActivityMainBinding::inflate)