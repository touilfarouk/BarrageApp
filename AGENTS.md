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
- Roles shown in UI by decoding JWT (`UserInfo.roles`).

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
- `composeApp/src/commonMain/kotlin/com/codewithfk/travenor/ui/programmes/ProgrammesScreen.kt`
- `presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/auth/JwtUtils.kt`

## Known Fix
- DataStore key import: use `stringPreferencesKey` (not `preferencesKey`).

## Next Steps (Optional)
- Improve programmes UI and map additional fields from API response.
- Add pull-to-refresh and pagination if needed.


• Root cause: when the token is expired the API returns 302 with text/html, and response.body<List<ProgrammeDto>>()
  tries to deserialize HTML as JSON. That triggers NoTransformationFoundException. The fix is to check status/content-
  type first, detect auth expiry, and clear the token so the app navigates back to login.

  I made the following changes:

  1. Added explicit response checks in RemoteDataSource.getProgrammes so we don’t attempt JSON parsing on 302/HTML and
     instead return a typed auth-expired error.
  2. Introduced AuthExpiredException and UnexpectedResponseException in domain for clean propagation.
  3. Propagated original exceptions from ProgrammeRepositoryImpl (no wrapping), so the UI can detect auth expiry.
  4. In ProgrammesViewModel, clear the token when auth expires to trigger redirect.

  Changed files:

  - data/src/commonMain/kotlin/com/codewithfk/data/datasource/RemoteDataSource.kt
  - domain/src/commonMain/kotlin/com/codewithfk/domain/error/NetworkExceptions.kt
  - data/src/commonMain/kotlin/com/codewithfk/data/repository/ProgrammeRepositoryImpl.kt
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/programmes/ProgrammesViewModel.kt
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/di/PresentationModule.kt

  If you want, I can also:

  1. Add a toast/alert on session expiry before redirect.
Done. The toast/alert is now shown as a Snackbar before redirecting to login.

  What changed:

  - Added a sessionExpired flag to ProgrammesUiState.
  - ProgrammesViewModel now sets sessionExpired = true on AuthExpiredException instead of clearing the token
    immediately.
  - ProgrammesScreen shows a Snackbar when sessionExpired becomes true, then calls tokenViewModel.logout() after the
    Snackbar is displayed.
  - Updated DI to match the new ProgrammesViewModel signature.

  Updated files:

  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/programmes/ProgrammesUiState.kt
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/programmes/ProgrammesViewModel.kt
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/di/PresentationModule.kt
  - composeApp/src/commonMain/kotlin/com/codewithfk/travenor/ui/programmes/ProgrammesScreen.kt

  If you want the Snackbar to persist longer or use a dialog instead, tell me your preference.




• Done. The “Se souvenir de moi” checkbox now persists across logout and app restarts.

  What I changed:

  - Added RememberMeStorage in domain.
  - Implemented persistence in Android (DataStore) and iOS (NSUserDefaults).
  - Wired it into Koin on both platforms.
  - SignInViewModel now loads and saves the remember-me value.
  - LoginScreen uses the ViewModel state instead of local state.

  Updated files:

  - domain/src/commonMain/kotlin/com/codewithfk/domain/storage/RememberMeStorage.kt
  - composeApp/src/androidMain/kotlin/com/codewithfk/travenor/storage/AndroidTokenStorage.kt
  - composeApp/src/iosMain/kotlin/com/codewithfk/travenor/storage/IosTokenStorage.kt
  - composeApp/src/androidMain/kotlin/com/codewithfk/travenor/di/AppModule.android.kt
  - composeApp/src/iosMain/kotlin/com/codewithfk/travenor/di/AppModule.ios.kt
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/feature/register/SignInViewModel.kt
  - presentation/src/commonMain/kotlin/com/codewithfk/presentation/di/PresentationModule.kt
  - composeApp/src/commonMain/kotlin/com/codewithfk/travenor/ui/signin/LoginScreen.kt

  If you want “remember me” to also control whether the token is stored (i.e., don’t save token when unchecked), say the
  word and I’ll wire that behavior too.
  