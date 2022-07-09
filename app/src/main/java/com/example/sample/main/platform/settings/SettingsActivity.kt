package com.example.sample.main.platform.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.R
import com.example.sample.base.SharedPreferenceHelper.setValue
import com.example.sample.main.di.AppModule
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
	lateinit var prefs: SharedPreferences
	lateinit var preferences: SharedPreferences
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		preferences = application.getSharedPreferences("mSharedPrefs", Context.MODE_PRIVATE)
		showLanguageDialog()
	}
	
	private fun showLanguageDialog() {
		val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
		alertDialogBuilder.setTitle("Set limit article")
		val items = arrayOf("USD", "GBP", "EUR")
		alertDialogBuilder.setSingleChoiceItems(items, -1,
			{ dialog, item ->
				Log.d("TAG", "showLanguageDialog: " + items[item])
				preferences.setValue("language", items[item])
				dialog.dismiss()
				finish()
			})
		alertDialogBuilder.show()
	}
}