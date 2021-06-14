# Bootcamp Testing Challenge

## 🎙 About the challenge

The challenge consists on create a rest application that will contain integration and unit tests.

### Summary

1. [How to start the application](#startapp)
2. [How to test](#test)
3. [Swagger](#docs)
4. [Collections](#collections)

## 🔨 How to start the application <a name="startapp"></a>

#### Using Gradle build tool

- At MacOS and Linux Run the command: `./gradlew bootRun`

- At Windows Run the command: `gradlew bootRun`

#### 🐳 Using docker

Just run the command: `docker run -dp 8080:8080 vinidomeli/bootcamp-testing-challenge`

* Ps: The application will run automatically, you can check it running the command `docker ps`.

## 📦 How to run the tests <a name="test"></a>

This feature will be only available when you are starting the application, you can use the gradle to help you with this
task.

#### Using Gradle build tool

- At MacOS and Linux Run the command: `./gradlew test`

- At Windows Run the command: `gradlew test`

## 📰 Documentation <a name="docs"></a>

![image](https://user-images.githubusercontent.com/84407703/121899962-47e54f00-ccfb-11eb-89cf-be231a2aa91b.png)

All implemented endpoints are available on swagger-ui.

Running Local: `localhost:8080/swagger-ui.html`

URI: `/swagger-ui.html`

## ⚙️ Collections <a name="collections"></a>

To make all things easier you can download the postman collection with all requests. They are
available [here](https://github.com/vinidomeli/bootcamp-testing-challenge/blob/master/collections/Bootcamp%20Test%20Challenge.postman_collection.json)

![image](https://user-images.githubusercontent.com/84407703/121900041-5c294c00-ccfb-11eb-8ae8-86d83c6d0006.png)

That's it! 🪐
