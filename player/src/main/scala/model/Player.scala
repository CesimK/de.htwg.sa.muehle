package player.model

case class Player(name: String, color:String, placed:Int = 0, stones:Int = 9, mills:Int = 0) {
  val jump:Boolean = stones <= 3


  override def toString:String = "%s (%s)".format(name, color)

  def playerToHTML: String = "<p  style=\"font-family:'Lucida Console', monospace\"> " + toString +"</p>"
}
