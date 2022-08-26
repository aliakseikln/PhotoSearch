# PhotoSearch
Easy search images in the simple application in your pocket. When you press on an image you will see a larger version of the image and you can download it to your gallery also this app has history of search. This application is based on Flickr api.

## Preview:
<img src="https://user-images.githubusercontent.com/48939805/186480859-535f5e2c-852e-40e4-9d56-320710960854.gif" width=35% height=35%>

## Case Study:
* [MVP](https://www.geeksforgeeks.org/mvp-model-view-presenter-architecture-pattern-in-android-with-example/): Architectural pattern 
  - Model - Layer for storing data. This layer is responsible for the abstraction of the data sources. Model and ViewModel work together to get and save the data.  
  - View - User Interface layer. The purpose of this layer is to inform the ViewModel about the user’s action.
  - Presenter - This layer fetch the data from the model and applies the UI logic to decide what to display. It exposes those data streams which are relevant to the View. Moreover, it servers as a link between the Model and the View.

* [Dagger 2](https://developer.android.com/training/dependency-injection/dagger-android) - Dependency injection.
  - How to define modules which provides dependencies.
  - How to define components
  - How to defining dependency providers (object providers)
  - How to defining dependencies (object consumers)
  - How to connecting consumers and providers
  - How to use scope annotations
  - How to declare special treatment of fields in Dagger
  
 

# Libraries and technologies used.
- [MVP](https://www.geeksforgeeks.org/mvp-model-view-presenter-architecture-pattern-in-android-with-example/) - Pattern overcomes the challenges of MVC and provides an easy way to structure the project codes
- [Glide](https://github.com/bumptech/glide) - Load and cache images by URL.
- [Retrofit](https://square.github.io/retrofit/) - Making HTTP connection with the rest API and convert reponse json file to Kotlin/Java object.
- [Dagger 2](https://developer.android.com/training/dependency-injection/dagger-android) - Compile-time framework for dependency injection.
