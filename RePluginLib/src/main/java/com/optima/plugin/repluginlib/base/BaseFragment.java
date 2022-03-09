package com.optima.plugin.repluginlib.base;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.optima.plugin.repluginlib.Logger;
import com.optima.plugin.repluginlib.pluginUtils.P_Constants;
import com.optima.plugin.repluginlib.pluginUtils.P_Context;

/**
 * create by wma
 * on 2020/8/17 0017
 */
public class BaseFragment extends Fragment {
    public String TAG;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra(P_Constants.INTENT_KEY);
            Logger.d(TAG, "onCreate: stringExtra = " + stringExtra);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d(TAG, "onActivityResult: requestCode = " + requestCode);
        if (data != null) {
            Logger.d(TAG, "onActivityResult: data = " + data.getStringExtra(P_Constants.INTENT_KEY));
        }
    }



    /**
     * 适用于：
     * （1）宿主跳转插件
     * （2）插件之间跳转插件
     * （3）插件跳转宿主
     * @param intent
     * @param isOuterClass 是否为其他插件或宿主Activity
     *
     */
    public void startActivity(Intent intent, boolean isOuterClass) {
        ComponentName component = intent.getComponent();
        String packageName = component.getPackageName();
        Logger.d(TAG, "startActivity: packageName = " + packageName);
        intent.putExtra(P_Constants.INTENT_KEY, this.getClass().getSimpleName());
        if (isOuterClass) {
            if (P_Constants.HOST_PACKAGE_NAME.equals(packageName)) {// 跳转宿主
                startActivity(intent);
            } else {
                P_Context.startPluginActivity(getActivity(), intent);// 跳转插件
            }
        } else {
            startActivity(intent);// 插件内部跳转
        }
    }


    @Override
    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        getActivity().startActivityForResult(intent, requestCode);
    }

    /**
     * @param intent
     * @param requestCode
     * @param isOuterClass true 跳转其他插件或者跳转宿主
     *                     在Fragment中使用，onActivityForResult 都在Activity中响应
     */
    public void startActivityForResult(Intent intent, int requestCode, boolean isOuterClass) {
        ComponentName component = intent.getComponent();
        String packageName = component.getPackageName();
        Logger.d(TAG, "startActivityForResult: packageName = " + packageName);
        intent.putExtra(P_Constants.INTENT_KEY, this.getClass().getSimpleName());
        if (isOuterClass) {
            if (P_Constants.HOST_PACKAGE_NAME.equals(packageName)) {// 跳转宿主
                startActivityForResult(intent, requestCode);
            } else {
                P_Context.startPluginActivityForResult(getActivity(), intent, requestCode);// 跳转插件
            }
        } else {
            startActivityForResult(intent, requestCode);// 插件内部跳转
        }
    }

}
