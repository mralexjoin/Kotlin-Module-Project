class MenuItem constructor(index: Int, text: String, val executor: (Menu) -> Unit) {
    constructor(text: String, executor: (Menu) -> Unit): this(0, text, executor)
    val text = "$index. $text"
}
class MenuItems(val header: String, list: List<MenuItem>) {
    val list = list.toMutableList()
}
