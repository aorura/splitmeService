package io.github.hidroh.splitme

import android.accessibilityservice.AccessibilityServiceInfo
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.service.quicksettings.TileServiceCompat.startActivityAndCollapse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splitbtn = findViewById<Button>(R.id.splitbtn)

        splitbtn.setOnClickListener() {
            Log.d("MainActivity", "onClick")
            if (enabled()) {
                toggleAndCollapse()
            } else {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY))
            }
        }
    }

    @SuppressLint("StartActivityAndCollapseDeprecated")
    private fun toggleAndCollapse() {
        startActivity(Intent(this, InvisibleActivity::class.java)
            .setAction(ACTION_TOGGLE_SPLIT_SCREEN)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    fun enabled(): Boolean {
        val manager = getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
        if (!manager.isEnabled) return false

        return false
    }
}