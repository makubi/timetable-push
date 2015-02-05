// @SOURCE:/home/basti/IdeaProjects/UntisCrawler/conf/routes
// @HASH:9ba82540c956e10faab7699eb9c7e55cf26d8d5d
// @DATE:Sun Dec 21 15:31:44 CET 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:17
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
package controllers {

// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
class ReverseHomeController {


// @LINE:12
def loadLists(server:String, school:String, user:String, password:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "loadLists" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("server", server)), Some(implicitly[QueryStringBindable[String]].unbind("school", school)), Some(implicitly[QueryStringBindable[String]].unbind("user", user)), Some(implicitly[QueryStringBindable[String]].unbind("password", password)))))
}
                        

// @LINE:10
def schoolSearch(searchParam:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "search" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("searchParam", searchParam)))))
}
                        

// @LINE:8
def getList(elementType:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "list/" + implicitly[PathBindable[Int]].unbind("elementType", elementType))
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          

// @LINE:17
class ReverseAssets {


// @LINE:17
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          
}
                  


// @LINE:17
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
class ReverseHomeController {


// @LINE:12
def loadLists : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.HomeController.loadLists",
   """
      function(server,school,user,password) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "loadLists" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("server", server), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("school", school), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("user", user), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("password", password)])})
      }
   """
)
                        

// @LINE:10
def schoolSearch : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.HomeController.schoolSearch",
   """
      function(searchParam) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "search" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("searchParam", searchParam)])})
      }
   """
)
                        

// @LINE:8
def getList : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.HomeController.getList",
   """
      function(elementType) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "list/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("elementType", elementType)})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.HomeController.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              

// @LINE:17
class ReverseAssets {


// @LINE:17
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              
}
        


// @LINE:17
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
package controllers.ref {


// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
class ReverseHomeController {


// @LINE:12
def loadLists(server:String, school:String, user:String, password:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).loadLists(server, school, user, password), HandlerDef(this.getClass.getClassLoader, "", "controllers.HomeController", "loadLists", Seq(classOf[String], classOf[String], classOf[String], classOf[String]), "POST", """""", _prefix + """loadLists""")
)
                      

// @LINE:10
def schoolSearch(searchParam:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).schoolSearch(searchParam), HandlerDef(this.getClass.getClassLoader, "", "controllers.HomeController", "schoolSearch", Seq(classOf[String]), "POST", """""", _prefix + """search""")
)
                      

// @LINE:8
def getList(elementType:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).getList(elementType), HandlerDef(this.getClass.getClassLoader, "", "controllers.HomeController", "getList", Seq(classOf[Int]), "GET", """""", _prefix + """list/$elementType<[^/]+>""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.HomeController]).index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.HomeController", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

}
                          

// @LINE:17
class ReverseAssets {


// @LINE:17
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          
}
        
    