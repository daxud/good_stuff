package data

import scalafx.scene.input.MouseEvent
import scalafx.scene.input.KeyEvent
import scalafx.animation.KeyValue
import scala.collection.mutable.Buffer
import javax.swing.JComponent.KeyboardState



class Player(grid: TileGrid, w: WaveManager, t: Buffer[TowerCannon]) {
  
  val tileTypes: Array[TileType] = Array(Dirt, Grass)
  val waveManager = w
  val towerList = t

  //Given the mouses current location, sets the corresponding tile to Dirt.
  def setTile(me: MouseEvent) = grid.setTile(me.getX.toInt / 64, me.getY.toInt / 64, Dirt)
  
  def changeTile(ke: KeyEvent) = {
    var index = 0
    if (ke.getEventType == KeyB
  }
  
  def update(delta: Double) = {
    //Handle keyboard input
    
    
    towerList.foreach(t => t.update(delta))
  }
}