import com.example.mim.Production2
import com.example.mim.obj1

class ComparePricesShow {
    companion object : Comparator<Production2.DataK> {

        override fun compare(a: Production2.DataK, b: Production2.DataK): Int = when {
            a.price != b.price -> a.price - b.price
            else -> a.price - b.price
        }
    }
}