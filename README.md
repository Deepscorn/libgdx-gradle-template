# NewLibGDXProjectTemplate
This is an empty LibGDX project with integrated kotlin in Core module and optimized gradle configuration to decrease build and open IDE time. It includes all of the includes, which may be selected from gdx-setup.jar at the moment of latest gdx version is 1.6.0. It have disadvantage: when updating, for example gdx, you need to change gdx_version in buildscript/ext section in core submodule and in each PLATFORM.


Build app for iOS

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