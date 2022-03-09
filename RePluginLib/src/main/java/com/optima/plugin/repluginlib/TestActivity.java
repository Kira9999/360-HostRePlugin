package com.optima.plugin.repluginlib;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

import com.optima.plugin.repluginlib.base.BaseActivity;
import com.qihoo360.replugin.RePlugin;

/**
 * create by wma
 * on 2020/9/18 0018
 */
public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;//content://media/external/images/media
        ContentResolver contentResolver = TestActivity.this.getContentResolver();
        Cursor cursor = contentResolver.query(imageUri, null, null, null, null);
        Logger.d(TAG, "onClick: cursor = " + cursor);
    }
}
