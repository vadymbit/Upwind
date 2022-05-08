# Upwind (In progress)
Simple weather app built with Jetpack Compose  
<img src="https://github.com/vadymbit/Upwind/blob/master/media/home.png" width="200" height="400">
<img src="https://github.com/vadymbit/Upwind/blob/master/media/list.png" width="200" height="400">
<img src="https://github.com/vadymbit/Upwind/blob/master/media/settings.png" width="200" height="400">  

## Tech-stack
- [Kotlin](https://kotlinlang.org/) + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Retrofit](https://square.github.io/retrofit/) + [Moshi](https://github.com/square/moshi/)  - networking and json parsing
- [Koin](https://insert-koin.io/) - DI
- [Accompanist](https://github.com/google/accompanist) - A collection of extension libraries for Jetpack Compose.
- [Jetpack](https://developer.android.com/jetpack)
    - [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - deal with whole in-app navigation
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - allows more easily write code that interacts with views.
    - [Compose](https://developer.android.com/jetpack/compose) - modern toolkit for building native UI.
    - [Room](https://developer.android.com/jetpack/androidx/releases/room) - construct database.
    
 * Todo
   * Add more icon for different condition
   * Add animation for every condition
   * Add custom reordering to weather grid
   * Add weather box for current gps location
