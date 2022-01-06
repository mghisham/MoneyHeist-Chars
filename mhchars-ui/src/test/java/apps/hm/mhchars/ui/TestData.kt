package apps.hm.mhchars.ui

fun getDummyQuestions() = listOf(
    Question(
        id = 1,
        level = 1,
        langCategory = 1,
        hashtags = listOf("application"),
        question = "What is Application",
        answer = "The Application class in Android is the base class within an Android app that contains all other components such as activities and services. The Application class, or any subclass of the Application class, is instantiated before any other class when the process for your application/package is created."
    )
)

fun getDummyQuestionsEntity() = listOf(
    QuestionEntity(
        id = 1,
        level = 1,
        langCategory = 1,
        hashtags = listOf("application"),
        question = "What is Application",
        answer = "The Application class in Android is the base class within an Android app that contains all other components such as activities and services. The Application class, or any subclass of the Application class, is instantiated before any other class when the process for your application/package is created."
    )
)

fun getDummyFavQns() = listOf(
    QuestionEntity(
        id = 1,
        level = 1,
        langCategory = 1,
        hashtags = listOf("application"),
        question = "What is Application",
        answer = "The Application class in Android is the base class within an Android app that contains all other components such as activities and services. The Application class, or any subclass of the Application class, is instantiated before any other class when the process for your application/package is created.",
        isFavourite = true
    )
)

fun getDummyQuoteEntity() = QuoteEntity(
    id = "1",
    author = "Hisham",
    en = "There is always a first time!"
)

fun getDummyQuote() = Quote(
    id = "1",
    author = "Hisham",
    en = "There is always a first time!"
)