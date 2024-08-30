package bug

import androidx.activity.ComponentActivity
import kotlinx.coroutines.runBlocking

class MyComposeAndroidActivity : ComponentActivity() {
    companion object{
        const val ONE_SECOND = 1000L
    }
    override fun onDestroy() {
        runBlocking {
            Thread.sleep(ONE_SECOND)
        }
    }
}
