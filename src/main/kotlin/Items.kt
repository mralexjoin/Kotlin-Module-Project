interface Item {
    val name: String

    fun openItem(menu: Menu)
}

abstract class ItemList(header: String, creationText: String) {
    val menuItems = MenuItems(header, listOf(MenuItem(creationText) { menu -> createItem(menu) }))
    abstract fun createItem(menu: Menu)
    protected fun addItem(item: Item) =
        menuItems.list.add(MenuItem(menuItems.list.size, item.name) { menu -> item.openItem(menu) })
}

object ArchiveList : ItemList("Список архивов","Создать архив") {
    override fun createItem(menu: Menu) {
        addItem(Archive(menu.readLine("Введите название архива:")))
    }
}
private class Archive(override val name: String) : Item, ItemList(
    "Список заметок архива \"$name\"","Создать заметку") {
    override fun createItem(menu: Menu) {
        addItem(
            Note(
                menu.readLine("Введите название заметки:"),
                menu.readLine("Введите текст заметки:")
            )
        )
    }
    override fun openItem(menu: Menu) = menu.show(menuItems)
}
private class Note(override val name: String, val text: String) : Item {
    val menuItems = MenuItems(toString(), emptyList())
    override fun openItem(menu: Menu) = menu.show(menuItems)

    override fun toString(): String =
        "Название заметки:\n${name}\nТекст заметки:\n${text}"
}
