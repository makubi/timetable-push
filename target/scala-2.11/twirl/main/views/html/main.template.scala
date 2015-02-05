
package views.html

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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(/*7.17*/title),format.raw/*7.22*/("""</title>

        <meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes">

        <script src=""""),_display_(/*11.23*/routes/*11.29*/.Assets.at("polymer/bower_components/webcomponentsjs/webcomponents.js")),format.raw/*11.100*/(""""></script>

        <link rel="import" href=""""),_display_(/*13.35*/routes/*13.41*/.Assets.at("polymer/bower_components/font-roboto/roboto.html")),format.raw/*13.103*/("""">

        <link rel="stylesheet" href=""""),_display_(/*15.39*/routes/*15.45*/.Assets.at("stylesheets/main.css")),format.raw/*15.79*/("""">

        <link rel="import" href=""""),_display_(/*17.35*/routes/*17.41*/.Assets.at("polymer/bower_components/paper-shadow/paper-shadow.html")),format.raw/*17.110*/("""">

        <link rel="import" href=""""),_display_(/*19.35*/routes/*19.41*/.Assets.at("polymer/bower_components/core-collapse/core-collapse.html")),format.raw/*19.112*/("""">

        """),_display_(/*21.10*/polymer/*21.17*/.schoolsearch()),format.raw/*21.32*/("""
        """),_display_(/*22.10*/polymer/*22.17*/.login()),format.raw/*22.25*/("""
        """),_display_(/*23.10*/polymer/*23.17*/.list()),format.raw/*23.24*/("""

    """),format.raw/*25.5*/("""</head>

    <body unresolved>

        <paper-shadow z="2" style="bottom:32px;" >
            <core-collapse id="collapseLists" opened="false" style="background: #E5E5E5;">
                <div around-justified horizontal layout id="listContainer">
                </div>
            </core-collapse>
        </paper-shadow>

        <paper-shadow z="2" style="bottom:16px;">
            <core-collapse id="collapseLogin" opened="false">
                <school-login id="schoollogin" />
            </core-collapse>
        </paper-shadow>

        <paper-shadow z="2">
            <core-collapse id="collapseSchoolSearch" opened="true">
                <school-search id="schoolsearch" />
            </core-collapse>
        </paper-shadow>

        <script type="text/javascript">

            var loginOpened = false;
            var listOpened = false;

            var container  = document.querySelector('#schoolsearch');

            container.addEventListener('schoolSelected', function(school, e, f) """),format.raw/*55.81*/("""{"""),format.raw/*55.82*/("""
                """),format.raw/*56.17*/("""document.querySelector('#schoollogin').school = JSON.parse(school.detail);
                if(!loginOpened)"""),format.raw/*57.33*/("""{"""),format.raw/*57.34*/("""
                    """),format.raw/*58.21*/("""document.querySelector('#collapseLogin').toggle();
                    loginOpened = true;
                """),format.raw/*60.17*/("""}"""),format.raw/*60.18*/("""
                """),format.raw/*61.17*/("""if(listOpened)"""),format.raw/*61.31*/("""{"""),format.raw/*61.32*/("""
                    """),format.raw/*62.21*/("""listOpened = false;
                    document.querySelector("#collapseLists" ).toggle();
                """),format.raw/*64.17*/("""}"""),format.raw/*64.18*/("""
            """),format.raw/*65.13*/("""}"""),format.raw/*65.14*/(""");

            document.querySelector('#schoollogin').addEventListener("listsLoaded", function(data, e, f)"""),format.raw/*67.104*/("""{"""),format.raw/*67.105*/("""
                """),format.raw/*68.17*/("""var lists = data.detail;
                var container = document.getElementById('listContainer');
                container.innerHTML = "";
                for(var i in lists)"""),format.raw/*71.36*/("""{"""),format.raw/*71.37*/("""

                    """),format.raw/*73.21*/("""var list = new MasterList();

                    var item = lists[i];
                    list.data = item;
                    console.log(item);
                        container.appendChild(list);
                    """),format.raw/*79.21*/("""}"""),format.raw/*79.22*/("""
                    """),format.raw/*80.21*/("""if(!listOpened)"""),format.raw/*80.36*/("""{"""),format.raw/*80.37*/("""
                        """),format.raw/*81.25*/("""document.querySelector("#collapseLists" ).toggle();
                        listOpened = true;
                    """),format.raw/*83.21*/("""}"""),format.raw/*83.22*/("""
            """),format.raw/*84.13*/("""}"""),format.raw/*84.14*/(""");

            //var mockSchool = """),format.raw/*86.32*/("""{"""),format.raw/*86.33*/(""""address":"2000 Stockerau,  ","server":"kirke.webuntis.com","displayName":"Demo","loginName":"kbt""""),format.raw/*86.131*/("""}"""),format.raw/*86.132*/(""";
            //document.querySelector('#schoollogin' ).school = mockSchool;


        </script>
    </body>

</html>
"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Dec 21 18:33:25 CET 2014
                  SOURCE: /home/basti/IdeaProjects/UntisCrawler/app/views/main.scala.html
                  HASH: 3c0765b503a28ca8d2944bded33f780359f1b1e0
                  MATRIX: 509->1|627->31|655->33|732->84|757->89|934->239|949->245|1042->316|1116->363|1131->369|1215->431|1284->473|1299->479|1354->513|1419->551|1434->557|1525->626|1590->664|1605->670|1698->741|1738->754|1754->761|1790->776|1827->786|1843->793|1872->801|1909->811|1925->818|1953->825|1986->831|3026->1843|3055->1844|3100->1861|3235->1968|3264->1969|3313->1990|3448->2097|3477->2098|3522->2115|3564->2129|3593->2130|3642->2151|3778->2259|3807->2260|3848->2273|3877->2274|4013->2381|4043->2382|4088->2399|4292->2575|4321->2576|4371->2598|4620->2819|4649->2820|4698->2841|4741->2856|4770->2857|4823->2882|4966->2997|4995->2998|5036->3011|5065->3012|5128->3047|5157->3048|5284->3146|5314->3147
                  LINES: 19->1|22->1|24->3|28->7|28->7|32->11|32->11|32->11|34->13|34->13|34->13|36->15|36->15|36->15|38->17|38->17|38->17|40->19|40->19|40->19|42->21|42->21|42->21|43->22|43->22|43->22|44->23|44->23|44->23|46->25|76->55|76->55|77->56|78->57|78->57|79->58|81->60|81->60|82->61|82->61|82->61|83->62|85->64|85->64|86->65|86->65|88->67|88->67|89->68|92->71|92->71|94->73|100->79|100->79|101->80|101->80|101->80|102->81|104->83|104->83|105->84|105->84|107->86|107->86|107->86|107->86
                  -- GENERATED --
              */
          