package data

class Game(map: Array[Array[Int]]) {

  val grid = new TileGrid(map)
  val tower = new TowerCannon(grid.getTile(3,8), 64, 64, 10)
  val waveManager = new WaveManager(Easy, 3, 3, grid, tower.getProjectiles)
  val player = new Player(grid, waveManager) 
      )

  
  //Updates the game state, that is, calls the update for all the updatable classes.
  def update(delta: Double) = {
    
    grid.draw()
    waveManager.update(delta)
    tower.update(delta)
  }
}