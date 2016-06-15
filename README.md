That project is an enhanced gradle configuration for libgdx

# Features
1. Java 8 support (tested desktop & android) with lambdas
2. Decoupled projects - when working on android project, you wount see gradle doing something with RoboVM which is used only for iOS. So better performance and stability

# What this project is for
That project is not for HTML and Eclipse. It is primaraly used to write java 8 code in core and deploy for android & desktop. While iOS must work too (RoboVM has support for java 8) I update iOS project very rarely. If you want to do that, you can make a pull request. 

# Supported build tools and IDE's
1. Android Studio
2. JetBrains IDEA
3. gradle

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
