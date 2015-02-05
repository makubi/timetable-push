// @SOURCE:/home/basti/IdeaProjects/UntisCrawler/conf/routes
// @HASH:9ba82540c956e10faab7699eb9c7e55cf26d8d5d
// @DATE:Sun Dec 21 15:31:44 CET 2014


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_HomeController_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).index,
HandlerDef(this.getClass.getClassLoader, "", "controllers.HomeController", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:8
private[this] lazy val controllers_HomeController_getList1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("list/"),DynamicPart("elementType", """[^/]+""",true))))
private[this] lazy val controllers_HomeController_getList1_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).getList(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.HomeController", "getList", Seq(classOf[Int]),"GET", """""", Routes.prefix + """list/$elementType<[^/]+>"""))
        

// @LINE:10
private[this] lazy val controllers_HomeController_schoolSearch2_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("search"))))
private[this] lazy val controllers_HomeController_schoolSearch2_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).schoolSearch(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.HomeController", "schoolSearch", Seq(classOf[String]),"POST", """""", Routes.prefix + """search"""))
        

// @LINE:12
private[this] lazy val controllers_HomeController_loadLists3_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("loadLists"))))
private[this] lazy val controllers_HomeController_loadLists3_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).loadLists(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.HomeController", "loadLists", Seq(classOf[String], classOf[String], classOf[String], classOf[String]),"POST", """""", Routes.prefix + """loadLists"""))
        

// @LINE:17
private[this] lazy val controllers_Assets_at4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at4_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""@controllers.HomeController@.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """list/$elementType<[^/]+>""","""@controllers.HomeController@.getList(elementType:Int)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """search""","""@controllers.HomeController@.schoolSearch(searchParam:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """loadLists""","""@controllers.HomeController@.loadLists(server:String, school:String, user:String, password:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_HomeController_index0_route(params) => {
   call { 
        controllers_HomeController_index0_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).index)
   }
}
        

// @LINE:8
case controllers_HomeController_getList1_route(params) => {
   call(params.fromPath[Int]("elementType", None)) { (elementType) =>
        controllers_HomeController_getList1_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).getList(elementType))
   }
}
        

// @LINE:10
case controllers_HomeController_schoolSearch2_route(params) => {
   call(params.fromQuery[String]("searchParam", None)) { (searchParam) =>
        controllers_HomeController_schoolSearch2_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).schoolSearch(searchParam))
   }
}
        

// @LINE:12
case controllers_HomeController_loadLists3_route(params) => {
   call(params.fromQuery[String]("server", None), params.fromQuery[String]("school", None), params.fromQuery[String]("user", None), params.fromQuery[String]("password", None)) { (server, school, user, password) =>
        controllers_HomeController_loadLists3_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).loadLists(server, school, user, password))
   }
}
        

// @LINE:17
case controllers_Assets_at4_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at4_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     