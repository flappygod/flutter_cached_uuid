package com.flappy.flutter_cached_uuid;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodCall;

/**
 * FlutterCachedUuidPlugin
 */
public class FlutterCachedUuidPlugin implements FlutterPlugin,
        MethodCallHandler,
        ActivityAware,
        PluginRegistry.ActivityResultListener,
        PluginRegistry.RequestPermissionsResultListener {
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;

    //activity
    private Activity activity;

    //binding
    private ActivityPluginBinding pluginBinding;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_cached_uuid");
        channel.setMethodCallHandler(this);
    }


    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
        activity = null;
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        pluginBinding = binding;
        activity = binding.getActivity();
        pluginBinding.addActivityResultListener(this);
        pluginBinding.addRequestPermissionsResultListener(this);
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        pluginBinding = binding;
        activity = binding.getActivity();
        pluginBinding.addActivityResultListener(this);
        pluginBinding.addRequestPermissionsResultListener(this);
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        pluginBinding.removeActivityResultListener(this);
        pluginBinding.removeRequestPermissionsResultListener(this);
    }

    @Override
    public void onDetachedFromActivity() {
        pluginBinding.removeActivityResultListener(this);
        pluginBinding.removeRequestPermissionsResultListener(this);
        pluginBinding = null;
        activity = null;
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getUUID")) {
            result.success(DeviceIdUtil.getUniqueID(activity));
        } else {
            result.notImplemented();
        }
    }

    @Override
    public boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == DeviceIdUtil.REQUEST_EXTERNAL_STORAGE) {
            DeviceIdUtil.refreshUUIDToDirectories(activity);
        }
        return false;
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        return false;
    }
}
