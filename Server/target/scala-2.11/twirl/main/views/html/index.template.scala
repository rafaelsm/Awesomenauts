
package views.html

import models.Awesomenaut
import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[List[Awesomenaut],Form[Awesomenaut],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(awesomenauts: List[Awesomenaut], awesomenautForm: Form[Awesomenaut]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.71*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("Awesomenauts list")/*5.27*/ {_display_(Seq[Any](format.raw/*5.29*/("""

        """),format.raw/*7.9*/("""<h1>"""),_display_(/*7.14*/awesomenauts/*7.26*/.size()),format.raw/*7.33*/(""" """),format.raw/*7.34*/("""awesomenaut(s)</h1>

        <ul>
            """),_display_(/*10.14*/for(awesomenaut <- awesomenauts) yield /*10.46*/{_display_(Seq[Any](format.raw/*10.47*/("""
                """),format.raw/*11.17*/("""<li>
                    """),_display_(/*12.22*/awesomenaut/*12.33*/.name),format.raw/*12.38*/("""
                    
                    """),_display_(/*14.22*/form(routes.Application.deleteAwesomenaut(awesomenaut.id))/*14.80*/{_display_(Seq[Any](format.raw/*14.81*/("""
                        """),format.raw/*15.25*/("""<input type="submit" value="Delete">
                    """)))}),format.raw/*16.22*/("""
                """),format.raw/*17.17*/("""</li>
            """)))}),format.raw/*18.14*/("""
        """),format.raw/*19.9*/("""</ul>

        <h2>Add a new Awesomenaut</h2>

        """),_display_(/*23.10*/form(routes.Application.newAwesomenaut())/*23.51*/{_display_(Seq[Any](format.raw/*23.52*/("""
            """),_display_(/*24.14*/inputText(awesomenautForm("name"))),format.raw/*24.48*/("""
            """),format.raw/*25.13*/("""<input type="submit" value="Create">
        """)))}),format.raw/*26.10*/("""

""")))}),format.raw/*28.2*/("""
"""))}
  }

  def render(awesomenauts:List[Awesomenaut],awesomenautForm:Form[Awesomenaut]): play.twirl.api.HtmlFormat.Appendable = apply(awesomenauts,awesomenautForm)

  def f:((List[Awesomenaut],Form[Awesomenaut]) => play.twirl.api.HtmlFormat.Appendable) = (awesomenauts,awesomenautForm) => apply(awesomenauts,awesomenautForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Sep 26 00:05:02 BRT 2014
                  SOURCE: /Users/Rafael/projetos/Awesomenauts Server/awesome-server/app/views/index.scala.html
                  HASH: 94d67062d6f4c015f2fd3b3746863e178a3b8625
                  MATRIX: 752->1|924->70|952->89|979->91|1012->116|1051->118|1087->128|1118->133|1138->145|1165->152|1193->153|1267->200|1315->232|1354->233|1399->250|1452->276|1472->287|1498->292|1568->335|1635->393|1674->394|1727->419|1816->477|1861->494|1911->513|1947->522|2030->578|2080->619|2119->620|2160->634|2215->668|2256->681|2333->727|2366->730
                  LINES: 26->1|29->1|31->4|32->5|32->5|32->5|34->7|34->7|34->7|34->7|34->7|37->10|37->10|37->10|38->11|39->12|39->12|39->12|41->14|41->14|41->14|42->15|43->16|44->17|45->18|46->19|50->23|50->23|50->23|51->24|51->24|52->25|53->26|55->28
                  -- GENERATED --
              */
          