package controllers

import java.util.UUID

import play.api.mvc._

import scala.util.{Failure, Success, Try}

trait ControllerUtils {

  def parseUUID(id: String, success: UUID => Result, failure: Throwable => Result = (t => Results.NotFound(t.getMessage()))): Result = {
    Try(UUID.fromString(id)) match {
      case Success(uuid) => success(uuid)
      case Failure(e) => failure(e)
    }
  }

}