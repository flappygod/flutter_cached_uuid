import 'flutter_cached_uuid_platform_interface.dart';

class FlutterCachedUuid {
  static Future<String?> getUUID() {
    return FlutterCachedUuidPlatform.instance.getUUID();
  }
}
