package com.example.sample.main.platform

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.sample.R
import com.example.sample.base.exception.Failure
import com.example.sample.base.extension.failure
import com.example.sample.base.extension.observe
import com.example.sample.main.platform.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MFragment : Fragment() {
	private val viewModel: MViewModel by activityViewModels()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		with(viewModel) {
			observe(mValue, ::handleBitCoinResponse)
			failure(mfailure, ::handleFailure)
		}
	}
	
	private fun handleBitCoinResponse(value: String?) {
		tv.setText(value)
	}
	
	private fun handleFailure(failure: Failure?) {
		Toast.makeText(requireContext(), failure.toString(), Toast.LENGTH_LONG).show()
	}
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.frag_main, container, false)
	}
	
	lateinit var tv: TextView
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val btnRefresh = view.findViewById<Button>(R.id.btn_refresh)
		btnRefresh.setOnClickListener {
			viewModel.fetchBitcoinData()
		}
		view.findViewById<Button>(R.id.btn_language).setOnClickListener {
			val intent = Intent(requireContext(), SettingsActivity::class.java)
			requireContext().startActivity(intent)
		}
		tv = view.findViewById(R.id.txt_value)
		btnRefresh.callOnClick()
	}
	
	override fun onResume() {
		super.onResume()
		viewModel.setDisplayValue(viewModel.getLanguageValue())
	}
	
}