package com.tangwenchao.oldmancallbook.utils;

import android.Manifest;
import android.app.Activity;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/5 11:05
 * @描述 TODO
 */
public class PermissionsUtil {

    private static final String TAG = "PermissionsUtil";
    private static final int REQUEST_PERMISSIONS = 0x2;
    private static final int REQUEST_PERMISSION = 0x3;

    /**
     * 全部权限申请
     *
     * @param ac
     */
    public static void requestPerms(Activity ac) {
        String[] perms = {Manifest.permission.RECEIVE_BOOT_COMPLETED, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS};
        if (EasyPermissions.hasPermissions(ac, perms)) {
            LogUtil.i(TAG, "已拥有全部权限");
        } else {
            EasyPermissions.requestPermissions(ac, "申请权限", PermissionsUtil.REQUEST_PERMISSIONS, perms);
        }
    }

    /**
     * 单个权限申请
     *
     * @param ac
     * @param perm
     */
    public static void requestPerms(Activity ac, String perm) {
        if (EasyPermissions.hasPermissions(ac, perm)) {
            LogUtil.i(TAG, "已拥有该权限");
        } else {
            EasyPermissions.requestPermissions(ac, "申请权限", PermissionsUtil.REQUEST_PERMISSION, perm);
        }
    }
}
