import kotlinx.serialization.*
import kotlinx.serialization.json.*
import android.content.Context
import java.io.File

@Serializable
data class Quotes(val quote: String, val author: String)

fun saveQuotesToFile(context: Context) {

        // Creating sample quotes
        val quoteOne = Quotes("Success is the sum of small efforts, repeated day in and day out.", "Robert Collier")
        val quoteTwo = Quotes("There are no secrets to success. It is the result of preparation, hard work, and learning from failure.", "Colin Powell")
        val quoteThree = Quotes("Hard work beats talent when talent doesn’t work hard.", "Tim Notke")
        val quoteFour = Quotes("The only place where success comes before work is in the dictionary.", "Vidal Sassoon")
        val quoteFive = Quotes("I find that the harder I work, the more luck I seem to have.", "Thomas Jefferson")
        val quoteSix = Quotes("Don’t wish it were easier. Wish you were better", "Jim Rohn")
        val quoteSeven = Quotes("There are no shortcuts to any place worth going.", "Beverly Sills")
        val quoteEight = Quotes("Work hard in silence, let your success be your noise.", "Frank Ocean")
        val quoteNine = Quotes("The difference between ordinary and extraordinary is that little extra.", "Jimmy Johnson")
        val quoteTen = Quotes("Dreams don’t work unless you do", "John C. Maxwell")

        // Creating the list of quotes
        val quotesList = listOf(quoteOne, quoteTwo, quoteThree, quoteFour, quoteFive, quoteSix, quoteSeven, quoteEight, quoteNine, quoteTen)

        // Serializing the quotes list to JSON
        val jsonString = Json.encodeToString(quotesList)

        // Get the file from internal storage
        val file = File(context.filesDir, "quotes.json")

        // If the file doesn't exist, write the quotes
        if (!file.exists()) {
            file.writeText(jsonString)
            println("Quotes saved to file: ${file.absolutePath}")
        } else {
            println("Quotes file already exists.")
        }

}
