package com.optima.plugin.repluginlib.base;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.optima.plugin.repluginlib.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wma
 * on 2020/9/1 0001
 */
public class BasePermissionActivity extends BaseActivity {
    List<String> needRequestPermissions = new ArrayList<>();
    int requestCode = 100;
    private boolean isNeedCheckPermission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNeedCheckPermission) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            checkPermissions(getApplicationContext(), permissions);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(needRequestPermissions.size()>0){
                    requestPermissions(needRequestPermissions.toArray(new String[0]), requestCode);
                }
            }
        }
    }

    private void checkPermissions(Context context, String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                needRequestPermissions.add(permissions[i]);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            Logger.d(TAG, "onRequestPermissionsResult: permission = " + permissions[i]);
        }

        for (int i = 0; i < grantResults.length; i++) {
            Logger.d(TAG, "onRequestPermissionsResult: grantResult = " + grantResults[i]);

        }

    }

    public void setNeedCheckPermission(boolean isNeedCheckPermission) {
        this.isNeedCheckPermission = isNeedCheckPermission;
    }
}
