import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_cached_uuid_method_channel.dart';

abstract class FlutterCachedUuidPlatform extends PlatformInterface {
  /// Constructs a FlutterCachedUuidPlatform.
  FlutterCachedUuidPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterCachedUuidPlatform _instance = MethodChannelFlutterCachedUuid();

  /// The default instance of [FlutterCachedUuidPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterCachedUuid].
  static FlutterCachedUuidPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterCachedUuidPlatform] when
  /// they register themselves.
  static set instance(FlutterCachedUuidPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getUUID() {
    throw UnimplementedError('getUUID() has not been implemented.');
  }
}
