package com.example.nfc_payment_demo

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.nfc.NfcAdapter
import android.widget.Toast

class MainActivity : FlutterActivity() {
    private val CHANNEL = "nfc.payment/channel"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "startNFC") {
                val adapter = NfcAdapter.getDefaultAdapter(this)
                if (adapter != null && adapter.isEnabled) {
                    Toast.makeText(this, "NFC Ready", Toast.LENGTH_SHORT).show()
                    result.success("NFC Ready")
                } else {
                    result.error("UNAVAILABLE", "NFC is not available", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }
}
