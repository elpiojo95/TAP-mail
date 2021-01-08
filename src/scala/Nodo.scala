package scala

import oop.Message

abstract class Nodo {

  def printSpaces(int: Int) {
    for (i <- 1 to int) {
      printf("| ")
    }
  }

  def print() {
    printDeep(0)
  }

  def printDeep(deep: Int)

  def getMail: List[Message]
}
