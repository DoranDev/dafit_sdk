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
}
