# About Listing

This is just a simple app created through code challenges.
Each challenge will be in a separate branch, this readme will contain the details for each of them.

## Challenges history

* Fifth Challenge: Room Database
* Fourth Challenge: Dependency Injection
* Third Challenge: MVVM and flow
* Second Challenge: Allow items in the list to be deleted or edited.
  ![Editable List App](https://github.com/FranGarc/Listing/blob/develop/screenshots/challenge2.png)

* First Challenge: Create a simple list app
  ![Simple List App](https://github.com/FranGarc/Listing/blob/develop/screenshots/challenge1.png)

### Current challenge requisites

Challenge: Room Database

* Add the dependencies needed for Room.
* Work is to be done in data/local/database package.
* All Database work is I/O work. As such, it is to be done in coroutines, therefore all database functions (read, insert, etc) need to be _suspend fun_.
* Create an entity Element with an Int @PrimaryKey and a @NonNull String field for the value.
* Define the @Dao interface _Listing_. The _DataBase_ interface from the previous challenge can be refactored for this. Add a @Query to get all the elements from the database, with a return of type Flow<List<Element>>. Also, create an @Insert function to add a new Element (returns a Flow<Unit>)
* Create an abstract class to extend RoomDatabase. The _LocalDatabase_ class from the previous challenge can be refactored for this. Add it an abstract function that returns the @Dao entity created in the previous step.
* Add the @Provides function to the AppModule using "database/listing.db" as database file.
* Add a function _getAllElements_ to the Repository, with a return type of Flow<List<String>>. It will call the database method and convert the Flow<List<Element>> into Flow<List<String>>. Also add the _insertElement_ function to call the database @Insert function - no need to change the return type
  here.
* Make changes to the ViewModel so that the add function calls the database @Insert.
* Add an _init_ block to the ViewModel to call the "get all" @Query so the list gets loaded with each start of the app. Make the necessary changes in the Activity for the list to be displayed.
* Add a StateFlow variable in the ViewModel to alert the Activity of state changes such as _loading_, _ready_, or _error_. Activity will display some sort of information (Snackbar, Toast, etc) to alert the user of the current state.
* Change the _edit_ and _delete_ functions in the ViewModel so that they emit the _error_ state. The message will be "Functionality currently not available"

Additional resources: https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?hl=es-419#0