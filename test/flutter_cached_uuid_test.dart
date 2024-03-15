import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_cached_uuid/flutter_cached_uuid.dart';
import 'package:flutter_cached_uuid/flutter_cached_uuid_platform_interface.dart';
import 'package:flutter_cached_uuid/flutter_cached_uuid_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterCachedUuidPlatform
    with MockPlatformInterfaceMixin
    implements FlutterCachedUuidPlatform {
  @override
  Future<String?> getUUID() => Future.value('42');
}

void main() {
  final FlutterCachedUuidPlatform initialPlatform =
      FlutterCachedUuidPlatform.instance;

  test('$MethodChannelFlutterCachedUuid is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterCachedUuid>());
  });

  test('getPlatformVersion', () async {
    FlutterCachedUuid flutterCachedUuidPlugin = FlutterCachedUuid();
    MockFlutterCachedUuidPlatform fakePlatform =
        MockFlutterCachedUuidPlatform();
    FlutterCachedUuidPlatform.instance = fakePlatform;

    expect(await flutterCachedUuidPlugin.getUUID(), '42');
  });
}
