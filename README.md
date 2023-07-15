# About Listing

This is just a simple app created through code challenges.
Each challenge will be in a separate branch, this readme will contain the details for each of them.

## Current challenge requisites

Challenge: Room Database

[Introduction]

* We're adapting more of the Clean Architecture practices, so by the end of this the data and domain packages structure should look like this

```
/data
    Repository class
    /datasources
        /local (could be cache, remote)
            /database (could be SharedPrefs, sensors, or APIs for remote)
                /dto
                -database Dao interface
                -database abstract class
/domain
    /usecases
    /entities
    RepositoryContract
```

* Since all Database work is I/O work, it is to be done in coroutines as to not block the Main (UI) thread. Therefore all database functions (read, insert, etc) need to be _suspend fun_.

[Database]

* Add the dependencies needed for Room.
* Create within dto an entity (room terminology) ElementDto (data transfer object) with an Int @PrimaryKey and a @NonNull String field for the value.
* Define the @Dao (data access object) interface _ListingDao_. This will be our Local Datasource. The _DataBase_ interface from the previous challenge can be refactored for this. Add a @Query to get all the elements from the database, with a return of type Flow<List<ElementDto>>. Also, create an
  @Insert function to add a new Element (returns a Flow<Unit>)
* Create an abstract class to extend RoomDatabase. The _ListingDatabase_ class from the previous challenge can be refactored for this. Add it an abstract function that returns the @Dao entity created in the previous step.

[Dependency Injection]

* Add the @Provides function to the AppModule to initialize the Database singleton using "database/listing.db" as database file.

[Domain]

* Create within domain/entities an entity Element with identical to ElementDto except without the Room specificities (tags, etc).
* Add a function _getAllElements_ to the Repository contract (the interface in Domain package), with a return type of Flow<List<Element>>. It will call the database method and convert the Flow<List<ElementDto>> into Flow<List<Element>>. Also add the _insertElement_ function to call the database
  @Insert function - no need to change the return
  type here.

[Data]

* Create a _ListingRepository_ class that extends the Repository contract from Domain. Here you'll make the proper calls to implement each of the contract's functions, so it will have a dependency with the _ListingDatabase_.

[Presentation]

* Make changes to the ViewModel so that the add function calls the database @Insert.
* Add an _init_ block to the ViewModel to call the "get all" @Query so the list gets loaded with each start of the app. Make the necessary changes in the Activity for the list to be displayed.
* Add a StateFlow variable in the ViewModel to alert the Activity of state changes such as _loading_, _ready_, or _error_. Activity will display some sort of information (Snackbar, Toast, etc) to alert the user of the current state.
* Change the _edit_ and _delete_ functions in the ViewModel so that they emit the _error_ state. The message will be "Functionality currently not available"

[Additional resources]

* https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?hl=es-419#0

## Challenges history

* Fifth Challenge: Room Database
* Fourth Challenge: Dependency Injection
* Third Challenge: MVVM and flow
* Second Challenge: Allow items in the list to be deleted or edited.
  ![Editable List App](https://github.com/FranGarc/Listing/blob/develop/screenshots/challenge2.png)

* First Challenge: Create a simple list app
  ![Simple List App](https://github.com/FranGarc/Listing/blob/develop/screenshots/challenge1.png)
