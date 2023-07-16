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
            /local 
                /database 
                    /dto
                        entities
                    -database Dao interface
                    -database abstract class
    /domain
        /usecases
        /model
            entities
        /repository
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

* Add the @Provides function to the AppModule to initialize the Database singleton.

[Domain]

* Create within domain/entities an entity Element with identical to ElementDto except without the Room specificities (tags, etc).
* Add a function _getAllElements_ to the Repository contract (the interface in Domain package), with a return type of Flow<List<Element>>. It will call the database method and convert the Flow<List<ElementDto>> into Flow<List<Element>>. Also add the _insertElement_ function to call the database
  @Insert function - no need to change the return
  type here.
* Create an UseCase for getting the list elements (_GetElementsUseCase_) and another for adding a new element (_AddElementUseCase_). They will call the repository functions created above. You can use the below example as a guidance.
    ```
    class SampleUseCase @Inject constructor(
        private val repository: RepositoryContract
    ) {
        suspend operator fun invoke(): Flow<ResponseType> {
            val response = repository.sampleGet()
            //here you can add some domain logic or call another UseCase
            return response
        }
    }
    ```

[Data]

* Create a _ListingRepository_ class that extends the Repository contract from Domain. Here you'll make the proper calls to implement each of the contract's functions, so it will have a dependency with the _ListingDatabase_.

[Presentation]

* Make changes to the ViewModel so that the add function calls the _AddElementUseCase_.
* Add an _init_ block to the ViewModel to call the _GetElementsUseCase_ so the list gets loaded with each start of the app. Make the necessary changes in the Activity for the list to be displayed.
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
