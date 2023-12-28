import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.*

fun GetHeaders(): JsAny = js("({ \"Content-Type\": \"application/json\" })")
fun GetBody(path: String): JsAny = js("(path)")
fun GetReferred(): JsAny = js("(\"\")")


fun openFile(path: String): String {
    println("Opening file: $path")

    // Make POST request to server
    window.fetch("/file.open", RequestInit(
        method = "POST",
        headers = GetHeaders(),
        body = GetBody(Json.encodeToString(FileOpenRequest(path))),
        cache = RequestCache.NO_CACHE,
        credentials = RequestCredentials.SAME_ORIGIN,
        mode = RequestMode.CORS,
        redirect = RequestRedirect.FOLLOW,
        integrity = "",
        keepalive = false,
        referrerPolicy = GetReferred(),
        referrer = "about:client",
    )
    ).then {
        println("Made it")
        it.text()
    }
    return "Hello World"
}