import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement

fun MonacoSettings(): IStandaloneEditorConstructionOptions = js("({ })")

fun main() {

    // Init monaco
    val options = MonacoSettings().apply {
        value = "Open a file to get started"
        theme = "vs-dark"
        automaticLayout = true
    }
    val editor = create(
        domElement = document.getElementById("monaco")!!,
        options = options,
    )

    document.getElementById("open")!!.addEventListener("click") {
        val path = document.getElementById("path")!! as HTMLInputElement
        openFile(editor, path.value)
    }

    document.getElementById("save")!!.addEventListener("click") {
        val path = document.getElementById("path")!! as HTMLInputElement
        writeFile(path.value, editor.getValue())
    }



}