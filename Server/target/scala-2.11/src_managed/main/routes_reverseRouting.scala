// @SOURCE:/Users/Rafael/projetos/Awesomenauts Server/awesome-server/conf/routes
// @HASH:5e9fa6ca1f7eeb8884ab4b6673c2a6c04cdea57f
// @DATE:Thu Sep 25 23:42:12 BRT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers {

// @LINE:9
class ReverseAssets {


// @LINE:9
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:6
class ReverseApplication {


// @LINE:13
def newAwesomenaut(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "awesomenauts")
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

// @LINE:14
def deleteAwesomenaut(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "awesomenauts/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                        

// @LINE:12
def awesomenauts(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "awesomenauts")
}
                        

}
                          
}
                  


// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:9
class ReverseAssets {


// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:6
class ReverseApplication {


// @LINE:13
def newAwesomenaut : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.newAwesomenaut",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "awesomenauts"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:14
def deleteAwesomenaut : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.deleteAwesomenaut",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "awesomenauts/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:12
def awesomenauts : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.awesomenauts",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "awesomenauts"})
      }
   """
)
                        

}
              
}
        


// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:9
class ReverseAssets {


// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:6
class ReverseApplication {


// @LINE:13
def newAwesomenaut(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.newAwesomenaut(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "newAwesomenaut", Seq(), "POST", """""", _prefix + """awesomenauts""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:14
def deleteAwesomenaut(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.deleteAwesomenaut(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deleteAwesomenaut", Seq(classOf[Long]), "POST", """""", _prefix + """awesomenauts/$id<[^/]+>/delete""")
)
                      

// @LINE:12
def awesomenauts(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.awesomenauts(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "awesomenauts", Seq(), "GET", """ Awesomenauts""", _prefix + """awesomenauts""")
)
                      

}
                          
}
        
    