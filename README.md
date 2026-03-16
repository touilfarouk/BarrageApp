This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


 create mode 100644 composeApp/src/androidMain/kotlin/com/codewithfk/travenor/storage/AndroidTokenStorage.kt
 create mode 100644 composeApp/src/commonMain/kotlin/com/codewithfk/travenor/programmes/ProgrammesScreen.kt
 create mode 100644 composeApp/src/iosMain/kotlin/com/codewithfk/travenor/storage/IosTokenStorage.kt
 create mode 100644 data/src/commonMain/kotlin/com/codewithfk/data/mappers/ProgrammeMapper.kt
 create mode 100644 data/src/commonMain/kotlin/com/codewithfk/data/model/ProgrammeDto.kt
 create mode 100644 data/src/commonMain/kotlin/com/codewithfk/data/repository/ProgrammeRepositoryImpl.kt
 create mode 100644 domain/src/commonMain/kotlin/com/codewithfk/domain/model/Programme.kt
 create mode 100644 domain/src/commonMain/kotlin/com/codewithfk/domain/repository/ProgrammeRepository.kt
 create mode 100644 domain/src/commonMain/kotlin/com/codewithfk/domain/storage/TokenStorage.kt
 create mode 100644 domain/src/commonMain/kotlin/com/codewithfk/domain/usecase/ClearTokenUseCase.kt
 create mode 100644 domain/src/commonMain/kotlin/com/codewithfk/domain/usecase/GetAllProgrammesUseCase.kt
 create mode 100644 domain/src/commonMain/kotlin/com/codewithfk/domain/usecase/GetTokenUseCase.kt
 create mode 100644 domain/src/commonMain/kotlin/com/codewithfk/domain/usecase/ObserveTokenUseCase.kt
 create mode 100644 presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/auth/TokenUiState.kt
 create mode 100644 presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/auth/TokenViewModel.kt
 create mode 100644 presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/programmes/ProgrammesUiState.kt
 create mode 100644 presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/programmes/ProgrammesViewModel.kt




  Files updated/added

  - presentation/build.gradle.kts (added kotlinx-serialization-json)
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/auth/JwtUtils.kt
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/auth/TokenUiState.kt
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/auth/TokenViewModel.kt
  - composeApp/src/commonMain/kotlin/com/codewithfk/travenor/programmes/ProgrammesScreen.kt

  This is only for display; it does not restrict access, so both ADMIN and AGENT can still load all programmes as
  requested.