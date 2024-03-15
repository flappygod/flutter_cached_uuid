import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_cached_uuid_platform_interface.dart';

/// An implementation of [FlutterCachedUuidPlatform] that uses method channels.
class MethodChannelFlutterCachedUuid extends FlutterCachedUuidPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_cached_uuid');

  @override
  Future<String?> getUUID() async {
    final version = await methodChannel.invokeMethod<String>('getUUID');
    return version;
  }
}
