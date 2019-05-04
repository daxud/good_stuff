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
import data.TowerCannon
import data.Game
import scalafx.scene.input.KeyCode

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
  val map = Array(
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))

  //Lets start a new game.
  val game = new Game(map)
  def getGame: Game = game

  //Mouse events.
  gameCanvas.onMouseDragged = { me =>
    //p1.setTile(me)
  }
  
  //Key events.
  gameCanvas.onKeyPressed = { ke =>
    ke.code match {
      case KeyCode.Q => game.player
    }
  }

  //Animation timer and the time of the game.
  var lastTime = 0L

  val timer = AnimationTimer { t =>
    if (lastTime != 0) {
      val delta = (t - lastTime) / 1e9 //In seconds.
      game.update(delta)
    }
    lastTime = t
  }

  //Lets start the timer.
  timer.start()
}
  
 