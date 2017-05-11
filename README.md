That project is an enhanced gradle configuration for libgdx

# Features
1. Java 8 support (tested desktop & android) with lambdas
2. Decoupled projects - when working on android project, you wount see gradle doing something with RoboVM which is used only for iOS. So better performance and stability

# What this project is for
That project is not for HTML and Eclipse. It is primaraly used to write java 8 code in core and deploy for android & desktop. While iOS must work too (RoboVM has support for java 8) I update iOS project very rarely. If you want to do that, you can make a pull request.

# Prerequisites
1. JAVA_HOME set to Jdk 8 path

# Known problems
You may need to disable "instant run" in IDE (see https://developer.android.com/preview/j8-jack.html)

# IDE's support
1. Android Studio
2. IDEA
3. AIDE on mobile phone/tablet - use aide branch

# Android Studio
Import gradle project in Android Studio

# IDEA
Import in IDEA as gradle project:

check auto-import, 

uncheck create separate module per source set, 

use default gradle wrapper

# AIDE
AIDE has limitations on building gradle projects at the time of writing. So gradle configuration was simplified - see aide branch. Some things we need to do by hand:

1. Copy android/src/main/jniLibs from desktop to mobile device

2. Build core project on desktop, then copy built core.jar to android/libs

# AIDE limitations
1. Not working:

build.gradle file:

buildscript { 
  ext { 
    gdxVersion=1.9.3 
  } 
} 

dependencies {
  compile "com.badlogicgames.gdx:gdx-backend-android:$gdxVersion"
}

Working:

compile "com.badlogicgames.gdx:gdx-backend-android:1.9.3"

2. Not working:

gradle.properties file:

gdxVersion=1.9.3

build.gradle:

dependencies {
  compile "com.badlogicgames.gdx:gdx-backend-android:$gdxVersion"
}

Working:

compile "com.badlogicgames.gdx:gdx-backend-android:1.9.3"

3. Building core gradle project as regular java project in AIDE doesn't work. At the time of writing AIDE opens core as android project (probably it doesn't know that regular java project can use gradle build system) and fails to compile it. The suggestion is to build it on desktop and copy jar. To be able to write core code on mobile device you can write it in android project. Then just move it when you are back on desktop and build new jar. It's ugly, but it's working

# Build app for android

--------
First, uncomment unpackNatives() task found in android/build.gradle. It's used to unpack jars and put .so in correct directories, so that they will be included in apk. Nothing more is needed to do, just click "run" in IDE or execute in terminal:
./gradlew :android:installRelease
or
./gradlew :android:installDebug


# Build app for iOS

--------
First, assure that:
org.gradle.daemon=false
in gradle.properties (see https://github.com/robovm/robovm/issues/867) or you may get UnsatisfiedLinkError: Native Library already loaded in another classloader

now you can build as normal:

./gradlew :ios:launchIOSDevice

or

./gradlew :ios:launchIPhoneSimulator

or

./gradlew :ios:launchIPadSimulator

Note, that running on simulator I sometimes get an error related to audio playing. It's a RoboVM bug:
https://github.com/libgdx/libgdx/issues/1485. Will appreciate solving that mystery for me. I'm new to iOS develop and I don't here any sound playing in simulator. I think, it's switched off but I don't see any reason to crash when audio not available. Check https://github.com/Deepscorn/NewLibGDXProjectTemplate/tree/duckhunttest if you want to reproduce.

## Useful gradle commands
gradlew clean --no-daemon // Clean project, that one used by official setup script

## Useful reading
http://deepscorn.blogspot.ru/2016/07/building-and-running-android-studio.html
http://deepscorn.blogspot.ru/2015/05/speed-up-libgdx-gradle-build-in.html
http://deepscorn.blogspot.ru/2015/05/libgdx-kotlin-setup.html

