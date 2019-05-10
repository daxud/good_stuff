package data

import scalafx.scene.input.MouseEvent
import scalafx.scene.input.KeyEvent
import scalafx.animation.KeyValue
import scala.collection.mutable.Buffer
import javax.swing.JComponent.KeyboardState

class Player(grid: TileGrid, val waveManager: WaveManager) {

  val tileTypes: Array[TileType] = Array(Dirt, Grass)
  val towerList: Buffer[Tower] = Buffer[Tower]()
  
  //Player money and health.
  var money = 1000
  var health = 100
  
  //Pricelist of different weapons.
  val flamePrice = 300
  val cannonPrice = 250
  
  def updateHealth() = {
    //Lose 5 hp for each enemy that passes.
    val passed: Int = waveManager.currentWave.enemiesPassed
    health = health - (passed * 5)
    waveManager.currentWave.enemiesPassed = 0
  }

  //MouseEvents, NOT IN USE CURRENTLY
  def setTile(me: MouseEvent) = grid.setTile(me.getX.toInt / 64, me.getY.toInt / 64, Dirt)

  //Given a tile and a towertype as a string(see names below), sets the tower to the given tile if possible.
  def setTower(t: Tile, accessbileText: String) = {
    val enemies: Buffer[Enemy] = waveManager.currentWave.enemyList
    
    accessbileText match {
      case "Cannon Tower" => if (t.tiletype == Grass && money >= cannonPrice) {
        towerList += new CannonTower(t, 64, 64, 50, enemies)
        money -= cannonPrice
      }
      case "Flame Tower" => if (t.tiletype == Grass && money >= flamePrice) {
        towerList += new FlameTower(t, 64, 64, 50, enemies)
        money -= flamePrice
      }
    }
  }

  //KeyEvents

  //The player takes care of towers. Update each tower on the tower list.
  def update(delta: Double) = {
    this.updateHealth()
    towerList.foreach(t => {
      t.update(delta)
      t.updateEnemyList(waveManager.getCurrentWave.enemyList)
    }
    )
  }
}