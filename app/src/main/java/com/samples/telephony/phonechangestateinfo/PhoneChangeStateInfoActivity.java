package com.samples.telephony.phonechangestateinfo;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

public class PhoneChangeStateInfoActivity extends Activity implements View.OnClickListener {

    private TextView text;
    private TelephonyManager manager;

    private PhoneStateListener listener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            text.append("\nCall state:\t" +
                    convertCallStateToString(state) + "\nIncoming number:\t");
        }
        @Override
        public void onDataConnectionStateChanged(
                int state, int networkType) {
            text.append("\nNetwork Type:\t" +
                    convertNetworkTypeToString(networkType));
        }
        @Override
        public void onCallForwardingIndicatorChanged(boolean cfi) {
            text.append("\nCallForwardingIndicator:\t" + cfi);
        }
        @Override
        public void onDataActivity(int direction) {
            text.append("\nDataActivity:\t" +
                    convertDataActivityToString(direction));
        }
        @Override
        public void onDataConnectionStateChanged(int state) {
            text.append("\nDataConnectionState:\t");
                //+ convertDataConnStateToString(state));
        }
        @Override
        public void onMessageWaitingIndicatorChanged(boolean mwi) {
            text.append("\nMessageWaitingIndicator:\t" + mwi);
        }
        @Override
        public void onServiceStateChanged(ServiceState serviceState) {
            text.append("\nService State:\t" +
                    convertServiceStateToString(serviceState.getState()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_change_state_info);

        text = (TextView) findViewById(R.id.text);
        text.setText("Listener is stopped");

        manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bStart:
                    manager.listen(listener,
                            PhoneStateListener.LISTEN_CALL_STATE |
                            PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR |
                            PhoneStateListener.LISTEN_DATA_ACTIVITY |
                            PhoneStateListener.LISTEN_DATA_CONNECTION_STATE |
                            PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR |
                            PhoneStateListener.LISTEN_SERVICE_STATE);
                text.setText("Start phone info listener ...");
                break;
            case R.id.bStop:
                manager.listen(listener, TelephonyManager.PHONE_TYPE_NONE);
                text.setText("Listener is stopped");
                break;
        }
    }

    private String convertCallStateToString(int callState) {
        switch (callState) {
            case TelephonyManager.CALL_STATE_IDLE:
                return "IDLE";
            case TelephonyManager.CALL_STATE_OFFHOOK:
                return "OFFHOOK";
            case TelephonyManager.CALL_STATE_RINGING:
                return "RINGING";
            default:
                return "Not defined";
        }
    }

    private String convertNetworkTypeToString(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "EVDO revision 0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "EVDO revision A";
            //case TelephonyManager.NETWORK_TYPE_EVDO_B:
            // return "EVDO revision B";
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA";
            //case TelephonyManager.NETWORK_TYPE_IDEN:
            // return "iDen";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return "Unknown";
            default:
                return "Not defined";
        }
    }
    private String convertDataActivityToString(int dataActivity) {
        switch (dataActivity) {
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                return "Dormant";
            case TelephonyManager.DATA_ACTIVITY_IN:
                return "In";
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                return "In-out";
            case TelephonyManager.DATA_ACTIVITY_NONE:
                return "None";
            case TelephonyManager.DATA_ACTIVITY_OUT:
                return "Out";
            default:
                return "Not defined";
        }
    }

    private String convertDataStateToString(int dataState) {
        switch (dataState) {
            case TelephonyManager.DATA_CONNECTED:
                return "Data connected";
            case TelephonyManager.DATA_CONNECTING:
                return "Data connecting";
            case TelephonyManager.DATA_DISCONNECTED:
                return "Data suspended";
            case TelephonyManager.DATA_SUSPENDED:
                return "Data suspended";
            default:
                return "Not defined";
        }
    }
    private String convertPhoneTypeToString(int phoneType) {
        switch (phoneType) {
            case TelephonyManager.PHONE_TYPE_GSM:
                return "GSM";
            case TelephonyManager.PHONE_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.PHONE_TYPE_NONE:
                return "NONE";
            default:
                return "Not defined";
        }
    }

    private String convertServiceStateToString(int serviceState) {
        switch (serviceState) {
            case ServiceState.STATE_EMERGENCY_ONLY:
                return "Emergensy Only";
            case ServiceState.STATE_IN_SERVICE:
                return "In Service";
            case ServiceState.STATE_OUT_OF_SERVICE:
                return "Out of Service";
            case ServiceState.STATE_POWER_OFF:
                return "Power OFF";
            default:
                return "Not defined";
        }
    }
}
