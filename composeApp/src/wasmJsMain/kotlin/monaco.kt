@file:JsModule("monaco-editor")
@file:JsQualifier("editor")

import org.w3c.dom.Element

external interface IStandaloneCodeEditor : JsAny {
    fun getValue(): String
    fun setValue(value: String)
    fun onDidChangeModelContent(callback: (e: JsAny) -> Unit)
    fun dispose()
}

external interface IStandaloneEditorConstructionOptions : JsAny {
    var value: String
    var language: String
    var theme: String
    var automaticLayout: Boolean
}

external fun create(
    domElement: Element,
    options: IStandaloneEditorConstructionOptions = definedExternally
): IStandaloneCodeEditor