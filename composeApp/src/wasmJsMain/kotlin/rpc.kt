import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.*

fun GetHeaders(): JsAny = js("({ \"Content-Type\": \"application/json\" })")
fun GetBody(path: String): JsAny = js("(path)")
fun GetReferred(): JsAny = js("(\"\")")

inline fun <reified T> genericJsonPostRequest (path: String, body: T) =
    // Have to manually specifiy all of this because otherwise get null errors!
    window.fetch(path, RequestInit(
        method = "POST",
        headers = GetHeaders(),
        body = GetBody(Json.encodeToString(body)),
        cache = RequestCache.NO_CACHE,
        credentials = RequestCredentials.SAME_ORIGIN,
        mode = RequestMode.CORS,
        redirect = RequestRedirect.FOLLOW,
        integrity = "",
        keepalive = false,
        referrerPolicy = GetReferred(),
        referrer = "about:client",
    ))

fun openFile(monaco: IStandaloneCodeEditor, path: String): String {
    println("Opening file: $path")

    // Make POST request to server
    genericJsonPostRequest("http://localhost:$SERVER_PORT/file.open", FileOpenRequest(path)).then {
        println("Made it")
        it.text().then { text ->
            println("Got text: $text")
            monaco.setValue(text.toString())
            text
        }
    }
    return "Hello World"
}