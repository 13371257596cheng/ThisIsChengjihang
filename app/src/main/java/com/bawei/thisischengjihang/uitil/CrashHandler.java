package com.bawei.thisischengjihang.uitil;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE = new CrashHandler();

    /**
     * 程序的Context对象
     */
    private Context mContext;

    /**
     * 用来存储设备信息和异常信息
     */
    private Map<String, String> infos = new HashMap<String, String>();

    /**
     *  用于格式化日期,作为日志文件名的一部分
     */
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /** 保证只有一个CrashHandler实例 */
    private CrashHandler() {
    }

    /** 获取CrashHandler实例 ,单例模式 */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化，这个是我们自己写的
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器吗，这句话非常重要！！！！！！！！！！
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 这个是系统重写的
     * 当 UncaughtException 发生时会转入该函数来处理
     * thread：线程，异常所在的线程
     * ex：具体的异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // !handleException(ex) 代表我们已经成功的抓取到了崩溃信息并成功写入文件，不执行系统的崩溃了

        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            // 基本就等于直接应用崩溃
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            // 不执行系统崩溃，我们就要自己处理了
            // 底下的话，休眠3秒之后，退出程序，并尝试重启
            // 具体到我们这个demo，不建议重启，所以我先注释掉下面的话，你们应该根据需求自己修改
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                Log.e(TAG, "error : ", e);
//            }
//            //退出程序
//            android.os.Process.killProcess(android.os.Process.myPid());
//            // System.exit(1); 1 表示退出并重启
//            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     * 我们需要返回 true 来表示自己有能力处理这个问题，不想交给系统让他崩溃出去
     *
     * @param ex：崩溃信息
     * @return true:如果处理了该异常信息; 否则返回false.
     */
    private boolean handleException(Throwable ex) {
        //没有崩溃信息，就return false，告知系统崩出去
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            // 这个是获取应用包名等信息
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                // 这个是获取应用版本号
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "收集版本信息失败", e);
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "获取设备信息失败", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return  返回文件名称,便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

        // 通过遍历我们刚才获取的手机信息，写入 StringBuffer 中，如果获取手机信息没写，这几行就不用写了
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        // new 一个 StringWriter，你们用 io 流也可以
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);

        // 这里是重点，我要把崩溃信息，通过 while 循环的形式，全部获取出来
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        printWriter.close();

        // 写入到 StringBuffer 中，StringBuffer 中就已经有了设备信息和崩溃信息
        String result = writer.toString();
        sb.append(result);

        // 以下就是把信息写入指定的文件
        try {
            // 以下三行代码，是使用 日期+时间 为文件命名
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                // 设定文件路径，你们可以改成你们相应的路径
                String path = "/sdcard/crash/";
                // 通过路径，创建文件
                File dir = new File(path);

                // 以下两行很重要 ，判断文件是否已经创建过，如果没有则创建一个
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // 通过流的形式，把 StringBuffer 写进去
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "写入失败", e);
        }
        return null;
    }
}
