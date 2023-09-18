import java.util.InputMismatchException
import java.util.Scanner

class Menu {
    companion object {
        private val SCANNER = Scanner(System.`in`)
    }

    fun show(menuItems: MenuItems) {
        while (true) {
            val exitCode = menuItems.list.size
            println(menuItems.header)
            menuItems.list.forEach { println(it.text) }
            println("$exitCode. Выход")
            println("Выберите пункт меню: ")

            val input = try { SCANNER.nextInt() } catch (_: InputMismatchException) { -1 }
            SCANNER.nextLine()
            when (input) {
                in 0 until exitCode -> menuItems.list[input].executor(this)
                exitCode -> return
                else -> println("Недопустимый ввод. Допустимы только числа от 0 до $exitCode")
            }
        }
    }

    fun readLine(greeting: String): String {
        while (true) {
            println(greeting)
            val line = SCANNER.nextLine().trim()
            if (line.isNotEmpty())
                return line
        }
    }
}
