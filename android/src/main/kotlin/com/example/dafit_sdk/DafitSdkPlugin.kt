package com.example.dafit_sdk

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import com.google.gson.Gson

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** DafitSdkPlugin */
class DafitSdkPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
  private lateinit var channel : MethodChannel
  private lateinit var mContext : Context
  private lateinit var mActivity: Activity
  private var gson = Gson()


  private var deviceDataReceivedChannel: EventChannel? = null
  private var deviceDataReceivedSink : EventChannel.EventSink? = null
  private val deviceDataReceivedHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      deviceDataReceivedSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "dafit_sdk")
    channel.setMethodCallHandler(this)
    mContext = flutterPluginBinding.applicationContext

    deviceDataReceivedChannel = EventChannel(flutterPluginBinding.binaryMessenger, "deviceDataReceived")
    deviceDataReceivedChannel!!.setStreamHandler(deviceDataReceivedHandler)

  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method){
      "dddd" -> {
        result.success("Android ${android.os.Build.VERSION.RELEASE}")
      }
      else -> result.notImplemented()

    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    Log.d("DafitSdkPlugin","onDetachedFromEngine")
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    Log.d("DafitSdkPlugin","onAttachedToActivity")
    mActivity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {
    Log.d("DafitSdkPlugin","onDetachedFromActivityForConfigChanges")
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    Log.d("DafitSdkPlugin","onReattachedToActivityForConfigChanges")
  }

  override fun onDetachedFromActivity() {
    Log.d("DafitSdkPlugin","onDetachedFromActivity")
  }
}
