package data

import scala.collection.mutable.Buffer

trait Tower {
  
  def shoot: Unit
  def draw: Unit
  def update(delta: Double): Unit
  def updateEnemyList(newList: Buffer[Enemy]): Unit
}