# Kotlin/WASM Monaco Editor
This is a simple project that uses Kotlin/WASM interop to create a simple HTML page that allow the user to open a text file on their machine and edit it with the Monaco editor.
The project uses a RPC-based HTTP server to faciliate file reading and writing, as no pure-RPC library that supported Kotlin/WASM could be found.

The server has two routes:
1. `POST /file.open` to open files, returning file contents given a path
2. `POST /file.save` to save files, being provided the path to a file and its new contents

# Usage
1. In one terminal, run `./gradlew runFatJar` to start the server (runs on port 9090)
2. In another terminal, run `./gradlew wasmJsBrowserDevelopmentRun` to start the webpack dev server (runs on port 8080)
3. Navigate to `localhost:8080` in your browser - the UI will then pop up.
4. Enter the absolute path to a file on your machine in the text box and click "Open"
5. The file will be opened in the Monaco editor. Edit it as you wish, and click "Save" to save the file.