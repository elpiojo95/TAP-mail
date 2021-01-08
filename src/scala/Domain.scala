package scala

import oop.Message

import scala.collection.mutable.ListBuffer

class Domain(str: String) extends Nodo{

  val list = new ListBuffer[Nodo]

  def printDeep(deep: Int) {
    printSpaces(deep)
    println("|"+str)
    for (n <- list) {
      n.printDeep(deep + 1)
    }
  }

  def addChild(n: Nodo) {
    list += n
  }

  def getMail: List[Message] = {
    var l = new ListBuffer[Message]
    list.foreach { e =>
      var f = e.getMail
      l.addAll(f)
    }

    return l.toList
  }

}
