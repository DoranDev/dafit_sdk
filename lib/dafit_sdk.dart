import 'package:flutter/services.dart';

class DafitSdk {
  final methodChannel = const MethodChannel('dafit_sdk');
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
