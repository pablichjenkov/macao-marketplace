import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

class RealmDriver {

    val realm: Realm by lazy {
        val configuration = RealmConfiguration.create(schema = setOf(Expression::class, AllTypes::class))
        Realm.open(configuration)
    }

    fun addExpression(expression: String): Expression = realm.writeBlocking {
        copyToRealm(Expression().apply { expressionString = expression })
    }

    fun expressions(): List<Expression> = realm.query<Expression>().find()

    /*(
    fun observeChanges(): Flow<List<Expression>> =
        realm.query<Expression>().asFlow().map { resultsChange: ResultsChange<Expression> ->
            resultsChange.list
        }
    */
}
