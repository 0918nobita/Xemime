package vision.kodai.xemime

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.text.Text
import javafx.stage.Stage

class Window : Application() {
    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) return

        val msg = Text("Welcome to Xemime")
        val group = Group(msg)
        group.layoutX = 30.0
        group.layoutY = 80.0
        val scene = Scene(group, 320.0, 160.0)
        primaryStage.scene = scene
        primaryStage.title = "Xemime"
        primaryStage.isResizable = false
        primaryStage.show()
    }
}
