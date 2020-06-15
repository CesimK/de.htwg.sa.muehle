import com.google.inject.AbstractModule
import database.IDatabaseGame
import net.codingwell.scalaguice.ScalaModule

class DatabaseModule extends AbstractModule with ScalaModule {
  def configure() = {
    bind[IDatabaseGame].to[database.slick.RelationalDatabase]
  }
}