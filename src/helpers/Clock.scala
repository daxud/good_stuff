package helpers

import scalafx.animation.AnimationTimer
import gui.Drawing

class Clock {

  var paused: Boolean = false
  var lastTime: Long = 0L
  var totaltime = 0.0
  var d = 0.0
  var multipl = 1

  //Returns the current time in milliseconds.
  def getTime(): Long = System.currentTimeMillis()

  //Gets the time betweem the last event and now.
  def getDelta = {
    val current = this.getTime()
    val delta: Long = (current - lastTime) / 1000
    lastTime = this.getTime()
    delta
  }

  def delta() = if (paused) 0 else d * multiplier

  def totalTime(): Double = totaltime

  def multiplier(): Int = multipl

  //Updates the clock state.
  def update() = {
    d = getDelta
    totaltime += d
  }

  //Changes the multiplier to the parameter value.
  def changeMultiplier(m: Int): Unit = {
    multipl = m
  }

  //Pauses or unpauses the game.
  def pause = if (paused) paused = false else paused = true

}