import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.dom.appendElement
import org.w3c.dom.HTMLInputElement

fun MonacoSettings(): IStandaloneEditorConstructionOptions = js("({ })")

fun main() {

    // Init monaco
    val options = MonacoSettings().apply {
        value = "fun main() {\n    println(\"Hello World\")\n}"
        language = "kotlin"
        theme = "vs-dark"
        automaticLayout = true
    }
    val editor = create(
        domElement = document.getElementById("monaco")!!,
        options = options,
    )

    // Populate #path with home dir
    document.getElementById("open")!!.addEventListener("click") {
        val path = document.getElementById("path")!! as HTMLInputElement
        val scope = CoroutineScope(kotlinx.coroutines.MainScope().coroutineContext)

        scope.launch {
            val contents = openFile(editor, path.value)
            println(contents)
        }
    }



}