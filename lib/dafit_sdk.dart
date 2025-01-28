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

  /// Checks if a device is connected.
  Future<bool?> isConnected() async {
    final res = await _methodChannel.invokeMethod('isConnected');
    return res;
  }

  /// Creates a bond with the device.
  Future<bool?> createBond() async {
    final res = await _methodChannel.invokeMethod('createBond');
    return res;
  }

  /// Finds the band device.
  Future<void> findBand() async {
    await _methodChannel.invokeMethod('find_band');
  }

  Future<void> doNotDistrub({required bool isDoNotDistrub}) async {
    await _methodChannel.invokeMethod(
        'sendDoNotDistrubTime', {'isDoNotDistrub': isDoNotDistrub});
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

  /// Queries the last ECG measurement data.
  Future<void> queryLastEcg() async {
    await _methodChannel.invokeMethod('query_last_ecg');
  }

  /// Queries today's steps category.
  Future<void> queryTodayStepsCategory() async {
    await _methodChannel.invokeMethod('query_today_steps_category');
  }

  /// Queries yesterday's steps category.
  Future<void> queryYesterdayStepsCategory() async {
    await _methodChannel.invokeMethod('query_yesterday_steps_category');
  }

  /// Queries supported watch faces.
  Future<void> querySupportWatchFace() async {
    await _methodChannel.invokeMethod('query_support_watch_face');
  }

  /// Queries watch face store information.
  Future<void> queryWatchFaceStore() async {
    await _methodChannel.invokeMethod('query_watch_face_store');
  }

  /// Queries watch face store tags.
  Future<void> queryWatchFaceStoreTag() async {
    await _methodChannel.invokeMethod('query_watch_face_store_tag');
  }

  /// Queries watch face store list.
  Future<void> queryWatchFaceStoreList() async {
    await _methodChannel.invokeMethod('query_watch_face_store_list');
  }

  /// Queries watch face details.
  Future<void> queryWatchFaceDetail() async {
    await _methodChannel.invokeMethod('query_watch_face_detail');
  }

  /// Queries watch face information.
  Future<void> queryWatchFace() async {
    await _methodChannel.invokeMethod('query_watch_face');
  }

  /// Queries sleep action data.
  Future<void> querySleepAction() async {
    await _methodChannel.invokeMethod('query_sleep_action');
  }

  /// Sends band UI information.
  Future<void> sendBandUi() async {
    await _methodChannel.invokeMethod('send_band_ui');
  }

  /// Queries quick contacts configuration.
  Future<void> queryQuickContacts() async {
    await _methodChannel.invokeMethod('query_quick_contacts');
  }

  /// Enables HRV (Heart Rate Variability) measurement.
  Future<void> enableHrv() async {
    await _methodChannel.invokeMethod('enable_hrv');
  }

  /// Disables HRV (Heart Rate Variability) measurement.
  Future<void> disableHrv() async {
    await _methodChannel.invokeMethod('disable_hrv');
  }

  /// Queries HRV measurement interval.
  Future<void> queryHrvMeasureInterval() async {
    await _methodChannel.invokeMethod('query_hrv_measure_interval');
  }

  /// Queries HRV measurement count.
  Future<void> queryHrvMeasureCount() async {
    await _methodChannel.invokeMethod('query_hrv_measure_count');
  }

  /// Queries HRV measurement result.
  Future<void> queryHrvMeasureResult() async {
    await _methodChannel.invokeMethod('query_hrv_measure_result');
  }

  /// Enables continuous blood pressure monitoring.
  Future<void> enableContinueBloodPressure() async {
    await _methodChannel.invokeMethod('enable_continue_blood_pressure');
  }

  /// Disables continuous blood pressure monitoring.
  Future<void> disableContinueBloodPressure() async {
    await _methodChannel.invokeMethod('disable_continue_blood_pressure');
  }

  /// Queries continuous blood pressure state.
  Future<void> queryContinueBloodPressureState() async {
    await _methodChannel.invokeMethod('query_continue_blood_pressure_state');
  }

  /// Queries last 24-hour blood pressure data.
  Future<void> queryLast24HourBloodPressure() async {
    await _methodChannel.invokeMethod('query_continue_blood_pressure');
  }

  /// Enables continuous blood oxygen monitoring.
  Future<void> enableContinueBloodOxygen() async {
    await _methodChannel.invokeMethod('enable_continue_blood_oxygen');
  }

  /// Disables continuous blood oxygen monitoring.
  Future<void> disableContinueBloodOxygen() async {
    await _methodChannel.invokeMethod('disable_continue_blood_oxygen');
  }

  /// Queries continuous blood oxygen state.
  Future<void> queryContinueBloodOxygenState() async {
    await _methodChannel.invokeMethod('query_continue_blood_oxygen_state');
  }

  /// Queries last 24-hour blood oxygen data.
  Future<void> queryLast24HourBloodOxygen() async {
    await _methodChannel.invokeMethod('query_continue_blood_oxygen');
  }

  /// Enables continuous temperature monitoring.
  Future<void> enableContinueTemp() async {
    await _methodChannel.invokeMethod('enable_continue_temp');
  }

  /// Disables continuous temperature monitoring.
  Future<void> disableContinueTemp() async {
    await _methodChannel.invokeMethod('disable_continue_temp');
  }

  /// Queries continuous temperature state.
  Future<void> queryContinueTempState() async {
    await _methodChannel.invokeMethod('query_continue_temp_state');
  }

  /// Queries last 24-hour temperature data.
  Future<void> queryLast24HourTemp() async {
    await _methodChannel.invokeMethod('query_continue_temp');
  }

  /// Starts breath rate measurement.
  Future<void> startMeasureBreathRate() async {
    await _methodChannel.invokeMethod('start_measure_breath_rate');
  }

  /// Stops breath rate measurement.
  Future<void> stopMeasureBreathRate() async {
    await _methodChannel.invokeMethod('stop_measure_breath_rate');
  }

  /// Queries historical breath rate data.
  Future<void> queryHistoryBreathRate() async {
    await _methodChannel.invokeMethod('query_history_breath_rate');
  }

  /// Enables timing breath rate measurement.
  Future<void> enableTimingBreathRate() async {
    await _methodChannel.invokeMethod('enable_timing_breath_rate');
  }

  /// Disables timing breath rate measurement.
  Future<void> disableTimingBreathRate() async {
    await _methodChannel.invokeMethod('disable_timing_breath_rate');
  }

  /// Queries timing breath rate data.
  Future<void> queryTimingBreathRate() async {
    await _methodChannel.invokeMethod('query_timing_breath_rate');
  }

  /// Queries timing breath rate state.
  Future<void> queryTimingBreathRateState() async {
    await _methodChannel.invokeMethod('query_timing_breath_rate_state');
  }

  /// Queries quick responses count.
  Future<void> queryQuickResponsesCount() async {
    await _methodChannel.invokeMethod('query_quick_responses_count');
  }

  /// Enables quick responses.
  Future<void> enableQuickResponses() async {
    await _methodChannel.invokeMethod('enable_quick_responses');
  }

  /// Disables quick responses.
  Future<void> disableQuickResponses() async {
    await _methodChannel.invokeMethod('disable_quick_responses');
  }

  /// Queries quick responses.
  Future<void> queryQuickResponses() async {
    await _methodChannel.invokeMethod('query_quick_responses');
  }

  /// Sends quick responses.
  Future<void> sendQuickResponses(String message) async {
    await _methodChannel
        .invokeMethod('send_quick_responses', {'message': message});
  }

  /// Queries support for stock information.
  Future<void> querySupportStock() async {
    await _methodChannel.invokeMethod('query_support_stock');
  }

  /// Sends stock information.
  Future<void> sendStock(String abbreviation, String code, String currency,
      String exchange) async {
    await _methodChannel.invokeMethod('send_stock', {
      'abbreviation': abbreviation,
      'code': code,
      'currency': currency,
      'exchange': exchange,
    });
  }

  /// Sends stock price information.
  Future<void> sendStockPrice(
      int id, double price, double change, double changePercent) async {
    await _methodChannel.invokeMethod('send_stock_price', {
      'id': id,
      'price': price,
      'change': change,
      'changePercent': changePercent,
    });
  }

  /// Checks firmware version.
  Future<void> checkFirmware() async {
    await _methodChannel.invokeMethod('check_firmware');
  }

  /// Upgrades firmware.
  Future<void> firmwareUpgrade() async {
    await _methodChannel.invokeMethod('firmware_upgrade');
  }

  /// Queries alarm clock settings.
  Future<void> queryAlarmClock() async {
    await _methodChannel.invokeMethod('query_alarm_clock');
  }

  /// Sends alarm clock settings.
  Future<void> sendAlarmClock(int hour, int minute, int repeatMode,
      DateTime date, bool isEnable) async {
    await _methodChannel.invokeMethod('send_alarm_clock', {
      'hour': hour,
      'minute': minute,
      'repeatMode': repeatMode,
      'date': date.millisecondsSinceEpoch,
      'isEnable': isEnable,
    });
  }

  /// Sends quick view time settings.
  Future<void> sendQuickViewTime(
      int startHour, int startMinute, int endHour, int endMinute) async {
    await _methodChannel.invokeMethod('send_quickview_time', {
      'startHour': startHour,
      'startMinute': startMinute,
      'endHour': endHour,
      'endMinute': endMinute,
    });
  }

  /// Queries quick view time settings.
  Future<void> queryQuickViewTime() async {
    await _methodChannel.invokeMethod('query_quickview_time');
  }

  /// Sends device language settings.
  Future<void> sendDeviceLanguage(String language) async {
    await _methodChannel
        .invokeMethod('send_device_language', {'language': language});
  }

  /// Queries device language settings.
  Future<void> queryDeviceLanguage() async {
    await _methodChannel.invokeMethod('query_device_language');
  }

  /// Sends other message settings.
  Future<void> sendOtherMessage() async {
    await _methodChannel.invokeMethod('send_other_message');
  }

  /// Queries other message settings.
  Future<void> queryOtherMessage() async {
    await _methodChannel.invokeMethod('query_other_message');
  }

  /// Sends sedentary reminder settings.
  Future<void> sendSedentaryReminder(bool enable) async {
    await _methodChannel
        .invokeMethod('send_sedentary_reminder', {'enable': enable});
  }

  /// Queries sedentary reminder settings.
  Future<void> querySedentaryReminder() async {
    await _methodChannel.invokeMethod('query_sedentary_reminder');
  }

  /// Sends display watch face settings.
  Future<void> sendDisplayWatchFace(int type) async {
    await _methodChannel
        .invokeMethod('send_display_watch_face', {'type': type});
  }

  /// Queries display watch face settings.
  Future<void> queryDisplayWatchFace() async {
    await _methodChannel.invokeMethod('query_display_watch_face');
  }

  /// Synchronizes last heart rate data.
  Future<void> syncLastHeartRate() async {
    await _methodChannel.invokeMethod('sync_last_heart_rate');
  }

  /// Queries today's heart rate data.
  Future<void> queryTodayHeartRate() async {
    await _methodChannel.invokeMethod('query_today_hreat_rate');
  }

  /// Queries yesterday's heart rate data.
  Future<void> queryYesterdayHeartRate() async {
    await _methodChannel.invokeMethod('query_yesterday_hreat_rate');
  }

  /// Queries movement heart rate data.
  Future<void> queryMovementHeartRate() async {
    await _methodChannel.invokeMethod('query_movement_hreat_rate');
  }

  /// Sends device version information.
  Future<void> sendDeviceVersion(String version) async {
    await _methodChannel
        .invokeMethod('send_device_version', {'version': version});
  }

  /// Queries device version information.
  Future<void> queryDeviceVersion() async {
    await _methodChannel.invokeMethod('query_device_version');
  }

  /// Sends breathing light settings.
  Future<void> sendBreathingLight(bool enable) async {
    await _methodChannel
        .invokeMethod('send_breathing_light', {'enable': enable});
  }

  /// Queries breathing light settings.
  Future<void> queryBreathingLight() async {
    await _methodChannel.invokeMethod('query_breathing_light');
  }

  /// Sends watch face layout settings.
  Future<void> sendWatchFaceLayout(int timePosition, int timeTopContent,
      int timeBottomContent, int textColor, String backgroundPictureMd5) async {
    await _methodChannel.invokeMethod('send_watch_face_layout', {
      'timePosition': timePosition,
      'timeTopContent': timeTopContent,
      'timeBottomContent': timeBottomContent,
      'textColor': textColor,
      'backgroundPictureMd5': backgroundPictureMd5,
    });
  }

  /// Queries watch face layout settings.
  Future<void> queryWatchFaceLayout() async {
    await _methodChannel.invokeMethod('query_watch_face_layout');
  }

  /// Deletes stock information.
  Future<void> deleteStock(int id) async {
    await _methodChannel.invokeMethod('delete_stock', {'id': id});
  }

  /// Clears all stock information.
  Future<void> clearStock() async {
    await _methodChannel.invokeMethod('clear_stock');
  }

  /// Resets stock list.
  Future<void> resetStockList(List<int> stockArray) async {
    await _methodChannel
        .invokeMethod('reset_stock_list', {'stockArray': stockArray});
  }

  // Connection state stream
  final EventChannel _connectionStateChannel =
      const EventChannel('dafitConnectionState');
  Stream get connectionStateStream => _connectionStateChannel
      .receiveBroadcastStream(_connectionStateChannel.name)
      .cast();

  // Device data received stream
  final EventChannel _deviceDataReceivedChannel =
      const EventChannel('deviceDataReceived');
  Stream get deviceDataReceivedStream => _deviceDataReceivedChannel
      .receiveBroadcastStream(_deviceDataReceivedChannel.name)
      .cast();

  // Battery level stream
  final EventChannel _getBatteryLevelChannel =
      const EventChannel('dafitGetBatteryLevel');
  Stream get batteryLevelStream => _getBatteryLevelChannel
      .receiveBroadcastStream(_getBatteryLevelChannel.name)
      .cast();

  // Stock change stream
  final EventChannel _getStockChangeChannel =
      const EventChannel('dafitGetStockChange');
  Stream get stockChangeStream => _getStockChangeChannel
      .receiveBroadcastStream(_getStockChangeChannel.name)
      .cast();

  // Quick responses stream
  final EventChannel _getQuickResponsesChannel =
      const EventChannel('dafitGetQuickResponses');
  Stream get quickResponsesStream => _getQuickResponsesChannel
      .receiveBroadcastStream(_getQuickResponsesChannel.name)
      .cast();

  // Breath rate stream
  final EventChannel _getBreathRateChannel =
      const EventChannel('dafitGetBreathRate');
  Stream get breathRateStream => _getBreathRateChannel
      .receiveBroadcastStream(_getBreathRateChannel.name)
      .cast();

  // HRV stream
  final EventChannel _getHrvChannel = const EventChannel('dafitGetHrv');
  Stream get hrvStream =>
      _getHrvChannel.receiveBroadcastStream(_getHrvChannel.name).cast();

  // Temperature stream
  final EventChannel _getTempChannel = const EventChannel('dafitGetTemp');
  Stream get tempStream =>
      _getTempChannel.receiveBroadcastStream(_getTempChannel.name).cast();

  // Movement state stream
  final EventChannel _getMovementStateChannel =
      const EventChannel('dafitGetMovementState');
  Stream get movementStateStream => _getMovementStateChannel
      .receiveBroadcastStream(_getMovementStateChannel.name)
      .cast();

  // Training stream
  final EventChannel _getTrainingChannel =
      const EventChannel('dafitGetTraining');
  Stream get trainingStream => _getTrainingChannel
      .receiveBroadcastStream(_getTrainingChannel.name)
      .cast();

  // Sleep action stream
  final EventChannel _getSleepActionChannel =
      const EventChannel('dafitGetSleepAction');
  Stream get sleepActionStream => _getSleepActionChannel
      .receiveBroadcastStream(_getSleepActionChannel.name)
      .cast();

  // Steps category stream
  final EventChannel _getStepsCategoryChannel =
      const EventChannel('dafitGetStepsCategory');
  Stream get stepsCategoryStream => _getStepsCategoryChannel
      .receiveBroadcastStream(_getStepsCategoryChannel.name)
      .cast();

  // Step change stream
  final EventChannel _getStepChangeChannel =
      const EventChannel('dafitGetStepChange');
  Stream get stepChangeStream => _getStepChangeChannel
      .receiveBroadcastStream(_getStepChangeChannel.name)
      .cast();

  // Sleep change stream
  final EventChannel _getSleepChangeChannel =
      const EventChannel('dafitGetSleepChange');
  Stream get sleepChangeStream => _getSleepChangeChannel
      .receiveBroadcastStream(_getSleepChangeChannel.name)
      .cast();

  // Heart rate stream
  final EventChannel _getHeartRateChannel =
      const EventChannel('dafitGetHeartRate');
  Stream get heartRateStream => _getHeartRateChannel
      .receiveBroadcastStream(_getHeartRateChannel.name)
      .cast();

  // Blood pressure stream
  final EventChannel _getBloodPressureChannel =
      const EventChannel('dafitGetBloodPressure');
  Stream get bloodPressureStream => _getBloodPressureChannel
      .receiveBroadcastStream(_getBloodPressureChannel.name)
      .cast();

  // Blood oxygen stream
  final EventChannel _getBloodOxygenChannel =
      const EventChannel('dafitGetBloodOxygen');
  Stream get bloodOxygenStream => _getBloodOxygenChannel
      .receiveBroadcastStream(_getBloodOxygenChannel.name)
      .cast();

  // ECG stream
  final EventChannel _getECGChannel = const EventChannel('dafitGetECG');
  Stream get ecgStream =>
      _getECGChannel.receiveBroadcastStream(_getECGChannel.name).cast();

  // Contacts stream
  final EventChannel _getContactChannel =
      const EventChannel('dafitGetContacts');
  Stream get contactsStream =>
      _getContactChannel.receiveBroadcastStream(_getContactChannel.name).cast();

  //FindPhones stream
  final EventChannel _findPhonesChannel =
      const EventChannel('dafitGetFindPhone');
  Stream get findPhonesStream =>
      _findPhonesChannel.receiveBroadcastStream(_findPhonesChannel.name).cast();
}
