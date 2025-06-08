package com.example.nfc_payment_demo;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;


public class MyHostApduService extends HostApduService {

    private static final String TAG = "MyHostApduService";

    // A simple SELECT APDU command for demo.
    // Keep AID in sync with res/apduservice.xml (aid-filter="F222222222").
    private static final String SELECT_APDU_HEADER = "00A40400F222222222";

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        Log.d(TAG, "Received APDU: " + byteArrayToHex(apdu));

        // Convert APDU to hex string
        String apduStr = byteArrayToHex(apdu);

        if (apduStr.startsWith(SELECT_APDU_HEADER)) {
            Log.d(TAG, "SELECT command received.");
            // Send back a simple response (e.g., "Card Response Data")
            return hexStringToByteArray("43617264526573706F6E73659000");  // "CardResponse" + status word
        }

        // Unrecognized command
        return hexStringToByteArray("6F00");  // status word for "unknown instruction"
    }

    @Override
    public void onDeactivated(int reason) {
        Log.d(TAG, "Deactivated. Reason: " + reason);
    }

    // Helper method: convert byte array to hex string
    private String byteArrayToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    // Helper method: convert hex string to byte array
    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
