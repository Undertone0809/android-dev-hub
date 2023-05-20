# Camera Usage

## 要求

1. 新建一个Android工程，创建两个Activity， 一个为图片的列表显示主页面，一个为照片拍摄页面。
2. 点击主页面拍照按钮，跳转进入拍摄预览页面。
3. 预览页面可实时显示摄像头拍摄画面。
4. 点击预览页面的拍照，完成拍照，预览页面显示拍照结果3秒，然后跳转回主页面
5. 主页面将刚刚拍摄的照片存入列表中，并在每一个列表项目上显示照片拍摄时间。
6. 打开Android手机调试选项，将手机接入笔记本，选择真机调试，执行程序

## 快速上手

### 基础环境配置

**AndroidManifest配置**
```xml
<manifest>
    <uses-permission android:name="android.permission.INTERNET"/>
    <application>
        ...
        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="com.sh.cameraalbumnew.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
    </application>
</manifest>
```

**file_paths.xml配置**

在/xml中创建文件`file_paths.xml`，配置如下：

```xml
<?xml version = "1.0" encoding = "utf-8"?>
<paths xmlns:android = "http://schemas.android.com/apk/res/android">
    <external-path name = "my_images" path = "./">
    </external-path>
</paths>
```



### A