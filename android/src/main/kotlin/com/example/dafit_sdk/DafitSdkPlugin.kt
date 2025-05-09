package com.example.dafit_sdk

import android.app.Activity
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import com.crrepa.ble.CRPBleClient
import com.crrepa.ble.conn.CRPBleConnection
import com.crrepa.ble.conn.CRPBleDevice
import com.crrepa.ble.conn.bean.CRPAlarmInfo
import com.crrepa.ble.conn.bean.CRPBloodOxygenInfo
import com.crrepa.ble.conn.bean.CRPBloodPressureInfo
import com.crrepa.ble.conn.bean.CRPBreathRateInfo
import com.crrepa.ble.conn.bean.CRPContactConfigInfo
import com.crrepa.ble.conn.bean.CRPContactInfo
import com.crrepa.ble.conn.bean.CRPFunctionInfo
import com.crrepa.ble.conn.bean.CRPFutureWeatherInfo
import com.crrepa.ble.conn.bean.CRPFutureWeatherInfo.FutureBean
import com.crrepa.ble.conn.bean.CRPHeartRateInfo
import com.crrepa.ble.conn.bean.CRPHisiliconWatchFaceInfo
import com.crrepa.ble.conn.bean.CRPHistoryBloodOxygenInfo
import com.crrepa.ble.conn.bean.CRPHistoryBloodPressureInfo
import com.crrepa.ble.conn.bean.CRPHistoryBreathRateInfo
import com.crrepa.ble.conn.bean.CRPHistoryHeartRateInfo
import com.crrepa.ble.conn.bean.CRPHistoryTrainingInfo
import com.crrepa.ble.conn.bean.CRPHrvInfo
import com.crrepa.ble.conn.bean.CRPJieliSupportWatchFaceInfo
import com.crrepa.ble.conn.bean.CRPMessageInfo
import com.crrepa.ble.conn.bean.CRPMovementHeartRateInfo
import com.crrepa.ble.conn.bean.CRPNapSleepInfo
import com.crrepa.ble.conn.bean.CRPPeriodTimeInfo
import com.crrepa.ble.conn.bean.CRPPhysiologcalPeriodInfo
import com.crrepa.ble.conn.bean.CRPQuickResponsesCountInfo
import com.crrepa.ble.conn.bean.CRPQuickResponsesDetailInfo
import com.crrepa.ble.conn.bean.CRPSifliSupportWatchFaceInfo
import com.crrepa.ble.conn.bean.CRPSleepInfo
import com.crrepa.ble.conn.bean.CRPStepInfo
import com.crrepa.ble.conn.bean.CRPStockInfo
import com.crrepa.ble.conn.bean.CRPStockPriceInfo
import com.crrepa.ble.conn.bean.CRPSupportWatchFaceInfo
import com.crrepa.ble.conn.bean.CRPTempInfo
import com.crrepa.ble.conn.bean.CRPTodayWeatherInfo
import com.crrepa.ble.conn.bean.CRPTrainingInfo
import com.crrepa.ble.conn.bean.CRPTrainingRealtimeInfo
import com.crrepa.ble.conn.bean.CRPUserInfo
import com.crrepa.ble.conn.bean.CRPWatchFaceBackgroundInfo
import com.crrepa.ble.conn.bean.CRPWatchFaceDetailsInfo
import com.crrepa.ble.conn.bean.CRPWatchFaceDetailsRequestInfo
import com.crrepa.ble.conn.bean.CRPWatchFaceInfo
import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo
import com.crrepa.ble.conn.bean.CRPWatchFaceStoreInfo
import com.crrepa.ble.conn.bean.CRPWatchFaceStoreRequestInfo
import com.crrepa.ble.conn.bean.CRPWatchFaceStoreTagInfo
import com.crrepa.ble.conn.callback.CRPAlarmCallback
import com.crrepa.ble.conn.callback.CRPContactConfigCallback
import com.crrepa.ble.conn.callback.CRPDeviceFirmwareVersionCallback
import com.crrepa.ble.conn.callback.CRPDeviceFunctionCallback
import com.crrepa.ble.conn.callback.CRPDeviceMetricSystemCallback
import com.crrepa.ble.conn.callback.CRPDeviceOtherMessageCallback
import com.crrepa.ble.conn.callback.CRPDevicePhysiologcalPeriodCallback
import com.crrepa.ble.conn.callback.CRPDeviceSupportWatchFaceCallback
import com.crrepa.ble.conn.callback.CRPWatchFaceDetailsCallback
import com.crrepa.ble.conn.callback.CRPWatchFaceStoreCallback
import com.crrepa.ble.conn.callback.CRPWatchFaceStoreTagCallback
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener
import com.crrepa.ble.conn.listener.CRPBleECGChangeListener
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener
import com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener
import com.crrepa.ble.conn.listener.CRPBloodPressureChangeListener
import com.crrepa.ble.conn.listener.CRPBreathRateChangeListener
import com.crrepa.ble.conn.listener.CRPCameraOperationListener
import com.crrepa.ble.conn.listener.CRPContactListener
import com.crrepa.ble.conn.listener.CRPDeviceBatteryListener
import com.crrepa.ble.conn.listener.CRPFileTransListener
import com.crrepa.ble.conn.listener.CRPFindPhoneListener
import com.crrepa.ble.conn.listener.CRPHeartRateChangeListener
import com.crrepa.ble.conn.listener.CRPHrvChangeListener
import com.crrepa.ble.conn.listener.CRPMovementStateListener
import com.crrepa.ble.conn.listener.CRPPhoneOperationListener
import com.crrepa.ble.conn.listener.CRPQuickResponsesChangeListener
import com.crrepa.ble.conn.listener.CRPSleepActionChangeListener
import com.crrepa.ble.conn.listener.CRPSleepChangeListener
import com.crrepa.ble.conn.listener.CRPStepChangeListener
import com.crrepa.ble.conn.listener.CRPStepsCategoryChangeListener
import com.crrepa.ble.conn.listener.CRPStockChangeListener
import com.crrepa.ble.conn.listener.CRPTempChangeListener
import com.crrepa.ble.conn.listener.CRPTrainingChangeListener
import com.crrepa.ble.conn.listener.CRPWatchFaceTransListener
import com.crrepa.ble.conn.type.CRPBleMessageType
import com.crrepa.ble.conn.type.CRPDeviceLanguageType
import com.crrepa.ble.conn.type.CRPDeviceVersionType
import com.crrepa.ble.conn.type.CRPEcgMeasureType
import com.crrepa.ble.conn.type.CRPHeartRateType
import com.crrepa.ble.conn.type.CRPHistoryDay
import com.crrepa.ble.conn.type.CRPHistoryDynamicRateType
import com.crrepa.ble.conn.type.CRPMetricSystemType
import com.crrepa.ble.conn.type.CRPTimeSystemType
import com.crrepa.ble.conn.type.CRPWatchFaceLayoutType
import com.crrepa.ble.conn.type.CRPWatchFaceStoreType
import com.crrepa.ble.conn.type.CRPWeatherId
import com.crrepa.ble.scan.bean.CRPScanRecordInfo
import com.crrepa.ble.scan.bean.CRPScanRecordInfo.McuPlatform
import com.crrepa.ble.trans.tp.CRPTpInfo
import com.google.gson.Gson
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.File
import java.util.Date

/** DafitSdkPlugin */
class DafitSdkPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
  private lateinit var channel : MethodChannel
  private lateinit var mContext : Context
  private lateinit var mActivity: Activity
  private var gson = Gson()
  private lateinit var mBleClient: CRPBleClient
  var mBleDevice: CRPBleDevice? = null
  var mBleConnection: CRPBleConnection? = null
  var btBluetoothDevice: BluetoothDevice? = null
  private val TAG: String = "DafitSdkPlugin"
  var unBond: Boolean = true
  private var mSupportWatchFaceList: List<Int>? = null
  private var mWatchFaceLayoutInfo: CRPWatchFaceLayoutInfo? = null
  var mWatchFaceStoreType: CRPWatchFaceStoreType? = null
  var mWatchFaceMaxSize: Int = 0
  var mWatchFaceApiVersion: Int = -1
  var mWatchFaceFeature: Int = -1

  var mWatchFaceStoreTagInfoList: List<CRPWatchFaceStoreTagInfo>? = null
  var mWatchFaceStoreInfo: CRPWatchFaceStoreInfo? = null

  var watchfaceUrl: String? = null
  var watchfaceId: Int = 0

  private val mFirmwareVersion: String? = null
  private var mcuPlatform: McuPlatform? = null


  private val UI_FILE_PATH: String = (Environment.getExternalStorageDirectory().absolutePath
          + File.separator + "crrepa" + File.separator + "ota" + File.separator
          + "eaf49ccb4dbe5df51af35803662867d5.bin")

  private var deviceDataReceivedChannel: EventChannel? = null
  private var deviceDataReceivedSink : EventChannel.EventSink? = null
  private val deviceDataReceivedHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      deviceDataReceivedSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  private var connectionStateChannel: EventChannel? = null
  private var connectionStateSink : EventChannel.EventSink? = null
  private val connectionStateHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      connectionStateSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  private var getBatteryLevelChannel: EventChannel? = null
  private var getBatteryLevelSink : EventChannel.EventSink? = null
  private val getBatteryLevelHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getBatteryLevelSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  private var getStockChangeChannel: EventChannel? = null
  private var getStockChangeSink : EventChannel.EventSink? = null
  private val getStockChangeHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getStockChangeSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Quick Responses Channel
  private var getQuickResponsesChannel: EventChannel? = null
  private var getQuickResponsesSink: EventChannel.EventSink? = null
  private val getQuickResponsesHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getQuickResponsesSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Breath Rate Channel
  private var getBreathRateChannel: EventChannel? = null
  private var getBreathRateSink: EventChannel.EventSink? = null
  private val getBreathRateHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getBreathRateSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // HRV Channel
  private var getHrvChannel: EventChannel? = null
  private var getHrvSink: EventChannel.EventSink? = null
  private val getHrvHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getHrvSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Temperature Channel
  private var getTempChannel: EventChannel? = null
  private var getTempSink: EventChannel.EventSink? = null
  private val getTempHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getTempSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Movement State Channel
  private var getMovementStateChannel: EventChannel? = null
  private var getMovementStateSink: EventChannel.EventSink? = null
  private val getMovementStateHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getMovementStateSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Training Channel
  private var getTrainingChannel: EventChannel? = null
  private var getTrainingSink: EventChannel.EventSink? = null
  private val getTrainingHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getTrainingSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Sleep Action Channel
  private var getSleepActionChannel: EventChannel? = null
  private var getSleepActionSink: EventChannel.EventSink? = null
  private val getSleepActionHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getSleepActionSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Steps Category Channel
  private var getStepsCategoryChannel: EventChannel? = null
  private var getStepsCategorySink: EventChannel.EventSink? = null
  private val getStepsCategoryHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getStepsCategorySink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Step Change Channel
  private var getStepChangeChannel: EventChannel? = null
  private var getStepChangeSink: EventChannel.EventSink? = null
  private val getStepChangeHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getStepChangeSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Sleep Change Channel
  private var getSleepChangeChannel: EventChannel? = null
  private var getSleepChangeSink: EventChannel.EventSink? = null
  private val getSleepChangeHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getSleepChangeSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Heart Rate Channel
  private var getHeartRateChannel: EventChannel? = null
  private var getHeartRateSink: EventChannel.EventSink? = null
  private val getHeartRateHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getHeartRateSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Blood Pressure Channel
  private var getBloodPressureChannel: EventChannel? = null
  private var getBloodPressureSink: EventChannel.EventSink? = null
  private val getBloodPressureHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getBloodPressureSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // Blood Oxygen Channel
  private var getBloodOxygenChannel: EventChannel? = null
  private var getBloodOxygenSink: EventChannel.EventSink? = null
  private val getBloodOxygenHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getBloodOxygenSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  // ECG Channel
  private var getECGChannel: EventChannel? = null
  private var getECGSink: EventChannel.EventSink? = null
  private val getECGHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getECGSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  private var getContactChannel: EventChannel? = null
  private var getContactSink: EventChannel.EventSink? = null
  private val getContactHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getContactSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  private var getFindPhoneChannel: EventChannel? = null
  private var getFindPhoneSink: EventChannel.EventSink? = null
  private val getFindPhoneHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      getFindPhoneSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  private var cameraChannel: EventChannel? = null
  private var cameraSink: EventChannel.EventSink? = null
  private val cameraHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      cameraSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }

  private var onLoadingChannel: EventChannel? = null
  private var onLoadingSink: EventChannel.EventSink? = null
  private val onLoadingHandler = object : EventChannel.StreamHandler {
    override fun onListen(arg: Any?, eventSink: EventChannel.EventSink?) {
      onLoadingSink = eventSink
    }
    override fun onCancel(o: Any?) {}
  }


  fun getBleClient(context: Context): CRPBleClient {
    return mBleClient
  }

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "dafit_sdk")
    channel.setMethodCallHandler(this)
    mContext = flutterPluginBinding.applicationContext
    mBleClient = CRPBleClient.create(mContext)
    deviceDataReceivedChannel = EventChannel(flutterPluginBinding.binaryMessenger, "deviceDataReceived")
    deviceDataReceivedChannel!!.setStreamHandler(deviceDataReceivedHandler)

    connectionStateChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitConnectionState")
    connectionStateChannel!!.setStreamHandler(connectionStateHandler)

    getBatteryLevelChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetBatteryLevel")
    getBatteryLevelChannel!!.setStreamHandler(getBatteryLevelHandler)

    getStockChangeChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetStockChange")
    getStockChangeChannel!!.setStreamHandler(getStockChangeHandler)
    // Quick Responses Channel Setup
    getQuickResponsesChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetQuickResponses")
    getQuickResponsesChannel!!.setStreamHandler(getQuickResponsesHandler)

// Breath Rate Channel Setup
    getBreathRateChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetBreathRate")
    getBreathRateChannel!!.setStreamHandler(getBreathRateHandler)

// HRV Channel Setup
    getHrvChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetHrv")
    getHrvChannel!!.setStreamHandler(getHrvHandler)

// Temperature Channel Setup
    getTempChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetTemp")
    getTempChannel!!.setStreamHandler(getTempHandler)

// Movement State Channel Setup
    getMovementStateChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetMovementState")
    getMovementStateChannel!!.setStreamHandler(getMovementStateHandler)

// Training Channel Setup
    getTrainingChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetTraining")
    getTrainingChannel!!.setStreamHandler(getTrainingHandler)

// Sleep Action Channel Setup
    getSleepActionChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetSleepAction")
    getSleepActionChannel!!.setStreamHandler(getSleepActionHandler)

// Steps Category Channel Setup
    getStepsCategoryChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetStepsCategory")
    getStepsCategoryChannel!!.setStreamHandler(getStepsCategoryHandler)

// Step Change Channel Setup
    getStepChangeChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetStepChange")
    getStepChangeChannel!!.setStreamHandler(getStepChangeHandler)

// Sleep Change Channel Setup
    getSleepChangeChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetSleepChange")
    getSleepChangeChannel!!.setStreamHandler(getSleepChangeHandler)

// Heart Rate Channel Setup
    getHeartRateChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetHeartRate")
    getHeartRateChannel!!.setStreamHandler(getHeartRateHandler)

// Blood Pressure Channel Setup
    getBloodPressureChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetBloodPressure")
    getBloodPressureChannel!!.setStreamHandler(getBloodPressureHandler)

// Blood Oxygen Channel Setup
    getBloodOxygenChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetBloodOxygen")
    getBloodOxygenChannel!!.setStreamHandler(getBloodOxygenHandler)

// ECG Channel Setup
    getECGChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetECG")
    getECGChannel!!.setStreamHandler(getECGHandler)


// Contact Channel Setup
    getContactChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetContact")
    getContactChannel!!.setStreamHandler(getContactHandler)

// Find Phone Channel Setup
    getFindPhoneChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitGetFindPhone")
    getFindPhoneChannel!!.setStreamHandler(getFindPhoneHandler)

// camera
    cameraChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitCamera")
    cameraChannel!!.setStreamHandler(cameraHandler)

    onLoadingChannel = EventChannel(flutterPluginBinding.binaryMessenger, "dafitOnLoading")
    onLoadingChannel!!.setStreamHandler(onLoadingHandler)
  }

  fun sendToMainUI( event: EventChannel.EventSink?, map: MutableMap<String, Any?>) {
    Handler(Looper.getMainLooper()).post {
      event?.success(map)
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method){
      "connect" -> {
        val macAddress = call.argument<String>("macAddress")
        mBleDevice = mBleClient.getBleDevice(macAddress)
        if (mBleDevice != null && !mBleDevice!!.isConnected) {
          connect()
        }
      }
      "disconnect" -> {
        if (mBleDevice != null) {
          mBleDevice!!.disconnect()
        }
      }
      "isConnected" -> {
        if (mBleDevice != null) {
          val res = mBleDevice!!.isConnected
          result.success(res)
        }
      }
      "createBond" -> {
        if (mBleDevice != null) {
          val res = mBleDevice!!.bluetoothDevice.createBond()
          result.success(res)
        }
      }
      else -> {
//        if (!mBleDevice!!.isConnected) {
//          result.error("not connected", "not connected", null)
//          return
//        }
        when(call.method){
          "sendDoNotDistrubTime" -> {
            val isDoNotDistrub: Boolean? = call.argument<Boolean>("isDoNotDistrub")
            var cRPPeriodTimeInfo = CRPPeriodTimeInfo()
            if (isDoNotDistrub==true){
              cRPPeriodTimeInfo.startMinute=1
              cRPPeriodTimeInfo.startHour=0
              cRPPeriodTimeInfo.endMinute=59
              cRPPeriodTimeInfo.endHour=23;
            }else{
              cRPPeriodTimeInfo.startMinute=0
              cRPPeriodTimeInfo.startHour=0
              cRPPeriodTimeInfo.endMinute=0
              cRPPeriodTimeInfo.endHour=0
            }
            mBleConnection!!.sendDoNotDistrubTime(cRPPeriodTimeInfo);
          }
          "sendDisplayTime" -> {
            val time = call.argument<Int>("time")
            mBleConnection!!.sendDisplayTime(time!!)
          }
          "sendTapToWakeState" -> {
            val enable = call.argument<Boolean>("enable")
            mBleConnection!!.sendTapToWakeState(enable!!)
          }
          "sendQuickView" -> {
            val enable = call.argument<Boolean>("enable")
            mBleConnection!!.sendQuickView(enable!!)
          }
          "camera" -> {
            val isOpen = call.argument<Boolean>("isOpen")
            if(isOpen==true){
              mBleConnection!!.enterCameraView()
            }else{
              mBleConnection!!.exitCameraView()
            }
            mBleConnection!!.setCameraOperationListener(object :CRPCameraOperationListener{
              override fun onTakePhoto() {
                var map = HashMap<String, Any?>()
                map["onTakePhoto"] = true
                sendToMainUI(cameraSink, map)
              }

              override fun onExitCamera() {
                var map = HashMap<String, Any?>()
                map["onExitCamera"] = true
                sendToMainUI(cameraSink, map)
              }

            })
          }
          "sendWatchFace" -> {
            val path = call.argument<String>("path")
            // Create a File object from the provided path
            val file = File(path)

            // Check if the file exists
            if (!file.exists()){
              Log.d(
                TAG,
                "file does not exist"
              )
              return
            }
            val watchFaceInfo =
              CRPWatchFaceInfo(file, CRPWatchFaceInfo.WacthFaceType.JIELI)
            mBleConnection!!.sendWatchFace(watchFaceInfo, object : CRPWatchFaceTransListener {
              override fun onInstallStateChange(success: Boolean) {
                Log.d(
                  TAG,
                  "onInstallStateChange: $success"
                )
                var map =   HashMap<String, Any?>()
                map["status"] = "onInstallStateChange"
                map["success"] = success
                sendToMainUI(onLoadingSink, map)
              }

              override fun onTransProgressStarting() {
                Log.d(TAG, "onTransProgressStarting")
                var map =   HashMap<String, Any?>()
                map["status"] = "onTransProgressStarting"
                sendToMainUI(onLoadingSink, map)
              }

              override fun onTransProgressChanged(percent: Int) {
                Log.d(
                  TAG,
                  "onTransProgressChanged: $percent"
                )
                var map =   HashMap<String, Any?>()
                map["status"] = "onTransProgressChanged"
                map["progress"] = percent
                sendToMainUI(onLoadingSink, map)
              }

              override fun onTransCompleted() {
                Log.d(TAG, "onTransCompleted")
                var map =   HashMap<String, Any?>()
                map["status"] = "onTransCompleted"
                sendToMainUI(onLoadingSink, map)
//                mBleConnection!!.sendJieliWatchFaceId(watchfaceId, false)
              }

              override fun onError(type: Int) {
                Log.d(TAG, "onError: $type")
                var map =   HashMap<String, Any?>()
                map["status"] = "onError"
                map["type"] = type
                sendToMainUI(onLoadingSink, map)
              }
            }, 30)
          }
          "query_physiologcal_period" -> mBleConnection!!.queryPhysiologcalPeriod(CRPDevicePhysiologcalPeriodCallback { info ->
            Log.d(
              TAG,
              "isMenstrualReminder: " + info.isMenstrualReminder
            )
            Log.d(
              TAG,
              "isOvulationEndReminder: " + info.isOvulationEndReminder
            )
            Log.d(
              TAG,
              "isOvulationDayReminder: " + info.isOvulationDayReminder
            )
            Log.d(
              TAG,
              "isOvulationReminder: " + info.isOvulationReminder
            )
            Log.d(
              TAG,
              "physiologcalPeriod: " + info.physiologcalPeriod
            )
            Log.d(TAG, "startDate: " + info.startDate)
            Log.d(TAG, "hour: " + info.reminderHour)
            Log.d(TAG, "minute: " + info.reminderMinute)
          })
          "send_physiologcal_period" -> {
            val physiologcalPeriodInfo = CRPPhysiologcalPeriodInfo()
            physiologcalPeriodInfo.reminderHour = 10
            physiologcalPeriodInfo.reminderMinute = 0
            physiologcalPeriodInfo.startDate = Date()
            physiologcalPeriodInfo.menstrualPeriod = 5
            physiologcalPeriodInfo.physiologcalPeriod = 28
            physiologcalPeriodInfo.isOvulationEndReminder = true
            physiologcalPeriodInfo.isMenstrualReminder = true
            physiologcalPeriodInfo.isOvulationReminder = true
            physiologcalPeriodInfo.isOvulationDayReminder = true
            mBleConnection!!.sendPhysiologcalPeriod(physiologcalPeriodInfo)
          }
          "query_device_support_function" -> mBleConnection!!.queryDeviceSupportFunction(CRPDeviceFunctionCallback { info ->
            for (integer in info.functionList) {
              Log.d(
                TAG,
                "function: $integer"
              )
            }
          })
          "send_device_function" -> {
            val functionList: MutableList<Int> = java.util.ArrayList()
            functionList.add(CRPFunctionInfo.TIME_VIEW)
            functionList.add(CRPFunctionInfo.STEP_VIEW)
            functionList.add(CRPFunctionInfo.SLEEP_VIEW)
            functionList.add(CRPFunctionInfo.HR_VIEW)
            functionList.add(CRPFunctionInfo.TRAINING_VIEW)
            functionList.add(CRPFunctionInfo.BP_VIEW)
            functionList.add(CRPFunctionInfo.BO_VIEW)
            functionList.add(CRPFunctionInfo.WEATHER_VIEW)
            functionList.add(CRPFunctionInfo.MSG_LIST_VIEW)
            functionList.add(CRPFunctionInfo.MUSIC_PLAYER_VIEW)
            functionList.add(CRPFunctionInfo.CAMERA_VIEW)
            functionList.add(CRPFunctionInfo.OTHER_VIEW)
            val functionInfo = CRPFunctionInfo()
            functionInfo.functionList = functionList
            mBleConnection!!.sendDislpayDeviceFunction(functionInfo)
          }
          "query_device_function" -> {
            mBleConnection!!.queryDisplayDeviceFunction { info ->
              for (integer in info.functionList) {
                Log.d(
                  TAG,
                  "function: $integer"
                )
              }
            }
            mBleConnection!!.queryMessageList { list -> Log.d(TAG,"Support message list：$list") }
          }
          "query_firmware" -> mBleConnection!!.queryFrimwareVersion { version ->
            Log.d(TAG, "onDeviceFirmwareVersion：$version")
            result.success(version);
          }

          "check_firmware" -> {}
          "firmware_upgrade" -> {}
          "switch_background" -> {
            if (mWatchFaceLayoutInfo == null) {
              return
            }
            val bgBytes: ByteArray? = call.argument<ByteArray?>("bgBytes")
            val offset = 0
            val bglength = bgBytes!!.size
            val bgBitmapX = BitmapFactory.decodeByteArray(bgBytes, offset, bglength)

            val changeBitmap: Bitmap = changeBitmapSize(
              bgBitmapX,
              mWatchFaceLayoutInfo!!.width,
              mWatchFaceLayoutInfo!!.height
            )
            Log.d(
              TAG,
              "changeBitmap width: " + changeBitmap.width + ", height: " + mWatchFaceLayoutInfo!!.getHeight()
            )
            var watchFaceBackgroundInfo = CRPWatchFaceBackgroundInfo(
              changeBitmap,
              mWatchFaceLayoutInfo!!.getCompressionType()
            )

            if(0<mWatchFaceLayoutInfo!!.thumHeight && 0 < mWatchFaceLayoutInfo!!.thumWidth){
              val thumBitmap : Bitmap = changeBitmapSize(bgBitmapX , mWatchFaceLayoutInfo!!.thumWidth,mWatchFaceLayoutInfo!!.thumHeight)
              watchFaceBackgroundInfo.thumBitmap = thumBitmap
            }

            mBleConnection!!.sendWatchFaceBackground(
              watchFaceBackgroundInfo,
              object : CRPFileTransListener {
                override fun onTransProgressStarting() {
                  Log.d(TAG, "onTransProgressStarting")
                  var map =   HashMap<String, Any?>()
                  map["status"] = "onTransProgressStarting"
                  sendToMainUI(onLoadingSink, map)
                }

                override fun onTransProgressChanged(percent: Int) {
                  Log.d(
                    TAG,
                    "onTransProgressChanged: $percent"
                  )
                  var map =   HashMap<String, Any?>()
                  map["status"] = "onTransProgressChanged"
                  map["progress"] = percent
                  sendToMainUI(onLoadingSink, map)
                }

                override fun onTransCompleted() {
                  Log.d(TAG, "onTransCompleted")
                  var map =   HashMap<String, Any?>()
                  map["status"] = "onTransCompleted"
                  sendToMainUI(onLoadingSink, map)
//                mBleConnection!!.sendJieliWatchFaceId(watchfaceId, false)
                }

                override fun onError(type: Int) {
                  Log.d(TAG, "onError: $type")
                  var map =   HashMap<String, Any?>()
                  map["status"] = "onError"
                  map["type"] = type
                  sendToMainUI(onLoadingSink, map)
                }
              })
          }
          "query_battery" -> {
            mBleConnection!!.subscribeDeviceBattery()
            mBleConnection!!.setDeviceBatteryListener(object : CRPDeviceBatteryListener {
              override fun onSubscribe(success: Boolean) {
                Log.d(
                  TAG,
                  " battery subscribed: $success"
                )
              }

              override fun onDeviceBattery(battery: Int) {
                Log.d(TAG, "battery: $battery%")
                var map = HashMap<String, Any?>()
                map["battery"] = battery
                sendToMainUI(getBatteryLevelSink, map)
              }
            })
            mBleConnection!!.queryDeviceBattery()
          }
          "sync_time" -> {
            mBleConnection!!.syncTime()
            mBleConnection!!.sendTimeSystem(CRPTimeSystemType.TIME_SYSTEM_24)
          }
          "music_control" -> mBleConnection!!.setPhoneOperationListener(CRPPhoneOperationListener { type ->
            Log.d(
              TAG,
              "onOperationChange: $type"
            )
          })
          "send_user_info" -> {
            val userInfo = CRPUserInfo(75, 178, CRPUserInfo.MALE, 24)
            mBleConnection!!.sendUserInfo(userInfo)
          }
          "sync_step" -> mBleConnection!!.syncStep()
          "sync_past_step" -> {
            mBleConnection!!.queryHistoryStep(CRPHistoryDay.YESTERDAY)
            mBleConnection!!.queryHistoryStep(CRPHistoryDay.THE_DAY_BEFORE_YESTERDAY)
            mBleConnection!!.queryHistoryStep(CRPHistoryDay.THREE_DAYS_AGO)
          }
          "sync_sleep" -> mBleConnection!!.syncSleep()
          "sync_past_sleep" -> {
            mBleConnection!!.syncRemSleep()
            mBleConnection!!.queryHistorySleep(CRPHistoryDay.YESTERDAY)
            mBleConnection!!.queryHistorySleep(CRPHistoryDay.THE_DAY_BEFORE_YESTERDAY)
            mBleConnection!!.queryHistorySleep(CRPHistoryDay.THREE_DAYS_AGO)
          }
          "send_metric_system" -> //                List<Integer> savedTimeList = new ArrayList<>();
//                mBleConnection!!.setGpsChangeListener(new CRPGpsChangeListener() {
//                    @Override
//                    public void onHistoryGpsPathChange(List<Integer> timeList) {
//                        if (timeList != null) {
//                            Log.d(TAG, "onHistoryGpsChange: " + timeList.toString());
////                            for (Integer time : timeList) {
////                                mBleConnection!!.queryGpsDetail(time);
////                            }
//                            savedTimeList.addAll(timeList);
//                            mBleConnection!!.queryGpsDetail(savedTimeList.remove(0));
//                        }
//                    }
//
//                    @Override
//                    public void onGpsPathChange(CRPGpsPathInfo info) {
//                        Log.d(TAG, "onGpsPathChange: " + info.toString());
//                        if (!savedTimeList.isEmpty()) {
//                            mBleConnection!!.queryGpsDetail(savedTimeList.remove(0));
//                        }
//                    }
//
//                    @Override
//                    public void onLocationChanged(CRPGpsPathInfo.Location location) {
//                        Log.d(TAG, "onLocationChanged: " + location.toString());
//                    }
//
//                    @Override
//                    public void onUpdateEpoChange(CRPEpoType type) {
//                        sendEpoFile(type);
//                    }
//
//                });
//
//                mBleConnection!!.queryHistoryGps();
            mBleConnection!!.sendMetricSystem(CRPMetricSystemType.METRIC_SYSTEM)
          "query_metric_system" -> mBleConnection!!.queryMetricSystem(object :
            CRPDeviceMetricSystemCallback {
            override fun onMetricSystem(type: Int) {
              Log.d(
                TAG,
                "onMetricSystem: $type"
              )
            }
          })
          "send_time_system" -> mBleConnection!!.sendTimeSystem(CRPTimeSystemType.TIME_SYSTEM_12)
          "query_time_system" -> mBleConnection!!.queryTimeSystem { type ->
            Log.d(
              TAG,
              "onTimeSystem: $type"
            )
          }

          "send_quick_view" -> mBleConnection!!.sendQuickView(false)
          "query_quick_view" -> mBleConnection!!.queryQuickView { state ->
            Log.d(
              TAG,
              "onQuickView: $state"
            )
          }

          "send_goal_step" -> mBleConnection!!.sendGoalSteps(10000)
          "query_goal_step" -> mBleConnection!!.queryGoalStep { steps ->
            Log.d(
              TAG,
              "onGoalStep: $steps"
            )
          }

          "find_band" -> //                mBleConnection!!.querySupportStress();
            mBleConnection!!.findDevice()
          "send_message" -> {
            val message: String? = call.argument<String>("message")
            val type: Int? = call.argument<Int>("type")

            Log.d(
              TAG,
              "message: $message , type: $type"
            )
            val versionCode: Int? = call.argument<Int>("versionCode")
            val messageInfo = CRPMessageInfo()
            messageInfo.message = message
            if (type != null) {
              messageInfo.type = type
            }else{
              messageInfo.type = CRPBleMessageType.MESSAGE_OTHER
            }
            messageInfo.versionCode = versionCode!!
            messageInfo.isHs = false
            messageInfo.isSmallScreen = false
            mBleConnection?.sendMessage(messageInfo)

          }
          "send_alarm_clock" -> {
            val alarmInfo = CRPAlarmInfo()
            alarmInfo.id = CRPAlarmInfo.FIRST_ALARM
            alarmInfo.hour = 14
            alarmInfo.minute = 24
            alarmInfo.repeatMode = CRPAlarmInfo.SINGLE.toInt()
            alarmInfo.date = Date()
            alarmInfo.isEnable = true
            mBleConnection!!.sendNewAlarm(alarmInfo)
          }
          "query_alarm_clock" -> {
            val alarmCallback: CRPAlarmCallback = object : CRPAlarmCallback {
              override fun onAlarmList(list: List<CRPAlarmInfo>) {
                for (info in list) {
                  Log.d(TAG, info.toString())
                }
              }

              override fun onNewAlarmList(list: List<CRPAlarmInfo>) {
                for (info in list) {
                  Log.d(TAG, info.toString())
                }
              }
            }
            mBleConnection!!.queryAllAlarm(alarmCallback)
          }
          "send_quickview_time" -> {
            val info = CRPPeriodTimeInfo()
            info.startHour = 8
            info.startMinute = 0
            info.endHour = 20
            info.endMinute = 0
            mBleConnection!!.sendQuickViewTime(info)
          }
          "query_quickview_time" -> mBleConnection!!.queryQuickViewTime { type, periodTimeInfo ->
            val startHour = periodTimeInfo.startHour
            val startMinute = periodTimeInfo.startMinute
            val endHour = periodTimeInfo.endHour
            val endMinute = periodTimeInfo.endMinute
            Log.d(
              TAG,
              "startTime: $startHour:$startMinute"
            )
            Log.d(
              TAG,
              "endTime: $endHour:$endMinute"
            )
          }

          "send_device_language" -> {
            mBleConnection!!.sendDeviceVersion(CRPDeviceVersionType.INTERNATIONAL_EDITION)
            mBleConnection!!.sendDeviceLanguage(CRPDeviceLanguageType.LANGUAGE_UKRAINIAN)
          }
          "query_device_language" -> //                mBleConnection!!.queryTapToWakeState(new CRPTapToWakeCallback() {
//                    @Override
//                    public void onWakeState(boolean enable) {
//                        Log.d(TAG, "onWakeState: " + enable);
//                    }
//                });
            mBleConnection!!.queryDeviceLanguage { type, languageArray ->
              Log.d(
                TAG,
                "onDeviceLanguage display language: $type"
              )
              for (i in languageArray) {
                Log.d(
                  TAG,
                  "onDeviceLanguage languageArray $i"
                )
              }
            }
          "send_other_message" -> //
            mBleConnection!!.sendOtherMessageState(true);
          "query_other_message" -> {
            mBleConnection!!.queryOtherMessageState(object : CRPDeviceOtherMessageCallback {
              override fun onOtherMessage(p0: Boolean) {
                Log.d(
                  TAG,
                  "queryOtherMessageState: $p0"
                )
              }
            })
          }
          "send_sedentary_reminder" -> mBleConnection!!.sendSedentaryReminder(true)
          "query_sedentary_reminder" -> mBleConnection!!.querySedentaryReminder { state ->
            Log.d(
              TAG,
              "onSedentaryReminder: $state"
            )
          }

          "send_display_watch_face" -> mBleConnection!!.sendDisplayWatchFace(1.toByte())
          "query_display_watch_face" -> mBleConnection!!.queryDisplayWatchFace { type ->
            Log.d(
              TAG,
              "onDisplayWatchFace: $type"
            )
          }

          "start_measure_heart_rate" -> mBleConnection!!.startMeasureOnceHeartRate()
          "stop_measure_heart_rate" -> mBleConnection!!.stopMeasureOnceHeartRate()
          "sync_last_heart_rate" -> mBleConnection!!.queryLastDynamicRate(CRPHistoryDynamicRateType.FIRST_HEART_RATE)
          "open_24_heart_rate" -> mBleConnection!!.enableTimingMeasureHeartRate(1)
          "close_24_heart_rate" -> mBleConnection!!.disableTimingMeasureHeartRate()
          "query_today_heart_rate" -> mBleConnection!!.queryTodayHeartRate(CRPHeartRateType.TIMING_MEASURE_HEART_RATE)
          "query_yesterday_heart_rate" -> {
            mBleConnection!!.queryHistoryTimingHeartRate(CRPHistoryDay.TODAY)
            mBleConnection!!.queryHistoryTimingHeartRate(CRPHistoryDay.YESTERDAY)
            mBleConnection!!.queryHistoryTimingHeartRate(CRPHistoryDay.THE_DAY_BEFORE_YESTERDAY)
          }
          "query_movement_heart_rate" -> mBleConnection!!.startMovement(CRPMovementHeartRateInfo.WALK_TYPE)

          "send_device_version" -> mBleConnection!!.sendDeviceVersion(CRPDeviceVersionType.INTERNATIONAL_EDITION)

          "query_device_version" -> mBleConnection!!.queryDeviceVersion { version ->
            Log.d(
              TAG,
              "onDeviceVersion: $version"
            )
          }

          "start_measure_blood_pressure" -> mBleConnection!!.startMeasureBloodPressure()
          "stop_measure_blood_pressure" -> mBleConnection!!.stopMeasureBloodPressure()
          "send_today_weather" -> {
            val todayWeatherInfo = CRPTodayWeatherInfo()
            todayWeatherInfo.city =
              "深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳深圳"
            //                todayWeatherInfo.setFestival(" ");
            todayWeatherInfo.lunar = "六月十四"
            todayWeatherInfo.pm25 = 10
            todayWeatherInfo.temp = 38
            todayWeatherInfo.weatherId = CRPWeatherId.RAINY
            mBleConnection!!.sendTodayWeather(todayWeatherInfo)
          }
          "send_future_weather" -> {
            val futureWeatherInfo = CRPFutureWeatherInfo()
            val list: MutableList<FutureBean> = java.util.ArrayList()

            var i = 0
            while (i < 7) {
              val bean = FutureBean()
              bean.weatherId = 300
              bean.lowTemperature = 20 + i
              bean.highTemperature = 30 + i
              list.add(bean)
              i++
            }
            futureWeatherInfo.future = list
            mBleConnection!!.sendFutureWeather(futureWeatherInfo)
          }
          "start_measure_blood_oxygen" -> mBleConnection!!.startMeasureBloodOxygen()
          "stop_measure_blood_oxygen" -> mBleConnection!!.stopMeasureBloodOxygen()
          "send_breathing_light" -> mBleConnection!!.sendBreathingLight(true)
          "query_breathing_light" -> mBleConnection!!.queryBreathingLight { enable ->
            Log.d(
              TAG,
              "onBreathingLight: $enable"
            )
          }

          "send_watch_face_layout" -> {
            val watchFaceLayoutInfo = CRPWatchFaceLayoutInfo()
            val timePosition: Int? = call.argument<Int>("timePosition")
            val timeTopContent: Int? = call.argument<Int>("timeTopContent")
            val timeBottomContent: Int? = call.argument<Int>("timeBottomContent")
            val textColor: Number? = call.argument<Number>("textColor")
            val backgroundPictureMd5: String? = call.argument<String>("backgroundPictureMd5")

            watchFaceLayoutInfo.timePosition = timePosition!!
            watchFaceLayoutInfo.timeTopContent = timeTopContent!!
            watchFaceLayoutInfo.timeBottomContent = timeBottomContent!!
            watchFaceLayoutInfo.textColor = textColor!!.toInt()
            watchFaceLayoutInfo.backgroundPictureMd5 = backgroundPictureMd5!!
            mBleConnection!!.sendWatchFaceLayout(watchFaceLayoutInfo)
          }
          "query_watch_face_layout" -> mBleConnection!!.queryWatchFaceLayout { info ->
            Log.d(
              TAG,
              "onWatchFaceLayoutChange: $info"
            )
            mWatchFaceLayoutInfo = info
            val jsonResult = gson.toJson(info)
            val json = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
            result.success(json)
          }

          "start_measure_ecg" -> {
            mBleConnection!!.startECGMeasure()
            mBleConnection!!.sendBatterySaving(true)
          }
          "stop_measure_ecg" -> //                mBleConnection!!.stopECGMeasure();
            mBleConnection!!.queryBatterySaving()
          "query_last_ecg" -> mBleConnection!!.queryLastMeasureECGData()
          "query_today_steps_category" -> //                mBleConnection!!.queryStepsCategory(CRPStepsCategoryDateType.TODAY_STEPS_CATEGORY);
            mBleConnection!!.sendSongTitle("hello")
          "query_yesterday_steps_category" -> //                mBleConnection!!.queryStepsCategory(CRPStepsCategoryDateType.YESTERDAY_STEPS_CATEGORY);
            mBleConnection!!.closeMusicControl()
          "query_support_watch_face" -> {
            mBleConnection!!.querySupportWatchFace(object : CRPDeviceSupportWatchFaceCallback {
              override fun onSupportWatchFace(info: CRPSupportWatchFaceInfo) {
                Log.d(
                  TAG,
                  "onSupportWatchFace: $info"
                )
                mWatchFaceStoreType = CRPWatchFaceStoreType.DEFAULT
                mSupportWatchFaceList = info.supportWatchFaceList
              }

              override fun onSifliSupportWatchFace(info: CRPSifliSupportWatchFaceInfo) {
                Log.d(
                  TAG,
                  "onSifliSupportWatchFace: $info"
                )
                mWatchFaceStoreType = CRPWatchFaceStoreType.SIFLI
//                mSupportWatchFaceList = java.util.ArrayList<Int>()
//                mSupportWatchFaceList.add(info.type)
              }

              override fun onJieliSupportWatchFace(info: CRPJieliSupportWatchFaceInfo) {
                Log.d(
                  TAG,
                  "onJieliSupportWatchFace: $info"
                )
                mWatchFaceStoreType = CRPWatchFaceStoreType.JIELI
                mSupportWatchFaceList = info.supportTypeList

                mWatchFaceMaxSize = info.watchFaceMaxSize
              }

              override fun onJieliDownloadWatchFaceList(list: List<Int>) {
              }

              override fun onHisiliconSupportWatchFace(info: CRPHisiliconWatchFaceInfo) {
              }
            })
            mBleConnection!!.queryAvailableStorage { size ->
              Log.d(
                TAG,
                "onAvailableSize: $size"
              )
              mWatchFaceMaxSize = size * 1024
            }
          }
          "query_watch_face_store" -> mBleConnection!!.queryWatchFaceStore(mSupportWatchFaceList, mFirmwareVersion, 20, 0,
            object : CRPWatchFaceStoreCallback {
              override fun onWatchFaceStoreChange(info: CRPWatchFaceStoreInfo) {
                Log.d(
                  TAG,
                  "onWatchFaceStoreChange: " + info.list.size
                )
              }

              override fun onError(msg: String) {
              }
            })
          "query_watch_face_store_tag" -> {
            // 1
            if (mWatchFaceStoreType == null) {
              Log.e(TAG, "mWatchFaceStoreType is null!")
              return
            }

            if (mSupportWatchFaceList == null || mSupportWatchFaceList?.isEmpty() == true) {
              Log.e(TAG, "mSupportWatchFaceList is null!")
              return
            }

            if (TextUtils.isEmpty(mFirmwareVersion)) {
              Log.e(TAG, "mFirmwareVersion is null!")
              return
            }

            val watchFaceStoreRequestInfo = CRPWatchFaceStoreRequestInfo(
              mWatchFaceStoreType,
              mSupportWatchFaceList,
              mFirmwareVersion,
              20,
              1,
              mWatchFaceMaxSize
            )
            if (mcuPlatform == CRPScanRecordInfo.McuPlatform.PLATFORM_JIELI) {
              if (mWatchFaceApiVersion < 0 || mWatchFaceFeature < 0) {
                mBleConnection!!.queryJieliWatchFaceInfo { info ->
                  Log.d(
                    TAG,
                    "onWatchFaceInfo: $info"
                  )
                  mWatchFaceApiVersion = info.apiVersion.toInt()
                  mWatchFaceFeature = info.feature.toInt()
                }
                return
              }

              watchFaceStoreRequestInfo.feature = mWatchFaceFeature
              watchFaceStoreRequestInfo.apiVersion = mWatchFaceApiVersion
            }
            mBleConnection!!.queryWatchFaceStoreTagList(
              watchFaceStoreRequestInfo,
              object : CRPWatchFaceStoreTagCallback {
                override fun onWatchFaceStoreTagChange(list: List<CRPWatchFaceStoreTagInfo>) {
                  Log.d(
                    TAG,
                    "onWatchFaceStoreTagChange: " + list.size
                  )
                  mWatchFaceStoreTagInfoList = list
                }

                override fun onError(s: String) {
                }
              })
          }
          "query_watch_face_store_list" -> {
            if (mWatchFaceStoreTagInfoList == null || mWatchFaceStoreTagInfoList?.isEmpty()==true) {
              Log.e(
                TAG,
                "mWatchFaceStoreTagInfoList is null!"
              )
              return
            }
            val watchFaceStoreListRequestInfo = CRPWatchFaceStoreRequestInfo(
              mWatchFaceStoreType,
              mSupportWatchFaceList,
              mFirmwareVersion,
              20,
              1,
              mWatchFaceMaxSize
            )
            if (mcuPlatform == CRPScanRecordInfo.McuPlatform.PLATFORM_JIELI) {
              if (mWatchFaceApiVersion < 0 || mWatchFaceFeature < 0) {
                mBleConnection!!.queryJieliWatchFaceInfo { info ->
                  Log.d(
                    TAG,
                    "onWatchFaceInfo: $info"
                  )
                  mWatchFaceApiVersion = info.apiVersion.toInt()
                  mWatchFaceFeature = info.feature.toInt()
                }
                return
              }

              watchFaceStoreListRequestInfo.feature = mWatchFaceFeature
              watchFaceStoreListRequestInfo.apiVersion = mWatchFaceApiVersion
            }
            mBleConnection!!.queryWatchFaceStoreList(
              watchFaceStoreListRequestInfo,
              mWatchFaceStoreTagInfoList!![0].tagId,
              object : CRPWatchFaceStoreCallback {
                override fun onWatchFaceStoreChange(info: CRPWatchFaceStoreInfo) {
                  Log.d(
                    TAG,
                    "onWatchFaceStoreChange: $info"
                  )
                  mWatchFaceStoreInfo = info
                }

                override fun onError(s: String) {
                  Log.d(TAG, "onError: $s")
                }
              })
          }
          "query_watch_face_detail" -> {
            if (mWatchFaceStoreInfo == null) {
              Log.e(TAG, "mWatchFaceStoreInfo is null!")
              return
            }
            val watchfaceList: List<CRPWatchFaceStoreInfo.WatchFaceBean> = mWatchFaceStoreInfo!!.getList()
            if (watchfaceList == null || watchfaceList.isEmpty()) {
              Log.e(TAG, "watchfaceList is null!")
              return
            }

            watchfaceId = watchfaceList!![0].id
            val watchFaceDetailsRequestInfo =
              CRPWatchFaceDetailsRequestInfo(mWatchFaceStoreType, watchfaceId)
            watchFaceDetailsRequestInfo.firmwareVersion = mFirmwareVersion
            watchFaceDetailsRequestInfo.maxSize = mWatchFaceMaxSize
            if (mcuPlatform == CRPScanRecordInfo.McuPlatform.PLATFORM_JIELI) {
              if (mWatchFaceApiVersion < 0 || mWatchFaceFeature < 0) {
                mBleConnection!!.queryJieliWatchFaceInfo { info ->
                  Log.d(
                    TAG,
                    "onWatchFaceInfo: $info"
                  )
                  mWatchFaceApiVersion = info.apiVersion.toInt()
                  mWatchFaceFeature = info.feature.toInt()
                }
                return
              }

              watchFaceDetailsRequestInfo.feature = mWatchFaceFeature
              watchFaceDetailsRequestInfo.apiVersion = mWatchFaceApiVersion
            }
            mBleConnection!!.queryWatchFaceDetail(
              watchFaceDetailsRequestInfo,
              object : CRPWatchFaceDetailsCallback {
                override fun onWatchFaceChange(watchFaceBean: CRPWatchFaceStoreInfo.WatchFaceBean) {
                  Log.d(
                    TAG,
                    "onWatchFaceChange: $watchFaceBean"
                  )
                  watchfaceUrl = watchFaceBean.file
                }

                override fun onNewWatchFaceChange(info: CRPWatchFaceDetailsInfo) {
                  Log.d(
                    TAG,
                    "onNewWatchFaceChange: $info"
                  )
                  watchfaceUrl = info.file
                }

                override fun onError(s: String) {
                  Log.e(TAG, "onError: $s")
                }
              })
          }
          "query_watch_face" -> {
            if (TextUtils.isEmpty(watchfaceUrl)) {
              Log.e(TAG, "watchfaceUrl is null!")
              return
            }
            Log.d(
              TAG,
              "watchfaceUrl: $watchfaceUrl"
            )

            val watchfacePath: String = File(
              mContext.filesDir, "moyoung" + File.separator +
                      "watchface" + File.separator +
                      "2023-10-09.bin"
            ).path
            FileDownloader.getImpl()
              .create(watchfaceUrl)
              .setPath(watchfacePath)
              .setListener(object : FileDownloadListener() {
                override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                }

                override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                  Log.d(
                    TAG,
                    "progress soFarBytes: $soFarBytes"
                  )
                }

                override fun completed(task: BaseDownloadTask) {
                  Log.d(
                    TAG,
                    "completed path: " + task.getPath()
                  )
                  val watchFaceInfo =
                    CRPWatchFaceInfo(File(task.getPath()), CRPWatchFaceInfo.WacthFaceType.JIELI)
                  mBleConnection!!.sendWatchFace(watchFaceInfo, object : CRPWatchFaceTransListener {
                    override fun onInstallStateChange(success: Boolean) {
                      Log.d(
                        TAG,
                        "onInstallStateChange: $success"
                      )
                    }

                    override fun onTransProgressStarting() {
                      Log.d(TAG, "onTransProgressStarting")
                    }

                    override fun onTransProgressChanged(percent: Int) {
                      Log.d(
                        TAG,
                        "onTransProgressChanged: $percent"
                      )
                    }

                    override fun onTransCompleted() {
                      Log.d(TAG, "onTransCompleted")

                      mBleConnection!!.sendJieliWatchFaceId(watchfaceId, false)
                    }

                    override fun onError(type: Int) {
                      Log.d(
                        TAG,
                        "onError: $type"
                      )
                    }
                  }, 30)
                }

                override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                }

                override fun error(task: BaseDownloadTask?, e: Throwable) {
                  e.printStackTrace()
                }

                override fun warn(task: BaseDownloadTask?) {
                }
              })
              .start()
          }
          "query_sleep_action" -> {
            mBleConnection!!.querySleepAction(0)
            mBleConnection!!.querySleepAction(1)
            mBleConnection!!.querySleepAction(2)
            mBleConnection!!.querySleepAction(3)
            mBleConnection!!.querySleepAction(4)
            mBleConnection!!.querySleepAction(5)
            mBleConnection!!.querySleepAction(6)
            mBleConnection!!.querySleepAction(7)
            mBleConnection!!.querySleepAction(8)
          }
          "send_band_ui" -> {
            val file: File = File(UI_FILE_PATH)
            Log.d(TAG, "file exist: " + file.exists())
            val startIndex = 0x86000
            val length = 0xa000
            val deviceStartIndex = 0x886000

            val tpInfo = CRPTpInfo()
            tpInfo.file = file
            tpInfo.deviceStartIndex = deviceStartIndex
            tpInfo.firmwareVersion = "MOY-NAA3-2.0.0"
            tpInfo.length = length
            tpInfo.startIndex = startIndex
          }
          "query_quick_contacts" -> mBleConnection!!.checkSupportQuickContact(object :
            CRPContactConfigCallback {
            override fun onContactConfig(info: CRPContactConfigInfo) {
              mQuickContactConfigInfo = info
            }
          })
          "send_quick_contacts" -> {
            if (mQuickContactConfigInfo == null || !mQuickContactConfigInfo!!.isSupported) {
              return
            }
            val avatarHeight = mQuickContactConfigInfo!!.height
            val avatarWidth = mQuickContactConfigInfo!!.width

            Log.d(
              TAG,
              "avatarHeight: $avatarHeight"
            )
            Log.d(
              TAG,
              "avatarWidth: $avatarWidth"
            )

            var i = 0
            while (i < mQuickContactConfigInfo!!.count) {
              val contactInfo = CRPContactInfo()
              contactInfo.address = 0
              contactInfo.height = avatarHeight
              contactInfo.width = avatarWidth
              contactInfo.id = i
              contactInfo.number = "11113333"
              contactInfo.name = "AAA$i"
              mBleConnection!!.sendContact(contactInfo)
              i++
            }

            val avatarOptions = BitmapFactory.Options()
            avatarOptions.inScaled = false

            val avatarBytes: ByteArray? = call.argument<ByteArray?>("avatarBytes")
            val offset = 0
            val avatarLength = avatarBytes!!.size
            val avatarBitmapX = BitmapFactory.decodeByteArray(avatarBytes, offset, avatarLength)

            val avatarBitmap = changeBitmapSize(avatarBitmapX, avatarWidth, avatarHeight)

            mBleConnection!!.sendContactAvatar(1, avatarBitmap, 30, object : CRPFileTransListener {
              override fun onTransProgressStarting() {
              }

              override fun onTransProgressChanged(percent: Int) {
                Log.d(
                  TAG,
                  "onTransProgressChanged: $percent"
                )
              }

              override fun onTransCompleted() {
                Log.d(TAG, "onTransCompleted")
              }

              override fun onError(type: Int) {
                Log.d(
                  TAG,
                  "onError: $type"
                )
              }
            })
          }
          "delete_quick_contacts" -> {
            // Delete a contact
            mBleConnection!!.deleteContact(0)
            mBleConnection!!.deleteContactAvatar(0)
            // Clear contact
            mBleConnection!!.clearContact()
          }
          "enable_hrv" -> mBleConnection!!.enableHrvMeasure(1)
          "disable_hrv" -> mBleConnection!!.disableHrvMeasure()
          "query_hrv_measure_interval" -> mBleConnection!!.queryHrvMeasureInterval()
          "query_hrv_measure_count" -> //                mBleConnection!!.queryHrvMeasureCount(0);
            mBleConnection!!.startMeasureHrv()
          "query_hrv_measure_result" -> //                mBleConnection!!.queryHrv(0, 1);
            mBleConnection!!.stopMeasureHrv()
          "enable_continue_blood_pressure" -> mBleConnection!!.enableContinueBloodPressure()
          "disable_continue_blood_pressure" -> mBleConnection!!.disableContinueBloodPressure()
          "query_continue_blood_pressure_state" -> mBleConnection!!.queryContinueBloodPressureState()
          "query_continue_blood_pressure" -> mBleConnection!!.queryLast24HourBloodPressure()
          "enable_continue_blood_oxygen" -> mBleConnection!!.enableContinueBloodOxygen()
          "disable_continue_blood_oxygen" -> mBleConnection!!.disableContinueBloodOxygen()
          "query_continue_blood_oxygen_state" -> mBleConnection!!.queryContinueBloodOxygenState()
          "query_continue_blood_oxygen" -> mBleConnection!!.queryLast24HourBloodOxygen()
          "enable_continue_temp" -> mBleConnection!!.enableContinueTemp()
          "disable_continue_temp" -> mBleConnection!!.disableContinueTemp()
          "query_continue_temp_state" -> mBleConnection!!.queryContinueTempState()
          "query_continue_temp" -> mBleConnection!!.queryLast24HourTemp()
          "start_measure_breath_rate" -> mBleConnection!!.startMeatureBreathRate()
          "stop_measure_breath_rate" -> mBleConnection!!.stopMeatureBreathRate()
          "query_history_breath_rate" -> mBleConnection!!.queryHistoryBreathRate()
          "enable_timing_breath_rate" -> mBleConnection!!.enableTimingBreathRate()
          "disable_timing_breath_rate" -> mBleConnection!!.disableTimingBreathRate()
          "query_timing_breath_rate" -> mBleConnection!!.queryTimingBreathRate(CRPHistoryDay.TODAY)
          "query_timing_breath_rate_state" -> mBleConnection!!.queryTimingBreathRateState()
          "query_quick_responses_count" -> mBleConnection!!.queryQuickResponsesCount()
          "enable_quick_responses" -> mBleConnection!!.enableQuickResponses()
          "disable_quick_responses" -> mBleConnection!!.disableQuickResponses()
          "query_quick_responses" -> mBleConnection!!.queryQuickResponses(1.toByte())
          "send_quick_responses" -> {
            val quickResponsesDetailInfo = CRPQuickResponsesDetailInfo(1.toByte(), "I'll call you later.")
            mBleConnection!!.sendQuickResponses(quickResponsesDetailInfo)
          }
          "query_support_stock" -> mBleConnection!!.querySupportStock()
          "send_stock" -> {
            val stockInfo = CRPStockInfo()
            stockInfo.id = 1.toByte()
            stockInfo.abbreviation = "AMZN"
            stockInfo.code = "000000"
            stockInfo.currency = "U.S"
            stockInfo.exchange = "1.0"
            mBleConnection!!.sendStock(stockInfo)

            val stockInfo1 = CRPStockInfo()
            stockInfo1.id = 2.toByte()
            stockInfo1.abbreviation = "AM"
            stockInfo1.code = "000001"
            stockInfo1.currency = "U.S"
            stockInfo1.exchange = "1.1"
            mBleConnection!!.sendStock(stockInfo1)
          }
          "send_stock_price" -> {
            val stockPriceInfo = CRPStockPriceInfo()
            stockPriceInfo.id = 1.toByte()
            stockPriceInfo.marketValue = 10000000000L
            stockPriceInfo.highestPrice = 100f
            stockPriceInfo.lowestPrice = 10f
            stockPriceInfo.change = 898777789f
            stockPriceInfo.average = 9980008L
            stockPriceInfo.isEnable = true
            stockPriceInfo.changePercent = 1f
            stockPriceInfo.openingPrice = 99887898f
            stockPriceInfo.pe = 23f
            stockPriceInfo.price = 87f
            stockPriceInfo.turnover = 123398676
            stockPriceInfo.yearHighestPrice = 120f
            stockPriceInfo.yearLowestPrice = 9f
            mBleConnection!!.sendStockPrice(stockPriceInfo)

            val stockPriceInfo1 = CRPStockPriceInfo()
            stockPriceInfo1.id = 2.toByte()
            stockPriceInfo1.marketValue = 10000002300L
            stockPriceInfo1.highestPrice = 100f
            stockPriceInfo1.lowestPrice = 10f
            stockPriceInfo1.change = 898777789f
            stockPriceInfo1.average = 9980008L
            stockPriceInfo1.isEnable = true
            stockPriceInfo1.changePercent = 1f
            stockPriceInfo1.openingPrice = 99887898f
            stockPriceInfo1.pe = 23f
            stockPriceInfo1.price = 87f
            stockPriceInfo1.turnover = 123398676
            stockPriceInfo1.yearHighestPrice = 120f
            stockPriceInfo1.yearLowestPrice = 9f
            mBleConnection!!.sendStockPrice(stockPriceInfo1)
          }
          "delete_stock" -> mBleConnection!!.deleteStock(1.toByte())
          "clear_stock" -> mBleConnection!!.clearStock()
          "reset_stock_list" -> {
            val stockArray = byteArrayOf(2, 1)
            mBleConnection!!.resetStockList(stockArray)
          }
          else -> result.notImplemented()
        }
      }
    }
  }

  fun connect() {
    mBleConnection = mBleDevice!!.connect()
    mBleConnection?.setConnectionStateListener(CRPBleConnectionStateListener { newState ->
      Log.d(
        TAG,
        "onConnectionStateChange: $newState"
      )
      var state = -1
      var map =   HashMap<String, Any?>()
      map["state"] = newState
      when (newState) {
        CRPBleConnectionStateListener.STATE_CONNECTED -> {
          state = CRPBleConnectionStateListener.STATE_CONNECTED
          testSet()
          map["name"] = "STATE_CONNECTED"
        }

        CRPBleConnectionStateListener.STATE_CONNECTING -> {
          state = CRPBleConnectionStateListener.STATE_CONNECTING
          map["name"] = "STATE_CONNECTING"
        }
        CRPBleConnectionStateListener.STATE_DISCONNECTED -> {
          closeGatt()
          state = CRPBleConnectionStateListener.STATE_DISCONNECTED
          map["name"] = "STATE_DISCONNECTED"
//       if (unBond) {
//            CRPBluetoothManager.getInstance(mContext).unBondDevice(btBluetoothDevice)
//          }
        }
      }

      sendToMainUI(connectionStateSink, map)

      updateConnectState(state)
    })

    mBleConnection?.setStepChangeListener(mStepChangeListener)
    mBleConnection?.setSleepChangeListener(mSleepChangeListener)
    mBleConnection?.setHeartRateChangeListener(mHeartRateChangListener)
    mBleConnection?.setBloodPressureChangeListener(mBloodPressureChangeListener)
    mBleConnection?.setBloodOxygenChangeListener(mBloodOxygenChangeListener)
    mBleConnection?.setFindPhoneListener(mFindPhoneListener)
    mBleConnection?.setECGChangeListener(mECGChangeListener, CRPEcgMeasureType.TI)
    mBleConnection?.setStepsCategoryListener(mStepsCategoryChangeListener)
    mBleConnection?.setSleepActionChangeListener(mSleepActionChangeListener)
    mBleConnection?.setMovementStateListener(mMovementStateListener)
    mBleConnection?.setTempChangeListener(mTempChangeListener)
    mBleConnection?.setContactListener(mContactListener)
    mBleConnection?.setHrvChangeListener(mHrvChangeListener)
    mBleConnection?.setBreathRateListener(mBreathRateChangeListener)
    mBleConnection?.setTrainingListener(mTrainingChangeListener)
    mBleConnection?.setStockListener(mStockChangeListener)
  }

  private fun closeGatt() {
    if (mBleConnection != null) {
      mBleConnection!!.close()
    }
  }

  fun updateConnectState(state: Int) {
    if (state < 0) {
      return
    }
  }

  private fun testSet() {
    Log.d(TAG, "testSet")

    mBleConnection!!.queryBtAddress { address ->
      btBluetoothDevice = mBleClient.getBleDevice(address).bluetoothDevice
      val bondState: Int? = btBluetoothDevice?.getBondState()
      Log.d(TAG,
        "bondState: $bondState"
      )
    }

//        mBleConnection!!.queryHistoryHeartRate();
//        mBleConnection!!.queryHistoryBloodPressure();
//        mBleConnection!!.queryHistoryBloodOxygen();

//        mBleConnection!!.queryTimingMeasureTemp(CRPTempTimeType.TODAY);
//        mBleConnection!!.queryTimingMeasureTemp(CRPTempTimeType.YESTERDAY);

//        mBleConnection!!.queryTodayHeartRate(CRPHeartRateType.ALL_DAY_HEART_RATE);
//
//        mBleConnection!!.syncTime();
//        mBleConnection!!.queryPastHeartRate();
//        mBleConnection!!.syncStep();
//        mBleConnection!!.syncSleep();
//        mBleConnection!!.syncPastStep(CRPPastTimeType.YESTERDAY_STEPS);
//        mBleConnection!!.syncPastStep(CRPPastTimeType.DAY_BEFORE_YESTERDAY_STEPS);
//        mBleConnection!!.syncPastSleep(CRPPastTimeType.YESTERDAY_SLEEP);
//        mBleConnection!!.syncPastSleep(CRPPastTimeType.DAY_BEFORE_YESTERDAY_SLEEP);
//        mBleConnection!!.queryStepsCategory(CRPStepsCategoryDateType.TODAY_STEPS_CATEGORY);

//        sendFindBandMessage();
  }

  private fun changeBitmapSize(bitmap: Bitmap, width: Int, height: Int): Bitmap {
    val bitmapWidth = bitmap.width
    val bitmapHeight = bitmap.height

    val scaleWidth = width.toFloat() / bitmapWidth
    val scaleHeight = height.toFloat() / bitmapHeight
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)

    return Bitmap.createBitmap(
      bitmap, 0, 0,
      bitmapWidth, bitmapHeight, matrix, false
    )
  }


  private val mStockChangeListener: CRPStockChangeListener = object : CRPStockChangeListener {
    override fun onSupportStockCount(i: Int) {
      Log.d(TAG,"onSupportStockCount：$i")
      var map =   HashMap<String, Any?>()
      map["onSupportStockCount"] = i
      sendToMainUI(getStockChangeSink, map)
    }

    override fun onUpdateStockChange() {
      // Update all stock information to your watch
    }
  }

  private val mQuickResponsesChangeListener: CRPQuickResponsesChangeListener =
    object : CRPQuickResponsesChangeListener {
      override fun onQuickResponsesCount(info: CRPQuickResponsesCountInfo) {
        Log.d(TAG,"onQuickResponsesCount：$info")
        var map =   HashMap<String, Any?>()
        val jsonResult = gson.toJson(info)
        map["onQuickResponsesCount"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
        sendToMainUI(getQuickResponsesSink, map)
      }

      override fun onQuickResponsesDetail(info: CRPQuickResponsesDetailInfo) {
        Log.d(TAG,"onQuickResponsesDetail：$info")
        var map =   HashMap<String, Any?>()
        val jsonResult = gson.toJson(info)
        map["onQuickResponsesDetail"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
        sendToMainUI(getQuickResponsesSink, map)
      }

      override fun onSendSms(msg: String) {
        Log.d(TAG,"onSendSms：$msg")
        var map =   HashMap<String, Any?>()
        map["onSendSms"] = msg
        sendToMainUI(getQuickResponsesSink, map)
      }
    }

  private val mBreathRateChangeListener: CRPBreathRateChangeListener =
    object : CRPBreathRateChangeListener {
      override fun onBreathRate(breathRate: Int) {
        Log.d(TAG,"onBreathRate：$breathRate")
        var map =   HashMap<String, Any?>()
        map["onBreathRate"] = breathRate
        sendToMainUI(getBreathRateSink, map)
      }

      override fun onHistoryBreathRate(list: List<CRPHistoryBreathRateInfo>) {
        Log.d(TAG,"onHistoryBreathRate：$list")
        var map =   HashMap<String, Any?>()
        val jsonResult = gson.toJson(list)
        map["onHistoryBreathRate"] = gson.toJson(gson.fromJson(jsonResult, List::class.java))
        sendToMainUI(getBreathRateSink, map)
      }

      override fun onTimingBreathRateState(enable: Boolean) {
        Log.d(TAG,"onTimingBreathRateState：$enable")
        var map =   HashMap<String, Any?>()
        map["onTimingBreathRateState"] = enable
        sendToMainUI(getBreathRateSink, map)
      }

      override fun onTimingBreathRate(info: CRPBreathRateInfo) {
        Log.d(TAG,"onTimingBreathRate：$info")
        var map =   HashMap<String, Any?>()
        val jsonResult = gson.toJson(info)
        map["onTimingBreathRate"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
        sendToMainUI(getBreathRateSink, map)
      }
    }


  private val mHrvChangeListener: CRPHrvChangeListener = object : CRPHrvChangeListener {
    override fun onRealRri(rri: Int, activeLevel: Int) {
      Log.d(TAG, "onRealRri rri: $rri")
      Log.d(
        TAG,
        "onRealRri activeLevel: $activeLevel"
      )
      var map =   HashMap<String, Any?>()
      map["onRealRri"] = rri
      map["onRealRriActiveLevel"] = activeLevel
      sendToMainUI(getHrvSink, map)
    }

    override fun onMeasureInterval(interval: Int) {
      Log.d(
        TAG,
        "onMeasureInterval: $interval"
      )
      var map =   HashMap<String, Any?>()
      map["onMeasureInterval"] = interval
      sendToMainUI(getHrvSink, map)
    }

    override fun onMeasureCount(dayIndex: Int, count: Int) {
      Log.d(
        TAG,
        "onMeasureCount: $dayIndex-$count"
      )
      var map =   HashMap<String, Any?>()
      map["onMeasureCountDayIndex"] = dayIndex
      map["onMeasureCountCount"] = count
      sendToMainUI(getHrvSink, map)
    }

    override fun onHrvChange(info: CRPHrvInfo) {
      Log.d(
        TAG,
        "onHrvChange: " + info.rriList.toString()
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onHrvChange"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getHrvSink, map)
    }

    override fun onMeasureResult(dayIndex: Int, index: Int, info: CRPHrvInfo) {
      Log.d(
        TAG,
        "onMeasureResult: $dayIndex-$index"
      )
      Log.d(
        TAG,
        "onMeasureResult: " + info.rriList.toString()
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onMeasureResultDayIndex"] = dayIndex
      map["onMeasureResultIndex"] = index
      map["onMeasureResultInfo"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getHrvSink, map)
    }
  }

  private var mQuickContactConfigInfo: CRPContactConfigInfo? = null

  private val mContactListener: CRPContactListener = object : CRPContactListener {
    override fun onSavedSuccess(id: Int) {
      Log.d(TAG, "onSavedSuccess: $id")
      var map =   HashMap<String, Any?>()
      map["onSavedSuccess"] = id
      sendToMainUI(getContactSink, map)
    }

    override fun onSavedFail(id: Int) {
      Log.d(TAG, "onSavedFail: $id")
      var map =   HashMap<String, Any?>()
      map["onSavedFail"] = id
      sendToMainUI(getContactSink, map)
    }
  }


  private var mTempChangeListener: CRPTempChangeListener = object : CRPTempChangeListener {
    override fun onContinueState(state: Boolean) {
      Log.d(
        TAG,
        "onContinueState: $state"
      )
      var map =   HashMap<String, Any?>()
      map["onContinueState"] = state
      sendToMainUI(getTempSink, map)
    }

    override fun onMeasureTemp(temp: Float) {
      Log.d(TAG, "onMeasureTemp: $temp")
      var map =   HashMap<String, Any?>()
      map["onMeasureTemp"] = temp
      sendToMainUI(getTempSink, map)
    }

    override fun onMeasureState(state: Boolean) {
      Log.d(TAG, "onTimingState: $state")
      var map =   HashMap<String, Any?>()
      map["onTimingState"] = state
      sendToMainUI(getTempSink, map)
    }

    override fun onContinueTemp(info: CRPTempInfo) {
      Log.d(TAG, "onTimingMeasureTemp: " + info.type)
      Log.d(
        TAG,
        "onTimingMeasureTemp: " + info.tempList.toString()
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onContinueTemp"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getTempSink, map)
    }
  }

  var mMovementStateListener: CRPMovementStateListener = object : CRPMovementStateListener {
    override fun onMeasureState(state: Int) {
      Log.d(
        TAG,
        "onMeasureState: $state"
      )
      var map =   HashMap<String, Any?>()
      map["onMeasureState"] = state
      sendToMainUI(getMovementStateSink, map)
    }

    override fun onMeasuring(i: Int) {
      Log.d(TAG,"onMeasuring:$i")
      var map =   HashMap<String, Any?>()
      map["onMeasuring"] = i
      sendToMainUI(getMovementStateSink, map)
    }

    override fun onStartSuccess(i: Int) {
      Log.d(TAG,"onStartSuccess:$i")
      var map =   HashMap<String, Any?>()
      map["onStartSuccess"] = i
      sendToMainUI(getMovementStateSink, map)
    }

    override fun onStartFailed() {
      Log.d(TAG,"onStartFailed")
      var map =   HashMap<String, Any?>()
      map["onStartFailed"] = true
      sendToMainUI(getMovementStateSink, map)
    }
  }

  private val trainingList: MutableList<Int> = ArrayList()

  var mTrainingChangeListener: CRPTrainingChangeListener = object : CRPTrainingChangeListener {
    override fun onHistoryTrainingChange(list: List<CRPHistoryTrainingInfo>) {
      trainingList.clear()
      for (i in list.indices) {
        val info = list[i]
        trainingList.add(info.id)
      }

      if (!trainingList.isEmpty()) {
        queryTrainingDetail()
      }
    }

    override fun onTrainingChange(info: CRPTrainingInfo) {
      Log.d(
        TAG,
        "onTrainingChange: $info"
      )

      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onTrainingChange"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getMovementStateSink, map)
      queryTrainingDetail()
    }

    override fun onRealtimeTrainingChange(crpTrainingRealtimeInfo: CRPTrainingRealtimeInfo) {
    }
  }

  private fun queryTrainingDetail() {
    if (!trainingList.isEmpty()) {
      val trainingId = trainingList.removeAt(0)
      mBleConnection!!.queryTraining(trainingId)
    }
  }


  var mSleepActionChangeListener: CRPSleepActionChangeListener =
    CRPSleepActionChangeListener { info ->
//      val hour = info.hour
//      Log.d(
//        TAG,
//        "onSleepActionChange Hour: $hour"
//      )
//      val actionList = info.actionList
//      for (integer in actionList) {
//        Log.d(
//          TAG,
//          "onSleepActionChange action: $integer"
//        )
//      }
      Log.d(
        TAG,
        "onSleepActionChange: $info"
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onSleepActionChange"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getSleepActionSink, map)
    }


  var mStepsCategoryChangeListener: CRPStepsCategoryChangeListener =
    CRPStepsCategoryChangeListener { info ->
      val stepsList = info.stepsList
      Log.d(
        TAG,
        "onStepsCategoryChange: $stepsList"
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onStepsCategoryChange"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getStepsCategorySink, map)
    }

  var mStepChangeListener: CRPStepChangeListener = object : CRPStepChangeListener {
    override fun onStepChange(info: CRPStepInfo) {
      Log.d(TAG, "onStepChange: $info")
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onStepChange"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getStepChangeSink, map)
    }

    override fun onHistoryStepChange(historyDay: CRPHistoryDay, info: CRPStepInfo) {
      Log.d(
        TAG,
        "onPastStepChange: " + historyDay.value + " - " + info.toString()
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onPastStepChange"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getStepChangeSink, map)
    }
  }

  var mSleepChangeListener: CRPSleepChangeListener = object : CRPSleepChangeListener {
    override fun onSleepChange(info: CRPSleepInfo) {
      val details = info.details ?: return
      Log.d(TAG, "onSleepChange: $info")
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onSleepChange"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getSleepChangeSink, map)
    }

    override fun onHistorySleepChange(historyDay: CRPHistoryDay, info: CRPSleepInfo) {
      Log.d(
        TAG,
        "onHistorySleepChange: $historyDay - $info"
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["onHistorySleepChange"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getSleepChangeSink, map)
    }

    override fun onHistoryNapSleepChange(historyDay: CRPHistoryDay, list: List<CRPNapSleepInfo>) {
      Log.d(
        TAG,
        "onHistoryNapSleepChange: " + historyDay + " - " + list.size
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(list)
      map["onHistoryNapSleepChange"] = gson.toJson(gson.fromJson(jsonResult, List::class.java))
      sendToMainUI(getSleepChangeSink, map)
    }
  }

  var mHeartRateChangListener: CRPHeartRateChangeListener = object : CRPHeartRateChangeListener {
    override fun onMeasuring(rate: Int) {
      Log.d(TAG, "onMeasuring: $rate")
      var map =   HashMap<String, Any?>()
      map["onMeasuring"] = rate
      sendToMainUI(getHeartRateSink, map)
    }

    override fun onOnceMeasureComplete(rate: Int) {
      Log.d(
        TAG,
        "onOnceMeasureComplete: $rate"
      )
      var map =   HashMap<String, Any?>()
      map["onOnceMeasureComplete"] = rate
      sendToMainUI(getHeartRateSink, map)
    }

    override fun onHistoryHeartRate(list: List<CRPHistoryHeartRateInfo>) {
      for (info in list) {
        Log.d(TAG, "onHistoryHeartRate: " + info.date)
        Log.d(TAG, "onHistoryHeartRate: " + info.hr)
      }
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(list)
      map["onHistoryHeartRate"] = gson.toJson(gson.fromJson(jsonResult, List::class.java))
      sendToMainUI(getHeartRateSink, map)
    }

    override fun onMeasureComplete(type: CRPHistoryDynamicRateType, info: CRPHeartRateInfo) {
      Log.d(
        TAG,
        "onMeasureComplete type: " + type.value
      )
      Log.d(
        TAG,
        "onMeasureComplete hr list: " + info.heartRateList.toString()
      )

      mBleConnection!!.queryMovementHeartRate()
      var map =   HashMap<String, Any?>()
      val jsonResultType = gson.toJson(type)
      val jasonResultInfo = gson.toJson(info)
      map["onMeasureCompleteType"] = gson.toJson(gson.fromJson(jsonResultType, HashMap::class.java))
      map["onMeasureCompleteInfo"] = gson.toJson(gson.fromJson(jasonResultInfo, HashMap::class.java))
      sendToMainUI(getHeartRateSink, map)
    }

    override fun on24HourMeasureResult(info: CRPHeartRateInfo) {
      Log.d(
        TAG,
        "on24HourMeasureResult: $info"
      )
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(info)
      map["on24HourMeasureResult"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
      sendToMainUI(getHeartRateSink, map)
    }

    override fun onMovementMeasureResult(list: List<CRPMovementHeartRateInfo>) {
      for (info in list) {
        if (info != null) {
          Log.d(
            TAG,
            "onMovementMeasureResult: " + info.startTime
          )
        }
      }
      var map =   HashMap<String, Any?>()
      val jsonResult = gson.toJson(list)
      map["onMovementMeasureResult"] = gson.toJson(gson.fromJson(jsonResult, List::class.java))
      sendToMainUI(getHeartRateSink, map)
    }
  }

  var mBloodPressureChangeListener: CRPBloodPressureChangeListener =
    object : CRPBloodPressureChangeListener {
      override fun onContinueState(state: Boolean) {
        Log.d(
          TAG,
          "onContinueState: $state"
        )
        var map =   HashMap<String, Any?>()
        map["onContinueState"] = state
        sendToMainUI(getBloodPressureSink, map)
      }

      override fun onBloodPressureChange(sbp: Int, dbp: Int) {
        Log.d(TAG, "sbp: $sbp,dbp: $dbp")
        var map =   HashMap<String, Any?>()
        map["onBloodPressureChangeSBP"] = sbp
        map["onBloodPressureChangeDBP"] = dbp
        sendToMainUI(getBloodPressureSink, map)
      }

      override fun onHistoryBloodPressure(list: List<CRPHistoryBloodPressureInfo>) {
        for (info in list) {
          Log.d(
            TAG,
            "onHistoryBloodPressure: " + info.date
          )
          Log.d(
            TAG,
            "onHistoryBloodPressure: " + info.sbp
          )
          Log.d(
            TAG,
            "onHistoryBloodPressure: " + info.dbp
          )
        }
        var map =   HashMap<String, Any?>()
        val jsonResult = gson.toJson(list)
        map["onHistoryBloodPressure"] = gson.toJson(gson.fromJson(jsonResult, List::class.java))
        sendToMainUI(getBloodPressureSink, map)
      }

      override fun onContinueBloodPressure(info: CRPBloodPressureInfo) {
        if (info != null) {
          Log.d(
            TAG,
            "onContinueBloodPressure: " + info.type
          )
          var emptCount = 0
          for (bean in info.list) {
            if (bean == null) {
              emptCount++
            }
          }
          Log.d(
            TAG,
            "onContinueBloodPressure size: $emptCount"
          )
        }
        var map =   HashMap<String, Any?>()
        val jsonResult = gson.toJson(info)
        map["onContinueBloodPressure"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
        sendToMainUI(getBloodPressureSink, map)
      }
    }

  var mBloodOxygenChangeListener: CRPBloodOxygenChangeListener =
    object : CRPBloodOxygenChangeListener {
      override fun onContinueState(state: Boolean) {
        Log.d(
          TAG,
          "onContinueState: $state"
        )
        var map =   HashMap<String, Any?>()
        map["onContinueState"] = state
        sendToMainUI(getBloodOxygenSink, map)
      }

      override fun onTimingMeasure(interval: Int) {
        Log.d(
          TAG,
          "onTimingMeasure: $interval"
        )
        var map =   HashMap<String, Any?>()
        map["onTimingMeasure"] = interval
        sendToMainUI(getBloodOxygenSink, map)
      }

      override fun onBloodOxygen(bloodOxygen: Int) {
        var map =   HashMap<String, Any?>()
        map["onBloodOxygen"] = bloodOxygen
        sendToMainUI(getBloodOxygenSink, map)
      }

      override fun onHistoryBloodOxygen(list: List<CRPHistoryBloodOxygenInfo>) {
        for (info in list) {
          Log.d(
            TAG,
            "onHistoryBloodOxygen: " + info.date
          )
          Log.d(TAG, "onHistoryBloodOxygen: " + info.bo)
        }
        var map =   HashMap<String, Any?>()
        val jsonResult = gson.toJson(list)
        map["onHistoryBloodOxygen"] = gson.toJson(gson.fromJson(jsonResult, List::class.java))
        sendToMainUI(getBloodOxygenSink, map)
      }

      override fun onContinueBloodOxygen(info: CRPBloodOxygenInfo) {
        if (info != null) {
          Log.d(
            TAG,
            "onContinueBloodOxygen: " + info.type
          )
          Log.d(
            TAG,
            "onContinueBloodOxygen: " + info.list.toString()
          )
          var map =   HashMap<String, Any?>()
          val jsonResult = gson.toJson(info)
          map["onContinueBloodOxygen"] = gson.toJson(gson.fromJson(jsonResult, HashMap::class.java))
          sendToMainUI(getBloodOxygenSink, map)
        }
      }
    }

  var mECGChangeListener: CRPBleECGChangeListener = object : CRPBleECGChangeListener {
    override fun onECGChange(ecg: IntArray) {
      for (i in ecg.indices) {
        Log.d(TAG, "ecg: " + ecg[i])
      }
      var map =   HashMap<String, Any?>()
      map["onECGChange"] = ecg
      sendToMainUI(getECGSink, map)
    }

    override fun onMeasureComplete() {
      Log.d(TAG, "onMeasureComplete")
      var map =   HashMap<String, Any?>()
      map["onMeasureComplete"] = true
      sendToMainUI(getECGSink, map)
    }

    override fun onTransCpmplete(date: Date) {
      Log.d(TAG, "onTransCpmplete")
      var map =   HashMap<String, Any?>()
      map["onTransCpmplete"] = date
      sendToMainUI(getECGSink, map)
    }

    override fun onCancel() {
      Log.d(TAG, "onCancel")
      var map =   HashMap<String, Any?>()
      map["onCancel"] = true
      sendToMainUI(getECGSink, map)
    }

    override fun onFail() {
      Log.d(TAG, "onFail")
      var map =   HashMap<String, Any?>()
      map["onFail"] = true
      sendToMainUI(getECGSink, map)
    }
  }


  private fun queryLastMeasureECGData() {
    mBleConnection!!.queryLastMeasureECGData()
  }


  var mFindPhoneListener: CRPFindPhoneListener = object : CRPFindPhoneListener {
    override fun onFindPhone() {
      Log.d(TAG, "onFindPhone")
      var map =   HashMap<String, Any?>()
      map["onFindPhone"] = true
      sendToMainUI(getFindPhoneSink, map)
    }

    override fun onFindPhoneComplete() {
      Log.d(TAG, "onFindPhoneComplete")
      var map =   HashMap<String, Any?>()
      map["onFindPhoneComplete"] = true
      sendToMainUI(getFindPhoneSink, map)
    }
  }

  var mFirmwareUpgradeListener: CRPBleFirmwareUpgradeListener =
    object : CRPBleFirmwareUpgradeListener {
      override fun onFirmwareDownloadStarting() {
        Log.d(TAG, "onFirmwareDownloadStarting")
      }

      override fun onFirmwareDownloadComplete() {
        Log.d(TAG, "onFirmwareDownloadComplete")
      }

      override fun onUpgradeProgressStarting(recoverable: Boolean) {
        Log.d(
          TAG,
          "onUpgradeProgressStarting: $recoverable"
        )
      }

      override fun onUpgradeProgressChanged(percent: Int, speed: Float) {
        Log.d(
          TAG,
          "onUpgradeProgressChanged: $percent"
        )
      }

      override fun onUpgradeCompleted() {
        Log.d(TAG, "onUpgradeCompleted")
      }

      override fun onUpgradeAborted() {
        Log.d(TAG, "onUpgradeAborted")
      }

      override fun onError(errorType: Int, message: String) {
        Log.d(TAG, "onError: $errorType")
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
