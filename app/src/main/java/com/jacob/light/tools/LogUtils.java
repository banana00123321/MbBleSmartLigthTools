package com.jacob.light.tools;


/*
 * Copyright 2112 Google Inc.
 *
 * Licensed under the Apache License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.1
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
* Log工具
*/


import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by jianhaohong on 2/9/15.
 */
public class LogUtils {

    public static String LOG_FILE_NAME = "LogUtils.log";

    /**
     * �õ��������
     */
    public static String makeLogTag(Class cls) {
        return cls.getSimpleName();
    }

    /**
     * Log ��ӡһ��DEBUG��Ϣ
     * @param tag ���
     * @param message ��Ϣ
     */
    public static void LOGD(String tag, String message) {
        if (canLog()) {
            Log.d(tag, avoidNullString(message));
        }
    }

    /**
     * Log ��ӡһ��VERBOSE��Ϣ
     * @param tag ���
     * @param message ��Ϣ
     */
    public static void LOGV(String tag, String message) {
        if (canLog()) {
            Log.v(tag, avoidNullString(message));
        }
    }

    /**
     * Log ��ӡһ��INFO��Ϣ
     * @param tag ���
     * @param message ��Ϣ
     */
    public static void LOGI(String tag, String message) {
        if (canLog()) {
            Log.i(tag, avoidNullString(message));
        }
    }

    /**
     * Log ��ӡһ��WARN��Ϣ
     * @param tag ���
     * @param message ��Ϣ
     */
    public static void LOGW(String tag, String message) {
        if (canLog()) {
            Log.w(tag, avoidNullString(message));
        }
    }


    /**
     * Log ��ӡһ��ERROR��Ϣ
     * @param tag ���
     * @param message ��Ϣ
     */
    public static void LOGE(String tag, String message) {
        if (canLog()) {
            Log.e(tag, avoidNullString(message));
        }
    }


    /**
     * /**
     * Log ��ӡһ��DEBUG��Ϣ�����ҽ��쳣��ӡ����
     * ʹ��LOGD(tag,message,new Throwable())���Խ�����ջ��ӡ����
     * @param tag ���
     * @param message ��Ϣ
     * @param cause �쳣��Ϣ
     */

    public static void LOGD(final String tag, String message, Throwable cause) {
        if (canLog()) {
            Log.d(tag, avoidNullString(message), cause);
        }
    }


    /**
     * Log ��ӡһ��VERBOSE��Ϣ�����ҽ��쳣��ӡ����
     * ʹ��LOGV(tag,message,new Throwable())���Խ�����ջ��ӡ����
     * @param tag ���
     * @param message ��Ϣ
     * @param cause �쳣��Ϣ
     */
    public static void LOGV(final String tag, String message, Throwable cause) {
        if (canLog()) {
            Log.v(tag, avoidNullString(message), cause);
        }
    }

    /**
     * Log ��ӡһ��INFO��Ϣ�����ҽ��쳣��ӡ����
     * ʹ��LOGI(tag,message,new Throwable())���Խ�����ջ��ӡ����
     * @param tag ���
     * @param message ��Ϣ
     * @param cause �쳣��Ϣ
     */
    public static void LOGI(final String tag, String message, Throwable cause) {
        if (canLog()) {
            Log.i(tag, avoidNullString(message), cause);
        }
    }

    /**
     * Log ��ӡһ��WARN��Ϣ�����ҽ��쳣��ӡ����
     * ʹ��LOGW(tag,message,new Throwable())���Խ�����ջ��ӡ����
     * @param tag ���
     * @param message ��Ϣ
     * @param cause �쳣��Ϣ
     */
    public static void LOGW(final String tag, String message, Throwable cause) {
        if (canLog()) {
            Log.w(tag, avoidNullString(message), cause);
        }
    }

    /**
     * Log ��ӡһ��ERROR��Ϣ�����ҽ��쳣��ӡ����
     * ʹ��LOGE(tag,message,new Throwable())���Խ�����ջ��ӡ����
     * @param tag ���
     * @param message ��Ϣ
     * @param cause �쳣��Ϣ
     */
    public static void LOGE(final String tag, String message, Throwable cause) {
        if (canLog()) {
            Log.e(tag, avoidNullString(message), cause);
        }
    }

    /**
     * Log ��ӡһ��VERBOSE��Ϣ�����ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     */
    public static void LOGV(String message) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.v(stackTraceElements[1].getFileName(), avoidNullString(message));
        }
    }

    /**
     * Log ��ӡһ��DEBUG��Ϣ�����ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     */
    public static void LOGD(String message) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.d(stackTraceElements[1].getFileName(), avoidNullString(message));
        }
    }

    /**
     * Log ��ӡһ��WARN��Ϣ�����ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     */
    public static void LOGW(String message) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.w(stackTraceElements[1].getFileName(), avoidNullString(message));
        }
    }

    /**
     * Log ��ӡһ��INFO��Ϣ�����ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     */
    public static void LOGI(String message) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.i(stackTraceElements[1].getFileName(), avoidNullString(message));
        }
    }

    /**
     * Log ��ӡһ��ERROR��Ϣ�����ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     */
    public static void LOGE(String message) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.e(stackTraceElements[1].getFileName(), avoidNullString(message));
        }
    }

    /**
     * Log ��ӡһ��DEBUG��Ϣ�����ҽ��쳣��Ϣ��ӡ���������ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     * @param cause �쳣
     */
    public static void LOGD(String message, Throwable cause) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.d(stackTraceElements[1].getFileName(), avoidNullString(message), cause);
        }
    }


    /**
     * Log ��ӡһ��VERBOSE��Ϣ�����ҽ��쳣��Ϣ��ӡ���������ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     * @param cause �쳣
     */
    public static void LOGV(String message, Throwable cause) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.v(stackTraceElements[1].getFileName(), avoidNullString(message), cause);
        }
    }


    /**
     * Log ��ӡһ��INFO��Ϣ�����ҽ��쳣��Ϣ��ӡ���������ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     * @param cause �쳣
     */
    public static void LOGI(String message, Throwable cause) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.i(stackTraceElements[1].getFileName(), avoidNullString(message), cause);
        }
    }

    /**
     * Log ��ӡһ��WARN��Ϣ�����ҽ��쳣��Ϣ��ӡ���������ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     * @param cause �쳣
     */
    public static void LOGW(String message, Throwable cause) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.w(stackTraceElements[1].getFileName(), avoidNullString(message), cause);
        }
    }


    /**
     * Log ��ӡһ��ERROR��Ϣ�����ҽ��쳣��Ϣ��ӡ���������ķ�����tagΪ��ǰ���ȫ��
     * @param message ��Ϣ
     * @param cause �쳣
     */
    public static void LOGE(String message, Throwable cause) {
        if (canLog()) {
            StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
            Log.e(stackTraceElements[1].getFileName(), avoidNullString(message), cause);
        }
    }

    /**
     * ��Log��Ϣ��¼������
     * @param message ��Ϣ
     */
    public static void LogToFile(String message) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + LOG_FILE_NAME;
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(avoidNullString(message) + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * ɾ������Log��־
     */
    public static void deleteLogFile() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + LOG_FILE_NAME;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * ����ն�������Ŀ�ָ���쳣
     * @param string Դ�ַ���
     * @return
     */
    private static String avoidNullString(String string) {
        if (string == null) {
            return "null";
        }
        return string;
    }

    /**
     * �Ƿ��ܹ���¼Log��Ϣ
     * @return
     */
    private static boolean canLog() {
        return BuildConfig.DEBUG;
    }

    private LogUtils() {
    }
}
