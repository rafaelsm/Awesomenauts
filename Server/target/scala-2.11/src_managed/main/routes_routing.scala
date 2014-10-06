// @SOURCE:/Users/Rafael/projetos/Awesomenauts/Server/conf/routes
// @HASH:5e9fa6ca1f7eeb8884ab4b6673c2a6c04cdea57f
// @DATE:Sun Oct 05 20:56:10 BRT 2014


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

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
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:9
private[this] lazy val controllers_Assets_at1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at1_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:12
private[this] lazy val controllers_Application_awesomenauts2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("awesomenauts"))))
private[this] lazy val controllers_Application_awesomenauts2_invoker = createInvoker(
controllers.Application.awesomenauts(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "awesomenauts", Nil,"GET", """ Awesomenauts""", Routes.prefix + """awesomenauts"""))
        

// @LINE:13
private[this] lazy val controllers_Application_newAwesomenaut3_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("awesomenauts"))))
private[this] lazy val controllers_Application_newAwesomenaut3_invoker = createInvoker(
controllers.Application.newAwesomenaut(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "newAwesomenaut", Nil,"POST", """""", Routes.prefix + """awesomenauts"""))
        

// @LINE:14
private[this] lazy val controllers_Application_deleteAwesomenaut4_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("awesomenauts/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_Application_deleteAwesomenaut4_invoker = createInvoker(
controllers.Application.deleteAwesomenaut(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deleteAwesomenaut", Seq(classOf[Long]),"POST", """""", Routes.prefix + """awesomenauts/$id<[^/]+>/delete"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """awesomenauts""","""controllers.Application.awesomenauts()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """awesomenauts""","""controllers.Application.newAwesomenaut()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """awesomenauts/$id<[^/]+>/delete""","""controllers.Application.deleteAwesomenaut(id:Long)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index())
   }
}
        

// @LINE:9
case controllers_Assets_at1_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at1_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:12
case controllers_Application_awesomenauts2_route(params) => {
   call { 
        controllers_Application_awesomenauts2_invoker.call(controllers.Application.awesomenauts())
   }
}
        

// @LINE:13
case controllers_Application_newAwesomenaut3_route(params) => {
   call { 
        controllers_Application_newAwesomenaut3_invoker.call(controllers.Application.newAwesomenaut())
   }
}
        

// @LINE:14
case controllers_Application_deleteAwesomenaut4_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_deleteAwesomenaut4_invoker.call(controllers.Application.deleteAwesomenaut(id))
   }
}
        
}

}
     