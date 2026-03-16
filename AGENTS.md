# Project Context (Travenor x Barrage-Vert)

## Goal
- Adapt the Travenor KMP app login and data flow to the Barrage-Vert API and display programmes.

## API
- Base URL: `http://213.179.181.50/api`
- Login: `POST /auth/login`
  - Body: `{ "username": "<email>", "password": "<password>" }`
  - Response: `{ success, accessToken }` (handled; token is stored).
- Programmes: `POST /allprogrammes` (requires `Authorization: Bearer <token>`).

## Key Changes Implemented
- Login now sends `username` and parses `accessToken`.
- Token storage:
  - Android: DataStore Preferences.
  - iOS: NSUserDefaults.
  - Shared `TokenStorage` interface.
- Token flow:
  - `TokenViewModel` observes token changes.
  - App auto-redirects: token -> `ProgrammesScreen`, no token -> `LoginScreen`.
- Programmes listing:
  - New domain/model/repo/usecase and UI screen to list programmes from `/allprogrammes`.
- Network security:
  - Android `usesCleartextTraffic="true"` set.
  - `composeApp/src/androidMain/res/xml/netowrk_config.xml` allows `213.179.181.50`.

## Important Files
- API + networking:
  - `data/src/commonMain/kotlin/com/codewithfk/data/datasource/RemoteDataSource.kt`
  - `composeApp/src/androidMain/res/xml/netowrk_config.xml`
- Token storage:
  - `domain/src/commonMain/kotlin/com/codewithfk/domain/storage/TokenStorage.kt`
  - `composeApp/src/androidMain/kotlin/com/codewithfk/travenor/storage/AndroidTokenStorage.kt`
  - `composeApp/src/iosMain/kotlin/com/codewithfk/travenor/storage/IosTokenStorage.kt`
- Auth flow:
  - `presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/register/SignInViewModel.kt`
  - `presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/auth/TokenViewModel.kt`
- App routing:
  - `composeApp/src/commonMain/kotlin/com/codewithfk/travenor/App.kt`
- Programmes:
  - `domain/src/commonMain/kotlin/com/codewithfk/domain/model/Programme.kt`
  - `data/src/commonMain/kotlin/com/codewithfk/data/repository/ProgrammeRepositoryImpl.kt`
  - `presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/programmes/ProgrammesViewModel.kt`
  - `composeApp/src/commonMain/kotlin/com/codewithfk/travenor/programmes/ProgrammesScreen.kt`

## Known Fix
- DataStore key import: use `stringPreferencesKey` (not `preferencesKey`).

## Next Steps (Optional)
- Improve programmes UI and map additional fields from API response.
- Add pull-to-refresh and pagination if needed.
