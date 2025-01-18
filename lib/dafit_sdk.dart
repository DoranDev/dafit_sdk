import 'package:flutter/services.dart';

class DafitSdk {
  static final DafitSdk _instance = DafitSdk._internal();
  factory DafitSdk() => _instance;
  DafitSdk._internal();

  final _methodChannel = const MethodChannel('dafit_sdk');

  /// Connects to the device with the specified [macAddress].
  Future<void> connect({required String macAddress}) async {
    await _methodChannel.invokeMethod('connect', {'macAddress': macAddress});
  }

  /// Disconnects from the currently connected device.
  Future<void> disconnect() async {
    await _methodChannel.invokeMethod('disconnect');
  }

  /// Queries the physiological period information.
  Future<void> queryPhysiologicalPeriod() async {
    await _methodChannel.invokeMethod('query_physiologcal_period');
  }

  /// Sends physiological period information to the device.
  Future<void> sendPhysiologicalPeriod() async {
    await _methodChannel.invokeMethod('send_physiologcal_period');
  }

  /// Queries supported device functions.
  Future<void> queryDeviceSupportFunction() async {
    await _methodChannel.invokeMethod('query_device_support_function');
  }

  /// Sends device function settings.
  Future<void> sendDeviceFunction() async {
    await _methodChannel.invokeMethod('send_device_function');
  }

  /// Queries current device functions.
  Future<void> queryDeviceFunction() async {
    await _methodChannel.invokeMethod('query_device_function');
  }

  /// Queries firmware version.
  Future<void> queryFirmware() async {
    await _methodChannel.invokeMethod('query_firmware');
  }

  /// Switches the watch face background.
  Future<void> switchBackground(Uint8List bgBytes) async {
    await _methodChannel
        .invokeMethod('switch_background', {'bgBytes': bgBytes});
  }

  /// Queries device battery level.
  Future<void> queryBattery() async {
    await _methodChannel.invokeMethod('query_battery');
  }

  /// Synchronizes time with the device.
  Future<void> syncTime() async {
    await _methodChannel.invokeMethod('sync_time');
  }

  /// Sets up music control.
  Future<void> setupMusicControl() async {
    await _methodChannel.invokeMethod('music_control');
  }

  /// Sends user information to the device.
  Future<void> sendUserInfo() async {
    await _methodChannel.invokeMethod('send_user_info');
  }

  /// Synchronizes step data.
  Future<void> syncStep() async {
    await _methodChannel.invokeMethod('sync_step');
  }

  /// Synchronizes past step data.
  Future<void> syncPastStep() async {
    await _methodChannel.invokeMethod('sync_past_step');
  }

  /// Synchronizes sleep data.
  Future<void> syncSleep() async {
    await _methodChannel.invokeMethod('sync_sleep');
  }

  /// Synchronizes past sleep data.
  Future<void> syncPastSleep() async {
    await _methodChannel.invokeMethod('sync_past_sleep');
  }

  /// Sends quick contacts to the device.
  Future<void> sendQuickContacts(Uint8List avatarBytes) async {
    await _methodChannel
        .invokeMethod('send_quick_contacts', {'avatarBytes': avatarBytes});
  }

  /// Deletes quick contacts from the device.
  Future<void> deleteQuickContacts() async {
    await _methodChannel.invokeMethod('delete_quick_contacts');
  }

  /// Starts heart rate measurement.
  Future<void> startMeasureHeartRate() async {
    await _methodChannel.invokeMethod('start_measure_heart_rate');
  }

  /// Stops heart rate measurement.
  Future<void> stopMeasureHeartRate() async {
    await _methodChannel.invokeMethod('stop_measure_heart_rate');
  }

  /// Enables 24-hour heart rate monitoring.
  Future<void> enable24HourHeartRate() async {
    await _methodChannel.invokeMethod('open_24_hreat_rate');
  }

  /// Disables 24-hour heart rate monitoring.
  Future<void> disable24HourHeartRate() async {
    await _methodChannel.invokeMethod('close_24_hreat_rate');
  }

  /// Starts blood pressure measurement.
  Future<void> startMeasureBloodPressure() async {
    await _methodChannel.invokeMethod('start_measure_blood_pressure');
  }

  /// Stops blood pressure measurement.
  Future<void> stopMeasureBloodPressure() async {
    await _methodChannel.invokeMethod('stop_measure_blood_pressure');
  }

  /// Starts blood oxygen measurement.
  Future<void> startMeasureBloodOxygen() async {
    await _methodChannel.invokeMethod('start_measure_blood_oxygen');
  }

  /// Stops blood oxygen measurement.
  Future<void> stopMeasureBloodOxygen() async {
    await _methodChannel.invokeMethod('stop_measure_blood_oxygen');
  }

  /// Starts ECG measurement.
  Future<void> startMeasureEcg() async {
    await _methodChannel.invokeMethod('start_measure_ecg');
  }

  /// Stops ECG measurement.
  Future<void> stopMeasureEcg() async {
    await _methodChannel.invokeMethod('stop_measure_ecg');
  }

  /// Sends weather information for today.
  Future<void> sendTodayWeather() async {
    await _methodChannel.invokeMethod('send_today_weather');
  }

  /// Sends weather forecast information.
  Future<void> sendFutureWeather() async {
    await _methodChannel.invokeMethod('send_future_weather');
  }

  // Device data received stream
  final EventChannel _deviceDataReceivedChannel =
      const EventChannel('deviceDataReceived');
  Stream get deviceDataReceivedStream => _deviceDataReceivedChannel
      .receiveBroadcastStream(_deviceDataReceivedChannel.name)
      .cast();

  // Battery level stream
  final EventChannel _getBatteryLevelChannel =
      const EventChannel('getBatteryLevel');
  Stream get batteryLevelStream => _getBatteryLevelChannel
      .receiveBroadcastStream(_getBatteryLevelChannel.name)
      .cast();

  // Stock change stream
  final EventChannel _getStockChangeChannel =
      const EventChannel('getStockChange');
  Stream get stockChangeStream => _getStockChangeChannel
      .receiveBroadcastStream(_getStockChangeChannel.name)
      .cast();

  // Quick responses stream
  final EventChannel _getQuickResponsesChannel =
      const EventChannel('getQuickResponses');
  Stream get quickResponsesStream => _getQuickResponsesChannel
      .receiveBroadcastStream(_getQuickResponsesChannel.name)
      .cast();

  // Breath rate stream
  final EventChannel _getBreathRateChannel =
      const EventChannel('getBreathRate');
  Stream get breathRateStream => _getBreathRateChannel
      .receiveBroadcastStream(_getBreathRateChannel.name)
      .cast();

  // HRV stream
  final EventChannel _getHrvChannel = const EventChannel('getHrv');
  Stream get hrvStream =>
      _getHrvChannel.receiveBroadcastStream(_getHrvChannel.name).cast();

  // Temperature stream
  final EventChannel _getTempChannel = const EventChannel('getTemp');
  Stream get tempStream =>
      _getTempChannel.receiveBroadcastStream(_getTempChannel.name).cast();

  // Movement state stream
  final EventChannel _getMovementStateChannel =
      const EventChannel('getMovementState');
  Stream get movementStateStream => _getMovementStateChannel
      .receiveBroadcastStream(_getMovementStateChannel.name)
      .cast();

  // Training stream
  final EventChannel _getTrainingChannel = const EventChannel('getTraining');
  Stream get trainingStream => _getTrainingChannel
      .receiveBroadcastStream(_getTrainingChannel.name)
      .cast();

  // Sleep action stream
  final EventChannel _getSleepActionChannel =
      const EventChannel('getSleepAction');
  Stream get sleepActionStream => _getSleepActionChannel
      .receiveBroadcastStream(_getSleepActionChannel.name)
      .cast();

  // Steps category stream
  final EventChannel _getStepsCategoryChannel =
      const EventChannel('getStepsCategory');
  Stream get stepsCategoryStream => _getStepsCategoryChannel
      .receiveBroadcastStream(_getStepsCategoryChannel.name)
      .cast();

  // Step change stream
  final EventChannel _getStepChangeChannel =
      const EventChannel('getStepChange');
  Stream get stepChangeStream => _getStepChangeChannel
      .receiveBroadcastStream(_getStepChangeChannel.name)
      .cast();

  // Sleep change stream
  final EventChannel _getSleepChangeChannel =
      const EventChannel('getSleepChange');
  Stream get sleepChangeStream => _getSleepChangeChannel
      .receiveBroadcastStream(_getSleepChangeChannel.name)
      .cast();

  // Heart rate stream
  final EventChannel _getHeartRateChannel = const EventChannel('getHeartRate');
  Stream get heartRateStream => _getHeartRateChannel
      .receiveBroadcastStream(_getHeartRateChannel.name)
      .cast();

  // Blood pressure stream
  final EventChannel _getBloodPressureChannel =
      const EventChannel('getBloodPressure');
  Stream get bloodPressureStream => _getBloodPressureChannel
      .receiveBroadcastStream(_getBloodPressureChannel.name)
      .cast();

  // Blood oxygen stream
  final EventChannel _getBloodOxygenChannel =
      const EventChannel('getBloodOxygen');
  Stream get bloodOxygenStream => _getBloodOxygenChannel
      .receiveBroadcastStream(_getBloodOxygenChannel.name)
      .cast();

  // ECG stream
  final EventChannel _getECGChannel = const EventChannel('getECG');
  Stream get ecgStream =>
      _getECGChannel.receiveBroadcastStream(_getECGChannel.name).cast();
}
