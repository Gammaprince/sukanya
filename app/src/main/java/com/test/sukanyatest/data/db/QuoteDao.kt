


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.test.sukanyatest.data.models.quotes.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<com.test.sukanyatest.data.models.quotes.Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes() : List<Result>
}