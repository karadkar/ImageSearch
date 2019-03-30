/**
 * Kotlin gradle dependecy management
 * check https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/
 */
object Versions {
    const val gradlePlugin = "3.1.4"

    const val androidSupportLib = "27.1.1"
    const val constraintLayout = "1.1.2"
    const val archExtensions = "1.1.1"

    const val koin = "1.0.2"
    const val kotlin = "1.2.71"

    const val androidTestSupportLib = "1.0.2"
    const val espresso = "3.0.2"
    const val junit = "4.12"
    const val mockito = "2.18.3"
    const val mockitoKotlin = "1.5.0"
    const val mockk = "1.8.13"
    const val barista = "2.7.1"

    const val retrofit = "2.3.0"
    const val retrofitRxAdapter = "1.0.0"
    const val rxJava = "2.1.7"
    const val rxAndroid = "2.0.1"
    const val gson = "2.8.0"
    const val okhttp = "3.9.1"
    const val robolectric = "3.6.1"

    const val room = "1.1.1"
}

object Libs {
    // android
    const val archExtensions = "android.arch.lifecycle:extensions:${Versions.archExtensions}"
    const val archCoreTesting = "android.arch.core:core-testing:${Versions.archExtensions}"

    // android support
    const val appCompat = "com.android.support:appcompat-v7:${Versions.androidSupportLib}"
    const val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"

    // gradle
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"

    // kotlin
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinAllOpen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"

    // koin
    const val koinAndroidViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    const val koinTest = "org.koin:koin-test:${Versions.koin}"

    // test
    const val atsRunner = "com.android.support.test:runner:${Versions.androidTestSupportLib}"
    const val atsRules = "com.android.support.test:rules:${Versions.androidTestSupportLib}"
    const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    const val junit = "junit:junit:${Versions.junit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    // mockito
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    const val mockitoKotin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockk = "io.mockk:mockk-android:${Versions.mockk}"
    const val barista = "com.schibsted.spain:barista:${Versions.barista}"

    // retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val retrofitRxAdapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.retrofitRxAdapter}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

    // room
    const val roomRuntime = "android.arch.persistence.room:runtime:${Versions.room}"
    const val roomCompiler = "android.arch.persistence.room:compiler:${Versions.room}"
    const val roomRxJava2 = "android.arch.persistence.room:rxjava2:${Versions.room}"
    const val roomTesting = "android.arch.persistence.room:testing:${Versions.room}"

}