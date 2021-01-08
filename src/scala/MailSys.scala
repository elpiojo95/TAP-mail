package scala

object MailSys extends App {
  import oop.MemoryMailStore
  import patterns.EnhancedMailSystem


  val mailStore = new MemoryMailStore()
  val mailSystem = new EnhancedMailSystem(mailStore)


  val root = new Domain("")
  val cat = new Domain("cat")
  val urv = new Domain("urv")
  val etse = new Domain("etse")
  val estudiants = new Domain("estudiants")
  val user1 = new Account("user1")
  val user2 = new Account("user2")
  val user3 = new Account("user3")
  val user4 = new Account("user4")

  user1.mailBox.send(user2.mailBox.getUser, "hello", "Hello user2, this is user1!")
  user1.mailBox.send(user1.mailBox.getUser, "hello", "Hello user1, this is you!")
  user1.mailBox.send(user4.mailBox.getUser, "greetings", "Regards")
  user1.mailBox.send(user3.mailBox.getUser, "spam", "spam spam")
  user2.mailBox.send(user1.mailBox.getUser, "spam", "spam spam")

  user1.mailBox.update()
  user2.mailBox.update()
  user3.mailBox.update()
  user4.mailBox.update()


  root.addChild(cat)
  cat.addChild(urv)
  urv.addChild(etse)
  urv.addChild(estudiants)
  cat.addChild(user1)
  urv.addChild(user2)
  etse.addChild(user3)
  estudiants.addChild(user4)

  cat.print()
  println()
  println("All mail: " + cat.getMail)
}
