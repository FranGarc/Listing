# About Listing

This is just a simple app created through code challenges.
Each challenge will be in a separate branch, this readme will contain the details for each of them.

## Challenges history

* Fourth Challenge: Dependency Injection
* Third Challenge: MVVM and flow
* Second Challenge: Allow items in the list to be deleted or edited.
  ![Editable List App](https://github.com/FranGarc/Listing/blob/develop/screenshots/challenge2.png)

* First Challenge: Create a simple list app
  ![Simple List App](https://github.com/FranGarc/Listing/blob/develop/screenshots/challenge1.png)

### Current challenge requisites

Challenge: Dependency Injection

* Add the dependencies needed for Dagger Hilt.
* Create the following packages (your app package name may vary): com.franciscogarciagarzon.listing.data and com.franciscogarciagarzon.listing,domain) - these will simulate separate modules as per Clean Architecture
* Within data, create subpackage local and add a dummy Interface LocalDatabase with the suspend function signature doDatabaseCall()
* Within data/local and add a dummy class LocalDatabaseImpl that extends LocalDatabase Interface and implements the doDatabaseCall function (just have it print a log so we can see it works).
* Within data, create subpackage repository and adda dummy class Repository that calls the suspend function in the dummy Interface LocalDatabase
* Within domain, create subpackage repository and add a class RepositoryImpl that implements the Repository interface and overrides the doDatabaseCall function (calls the LocalDatabase method).
* Have the RepositoryImpl get the LocalDatabase dependecy via constructor
* Create the  (your app package name may vary): com.franciscogarciagarzon.listing.di (dependency injection) package. Use it to create the DaggerHilt app module to hold the singletons like our database dummy (SingletonComponent).
* In the app module, add the @Provides function to provide our @Singleton database dependency
* @Inject in the ViewModel's constructor the repository we created earlier, so we can use it from there. You'll need to add the @Provides function in the app module to provide the repository @Singleton, similar to what you just did.
* Change in MainActivity the way you instantiate the ViewModel, since now is a hiltViewModel
* Add @AndroidEntryPoint to the MainActivity
* Create a class that extends Application and annotate it with @HiltAndroidApp - you may need to inject the app context in the future. You'll need to add it to the manifest.

Additional resources: https://www.youtube.com/watch?v=bbMsuI2p1DQ (though the video uses network calls, which we're not doing just yet)