package scala

import java.util.GregorianCalendar

import oop.Message
import patterns.EnhancedMailbox

import scala.MailSys.mailSystem
import scala.jdk.CollectionConverters._


class Account(str: String) extends Nodo{
  val mailBox: EnhancedMailbox = mailSystem.createUser(str,"",new GregorianCalendar())
  val username: String = str


  def printDeep(deep: Int) {
    printSpaces(deep)
    println("|@" + username)
  }

  def getMail: List[Message] = {
    mailBox.messageList().asScala.toList
  }

}
