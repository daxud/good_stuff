package gui

import data.Game
import helpers.Helpers
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.control.ToggleButton
import scalafx.scene.control.ToggleGroup
import scalafx.scene.image.ImageView
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.Pane
import scalafx.scene.layout.StackPane
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.scene.shape.Rectangle
import scalafx.scene.control.Button
import javax.swing.ButtonGroup
import scalafx.event.ActionEvent
import scalafx.event.EventHandler
import scalafx.event.EventHandlerDelegate
import scalafx.Includes._


object Drawing
  extends JFXApp {

  //Create canvas where the field is.
  val gameHeight = 960
  val gameWidth = 1280
  val gameCanvas = new Canvas(gameWidth, gameHeight)
  def getCanvas = gameCanvas

  //Create canvas where the weapons are.
  val weaponHeight = 960
  val weaponWidth = 200
  val weaponCanvas = new Canvas(weaponWidth, weaponHeight)
  val wgc = weaponCanvas.graphicsContext2D

  //Buttons on the weapon canvas.
  //Button 1
  val cannonButton = new ToggleButton
  val t = Helpers.loadText("src/res/whitecannon.png")
  val text = new ImageView(t)
  text.fitWidth = weaponWidth - 40
  text.fitHeight = 100
  cannonButton.graphic = text
  cannonButton.layoutX = 0
  cannonButton.layoutY = 50
  cannonButton.accessibleText = "Cannon Tower"

  //Button 2
  val flameButton = new ToggleButton
  val t1 = Helpers.loadText("src/res/flamecirclewhite.png")
  val text1 = new ImageView(t1)
  text1.fitWidth = weaponWidth - 40
  text1.fitHeight = 100
  flameButton.graphic = text1
  flameButton.layoutX = 0
  flameButton.layoutY = 200
  flameButton.accessibleText = "Flame Tower"
  
  //Button 3
  val startButton = new Button("Start Round")
  startButton.prefWidth = weaponWidth - 40
  startButton.prefHeight = 100
  startButton.layoutX = 0
  startButton.layoutY = 400
  startButton.accessibleText = "Start round"
  
  //Lists of different buttons
  val toggleButtons = List(cannonButton, flameButton)
  val allButtons = List(cannonButton, flameButton, startButton)

  val buttonGroup = new ToggleGroup
  buttonGroup.toggles = toggleButtons
  
  //Total dimension
  val totalWidth = gameWidth + weaponWidth
  val totalHeight = gameHeight

  //GraphicsContext
  val gc = gameCanvas.graphicsContext2D

  //Set the stage
  stage = new JFXApp.PrimaryStage {
    title = "TowerDefenseGame"
    height = totalHeight
    width = totalWidth
    scene = new Scene(totalWidth, totalHeight) {
      //Layout of the different panes
      val fieldStack = new StackPane //Using stack pane here to interact with the game.
      fieldStack.children = List(gameCanvas)
      val gunStack = new StackPane //Same here.
      val buttonPane = new Pane
      buttonPane.children = allButtons
      gunStack.children = List(weaponCanvas, buttonPane)

      val rootPane = new BorderPane
      rootPane.center = fieldStack
      rootPane.right = gunStack

      root = rootPane
    }
  }

  //The Map-Array which we use to create the map, size: 20x15.
  val map = Array(
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))

  //Lets start a new game.
  val game = new Game(map)
  def getGame: Game = game
  var firstWaveSpawned = false
  startButton.onAction = (event: ActionEvent) => {
    game.waveManager.newWave()
  }

  //Draw and update the money status.
  def drawMoneyAndHealth(): Unit = {
    wgc.fill = Color.rgb(200, 200, 255, 0.5)
    wgc.fillRect(0, 0, weaponWidth, weaponHeight)
    wgc.font = new Font("Helvetica", 40)
    wgc.fill = Color.Black
    wgc.fillText("Money", 0, 790)
    wgc.fillText("Health", 0, 630)
    wgc.font = new Font("Helvetica", 70)
    wgc.fillText(game.player.money.toString, 0, 860)
    wgc.fillText(game.player.health.toString, 0, 700)
  }

//  //When you move your mouse.
//  gameCanvas.onMouseMoved = { me =>
//    println("mouse x: " + me.x.toInt + "and mouse y: " + me.y.toInt)
//  }

  //When you click with your mouse.
  gameCanvas.onMouseClicked = { me =>
    val tile = game.grid.getTile((me.x / 64.0).toInt, (me.y / 64.0).toInt)
    val selected = toggleButtons.find(_.isSelected())

    if (selected.isDefined) {
      game.player.setTower(tile, selected.get.accessibleText.value)
    }
  }

  //Animation timer and the time of the game.
  var lastTime = 0L

  val timer = AnimationTimer { t =>
    if (lastTime != 0) {
      val delta = (t - lastTime) / 1e9 //In seconds.
      game.update(delta)
      this.drawMoneyAndHealth()
    }
    lastTime = t
  }

  //Lets start the timer.
  timer.start()
}
  
 