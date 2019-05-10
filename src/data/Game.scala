package data

class Game(map: Array[Array[Int]]) {

  val grid = new TileGrid(map)
  val waveManager = new WaveManager(Easy, 1, 10, grid)
  val player = new Player(grid, waveManager)
  var alive: Boolean = true

  //Updates the game state, that is, calls the update for all the updatable classes.

  def update(delta: Double) = {
    //Current wave from 0 to X
    if (alive) {
      val currentWave: Int = waveManager.waveNumber
      grid.draw()
      waveManager.update(delta)
      player.update(delta)
      if (waveManager.waveNumber != currentWave) player.money += 500
      if (player.health <= 0) alive = false
    }
  }
}