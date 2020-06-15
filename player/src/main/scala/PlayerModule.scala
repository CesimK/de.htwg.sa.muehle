import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import player.database.IDatabase

class PlayerModule extends AbstractModule with ScalaModule {
  def configure() = {
    bind[IDatabase].to[database.mongo.mongoDB]
  }
}