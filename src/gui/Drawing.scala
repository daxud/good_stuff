package gui

import data._
import scala.collection.mutable.Buffer

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.canvas.Canvas
import scalafx.Includes._
import scalafx.scene.layout.Pane
import scalafx.scene.control.ListView

object Drawing
  extends JFXApp {

  //Dimensions of canvas
  val canvasHeight = 960
  val canvasWidth = 1280

  //Create canvas
  val gameCanvas = new Canvas(canvasWidth, canvasHeight)

  //GraphicsContext
  val gc = gameCanvas.graphicsContext2D

  //Set the stage
  stage = new JFXApp.PrimaryStage {
    title = "TowerDefenseGame"
    height = canvasHeight
    width = canvasWidth
    scene = new Scene {
      root = new Pane {
        //this.addEventHandler(eventType, eventHandler)
        children = gameCanvas
      }
    }
    //Create the map
    val map = new TileGrid(gc)
    
    val listView = new ListView(List("moi", "joo", "jep"))
    listView.layoutX = 100
    listView.layoutY = 100
    listView.prefHeight = 150
    
    

    //The actual map, 0 = Grass, 1 = Dirt
    val grid = Array(
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
      
    map.drawDesired(grid)
    map.setTile(new DirtTile(0, 0))
    val enemy = new EasyEnemy(64, 64, 64, 64, 100, 100, gc)
    enemy.draw
    val enemy1 = new HardEnemy(5*64, 1*64, 100, 100, 100, 100, gc)
    enemy1.draw

  }
}