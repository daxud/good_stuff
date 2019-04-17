package gui

import data.Easy
import data.Enemy
import data.Player
import data.TileGrid
import data.Wave
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.layout.BorderPane
import scalafx.Includes._

object Drawing
  extends JFXApp {

  //Dimensions of canvas
  val canvasHeight = 960
  val canvasWidth = 1280

  //Create canvas
  val gameCanvas = new Canvas(canvasWidth, canvasHeight)
  def getCanvas = gameCanvas

  //GraphicsContext
  val gc = gameCanvas.graphicsContext2D
  def getGc: GraphicsContext = gc

  //Set the stage
  stage = new JFXApp.PrimaryStage {
    title = "TowerDefenseGame"
    height = canvasHeight + 50
    width = canvasWidth + 50
    scene = new Scene(1280, 960) {
      val border = new BorderPane
      border.center = gameCanvas
      root = border
    }
  }

  //The Map-Array which we use to create the map, size: 20x15.
  val arr = Array(
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

  //Map of the game.
  val gameMap = new TileGrid(arr)
  def getMap: TileGrid = gameMap
  gameMap.draw()

  //Player of the game.
  val p1 = new Player(gameMap)

  //Enemies of the game.
  val e1 = new Enemy(Easy, gameMap)
  val w1 = new Wave(3, Easy, gameMap)

  //Mouse events.
  gameCanvas.onMouseDragged = { me =>
    p1.setTile(me)
  }

  //Animation timer and the time of the game.
  var lastTime = 0L

  val timer = AnimationTimer { t =>
    if (lastTime != 0) {
      val delta = (t - lastTime) / 1e9 //In seconds.
      gameMap.draw()
      w1.update(delta)
    }
    lastTime = t
  }

  //Lets start the timer.
  timer.start()
}
  
 