
package views.html.polymer

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._

/**/
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""

"""),format.raw/*3.1*/("""<link rel="import" href=""""),_display_(/*3.27*/routes/*3.33*/.Assets.at("polymer/bower_components/polymer/polymer.html")),format.raw/*3.92*/("""">
<link rel="import" href=""""),_display_(/*4.27*/routes/*4.33*/.Assets.at("polymer/bower_components/paper-input/paper-input-decorator.html")),format.raw/*4.110*/("""">
<link rel="import" href=""""),_display_(/*5.27*/routes/*5.33*/.Assets.at("polymer/bower_components/paper-button/paper-button.html")),format.raw/*5.102*/("""">
<link rel="import" href=""""),_display_(/*6.27*/routes/*6.33*/.Assets.at("polymer/bower_components/core-ajax/core-ajax.html")),format.raw/*6.96*/("""">
<link rel="import" href=""""),_display_(/*7.27*/routes/*7.33*/.Assets.at("polymer/bower_components/paper-shadow/paper-shadow.html")),format.raw/*7.102*/("""">
<link rel="import" href=""""),_display_(/*8.27*/routes/*8.33*/.Assets.at("polymer/bower_components/paper-item/paper-item.html")),format.raw/*8.98*/("""">
<polymer-element name="master-list" constructor="MasterList" attribute="data">
    <template>
        <style>
        :host """),format.raw/*12.15*/("""{"""),format.raw/*12.16*/("""
            """),format.raw/*13.13*/("""display: block;
            padding: 8px;
            /*position: relative;*/
            background-color: #E5E5E5;
        """),format.raw/*17.9*/("""}"""),format.raw/*17.10*/("""

        """),format.raw/*19.9*/(""".card"""),format.raw/*19.14*/("""{"""),format.raw/*19.15*/("""
            """),format.raw/*20.13*/("""margin: 8px;
            font-size: 14px;
            padding-left: 16px;
            padding-right: 16px;
            padding-bottom: 8px;
            padding-top: 8px;
            background: #fff;
            line-height: 1.5;
            min-width: 300px;;
            max-width: 400px;
        """),format.raw/*30.9*/("""}"""),format.raw/*30.10*/("""

        """),format.raw/*32.9*/(""".cardhead"""),format.raw/*32.18*/("""{"""),format.raw/*32.19*/("""
            """),format.raw/*33.13*/("""font-size: 24px;
            color: #FF9800;
            margin-bottom: 8px;
        """),format.raw/*36.9*/("""}"""),format.raw/*36.10*/("""
        """),format.raw/*37.9*/(""".list"""),format.raw/*37.14*/("""{"""),format.raw/*37.15*/("""
             """),format.raw/*38.14*/("""max-height: 300px;
             overflow : auto ;
        """),format.raw/*40.9*/("""}"""),format.raw/*40.10*/("""

        """),format.raw/*42.9*/("""</style>


        <paper-shadow class="card" z="1" >
            <div class="cardhead">
                """),format.raw/*47.17*/("""{"""),format.raw/*47.18*/("""{"""),format.raw/*47.19*/("""data.elementTypeLabel || noAccess """),format.raw/*47.53*/("""}"""),format.raw/*47.54*/("""}"""),format.raw/*47.55*/("""
            """),format.raw/*48.13*/("""</div>

            <div class="list">
                <template repeat=""""),format.raw/*51.35*/("""{"""),format.raw/*51.36*/("""{"""),format.raw/*51.37*/("""element in data.elements"""),format.raw/*51.61*/("""}"""),format.raw/*51.62*/("""}"""),format.raw/*51.63*/("""">
                    <paper-item>"""),format.raw/*52.33*/("""{"""),format.raw/*52.34*/("""{"""),format.raw/*52.35*/("""element.longName"""),format.raw/*52.51*/("""}"""),format.raw/*52.52*/("""}"""),format.raw/*52.53*/(""" """),format.raw/*52.54*/("""("""),format.raw/*52.55*/("""{"""),format.raw/*52.56*/("""{"""),format.raw/*52.57*/("""element.name"""),format.raw/*52.69*/("""}"""),format.raw/*52.70*/("""}"""),format.raw/*52.71*/(""")</paper-item>
                </template>
            </div>
        </paper-shadow>

    </template>

    <script>
        Polymer("master-list", """),format.raw/*60.32*/("""{"""),format.raw/*60.33*/("""
            """),format.raw/*61.13*/("""data: null,
            noAccess: "No Access"
        """),format.raw/*63.9*/("""}"""),format.raw/*63.10*/(""");
    </script>

</polymer-element>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Dec 21 18:34:01 CET 2014
                  SOURCE: /home/basti/IdeaProjects/UntisCrawler/app/views/polymer/list.scala.html
                  HASH: 63c173f31ff44ec7d00070ae18f8e29fb6555bb6
                  MATRIX: 505->1|594->3|622->5|674->31|688->37|767->96|822->125|836->131|934->208|989->237|1003->243|1093->312|1148->341|1162->347|1245->410|1300->439|1314->445|1404->514|1459->543|1473->549|1558->614|1713->741|1742->742|1783->755|1935->880|1964->881|2001->891|2034->896|2063->897|2104->910|2430->1209|2459->1210|2496->1220|2533->1229|2562->1230|2603->1243|2715->1328|2744->1329|2780->1338|2813->1343|2842->1344|2884->1358|2969->1416|2998->1417|3035->1427|3168->1532|3197->1533|3226->1534|3288->1568|3317->1569|3346->1570|3387->1583|3488->1656|3517->1657|3546->1658|3598->1682|3627->1683|3656->1684|3719->1719|3748->1720|3777->1721|3821->1737|3850->1738|3879->1739|3908->1740|3937->1741|3966->1742|3995->1743|4035->1755|4064->1756|4093->1757|4269->1905|4298->1906|4339->1919|4420->1973|4449->1974
                  LINES: 19->1|22->1|24->3|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|26->5|27->6|27->6|27->6|28->7|28->7|28->7|29->8|29->8|29->8|33->12|33->12|34->13|38->17|38->17|40->19|40->19|40->19|41->20|51->30|51->30|53->32|53->32|53->32|54->33|57->36|57->36|58->37|58->37|58->37|59->38|61->40|61->40|63->42|68->47|68->47|68->47|68->47|68->47|68->47|69->48|72->51|72->51|72->51|72->51|72->51|72->51|73->52|73->52|73->52|73->52|73->52|73->52|73->52|73->52|73->52|73->52|73->52|73->52|73->52|81->60|81->60|82->61|84->63|84->63
                  -- GENERATED --
              */
          