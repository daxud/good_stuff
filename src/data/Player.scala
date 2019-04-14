package data

import scalafx.scene.input.MouseEvent
import scalafx.scene.input.KeyEvent
import scalafx.animation.KeyValue



class Player(grid: TileGrid) {
  
  val tileTypes: Array[TileType] = Array(Dirt, Grass)

  //Given the mouses current location, sets the corresponding tile to Dirt.
  def setTile(me: MouseEvent) = grid.setTile(me.getX.toInt / 64, me.getY.toInt / 64, Dirt)
  
  def changeTile(ke: KeyEvent) = {
    var index = 0
    ???
  }
}