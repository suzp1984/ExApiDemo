package suzp1984.github.io.exapidemo.system

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import suzp1984.github.io.exapidemo.R

class WifiStateActivity : AppCompatActivity() {

    @BindView(R.id.wifi_info)
    lateinit var wifiBSSID : Button

    @BindView(R.id.wifi_result)
    lateinit var wifiResult : TextView


    lateinit var wifiManager : WifiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_state)

        ButterKnife.bind(this)

        wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager

    }

    @OnClick(R.id.wifi_info)
    fun getWifiBssid() {
        var wifiInfo : WifiInfo = wifiManager.connectionInfo

        wifiResult.setText("bssid: ${wifiInfo.bssid}; frequency: ${wifiInfo.frequency}; ipAddress: ${wifiInfo.ipAddress};" +
                "linkSpeed: ${wifiInfo.linkSpeed}; macAddress: ${wifiInfo.macAddress}; networkId: ${wifiInfo.networkId}; " +
                "rssi: ${wifiInfo.rssi}; ssid: ${wifiInfo.ssid}: supplicate State: ${wifiInfo.supplicantState}")
    }

    @OnClick(R.id.scan_info)
    fun getScanInfo() {

        var results = wifiManager.scanResults

        Log.e("ScanInfo", "${results.size}")

        // info.is80211mcResponder isPasspointNetwork operatorFriendlyName venueName
        results.forEach { info ->
            Log.e("ScanInfo: ", "SSID: ${info.SSID}; BSSID: ${info.BSSID}; capabilities: ${info.capabilities}; " +
                    "centerFreq0: ${info.centerFreq0}; centerFreq1: ${info.centerFreq1}; channelWidth: ${info.channelWidth}; " +
                    "frequency: ${info.frequency}; level: ${info.level};"
            )
        }
    }

    @OnClick(R.id.saved_networks)
    fun getSavedWifi() {
        var results = wifiManager.configuredNetworks
        Log.e("ScanInfo", "${results.size}")

        results.forEach { conf ->
            Log.e("ScanInfo: ", "BSSID: ${conf.BSSID}; SSID: ${conf.SSID}; FQDN: ${conf.FQDN}; " +
                    "authAlgorithms: ${conf.allowedAuthAlgorithms}; groupCiphers: ${conf.allowedGroupCiphers}; " +
                    "keyManagement: ${conf.allowedKeyManagement}; Pairwise Ciphers: ${conf.allowedPairwiseCiphers}; " +
                    "protocols: ${conf.allowedProtocols}; + enterpriseConfig: ${conf.enterpriseConfig}; all: ${conf}")
        }

    }
}
