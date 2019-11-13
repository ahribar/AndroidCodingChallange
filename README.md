# Beenius Android Coding Challange
This repository is an android implementation of the coding challenge solution given at a Beenius job interview.

## About
The app consists of 4 main screens, based on the Material Design guidelines.
- **Users Screen** - Displays a simple list of users from which the app user can click on, proceeding to the selected user's albums.
- **Albums Screen** - Displays another simple list with a random thumbnail from the album. Clicking on an item reveals the containing photos.
- **Photos Screen** - Displays an adaptable grid of photos from which the user can select one to see some of it's details.
- **Photo Details Screen** - Displays a full screen image with the ability to expand and a bottom sheet with some extra info.

## Implementation
- Clean Architecture (Data-Domain-Presentation) with [Android Architecture Components (AAC)](https://developer.android.com/topic/libraries/architecture) and a repository pattern
- The data layer is implemented as a local data source, represented with **Room** database and a remote data source, provided via the [**Retrofit** library](https://square.github.io/retrofit/).
- Both data sources are hidden behind repositories, which utilize the mapping implementations and handle mocked expiration.
- All data is being handled with **coroutines**.
- The domain layer is a simple package consisting of domain models, used throughout the app, mapping and repository interfaces.
- The view portion of the app is based on the MVP architecture (contracts for Model, View, Presenter) with the help of AAC's **ViewModel** component which acts as a state for the view, rather than a conventional ViewModel, hence **MVVMP** :)
- The **ViewModel**'s inner states are implemented using AAC **LiveData**, which are passed to the View as observables of domain data or other view states.
- All of **ViewModel**'s data is passed directly to the View's XML portion via **DataBinding**, thus reducing the amount of boilerplate code in the actual View class. Thanks to it, the app survives orientation changes.
- For app routing the AAC's **Navigation** component has been used with the help of **SafeArgs** for passing arguments between fragments inside a single activity.
- Any and all image loading from the Internet is made possible with [**Glide**](https://github.com/bumptech/glide) in tandem with data binding.
- The majority of data layer is self-building by using [**Dagger**](https://github.com/google/dagger) injection library
