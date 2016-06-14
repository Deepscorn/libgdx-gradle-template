That project was born when I had very poor perforance using gradle in modern IDE's: JetBrains IDEA & Android Studio. So I've taken the template provided by libgdx and optimized it using some good gradle principles like http://gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects

# Project details
This is an empty LibGDX project with integrated kotlin in Core module and optimized gradle configuration to decrease build, open IDE time and remove lags when actually typing code in IDE. It includes most of the used includes, which may be selected from gdx-setup.jar at the moment of latest gdx version is 1.6.0. It have disadvantage: when updating, for example gdx, you need to change gdx_version in buildscript/ext section in core submodule and in each PLATFORM.

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

Note, that running on simulator I sometimes get an error related to audio playing
 No newline at end of file
Note, that running on simulator I sometimes get crash with exception related to audio playing, issue is: https://github.com/libgdx/libgdx/issues/1485. Will appreciate solving that mystery for me. I'm new to iOS develop and I don't here any sound playing in simulator. I think, it's switched off but I don't see any reason to crash when audio not available. Check https://github.com/Deepscorn/NewLibGDXProjectTemplate/tree/duckhunttest if you want to reproduce.

# HTML
--------
Html project is not included here, because of Kotlin used in core submodule. There is no way to convert kotlin code to javascript so that GWT can use it, as far as I know. But you can decouple HTML build configuration as I've done for android and desktop, using the same principle, used in that project. For tips you may found useful my blog: http://deepscorn.blogspot.ru/2015/05/speed-up-libgdx-gradle-build-in.html. And read http://gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects if you want to know more about gradle optimizations when building decoupled projects.

Issues, not described here
--------
Before adding any issues, try disabling the optimizations, I've done in gradle.properties. Simply, you may remove all of them.
